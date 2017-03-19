package com.tc.core.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import com.tc.core.exception.BusinessException;
import com.tc.core.exception.SystemException;
import com.tc.core.web.PageObject;

public class ConditionUtil {
	private final static String STRING = "java.lang.String";
	private final static String DATE = "java.util.Date";
	private final static String INTEGER = "java.lang.Integer";
	private final static String LONG = "java.lang.Long";
	private final static String ARRAY = "ARRAY";
	private final static String BOOLEAN = "java.lang.Boolean";

	/**
	 * 字符串的格式为[name:type:value],如果valu中含有:[]{},.字符进行转义 :转义成&&& [转义成&!& ]转义成!&!
	 * ,转义成!!! {转义成!!& }转义成&&! 如果是数组，value的格式为{type=value1,value2,...valueN}
	 * =转义成@@@
	 * 
	 * @param str
	 * @return
	 */
	public static Map getMapByString(String str) {
		Map result = new HashMap();

		String[] propertys = str.split("\\[*\\]");
		for (int i = 0; i < propertys.length; i++) {
			String[] property = propertys[i].substring(1).split(":");
			if (ARRAY.equals(property[1])) {
				result.put(property[0], toArrayByString(property[2]));
			} else if (STRING.equals(property[1])) {
				result.put(property[0], unesc(property[2]));
			} else if (INTEGER.equals(property[1])) {
				result.put(property[0], Integer.valueOf(unesc(property[2])));
			} else if (LONG.equals(property[1])) {
				result.put(property[0], Long.valueOf(unesc(property[2])));
			} else if (DATE.equals(property[1])) {
				SimpleDateFormat df = new SimpleDateFormat(
						"E MMM dd hh:mm:ss z yyyy", Locale.US);
				try {
					String temp = unesc(property[2]);
					Date date = df.parse(temp);
					result.put(property[0], date);
				} catch (ParseException e) {
					throw new SystemException("日期格式转换错误！", e);
				}
			} else if (BOOLEAN.equals(property[1])) {
				result.put(property[0], Boolean.valueOf(property[2]));
			}
		}
		return result;
	}

	public static String getStringByMap(Map map) {
		StringBuffer sb = new StringBuffer(100);
		Set keys = map.keySet();
		Iterator iter = keys.iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			Object value = map.get(key);
			if (value == null) {
				continue;
			}
			if (value.getClass().isArray()) {
				sb.append("[").append(key).append(":").append(ARRAY)
						.append(":").append(toStringByArray((Object[]) value))
						.append("]");
			} else if (STRING.equals(value.getClass().getName())
					|| INTEGER.equals(value.getClass().getName())
					|| LONG.equals(value.getClass().getName())
					|| BOOLEAN.equals(value.getClass().getName())
					|| DATE.equals(value.getClass().getName())) {
				if ("".equals(value.toString())) {
					continue;
				}
				sb.append("[").append(key).append(":").append(
						value.getClass().getName()).append(":").append(
						esc(value.toString())).append("]");
			}
		}
		return sb.toString();
	}

	private static String toStringByArray(Object[] value) {
		StringBuffer sb = new StringBuffer(100);
		String type = value[0].getClass().getName();
		sb.append("{").append(type).append("@");
		for (int i = 0; i < value.length; i++) {
			sb.append(esc(value[i].toString())).append(",");
		}
		return sb.substring(0, sb.length() - 1) + "}";
	}

	// 把值转换为数组
	private static Object[] toArrayByString(String str) {
		List result = new ArrayList();
		str = str.substring(1, str.length() - 1);// 去掉{}
		String type = str.substring(0, str.indexOf("@"));
		String[] values = str.substring(str.indexOf("@") + 1).split(",");
		for (int i = 0; i < values.length; i++) {
			String value = unesc(values[i]);
			if (LONG.equals(type)) {
				result.add(Long.valueOf(value));
			} else if (INTEGER.equals(type)) {
				result.add(Integer.valueOf(value));
			} else {
				result.add(value);
			}
		}
		return result.toArray();
	}

	// 字符反转义
	private static String unesc(String str) {
		return str.replaceAll("&&&", ":").replaceAll("&!&", "[").replaceAll(
				"!&!", "]").replaceAll("!!!", ",").replaceAll("!!&", "{")
				.replaceAll("&&!", "}").replaceAll("###", "@");
	}

	// 字符转义
	private static String esc(String str) {
		return str.replaceAll(":", "&&&").replaceAll("\\[", "&!&").replaceAll(
				"\\]", "!&!").replaceAll(",", "!!!").replaceAll("\\{", "!!&")
				.replaceAll("\\}", "&&!").replaceAll("@", "###");
	}

	public static PageObject getPageObjectByString(String condition)
			throws BusinessException {
		Map mpCondition = ConditionUtil.getMapByString(condition);
		PageObject po = new PageObject();
		po.setCountStatement((String) mpCondition.get("countStatement"));
		po.setSelectStatement((String) mpCondition.get("selectStatement"));
		po.setExeQuery(Boolean.valueOf(mpCondition.get("exeQuery").toString())
				.booleanValue());
		po.setSelectFirst(Boolean.valueOf(
				mpCondition.get("selectFirst").toString()).booleanValue());
		po.setPageIndex((Integer.valueOf(mpCondition.get("pageIndex")
				.toString()).intValue()));
		po.setPageSize((Integer.valueOf(mpCondition.get("pageSize").toString())
				.intValue()));
		if (mpCondition.get("url") != null) {
			po.setUrl(mpCondition.get("url").toString());
		}
		mpCondition.remove("countStatement");
		mpCondition.remove("selectStatement");
		mpCondition.remove("exeQuery");
		mpCondition.remove("selectFirst");
		mpCondition.remove("pageIndex");
		mpCondition.remove("pageSize");
		mpCondition.remove("url");
		po.getCondition().putAll(mpCondition);
		return po;
	}

}

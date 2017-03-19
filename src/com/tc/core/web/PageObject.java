package com.tc.core.web;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.tc.core.common.ConditionUtil;
import com.tc.core.exception.BusinessException;
import com.tc.core.exception.SystemException;

public class PageObject {
	private static final long serialVersionUID = 3256005453799404851L;

	private int totalCount = 0; // 总记录数
	private int pageSize = Pager.DEFAULT_PAGE_SIZE; // 每页最多 pageSize 条记录
	private int pageIndex = 1; // 第 pageIndex 页
	private Map condition = new HashMap();
	private String url = ""; // 查询页的url
	private boolean exeQuery = false; // 页面载入时是否自动查询
	private boolean selectFirst = false;// 在查询列表初始查询时，翻页栏显示“请先查询！”还是“请先选择！”，

	// 如果此值为True,则显示“请先选择！”。

	// 获取查询记录数的statement，对应ibatis配置文件中的select->id
	private String countStatement = "";
	// 获取查询记录的statement，对应ibatis配置文件中的select->id
	private String selectStatement = "";

	public int getOffset() {
		return pageSize * (pageIndex - 1);
	}

	public boolean isExeQuery() {
		return exeQuery;
	}

	public void setExeQuery(boolean query) {
		this.exeQuery = query;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int offset) {
		this.pageIndex = offset;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) throws BusinessException {
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		java.util.Date date = new java.util.Date();
		int hour = Integer.parseInt(sdf.format(date));
		if (pageSize > 3000) {
			throw new BusinessException("7：00到18:00点期间每页数据不能超出3000条");
		} else if (hour > 7 && hour < 18 && pageSize > 1000) {
			throw new BusinessException("每页数据不能超出1000条");
		}
		this.pageSize = pageSize;
		int endoffset = this.getOffset() + this.getPageSize();
		if(endoffset<=0){
			//modify by chenhb 20090814
			//throw new BusinessException("您所查询的最大记录必须大于0!"); 
			endoffset = 5;//异常情况查询时候默认5条数据
		}
		this.getCondition().put("endoffset",
				String.valueOf(endoffset));
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public boolean getSelectFirst() {
		return selectFirst;
	}

	public void setSelectFirst(boolean selectFirst) {
		this.selectFirst = selectFirst;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		if (url != null) {
			this.url = url;
		}
	}

	public boolean equals(Object o) {
		return false;
	}

	public int hashCode() {
		return 0;
	}

	public String toString() {
		return null;
	}

	public Map getCondition() {
		return condition;
	}

	public void setCondition(Object obj) {
//		try {
//			this.getCondition().putAll(PropertyUtils.describe(obj));
//		} catch (Exception e) {
//			throw new SystemException("获取查询条件出错！", e);
//		}

	}

	public String getCountStatement() {
		return countStatement;
	}

	public void setCountStatement(String countStatement) {
		this.countStatement = countStatement;
	}

	public String getSelectStatement() {
		return selectStatement;
	}

	public void setSelectStatement(String selectStatement) {
		this.selectStatement = selectStatement;
	}

	public String getsCondition() {
		Map map = new HashMap();
		map.putAll(this.condition);
		map.put("countStatement", this.countStatement);
		map.put("selectStatement", this.selectStatement);
		map.put("pageSize", String.valueOf(this.pageSize));
		map.put("pageIndex", String.valueOf(this.pageIndex));
		map.put("url", this.url);
		map.put("exeQuery", Boolean.valueOf(exeQuery));
		map.put("selectFirst", Boolean.valueOf(selectFirst));
		String result = ConditionUtil.getStringByMap(map);
		return result;

	}

}

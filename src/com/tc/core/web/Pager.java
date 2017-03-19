package com.tc.core.web;

public class Pager {
	public final static int DEFAULT_PAGE_SIZE = 15;
	// 无分页时的显示信息
	public static final String SHOW_NO_RESULT = "<font color=red><b>请先查询！</b></font>";

	public static final String QUERY_NO_CUST = "<b><font color=red>不存在所填号码的用户信息</font></b>";

	// 无分页时的显示信息
	public static final String SHOW_SELECT_FIRST = "<font color=red><b>请先选择！</b></font>";
	private boolean exeQuery = true;
	private int totalCount;
	private int pageSize = DEFAULT_PAGE_SIZE;
	private int pageIndex = 1;
	private String url;

	public final static String EXE_QUERY = "pager.exeQuery";

	public final static String TOTAL_COUNT = "pager.totalCount";

	public final static String PAGE_SIZE = "pager.pageSize";

	public final static String PAGE_INDEX = "pager.pageIndex";

	public final static String URL = "pager.url";

	private static int MAX_PAGE_INDEX = 5;

	public Pager() {
	}

	public static String generate(PageObject po,boolean showDlBtn,int linenum, String webpath) {

		if (!po.isExeQuery()) {
			StringBuffer sb = new StringBuffer(200);
			if (po.getSelectFirst()) {
				sb.append(SHOW_SELECT_FIRST).append("&nbsp;");
			} else {
				sb.append(SHOW_NO_RESULT).append("&nbsp;");
			}
			sb.append("<input type=hidden name=\"").append(URL).append(
					"\" value='").append(po.getUrl()).append("'>\n").append(
					"<input type=hidden name=\"").append(TOTAL_COUNT).append(
					"\" value=''>\n").append("<input type=hidden name=\"")
					.append(PAGE_INDEX).append("\" value='").append(
							po.getPageIndex()).append("'>\n").append(
							"<input type=hidden name=\"").append(EXE_QUERY)
					.append("\" value='").append(po.isExeQuery())
					.append("'>\n").append("<input type=hidden name=\"")
					.append(PAGE_SIZE).append("\" value='").append(
							po.getPageSize()).append("'>\n").append("&nbsp;");
			return sb.toString();

		}

		int totalCount = po.getTotalCount();
		int pageSize = po.getPageSize();

		if (totalCount > 0) {

			int pageCount = (totalCount + pageSize - 1) / pageSize;
			int offset = po.getOffset();
			StringBuffer sb = new StringBuffer(200);
			// 生成下载按钮
			if(showDlBtn)
			{
				sb.append("<ul id=\"dl_bar\">\n");
				sb.append("<li>\n");
				sb
						.append("<img id=\"dl_img\" src=\"")
						.append(webpath)
						.append(
								"/images/icons/download.gif\" align=\"bottom\" onclick=\"clickDlBtn();\"");
				sb
						.append("width=\"22\" height=\"22\" border=\"0\" align=\"middle\">");
				sb.append("</li>\n");
				sb.append("<li>\n");
				sb.append("<ul id=\"dl_menu\" >\n");
				sb.append("<li onclick=\"downloadTxt()\">TXT</li>\n");
				sb.append("<li onclick=\"downloadExcel()\">XLS</li>\n");
				sb.append("</ul>\n");
				sb.append("</li>\n");
				sb.append("</ul>\n");
				sb.append("<script>\n");
				sb.append("function downloadTxt(){\n");
				sb.append("clickDlBtn();\n");
				//下载限制
				sb.append("if(").append(totalCount).append(">"+linenum+"){\n");
				sb.append("alert('您要导出的数据量大于"+linenum+"条,请细化查询条件减少数量后导出');\n");
				sb.append("return;\n");
				sb.append("}\n");
				//下载限制结束
				sb.append("document.forms[0].action=\"").append(webpath).append(
						"/gloab/download/txt.so\";\n");
				sb.append("document.forms[0].submit();\n");
				sb.append("}\n");
				sb.append("function downloadExcel(){\n");
				sb.append("clickDlBtn();\n");
				//下载限制
				sb.append("if(").append(totalCount).append(">"+linenum+"){\n");
				sb.append("alert('您要导出的数据量大于"+linenum+"条,请细化查询条件减少数量后导出');\n");
				sb.append("return;\n");
				sb.append("}\n");
				//下载限制结束
				sb.append("document.forms[0].action=\"").append(webpath).append(
						"/gloab/download/excel.so\";\n");
	
				sb.append("document.forms[0].submit();\n");
				sb.append("}\n");
				sb.append("function clickDlBtn(){\n");
				sb
						.append("$(\"#dl_menu\").is(\":visible\")?$(\"#dl_menu\").hide(\"500\"):$(\"#dl_menu\").show(\"500\");\n");
				sb.append("}\n");
				sb.append("</script>\n");
			}
			sb.append("共 ").append(totalCount).append(" 行，").append(" 页  ");

			if (offset > 0) {
				sb
						.append("&nbsp;<a href='javascript:goPage(1)'><span style='FONT-FAMILY: Webdings'>9</span></a>\n");
			}

			int radius = (MAX_PAGE_INDEX / 2) * pageSize;
			int start;
			if (offset < radius)
				start = 0;
			else if (offset < totalCount - radius)
				start = offset - radius;
			else if (totalCount / pageSize - MAX_PAGE_INDEX < 0)
				start = 0;
			else if (totalCount % pageSize == 0)
				start = (totalCount / pageSize - MAX_PAGE_INDEX) * pageSize;
			else
				start = ((totalCount / pageSize + 1) - MAX_PAGE_INDEX)
						* pageSize;
			for (int i = start; i < totalCount
					&& i < start + MAX_PAGE_INDEX * pageSize; i += pageSize)
				if (i == offset) {
					sb.append("<b>").append((i / pageSize + 1))
							.append("</b>\n");

				} else {
					sb.append("&nbsp;<a href='javascript:goPage(").append(
							(i / pageSize + 1)).append(")'>").append(
							(i / pageSize + 1)).append("</a>&nbsp;\n");

				}
			if (offset < totalCount - pageSize) {
				sb.append("<a href='javascript:goPage(").append(pageCount)
						.append(")'>").append(
								"<span style='FONT-FAMILY: Webdings'>:</span>")
						.append("</a>&nbsp;&nbsp;\n");

			}

			sb.append("\u8F6C\u7B2C<input type=\"text\" name=\"goPage\" ");
			sb.append("value=\"");
			sb.append(((offset / pageSize) + 1));
			sb.append("\" size=\"3\">\n");
			sb
					.append("<input type=\"text\" name=\"temp\" style=\"display:none\">\n");
			sb.append("<input type=text size=3 name=\"");
			sb.append(PAGE_SIZE);
			sb.append("\" value=");
			sb.append(pageSize);
			sb.append(" ");
			sb.append("onchange=\"if(parent.document.all('");
			sb.append(PAGE_SIZE);
			sb.append("')){");
			sb.append("parent.document.all('");
			sb.append(PAGE_SIZE);
			sb.append("').value = this.value;}\">行/页\n");
			sb.append("<a href='javascript:goPage()'><img src=\"");
			sb.append(webpath);
			sb.append("/images/icons/go.gif\" ");
			sb
					.append("width=\"16\" height=\"16\" border=\"0\" align=\"middle\"></a>\n");
			sb.append("<input type=hidden name=\"");
			sb.append(URL);
			sb.append("\" value='");
			sb.append((po.getUrl().indexOf("?") > 0 ? po.getUrl().substring(0,
					po.getUrl().indexOf("?")) : po.getUrl()));
			sb.append("'>\n");
			sb.append("<input type=hidden name=\"");
			sb.append(TOTAL_COUNT);
			sb.append("\" value='");
			sb.append(totalCount);
			sb.append("'>\n");
			sb.append("<input type=hidden name=\"");
			sb.append(PAGE_INDEX);
			sb.append("\" value='");
			sb.append(po.getPageIndex());
			sb.append("'>\n");
			sb.append("<input type=hidden name=\"");
			sb.append(EXE_QUERY);
			sb.append("\" value='");
			sb.append(po.isExeQuery());
			sb.append("'>\n");
			sb.append("&nbsp;");
			return sb.toString();
		} else
			return "<b><font color=red>没有符合查询条件的数据</font></b>";
	}

	public boolean isExeQuery() {
		return exeQuery;
	}

	public void setExeQuery(boolean exeQuery) {
		this.exeQuery = exeQuery;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static String getURL() {
		return URL;
	}
}

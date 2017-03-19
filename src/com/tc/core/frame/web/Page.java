package com.tc.core.frame.web;

import java.util.List;

import com.tc.core.frame.vo.VO;

public class Page<E> {
	
	//总记录数
	private Long total;
	//查询数据
	private List<E> rows;
	
	public Page() {
		
	}
	
	public Page(Long total, List<E> rows){
		this.total = total;
		this.rows = rows;
	}
	
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<E> getRows() {
		return rows;
	}
	public void setRows(List<E> rows) {
		this.rows = rows;
	}
	
}

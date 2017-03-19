package com.tc.controller;

import java.util.List;

import com.tc.core.frame.vo.VO;

public class DataTableVO<E extends VO> {
	private int draw = 1;
	private int recordsTotal = 1;
	private int recordsFiltered = 1;
	private List<VO> data;
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public int getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public int getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public List<VO> getData() {
		return data;
	}
	public void setData(List<VO> data) {
		this.data = data;
	}
}

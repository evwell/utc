package com.tc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.tc.core.exception.BusinessException;
import com.tc.core.frame.controller.BasicController;
import com.tc.core.frame.vo.VO;
import com.tc.vo.impl.TcCommodityKind;
import com.tc.vo.impl.TcCommodityType;

public class TestController extends BasicController {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2407867313646058401L;
	
	private DataTableVO<TcCommodityType> dataTableVO = new DataTableVO<TcCommodityType>();
//	private JSONObject listTable;

	public void commodityTypeList(HttpServletRequest request,HttpServletResponse response) throws BusinessException{
		logger.debug("--------------");
		list = service.loadAll();
		dataTableVO.setData(list);
//		listTable = JSONObject.fromObject(dataTableVO);
//		Map<String,Object> map = new HashMap<String,Object>();
//		for(VO vo : service.loadAll()){
//			TcCommodityKind commodityKind = (TcCommodityKind) vo;
//			map = new HashMap<String,Object>();
//			map.put("id", commodityKind.getId());
//			map.put("name", commodityKind.getName());
//			map.put("pId", commodityKind.getParnetId());
//			list.add(map);
//		}
		logger.debug("Return rows of list : "+list.size());
	}

	public DataTableVO<TcCommodityType> getDataTableVO() {
		return dataTableVO;
	}

	public void setDataTableVO(DataTableVO<TcCommodityType> dataTableVO) {
		this.dataTableVO = dataTableVO;
	}

//	public JSONObject getlistTable() {
//		return listTable;
//	}
//
//	public void setlistTable(JSONObject listTable) {
//		this.listTable = listTable;
//	}
}

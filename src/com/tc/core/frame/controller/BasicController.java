package com.tc.core.frame.controller;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.tc.core.common.ConditionUtil;
import com.tc.core.constant.IdentificationDefined;
import com.tc.core.exception.BusinessException;
import com.tc.core.frame.service.Service;
import com.tc.core.frame.vo.VO;
import com.tc.core.util.CommonUtil;
import com.tc.core.frame.web.Page;
import com.tc.core.web.PageObject;
import com.tc.core.web.Pager;
import com.tc.vo.impl.TcUser;

public abstract class BasicController {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	protected final static String USERINFO = IdentificationDefined.DATA_CACHE_KEY_USERINFO.getStrValue();
	
	protected TcUser currentUser;
	
	protected HttpServletRequest request;
	
	protected HttpServletResponse response;
	
	protected HttpSession session;
	
	protected VO vo;
	
	protected String returnString;
	
	protected final static String RETURN_STRING_SUCCESS = "success";
	
	protected final static String RETURN_STRING_FAILURE = "failure";
	
	protected final static String RETURN_STRING_ERROR = "error";
	
	protected Service<VO> service;
	
	protected List<VO> list;

	protected static final int ACTION_FLAG_ADD = 0;// 增加页面

	protected static final int ACTION_FLAG_EDIT = 1;// 修改页面

	protected static final int ACTION_FLAG_AUDIT = 2;// 审核页面
	
	protected static final String INIT = "init";

	protected static final String LIST = "list";

	protected static final String ADD = "add";

	protected static final String EDIT = "edit";

	protected static final String DELETE = "delete";

	protected static final String UPDATE = "update";

	protected static final String VIEW = "view";

	protected static final String AUDIT = "audit";
	
	protected static final String FAILURE = "error";
	
	// 通用跳转页面
	protected ModelAndView returnToken;

	protected Page page;
	
	// 页面上需要保留的变量
	protected Pager pager;

	protected PageObject po;

	protected int action_flag = ACTION_FLAG_ADD;

	protected String currCondition;

	protected String log4jString(String str){
		return com.tc.core.util.CommonUtil.log4jString(str);
	}
	
	@ModelAttribute
	public void basic(HttpServletRequest request, HttpServletResponse response){
		logger.debug(log4jString("basicController"));
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		this.currentUser = (TcUser) this.session.getAttribute(USERINFO);
	}
	
	public int getAction_flag() {
		return action_flag;
	}

	public void setAction_flag(int action_flag) {
		this.action_flag = action_flag;
	}
	
	/**
	 * 获取分页、查询条件对象
	 * 
	 * @param
	 * @return
	 */
	protected PageObject getPageObject() throws BusinessException {
		PageObject po = new PageObject();
		
		
		if (pager == null) {
			pager = new Pager();
			po.setExeQuery(pager.isExeQuery());
			po.getCondition().put("beginoffset", "0"); 
														 
			po.getCondition().put("endoffset", pager.getPageSize()); 
																	 
			// modify Pager.DEFAULT_PAGE_SIZE by  
			// 特定情况下需要查询较多的条，最好在各自子类中实现
			return po;
		}
		if (!currCondition.isEmpty()) {
			po = ConditionUtil.getPageObjectByString(currCondition);
			po.setSelectFirst(false);
		} else {
			po.setSelectFirst(true);
		}
		po.setExeQuery(pager.isExeQuery());
		po.setTotalCount(pager.getTotalCount());
		po.setPageSize(pager.getPageSize());
		po.setPageIndex(pager.getPageIndex());

		po.getCondition().put("beginoffset", String.valueOf(po.getOffset()));
		if (po.getPageSize() <= 0) {
			// 页面传入异常数据时候
			po.setPageSize(15);
		}
		int endoffset = po.getOffset() + po.getPageSize();
		if (endoffset <= 0) {
			// throw new BusinessException("您所查询的最大记录必须大于0!");
			endoffset = 5;// 异常情况查询时候默认5条数据
		}
		po.getCondition().put("endoffset", String.valueOf(endoffset));

//		ActionContext ctx = ActionContext.getContext();
//		HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
//		logger.debug("request.getRequestURI():" + request.getRequestURI());
//		logger.debug("request.getQueryString():" + request.getQueryString());
//		if (request.getQueryString() != null) {
//			po.setUrl(request.getRequestURI() + "?" + request.getQueryString());
//		} else {
//			po.setUrl(request.getRequestURI());
//		}
		return po;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public String getCurrCondition() {
		return currCondition;
	}

	public void setCurrCondition(String currCondition) {
		this.currCondition = currCondition;
	}

	public Service<VO> getService() {
		return service;
	}

	public void setService(Service<VO> service) {
		this.service = service;
	}

	public boolean add(VO vo) throws BusinessException {
		logger.debug(log4jString(ADD));
		return false;
	}
	
	public ModelAndView edit(Serializable primaryKey) throws BusinessException {
		logger.debug(log4jString(EDIT));
		return returnToken;
	}
	
	public boolean update(VO vo) throws BusinessException {
		logger.debug(log4jString(UPDATE));
		return false;
	}

	public boolean delete(Serializable primaryKey) throws BusinessException {
		logger.debug(log4jString(DELETE));
		return false;
	}
	
	public List<VO> list() throws BusinessException {
		logger.debug(log4jString(LIST));
		list = service.loadAll();
		return list;
	}

}

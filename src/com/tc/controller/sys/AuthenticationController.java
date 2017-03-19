package com.tc.controller.sys;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tc.core.constant.IdentificationDefined;
import com.tc.core.exception.BusinessException;
import com.tc.core.frame.controller.BasicController;
import com.tc.core.frame.service.Service;
import com.tc.core.frame.vo.VO;
import com.tc.vo.impl.TcMenu;
import com.tc.vo.impl.TcUser;
import com.tc.vo.impl.TcUserRole;

/**
 * 
 * 
 * @author evwell
 *
 */
@Controller
@RequestMapping("/auth")
public class AuthenticationController extends BasicController {
	
	private final static String USERINFO = IdentificationDefined.DATA_CACHE_KEY_USERINFO.getStrValue();

	private TcUser tcUser;

	@RequestMapping(value="/init", method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request, HttpServletResponse response) throws BusinessException {
		returnToken = new ModelAndView("login");
		if (request.getSession() != null && request.getSession().getAttribute(USERINFO) != null) {
			logger.debug("Has beeb login @ "
					+ ((TcUser) (request.getSession().getAttribute(USERINFO))).getUserName());
			returnToken = new ModelAndView("welcome");
		}
		returnToken.addObject("msg", "忽忘初心");
		return returnToken;
	}

	@RequestMapping(value="/login", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> login(HttpServletRequest request, HttpServletResponse response, TcUser tcUser) throws BusinessException, IOException {
		// returnToken = new ModelAndView("login");
//		String msgJsonError = "{\"msg\":\"" + "Username or password is error!\"}";
//		String msgJsonEmpty = "{\"msg\":\"" + "Username or password is empty!\"}";
		String msgJsonError="Username or password is error!";
		String msgJsonEmpty="Username or password is empty!";
		String msgSuccess = "success";
		Map<String,Object> returnMap = new HashMap<String,Object>();
		logger.debug("Login...");
		if (request.getSession() != null && request.getSession().getAttribute(USERINFO) != null) {
			logger.debug("Has beeb login @ " + ((TcUser) (request.getSession().getAttribute(USERINFO))).getUserName());
			returnMap.put("msg", msgSuccess);
		}
//		tcUser = new TcUser();
//		tcUser.setCid((String) request.getParameter("cid"));
//		tcUser.setUserName((String) request.getParameter("userName"));
//		tcUser.setPassword((String) request.getParameter("password"));
		logger.debug(tcUser.getUserName() + ":" + tcUser.getPassword() + ":" + tcUser.getCid());
		if (!isEmptyLoginInfo(tcUser)) {
			List<VO> list = this.service.find(tcUser);
			if (list != null && list.size() == 1) {
				logger.debug(list.get(0));
				request.getSession().setAttribute(USERINFO, list.get(0));
				returnMap.put("msg", msgSuccess);
			} else {
				logger.debug(msgJsonError);
				returnMap.put("msg", msgJsonError);
			}
		} else {
			logger.debug(msgJsonEmpty);
			returnMap.put("msg", msgJsonEmpty);
		}
		return returnMap;
	}

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public void logout(HttpServletRequest request, HttpServletResponse response) throws BusinessException {
		logger.debug("Logout...");
		if (request.getSession() != null && request.getSession().getAttribute(USERINFO) != null) {
			request.getSession().removeAttribute(USERINFO);
			;
		}
	}

	@RequestMapping(value="/register", method = RequestMethod.GET)
	public @ResponseBody String register(HttpServletRequest request, HttpServletResponse response){
		String msg=null;
		logger.debug(request.getParameterMap().size());
		tcUser = new TcUser();
		tcUser.setCid("0");
		tcUser.setUserName("admin");
		tcUser.setPassword("123");
		tcUser.setRealName("administrator");
		tcUser.setPhone("18670008650");
		TcUserRole tcUserRole = new TcUserRole();
		tcUserRole.setCid("0");
		tcUserRole.setId(1L);
		tcUserRole.setRoleId(1);
		tcUserRole.setStatus((byte) 1);
		tcUserRole.setUserId(1);
		tcUser.setRoles(new HashSet<VO>(){{add(tcUserRole);}});
		try {
			this.service.save(tcUser);
			msg = "用户名：admin密码：123注册成功！";
		} catch (BusinessException e) {
			msg = e.getMessage();
			e.printStackTrace();
		}
		return msg;
	}

	@RequestMapping(value="/welcome")
	public ModelAndView welcome(HttpServletRequest request, HttpServletResponse response) throws BusinessException {
		logger.debug("welcome...");
		return new ModelAndView("welcome");
	}

	@RequestMapping(value="/delete/{id}", method = RequestMethod.POST)
	public @ResponseBody String delete(@PathVariable("id") Long tcUserId) throws BusinessException {
		logger.debug("delete...");
		TcUser tcUser = new TcUser();
		tcUser.setId(tcUserId);
		String msg=null;
		try{
			this.service.delete(tcUser);
			msg = "Delete success!";
		} catch (BusinessException e) {
			msg = e.getMessage();
			e.printStackTrace();
		}
		
		return msg;
	}
	
	private boolean isEmptyLoginInfo(TcUser tcUser) throws BusinessException {
		return tcUser.getCid() == null || tcUser.getCid().trim().length() == 0 || tcUser.getUserName() == null
				|| tcUser.getUserName().trim().length() == 0 || tcUser.getPassword() == null
				|| tcUser.getPassword().trim().length() == 0;
	}
	
	public TcUser getUserinfo() {
		return tcUser;
	}

	public void setUserinfo(TcUser tcUser) {
		this.tcUser = tcUser;
	}
}

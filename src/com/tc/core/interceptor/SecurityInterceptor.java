package com.tc.core.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tc.core.constant.IdentificationDefined;
import com.tc.core.exception.BusinessException;

public class SecurityInterceptor implements HandlerInterceptor {

	private Logger logger = Logger.getLogger(this.getClass());
	private List<String> excludedUrls;

	public void setExcludedUrls(List<String> excludedUrls) {
		this.excludedUrls = excludedUrls;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception e)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView model)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		logger.debug(" SecurityInterceptor ");
		String requestUri = request.getRequestURI();
		for (String url : excludedUrls) {
			if (requestUri.endsWith(url)) {
				logger.debug( "URL \"" + url + "\" is allowed! ");
				return true;
			}
		}

		HttpSession session = request.getSession();
		if (session.getAttribute(IdentificationDefined.DATA_CACHE_KEY_USERINFO.getStrValue()) == null) {
			logger.debug(" Please Login! ");
			throw new BusinessException(" 会话已失效，请重新登陆! ");
		} else {
			return true;
		}
	}

}

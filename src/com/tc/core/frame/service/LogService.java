package com.tc.core.frame.service;

import org.springframework.security.core.context.SecurityContextHolder;

import com.tc.core.security.TcUserDetail;
import com.tc.vo.impl.TcLog;
public interface LogService {
	
	public void log(TcLog tcLog) throws Exception;
	
	public default Integer loginUserId() throws Exception{
		return 1;
//		if (SecurityContextHolder.getContext() == null) {
//			return null;
//		}
//		if (SecurityContextHolder.getContext().getAuthentication() == null) {
//			return null;
//		}
//		TcUserDetail tcUserDetail = (TcUserDetail) SecurityContextHolder.getContext().getAuthentication()
//				.getPrincipal();
//		if (tcUserDetail == null) {
//			return null;
//		}
//		return tcUserDetail.getUserId();
	}

}

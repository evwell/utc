package com.tc.service.sys;

import java.util.List;

import com.tc.core.exception.BusinessException;
import com.tc.core.frame.service.AbstractService;
import com.tc.vo.impl.TcLog;

public class LogService extends AbstractService<TcLog> implements com.tc.core.frame.service.LogService {

	@Override
	public List<TcLog> find(TcLog e) throws BusinessException {
		return null;
	}

	@Override
	public void log(TcLog tcLog) throws Exception {
		this.save(tcLog);
	}
}

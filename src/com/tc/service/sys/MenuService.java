package com.tc.service.sys;

import java.util.List;

import com.tc.core.exception.BusinessException;
import com.tc.core.frame.service.AbstractService;
import com.tc.vo.impl.TcMenu;

public class MenuService extends AbstractService<TcMenu> {

	@Override
	public List<TcMenu> find(TcMenu e) throws BusinessException {
		logger.debug("query menu by named quyery 'list'!");
		return null;
	}
}

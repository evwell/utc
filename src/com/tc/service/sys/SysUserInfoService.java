package com.tc.service.sys;

import java.sql.Timestamp;
import java.util.List;

import com.tc.core.constant.IdentificationDefined;
import com.tc.core.exception.BusinessException;
import com.tc.core.frame.service.AbstractService;
import com.tc.vo.impl.TcUser;

public class SysUserInfoService extends AbstractService<TcUser> {

	public List<TcUser> find(TcUser e) throws BusinessException {
		return dao.findByNamedQuery("loginQuery", new String[] { e.getUserName(), e.getPassword(), e.getCid() });
	}

	public void save(TcUser e) throws BusinessException {
		List<?> userExist = dao.findByNamedQuery("loginQuery", new String[] { e.getUserName(), e.getPassword(), e.getCid() });
		if (userExist == null | userExist.size() == 0) {
			List<?> maxUserID = dao.findByNamedQuery("pkQuery");
			Long userID = 1L;
			if (maxUserID != null && maxUserID.get(0) != null) {
				logger.debug("当前最大userID=" + maxUserID.get(0));
				userID = (Long) maxUserID.get(0);
				userID = ++userID;
			}
			e.setId(userID);
			e.setLevel(IdentificationDefined.USER_LEVEL_SYSTEM.getByteValue());
			e.setStatus(IdentificationDefined.USER_STATUS_VALID.getByteValue());
			e.setCreateTime(new Timestamp(System.currentTimeMillis()));
			super.save(e);
		} else {
			logger.debug("<" + e.getUserName() + ">用户已经存在！");
			throw new BusinessException("用户已经存在！");
		}
	}

}

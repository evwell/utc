package com.tc.controller.sys;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tc.core.exception.BusinessException;
import com.tc.core.frame.controller.BasicController;
import com.tc.core.frame.vo.VO;

@Controller
@RequestMapping("/menu")
public class MenuController extends BasicController {
	
	@RequestMapping(value="/list", method = RequestMethod.POST)
	public @ResponseBody List<VO> list() throws BusinessException{
		logger.debug("...MenuList...");
		this.list = this.service.loadAllActive();
		return list;
	}

}

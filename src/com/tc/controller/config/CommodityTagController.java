package com.tc.controller.config;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tc.core.exception.BusinessException;
import com.tc.core.frame.controller.BasicController;
import com.tc.core.frame.vo.VO;
import com.tc.vo.impl.TcCommodityTag;

@Controller
@RequestMapping("/commodityTag")
public class CommodityTagController extends BasicController {
	
	@RequestMapping(value="/findTagName/{id}", method = RequestMethod.POST)
	public @ResponseBody List<VO> findTagName(@PathVariable("id") int kindId) throws BusinessException {
		logger.debug("kindId:" + kindId);
		TcCommodityTag tcCommodityTag = new TcCommodityTag();
		list = service.find(tcCommodityTag);
		return list;
	}
}

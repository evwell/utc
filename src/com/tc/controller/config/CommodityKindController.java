package com.tc.controller.config;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tc.core.exception.BusinessException;
import com.tc.core.frame.controller.BasicController;
import com.tc.core.frame.vo.VO;
import com.tc.vo.impl.TcCommodityKind;

@Controller
@RequestMapping("/commodityKind")
public class CommodityKindController extends BasicController {
	
	@RequestMapping(value="/listCommodityTypes/{tcCommodityKind}", method=RequestMethod.POST)
	public @ResponseBody List<VO> listCommodityTypes(@PathVariable("tcCommodityKind") int tcCommodityKind) throws BusinessException {
		list = service.findByNamedQuery("findAllTypeQuery", tcCommodityKind);
		logger.debug("Rows of the return list : " + list.size());
		return list;
	}
}

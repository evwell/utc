package com.tc.core.frame.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tc.core.constant.IdentificationDefined;


@Controller
@RequestMapping
public class SystemController extends BasicController {
	
	private Logger logger = Logger.getLogger(this.getClass());

	@RequestMapping(value="/readSysteDataCache", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> readSysteDataCache(){
		logger.debug("读取系统上下文数据缓存");
		Map<String,Object> dataCache = new HashMap<String,Object>();
		ServletContext sc = session.getServletContext();
		for(IdentificationDefined id:IdentificationDefined.values()){
			if(id.name().startsWith("DATA_CACHE_KEY")){
				dataCache.put(id.getStrValue(),sc.getAttribute(id.getStrValue()));
			}
		}
		logger.debug("缓存数据：" + Arrays.toString(dataCache.entrySet().toArray()));
		return dataCache;
	}
}

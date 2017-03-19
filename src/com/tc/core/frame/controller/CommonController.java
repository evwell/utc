package com.tc.core.frame.controller;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tc.core.exception.BusinessException;
import com.tc.core.frame.service.Service;
import com.tc.core.frame.vo.VO;
import com.tc.core.frame.web.Page;
import com.tc.core.util.CommonUtil;

@Controller
@RequestMapping("/{path}/{className}")
public class CommonController extends BasicController {

	private Map<String,Service<VO>> servicesMap;

	public CommonController(){
		
	}
	
	public Map<String,Service<VO>> getServices() {
		return servicesMap;
	}

	public void setServices(Map<String,Service<VO>> servicesMap) {
		this.servicesMap = servicesMap;
	}

	/**
	 * 处理初始化
	 * @param path
	 * @param className
	 * @return
	 * @throws BusinessException 
	 */
	private boolean initProcess(String path, String className) throws BusinessException{
		logger.debug(log4jString("initProcess"));
		list = null;
		service = null;
		service = servicesMap.get(className);
		if(service==null){
			logger.debug(log4jString("无效请求!--->" + path + "/" + className + "/" + className));
			throw new BusinessException("无效请求!--->" + path + "/" + className + "/" + className);
		}else{
			logger.debug(log4jString("初始化成功!--->" + path + "/" + className + "/" + className));
			return true;
		}
	}
	
	/**
	 * 功能处理初始化页面
	 * @param path
	 * @param className
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/init")
	public ModelAndView init(@PathVariable("path") String path, @PathVariable("className") String className, @RequestParam(value="sos", required=false) String sos) throws BusinessException{
		try{
			initProcess(path,className);
			returnToken = new ModelAndView(path + "/" + className + "/" + className + "_init");
			if(sos!=null&&sos.length()>0){
				logger.debug("The init method bind a param : " + sos);
				returnToken.addObject("sos",sos);
			}
			return returnToken;
		}catch(Exception e){
			e.printStackTrace();
			throw new BusinessException(e.getClass().getName(),e.getMessage());
		}
	}
	
	/**
	 * 处理添加页面明细页面
	 * @param path
	 * @param className
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/detail", method=RequestMethod.GET)
	public ModelAndView detail(@PathVariable("path") String path, @PathVariable("className") String className) throws BusinessException{
		try{
			initProcess(path,className);
			returnToken = new ModelAndView(path + "/" + className + "/" + className + "_detail");
			return returnToken;
		}catch(Exception e){
			throw new BusinessException(e.getClass().getName(),e.getMessage());
		}
	}
	
	@RequestMapping(value = "/detail/{id}", method=RequestMethod.GET)
	public ModelAndView detail(@PathVariable("path") String path, @PathVariable("className") String className,  @PathVariable("id") String id) throws BusinessException{
		try{
			initProcess(path,className);
			logger.debug(log4jString("id: "+id));
			returnToken = new ModelAndView(path + "/" + className + "/" + className + "_detail");
			if (id != null && id.length()!=0) {
				returnToken.addObject("vo",service.get(Long.parseLong(id)));
			}
			return returnToken;
		}catch(Exception e){
			throw new BusinessException(e.getClass().getName(),e.getMessage());
		}
	}
	
	@RequestMapping(value = "/detail", method=RequestMethod.POST)
	public ModelAndView detail(@PathVariable("path") String path, @PathVariable("className") String className, @RequestBody(required=false) Map<String, Object> upLoadPara) throws BusinessException{
		try{
			initProcess(path,className);
			logger.debug(log4jString("upLoadPara:"+upLoadPara));
			returnToken = new ModelAndView(path + "/" + className + "/" + className + "_detail");
			if (upLoadPara != null && upLoadPara.size()!=0) {
				String value = (String) upLoadPara.get(upLoadPara.keySet().toArray()[0]);
				logger.debug(log4jString("id:"+value));
				returnToken.addObject("vo",service.get(Long.parseLong(value)));
			}
			return returnToken;
		}catch(Exception e){
			throw new BusinessException(e.getClass().getName(),e.getMessage());
		}
	}
	
	
	@RequestMapping(value = "/delete", method=RequestMethod.POST)
	public @ResponseBody String delete(@PathVariable("path") String path, @PathVariable("className") String className, @RequestBody Map<String,Object> map) throws BusinessException{
		try{
			initProcess(path,className);
			returnString = RETURN_STRING_FAILURE;
			logger.debug(log4jString("delete @ map size:" + map.size()));
			vo = CommonUtil.createEntityByMap(className, map);
			service.delete(vo);
			returnString = RETURN_STRING_SUCCESS;
			return returnString;
		}catch(Exception e){
			throw new BusinessException(e.getClass().getName(),e.getMessage());
		}
	}
	
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	public @ResponseBody String save(@PathVariable("path") String path, @PathVariable("className") String className, @RequestBody Map<String,Object> map) throws BusinessException{
		try{
			initProcess(path,className);
			returnString = RETURN_STRING_FAILURE;
			logger.debug(log4jString("save @ map size:" + map.size()));
			map.put("modifyBy", this.currentUser.getId()); //操作员
			map.put("modifyTime", new Timestamp(System.currentTimeMillis())); //操作时间
			vo = CommonUtil.createEntityByMap(className, map);
			service.saveOrUpdate(vo);
			returnString = RETURN_STRING_SUCCESS;
			return returnString;
		}catch(Exception e){
			throw new BusinessException(e.getClass().getName(),e.getMessage());
		}
	}
	
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	public @ResponseBody String update(@PathVariable("path") String path, @PathVariable("className") String className, @RequestBody Map<String,Object> map) throws BusinessException{
		try{
			initProcess(path,className);
			returnString = RETURN_STRING_FAILURE;
			logger.debug(log4jString("update @ map size:" + map.size() + ",  id = " + map.get("id")));
			map.put("modifyBy", this.currentUser.getId()); //操作员
			map.put("modifyTime", new Timestamp(System.currentTimeMillis())); //操作时间
			vo = CommonUtil.fillingEntityByMap(service.load(Long.parseLong(String.valueOf(map.get("id")))), map);  //将数据库中数据与提交数据结合在一起
			service.update(vo);
			returnString = RETURN_STRING_SUCCESS;
			return returnString;
		}catch(Exception e){
			throw new BusinessException(e.getClass().getName(),e.getMessage());
		}
	}
	
	@RequestMapping(value="/list")
	public @ResponseBody List<VO> list(@PathVariable("path") String path, @PathVariable("className") String className) throws BusinessException {
		try{
			initProcess(path,className);
			logger.debug(log4jString(LIST));
			list = service.loadAll();
			return list;
		}catch(Exception e){
			throw new BusinessException(e.getClass().getName(),e.getMessage());
		}
	}
	
	@RequestMapping(value="/query", method=RequestMethod.POST)
	public @ResponseBody List<VO> query(@PathVariable("path") String path, @PathVariable("className") String className, @RequestBody Map<String,Object> map) throws BusinessException {
		try{
			initProcess(path,className);
			logger.debug(log4jString("请求参数：" + Arrays.toString(map.entrySet().toArray())));
			vo = CommonUtil.createEntityByMap(className, map);
			list = service.find(vo);
			logger.debug(log4jString("查询结果  :: 记录数="+list.size() + " 明细：" + Arrays.toString(list.toArray())));
			return list;
		}catch(Exception e){
			throw new BusinessException(e.getClass().getName(),e.getMessage());
		}
	}
	
	@RequestMapping(value="/queryPagination", method=RequestMethod.POST)
	public @ResponseBody Page queryPagination(@PathVariable("path") String path, @PathVariable("className") String className, @RequestBody Map<String,Object> map) throws BusinessException {
		try{
			initProcess(path,className);
			logger.debug(log4jString("请求参数：" + Arrays.toString(map.entrySet().toArray())));
			vo = CommonUtil.createEntityByMap(className, map);
			Integer offset = Integer.parseInt(String.valueOf(map.get("offset")));
			Integer limit = Integer.parseInt(String.valueOf(map.get("limit")));
			page = service.find(vo,offset,limit);
			logger.debug(log4jString("查询结果  :: 总记录数=" + page.getTotal() + " , 当前页记录数=" + page.getRows().size() + " , 明细：" + Arrays.toString(page.getRows().toArray())));
			return page;
		}catch(Exception e){
			throw new BusinessException(e.getClass().getName(),e.getMessage());
		}
	}
}

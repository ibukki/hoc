package com.prometheus.hoc.common.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.prometheus.hoc.common.bean.CompanyBean;
import com.prometheus.hoc.common.bean.ModuleIncludeBean;
import com.prometheus.hoc.common.bean.PageUrl;
import com.prometheus.hoc.common.context.GlobalContext;
import com.prometheus.hoc.common.module.xml.ModuleXMLParser;

@Controller
public class HomeController {
	
	@Autowired
	private GlobalContext context;
	
	@RequestMapping("/workspace")
	public ModelAndView showView(@RequestParam(value="module", required=false) String module) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("landing/pages/workspace");
		
		
		CompanyBean company = new CompanyBean();
		company.setClientId(1L);
		company.setCompanyId("prometheus");
		context.setCompanyBean(company);
		modelAndView.addObject(company);
		
		
		List<ModuleIncludeBean> pageList = ModuleXMLParser.getAllPageUrlsHasInclude();
		List<PageUrl> includedPageList = new ArrayList<PageUrl>();
		for (ModuleIncludeBean moduleInc : pageList) {
			
			//filter out only the include page needed for specific module
			if(!StringUtils.isEmpty(module)){
				if(!module.equalsIgnoreCase(moduleInc.getModule())){
					continue;
				}
			}
			
			String inpageUrl = this.getIncludedPageFromUrl(moduleInc.getPageUrl());
			if(inpageUrl != null){
				PageUrl inp = new PageUrl();
				inp.setPath(inpageUrl);
				includedPageList.add(inp);
			}
		}
		modelAndView.addObject(includedPageList);
		
		return modelAndView;
	}
	
	@RequestMapping("/home")
	public ModelAndView visitHome(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("landing/pages/landing_index");
		return modelAndView;
	}
	
	@RequestMapping("/login")
	public ModelAndView visitLogin(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/pages/login");
		return modelAndView;
	}
	
	@RequestMapping("/register")
	public ModelAndView visitRegister(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/pages/register");
		return modelAndView;
	}
	
	private String getIncludedPageFromUrl(String pageUrl){
		String includedFileUrl = null;
		if(pageUrl.lastIndexOf("/") > 0){
			String filePath = pageUrl.substring(0,pageUrl.lastIndexOf("/"));
			String fileName = pageUrl.substring(pageUrl.lastIndexOf("/") + 1);
			if(fileName.lastIndexOf(".") > 0){
				String fnNoExt = fileName.substring(0,fileName.lastIndexOf("."));
				String fnExt = fileName.substring(fileName.lastIndexOf(".") + 1);
				
				String includedFileName = fnNoExt.concat("Include").concat(".").concat(fnExt);
				
				if(filePath.startsWith("ui")){
					filePath = filePath.replace("ui", "../..");
				}
				
				includedFileUrl = filePath.concat("/").concat(includedFileName);
			}
		}
		return includedFileUrl;
	}
	
}

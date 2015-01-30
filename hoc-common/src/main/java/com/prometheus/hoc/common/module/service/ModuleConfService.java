package com.prometheus.hoc.common.module.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import com.prometheus.hoc.common.module.bean.ModuleConfModule;
import com.prometheus.hoc.common.module.bean.ModuleConfPage;
import com.prometheus.hoc.common.module.bean.ModuleConfSetting;
import com.prometheus.hoc.common.module.bean.ModuleConfSubModule;
import com.prometheus.hoc.common.module.bean.ModuleNaviItem;
import com.prometheus.hoc.common.module.xml.ModuleXMLParser;

@Component
@Path("moduleconfig")
public class ModuleConfService {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private LocaleResolver localeResolver;
	
	@GET
	@Path("list")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<ModuleNaviItem> list(@QueryParam("locale") String locale,@Context HttpServletRequest request,  
            @Context HttpServletResponse response){
		
		//set up locale
		if(StringUtils.isEmpty(locale)){
			locale = "en_US";
		}
		Locale l = this.getLocale(locale);
		localeResolver.setLocale(request, response, l);
		
		List<ModuleNaviItem> naviList = new ArrayList<ModuleNaviItem>();
		ModuleConfSetting setting = ModuleXMLParser.parseModulePagesSetting();
		if(setting != null){
			List<ModuleConfModule> modules = setting.getModules();
			for (ModuleConfModule m : modules) {
				ModuleNaviItem navi = new ModuleNaviItem();
				navi.setLabel(this.getTranslatedLabel(m.getTitle(), l));
				navi.setIcon(m.getIcon());
				if(m.getUrl() != null){
					navi.setHref("#" + m.getId());
					navi.setUrl(m.getUrl());
				}
				
				//check sub module
				List<ModuleConfSubModule> subList = m.getSubModules();
				if(subList != null){
					for (ModuleConfSubModule sub : subList) {
						ModuleNaviItem subnavi = new ModuleNaviItem();
						subnavi.setLabel(this.getTranslatedLabel(sub.getTitle(), l));
						subnavi.setIcon(sub.getIcon());
						if(sub.getUrl() != null){
							subnavi.setHref("#" + sub.getId());
							subnavi.setUrl(sub.getUrl());
						}
						
						List<ModuleConfPage> subPages = sub.getPages();
						for (ModuleConfPage subP : subPages) {
							ModuleNaviItem thirdnavi = new ModuleNaviItem();
							thirdnavi.setLabel(this.getTranslatedLabel(subP.getTitle(), l));
							thirdnavi.setHref("#" + subP.getId());
							thirdnavi.setUrl(subP.getUrl());
							thirdnavi.setIcon(subP.getIcon());
							subnavi.addChild(thirdnavi);
						}
						navi.addChild(subnavi);
					}
				}
				
				//check plan pages
				List<ModuleConfPage> pages = m.getPages();
				if(pages != null){
					for (ModuleConfPage p : pages) {
						ModuleNaviItem subNavi = new ModuleNaviItem();
						subNavi.setLabel(this.getTranslatedLabel(p.getTitle(), l));
						subNavi.setHref("#".concat(p.getId()));
						subNavi.setUrl(p.getUrl());
						subNavi.setIcon(p.getIcon());
						navi.addChild(subNavi);
					}
				}
				
				naviList.add(navi);
			}
		}
		return naviList;
	}
	
	private Locale getLocale(String localeTxt){
		if(StringUtils.isEmpty(localeTxt)){
			return Locale.US;
		}else{
			if(localeTxt.indexOf("_") > 0){
				String language = localeTxt.substring(0, 2);
				String country = localeTxt.substring(3);
				return new Locale(language, country);
			}else{
				return new Locale(localeTxt);
			}
		}
	}
	
	private String getTranslatedLabel(String msgKey, Locale l){
		String msg = msgKey;
		try{
			msg = messageSource.getMessage(msgKey,null,l);
		}catch(NoSuchMessageException e){
			//do nothing, log to be added
		}
		return msg;
	}
}

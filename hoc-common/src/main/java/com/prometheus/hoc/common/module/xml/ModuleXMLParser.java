package com.prometheus.hoc.common.module.xml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.prometheus.hoc.common.bean.ModuleIncludeBean;
import com.prometheus.hoc.common.module.bean.ModuleConfModule;
import com.prometheus.hoc.common.module.bean.ModuleConfPage;
import com.prometheus.hoc.common.module.bean.ModuleConfSetting;
import com.prometheus.hoc.common.module.bean.ModuleConfSubModule;
import com.thoughtworks.xstream.XStream;

/**
 * 
 * @author bubuwork
 *
 */
public class ModuleXMLParser {
	/**
	 * FIEL PATH
	 */
	public static final String PATH_MODULE_PAGE_SETTING = "module_pages_setting.xml";
	
	private static ModuleConfSetting setting;
	
	public static ModuleConfSetting parseModulePagesSetting(){
		if(setting == null){
			setting = new ModuleConfSetting();
			InputStream is = ModuleXMLParser.class.getClassLoader().getResourceAsStream(PATH_MODULE_PAGE_SETTING);
			XStream xstream = new XStream();
			xstream.alias("settings", ModuleConfSetting.class);
			xstream.alias("module", ModuleConfModule.class);
			xstream.alias("page", ModuleConfPage.class);
			xstream.alias("sub-module", ModuleConfSubModule.class);
			xstream.autodetectAnnotations(true);
			if(is != null){
				setting = (ModuleConfSetting) xstream.fromXML(is);
			}
		}
		return setting;
	}
	
	/**
	 * get all configured pages
	 * @return
	 */
	public static List<ModuleIncludeBean> getAllPageUrlsHasInclude(){
		List<ModuleIncludeBean> moduleIncList = new ArrayList<ModuleIncludeBean>();
		ModuleConfSetting setting = parseModulePagesSetting();
		List<ModuleConfModule> modules = setting.getModules();
		ModuleIncludeBean mib=null;
		for (ModuleConfModule m : modules) {
			if(m.isHasInclude() && m.getUrl() != null){
				mib = new ModuleIncludeBean();
				mib.setPageUrl(m.getUrl());
				mib.setModule(m.getId());
				moduleIncList.add(mib);
			}
			
			//parse the sublevel
			List<ModuleConfSubModule> subModules = m.getSubModules();
			if(subModules != null){
				for (ModuleConfSubModule subm : subModules) {
					if(subm.isHasInclude() && subm.getUrl() != null){
						mib = new ModuleIncludeBean();
						mib.setModule(subm.getId());
						mib.setPageUrl(subm.getUrl());
						moduleIncList.add(mib);
					}
					
					//parse page level
					List<ModuleConfPage> ipages = subm.getPages();
					if(ipages != null){
						for (ModuleConfPage ip : ipages) {
							if(ip.isHasInclude() && ip.getUrl() != null){
								mib = new ModuleIncludeBean();
								mib.setModule(ip.getId());
								mib.setPageUrl(ip.getUrl());
								moduleIncList.add(mib);
							}
						}
					}
				}
			}
			//parse page level
			List<ModuleConfPage> pages = m.getPages();
			if(pages != null){
				for (ModuleConfPage p : pages) {
					if(p.isHasInclude() && p.getUrl() != null){
						mib = new ModuleIncludeBean();
						mib.setModule(p.getId());
						mib.setPageUrl(p.getUrl());
						moduleIncList.add(mib);
					}
				}
			}
			
		}
		return moduleIncList;
	}
	
	public static void main(String[] args) {
		List<ModuleIncludeBean> urls = ModuleXMLParser.getAllPageUrlsHasInclude();
		System.out.println(urls);
	}
}

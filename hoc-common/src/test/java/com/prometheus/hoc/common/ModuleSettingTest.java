package com.prometheus.hoc.common;

import org.junit.Test;

import com.prometheus.hoc.common.module.bean.ModuleConfModule;
import com.prometheus.hoc.common.module.bean.ModuleConfPage;
import com.prometheus.hoc.common.module.bean.ModuleConfSetting;
import com.prometheus.hoc.common.module.bean.ModuleConfSubModule;
import com.thoughtworks.xstream.XStream;

public class ModuleSettingTest {
	
	@Test
	public void testGenerateXML(){
		XStream xstream = new XStream();
		xstream.alias("settings", ModuleConfSetting.class);
		xstream.alias("module", ModuleConfModule.class);
		xstream.alias("page", ModuleConfPage.class);
		xstream.autodetectAnnotations(true);
		
		ModuleConfPage page = new ModuleConfPage();
		page.setId("payrollp");
		page.setPermission("payrollp");
		page.setUrl("payroll_permission");
		
		ModuleConfPage page2 = new ModuleConfPage();
		page2.setId("payrollp");
		page2.setPermission("payrollp");
		page2.setUrl("payroll_permission");
		
		ModuleConfSubModule sub = new ModuleConfSubModule();
		sub.setTitle("wh");
		sub.setUrl("www.what the fuck");
		sub.addPage(page);
		
		ModuleConfModule module = new ModuleConfModule();
		module.addPage(page2);
		module.addSubModule(sub);
		module.setId("payroll");
		module.setTitle("Payroll");
		module.setUrl("http://shit");
		
		ModuleConfSetting setting = new ModuleConfSetting();
		setting.addModule(module);
		System.out.println(xstream.toXML(setting));
		
	}
}

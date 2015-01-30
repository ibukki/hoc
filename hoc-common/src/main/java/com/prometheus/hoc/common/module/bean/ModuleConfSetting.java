package com.prometheus.hoc.common.module.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("settings")
public class ModuleConfSetting implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1789140774324596080L;
	
	@XStreamAlias("modules")
	private List<ModuleConfModule> modules;

	/**
	 * @return the modules
	 */
	public List<ModuleConfModule> getModules() {
		return modules;
	}

	/**
	 * @param modules the modules to set
	 */
	public void setModules(List<ModuleConfModule> modules) {
		this.modules = modules;
	}
	
	public void addModule(ModuleConfModule module){
		if(this.modules == null){
			this.modules = new ArrayList();
		}
		this.modules.add(module);
	}
}

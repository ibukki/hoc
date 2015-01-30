package com.prometheus.hoc.common.module.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ModuleNaviItem implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6708922523802847069L;

	private String label;
	
	private List<ModuleNaviItem> children;
	
	private String href;
	
	private String url;
	
	private String icon;
	
	//TBD
	private String data;

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the children
	 */
	public List<ModuleNaviItem> getChildren() {
		return children;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(List<ModuleNaviItem> children) {
		this.children = children;
	}
	
	/**
	 * 
	 * @param child
	 */
	public void addChild(ModuleNaviItem child){
		if(this.children == null){
			this.children = new ArrayList<ModuleNaviItem>();
		}
		this.children.add(child);
	}

	/**
	 * @return the href
	 */
	public String getHref() {
		return href;
	}

	/**
	 * @param href the href to set
	 */
	public void setHref(String href) {
		this.href = href;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
}

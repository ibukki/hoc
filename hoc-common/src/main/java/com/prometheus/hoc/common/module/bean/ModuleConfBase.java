package com.prometheus.hoc.common.module.bean;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

public class ModuleConfBase implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String title;
	
	private String url;
	
	private String icon;
	
	private String permission;
	
	@XStreamAlias(value="has-include")
	@XStreamAsAttribute
	private boolean hasInclude;

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * @return the permission
	 */
	public String getPermission() {
		return permission;
	}

	/**
	 * @param permission the permission to set
	 */
	public void setPermission(String permission) {
		this.permission = permission;
	}

	/**
	 * @return the hasInclude
	 */
	public boolean isHasInclude() {
		return hasInclude;
	}

	/**
	 * @param hasInclude the hasInclude to set
	 */
	public void setHasInclude(boolean hasInclude) {
		this.hasInclude = hasInclude;
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

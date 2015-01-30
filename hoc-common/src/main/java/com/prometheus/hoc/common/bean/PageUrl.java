package com.prometheus.hoc.common.bean;

import java.io.Serializable;

public class PageUrl implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1270473708934930162L;
	
	
	private String path;

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
}

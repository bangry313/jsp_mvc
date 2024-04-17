package com.ezen.mall.web.common.core.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Model과 논리적인 뷰이름 저장
 */
public class ModelAndView {
	
	// 논리적 뷰 이름
	private String viewName;
	
	private Map<String, Object> model = new HashMap<>();

	public ModelAndView(){}

	public ModelAndView(String viewName) {
		this.viewName = viewName;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}

	public void setAttribute(String key, Object value){
		model.put(key, value);
	}

	@Override
	public String toString() {
		return "ModelAndView [viewName=" + viewName + ", model=" + model + "]";
	}
}

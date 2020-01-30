package com.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class KeyValue {
	private Object value;
	private Integer exiprytime;
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public Integer getExiprytime() {
		return exiprytime;
	}
	public void setExiprytime(Integer exiprytime) {
		this.exiprytime = exiprytime;
	}

}

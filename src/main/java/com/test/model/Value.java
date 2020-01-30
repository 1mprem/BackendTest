package com.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Value {
	private Object value;
	private Long createdTimeStamp;
	private Integer exiprytime;
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public Long getCreatedTimeStamp() {
		return createdTimeStamp;
	}
	public void setCreatedTimeStamp(Long createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}
	public Integer getExiprytime() {
		return exiprytime;
	}
	public void setExiprytime(Integer exiprytime) {
		this.exiprytime = exiprytime;
	}


}

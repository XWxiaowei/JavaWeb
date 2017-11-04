package com.jay.ssh.util;

public enum DistrictReturnNum {

	SUCCESS(104,"成功"),
	FAIL(103,"失败");

	private Integer value;
	private String name;
	private DistrictReturnNum(Integer value, String name) {
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}

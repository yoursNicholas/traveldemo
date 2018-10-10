package com.webproject.enums;

/**
 * 使用枚举表述常量数据字典
 */
public enum AppointStateEnum {

	SUCCESS(1, "修改成功"), NO_NUMBER(0, "没有此项"), REPEAT_APPOINT(-1, "重复提交"), INNER_ERROR(-2, "系统异常");

	private int state;

	private String stateInfo;

	private AppointStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static AppointStateEnum stateOf(int index) {
		for (AppointStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}
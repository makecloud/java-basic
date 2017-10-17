package com.liuyihui.common.Enum;

/**
 * 操作系统类型枚举
 * 
 * @author yinjy
 *
 */
public enum COsType {

	/** 字段相关 */
	/** ios */
	IOS((short) 1, "ios"),
	/** Android */
	ANDROID((short) 2, "Android"),
	/** windows */
	WINDOWS((short) 3, "windows");

	/** 属性相关 */
	/** 操作系统类型值 */
	private short value = 0;
	/** 操作系统类型描述 */
	private String description = null;

	/**
	 * 构造函数
	 * 
	 * @param value 操作系统类型值
	 * @param description 操作系统类型描述
	 */
	private COsType(short value, String description) {
		this.value = value;
		this.description = description;
	}

	/**
	 * 获取操作系统类型值
	 * 
	 * @return 操作系统类型值
	 */
	public short getValue() {
		return value;
	}

	/**
	 * 获取操作系统类型描述
	 * 
	 * @return 操作系统类型描述
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 获取操作系统类型描述
	 * 
	 * @param value 操作系统类型值
	 * @return 操作系统类型描述
	 */
	public static String getDescription(Short value) {
		if (value != null) {
			for (COsType field : values()) {
				if (value.equals(field.value)) {
					return field.description;
				}
			}
		}
		return null;
	}

	/**
	 * 根据值获取操作系统类型
	 * 
	 * @param value 操作系统类型值
	 * @return 操作系统类型
	 */
	public static COsType fromValue(Short value) {
		if (value != null) {
			for (COsType field : values()) {
				if (value.equals(field.value)) {
					return field;
				}
			}
		}
		return null;
	}

	/**
	 * 包含操作系统类型值
	 * 
	 * @param value 操作系统类型值
	 * @return 是否包含
	 */
	public static boolean contains(Short value) {
		if (value != null) {
			for (COsType field : values()) {
				if (value.equals(field.value)) {
					return true;
				}
			}
		}
		return false;
	}

}

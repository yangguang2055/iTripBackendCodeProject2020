package com.whackode.itrip.base.enums;

/**
 * <b>是否是热门城市枚举信息</b>
 * @author Arthur
 * @version 1.0.0
 * @since 1.0.0
 */
public enum AreaHotEnum {
	AREA_HOT_YES(1),
	AREA_HOT_NO(0)
	;
	private int code;

	private AreaHotEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}

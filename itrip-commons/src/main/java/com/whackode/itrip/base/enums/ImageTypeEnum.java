package com.whackode.itrip.base.enums;

public enum ImageTypeEnum {
	IMAGE_TYPE_HOTEL(0),
	IMAGE_TYPE_ROOM(1),
	IMAGE_TYPE_COMMENT(2)
	;
	private int code;

	private ImageTypeEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}

package com.whackode.itrip.base.enums;

public enum OrderStatusEnum {
	ORDER_STATUS_PREPAY(0),
	ORDER_STATUS_CANCEL(1),
	ORDER_STATUS_PAYED(2),
	ORDER_STATUS_SUCCESS(3),
	ORDER_STATUS_COMMENT(4)
	;
	private int code;

	private OrderStatusEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}

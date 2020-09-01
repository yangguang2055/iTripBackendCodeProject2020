package com.whackode.itrip.util.constant;

import java.util.Properties;

/**
 * <b>系统常量工具类</b>
 */
public class SystemConstant {
	private static Properties props = new Properties();

	static {
		try {
			props.load(SystemConstant.class.getClassLoader().getResourceAsStream("prop/commons.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final String SECRET_key = props.getProperty("secret.key");
}

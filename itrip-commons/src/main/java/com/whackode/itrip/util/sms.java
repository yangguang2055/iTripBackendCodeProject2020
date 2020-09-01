package com.whackode.itrip.util;


import com.cloopen.rest.sdk.CCPRestSmsSDK;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.Set;

@Component("sms")
public class sms {
	public boolean SMS(String userCode, String activeCode) throws Exception {
		System.out.println("sms开始");
		HashMap<String, Object> result = null;
		CCPRestSmsSDK restAPI = new CCPRestSmsSDK();

		//String reslut = "---";
		restAPI.init("app.cloopen.com", "8883");
		// 初始化服务器地址和端口，生产环境配置成app.cloopen.com，端口是8883.
		restAPI.setAccount("8aaf0708732220a60173e5b022a35538", "f24bf7bcb2b941c4b436725d225355f0");
		// 初始化主账号名称和主账号令牌，登陆云通讯网站后，可在控制首页中看到开发者主账号ACCOUNT SID和主账号令牌AUTH TOKEN。
		restAPI.setAppId("8aaf0708732220a60173e5b02388553e");
		// 请使用管理控制台中已创建应用的APPID。
		result = restAPI.sendTemplateSMS(userCode, "1", new String[]{activeCode, "【杨光test】1"});
		System.out.println("SDKTestGetSubAccounts result=" + result);
		if ("000000".equals(result.get("statusCode"))) {
			//正常返回输出data包体信息（map）
			HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
			Set<String> keySet = data.keySet();
			for (String key : keySet) {
				Object object = data.get(key);
				System.out.println(key + " = " + object);
			}
			return true;
		} else {
			//异常返回输出错误码和错误信息
			System.out.println("错误码=" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg"));
		}
		return false;
	}

	public String test(){
		return "can-run";
	}
}

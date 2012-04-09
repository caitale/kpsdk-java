package kp.client.model;

import java.util.HashMap;

/**
 * 获取用户信息API，无必填的参数
 * @author Tale
 *
 */
public class AccountInfo extends ApiRequest{

	public AccountInfo(){
		method = "account_info";
		this.requiredParameters = new HashMap<String, String>();
		this.optionalParameters = new HashMap<String, String>();
	}

}

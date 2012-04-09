package kp.client.test;

import kp.client.core.KuaipanApi;
import kp.client.core.KuaipanApiClient;
import kp.client.exception.KuaipanApiException;
import kp.client.model.Copy;

import org.json.JSONObject;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.Token;

/**
 * 
 * @author Tale
 *
 */
public class TestCopy {

	public static void main(String[] args){
		//获取到的access_token
		Token token = new Token("", "");
		String root = "app_folder";
		String fromPath = "/testFolder2/test.wps";
		String toPath = "/testFolder/test.wps";
		Copy copy = new Copy(root, fromPath, toPath);
		KuaipanApiClient client = new ServiceBuilder().provider(KuaipanApi.class).build();
		try{
			JSONObject obj = client.doCall(token, copy );
			System.out.println(obj);
		}catch(KuaipanApiException e ){
			System.out.println(e.getStatusCode() + ": " + e.getMessage());
		}
	}
}

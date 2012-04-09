package kp.client.test;

import kp.client.core.KuaipanApi;
import kp.client.core.KuaipanApiClient;
import kp.client.exception.KuaipanApiException;
import kp.client.model.Shares;

import org.json.JSONObject;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.Token;

/**
 * 
 * @author Tale
 *
 */
public class TestShares {

	public static void main(String[] args){
		//获取到的access_token
		Token token = new Token("", "");
		String path = "/testFolder/test.txt";
		Shares shares = new Shares(path);
		KuaipanApiClient client = new ServiceBuilder().provider(KuaipanApi.class).build();
		try{
			JSONObject obj = client.doCall(token, shares );
			System.out.println(obj);
		}catch(KuaipanApiException e ){
			System.out.println(e.getStatusCode() + ": " + e.getMessage());
		}
		
	}
}

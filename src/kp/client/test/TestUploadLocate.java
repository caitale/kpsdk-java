package kp.client.test;

import kp.client.core.KuaipanApi;
import kp.client.core.KuaipanApiClient;
import kp.client.exception.KuaipanApiException;
import kp.client.model.UploadLocate;

import org.json.JSONObject;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.Token;

/**
 * 
 * @author Tale
 *
 */
public class TestUploadLocate {

	public static void main(String[] args){
		try{
			//获取到的access_token
			Token token = new Token("6d2a4b7c8eb3a4ec74e5e4638ea2e646", "ec45cc027d2f1797fff30c2afcdc9c19");
			UploadLocate uploadLocate = new UploadLocate();
			KuaipanApiClient client = new ServiceBuilder().provider(KuaipanApi.class).build();
			JSONObject obj = client.doCall(token, uploadLocate );
			System.out.println(obj);
		}catch(KuaipanApiException e ){
			System.out.println(e.getStatusCode() + ": " + e.getMessage());
		}
	}
}

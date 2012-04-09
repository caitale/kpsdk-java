package kp.client.test;

import kp.client.core.KuaipanApi;
import kp.client.core.KuaipanApiClient;
import kp.client.exception.KuaipanApiException;
import kp.client.model.Move;

import org.json.JSONObject;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.Token;

/**
 * 
 * @author Tale
 *
 */
public class TestMove {

	public static void main(String[] args){
		//获取到的access_token
		Token token = new Token("", "");
		String root = "app_folder";
		String fromPath = "/testFolder/test.txt";
		String toPath = "/testFolder2/test2.txt";
		Move move = new Move(root, fromPath, toPath);
		KuaipanApiClient client = new ServiceBuilder().provider(KuaipanApi.class).build();
		try{
			JSONObject obj = client.doCall(token, move );
			System.out.println(obj);
		}catch(KuaipanApiException e ){
			System.out.println(e.getStatusCode() + ": " + e.getMessage());
		}
	}
}

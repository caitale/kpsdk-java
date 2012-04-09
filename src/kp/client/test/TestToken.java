package kp.client.test;

import java.io.IOException;
import java.util.Scanner;

import kp.client.core.KuaipanApi;
import kp.client.core.KuaipanApiClient;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.Token;
import org.scribe.model.Verifier;

/**
 * 
 * @author Tale
 *
 */
public class TestToken {
	public static void main(String[] args) throws IOException{
		KuaipanApiClient client = new ServiceBuilder()
		                              .provider(KuaipanApi.class)
									  .build();
		System.out.println("获取request token------>返回结果为");
		Token requestToken = client.getRequestToken();
		System.out.println("request_token: " + requestToken.getToken());
		System.out.println("token_secret: " + requestToken.getSecret());
		
		System.out.println("用户授权------>输入授权码");
		String authorizationUrl = client.getAuthorizationUrl(requestToken); 
		Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + authorizationUrl ); 
		Scanner scanner = new Scanner(System.in);
		String verifierString = scanner.nextLine();
		Verifier verifier = new Verifier(verifierString);
		
		System.out.println("获取access token------>返回结果为");
		Token accessToken = client.getAccessToken(requestToken, verifier);
		//若不传入verifier可将其设为null即可
		//Token accessToken = client.getAccessToken(requestToken, null);
		System.out.println("access token: " + accessToken.getToken());
		System.out.println("token_secret: " + accessToken.getSecret());	
	}
}

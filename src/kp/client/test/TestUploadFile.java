package kp.client.test;

import kp.client.constants.OverwriteType;
import kp.client.core.KuaipanApi;
import kp.client.core.KuaipanApiClient;
import kp.client.exception.KuaipanApiException;
import kp.client.model.UploadFile;
import kp.client.model.UploadLocate;

import org.json.JSONException;
import org.json.JSONObject;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.Token;

/**
 * 
 * @author Tale
 * 
 */
public class TestUploadFile {
	public static void main(String[] args) {
		try {
			//获取到的access_token
			Token token = new Token("", "");
			KuaipanApiClient client = new ServiceBuilder().provider(KuaipanApi.class).build();
			UploadLocate uploadLocate = new UploadLocate();
			//获取文件上传地址
			String uploadUrl = client.doCall(token, uploadLocate).getString("url");
			
			String root = "app_folder";
			String path = "/testFolder/upload.txt";

			String separator = System.getProperty("file.separator");
			String filePath = System.getProperty("user.dir") + "/" + root + path;
			filePath = filePath.replace("/", separator);
			UploadFile uploadFile = new UploadFile(OverwriteType.True, root, path, uploadUrl, filePath);

			JSONObject obj = client.doCall(token, uploadFile);
			System.out.println(obj);
		} catch (KuaipanApiException e) {
			System.out.println(e.getStatusCode() + ": " + e.getMessage());
		} catch(JSONException je){
			je.printStackTrace();
		}
	}
}

package kp.client.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;

import kp.client.exception.KuaipanApiException;
import kp.client.model.ApiRequest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.json.JSONException;
import org.json.JSONObject;
import org.scribe.model.OAuthRequest;
import org.scribe.utils.URLUtils;

/**
 * 文件上传和下载工具类
 * @author Tale
 * 
 */
public class FileUtils {

	public static JSONObject downloadFile(OAuthRequest request, ApiRequest apiRequest) {
		HttpClient hc = new HttpClient();
		hc.getParams().setParameter("http.protocol.cookie-policy",CookiePolicy.BROWSER_COMPATIBILITY);
		Map<String, String> querystringParams = request.getQueryStringParams();
		String url = URLUtils.appendParametersToQueryString(request.getUrl(), querystringParams);
		GetMethod get = new GetMethod(url);
		byte[] responseBody;
		try {
			int responseCode = hc.executeMethod(get);
			if (responseCode == 200) {
				responseBody = get.getResponseBody();
			} else {
				throw new KuaipanApiException(get.getStatusText(), responseCode);
			}
		} catch (HttpException he) {
			throw new KuaipanApiException("Problems with http protocol", he);
		} catch (IOException ioe) {
			throw new KuaipanApiException("Problems while downloading the file", ioe);
		} finally {
			get.releaseConnection();
		}

		try {
			writeFile(responseBody, request, apiRequest);
			JSONObject obj = new JSONObject();
			obj.put("msg", "ok");
			return obj;
		} catch (IOException ioe) {
			throw new KuaipanApiException("Problems while writing the file", ioe);
		} catch (JSONException je) {
			throw new KuaipanApiException("Problems while assembling json object", je);
		}
	}

	private static void writeFile(byte[] body, OAuthRequest request, ApiRequest apiRequest) throws IOException {
		String separator = System.getProperty("file.separator");
		String filePath = apiRequest.getFilePath().replace("/", separator);
		File file = new File(filePath);
		if (!file.exists()) {
			String folderPath = filePath.substring(0, filePath.lastIndexOf(separator));
			File folder = new File(folderPath);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			file.createNewFile();
		}
		FileOutputStream output = new FileOutputStream(file);
		output.write(body);
		output.close();
	}

	public static String uploadFile(OAuthRequest request, ApiRequest apiRequest) {
		File file = new File(apiRequest.getFilePath());
		HttpClient hc = new HttpClient();
		Map<String, String> querystringParams = request.getQueryStringParams();
		String url = URLUtils.appendParametersToQueryString(request.getUrl(), querystringParams);
		PostMethod post = new PostMethod(url);
		String responseString = null;
		try {
			FilePart filePart = new FilePart("file", file.getName(), file, new MimetypesFileTypeMap().getContentType(file), "UTF-8");
			filePart.setTransferEncoding("binary");
			Part[] parts = new Part[] { filePart };
			post.setRequestEntity(new MultipartRequestEntity(parts, post.getParams()));
			int responseCode = hc.executeMethod(post);
			if (responseCode == 200) {
				responseString = post.getResponseBodyAsString();
			} else{
				throw new KuaipanApiException(post.getStatusText(), responseCode);
			}
		} catch (FileNotFoundException fe) {
			throw new KuaipanApiException("[" + apiRequest.getFilePath() + "] File does not exist!", fe);
		} catch (HttpException he) {
			throw new KuaipanApiException("Problems with http protocol", he);
		} catch (IOException ioe) {
			throw new KuaipanApiException("Problems while uploading the file", ioe);
		} finally {
			post.releaseConnection();
		}
		return responseString;
	}
}

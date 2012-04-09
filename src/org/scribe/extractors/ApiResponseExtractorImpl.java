package org.scribe.extractors;

import kp.client.exception.KuaipanApiException;

import org.json.JSONException;
import org.json.JSONObject;

public class ApiResponseExtractorImpl implements ApiResponseExtractor{

	@Override
	public JSONObject extract(String response) {
		try{
			JSONObject obj = new JSONObject(response);
			return obj;
		} catch( JSONException e){
			throw new KuaipanApiException("Can't extract the json result: " + response);
		}
	}

}

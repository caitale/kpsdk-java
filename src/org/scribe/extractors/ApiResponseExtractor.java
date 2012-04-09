package org.scribe.extractors;

import org.json.JSONObject;

public interface ApiResponseExtractor {

	public JSONObject extract(String response);
}

package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Utilities {

	//based on: https://stackoverflow.com/questions/1548782/retrieving-json-object-literal-from-httpservletrequest
	public static JsonObject getJsonBody(HttpServletRequest r) {
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(r.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		JsonObject jsonObj = new Gson().fromJson(br, JsonObject.class);
		
		
		return jsonObj;
	}
	
	


}

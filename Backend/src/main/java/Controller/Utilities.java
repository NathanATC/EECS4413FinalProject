package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Utilities {


	public static JsonObject getJsonBody(HttpServletRequest r) {
		StringBuilder sb = new StringBuilder();
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(r.getInputStream()));
			
			String line;
			while((line = br.readLine()) != null) 
				sb.append(line);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		String jsonStr = sb.toString();
		
		JsonObject jsonObj = new Gson().fromJson(jsonStr, JsonObject.class);
		
		
		return jsonObj;
	}


}

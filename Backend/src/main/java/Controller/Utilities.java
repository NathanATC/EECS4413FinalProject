package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	public static void outputRes(HttpServletResponse res, String payload, int status) {
		res.setHeader("Content-Type", "application/json");
		try {
			res.setStatus(status);
			OutputStream s = res.getOutputStream();
			s.write(payload.getBytes());
			s.flush();
		} catch (Exception e) {
			System.out.print(e);
		}
	}
}

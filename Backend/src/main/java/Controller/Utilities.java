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

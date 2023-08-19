package Controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import Model.Item;

/**
 * Servlet implementation class catalogue
 */
@WebServlet("/catalogue")
public class catalogueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public catalogueServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.addHeader("Access-Control-Allow-Origin","*");
		DataAccsessMySQL db = new DataAccsessMySQL();
		JsonObject tmp = Utilities.getJsonBody(request);
		
		ArrayList<Item> catalogue = new ArrayList<Item>();
		if(tmp != null) {
			catalogue = db.getCatalogue(tmp.get("filterType").getAsString(), tmp.get("filterFor").getAsString());
		} else {
			catalogue = db.getCatalogue("","");
		} 
		
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(catalogue);
		this.outputRes(response, json, 200);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void outputRes(HttpServletResponse res, String payload, int status) {
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

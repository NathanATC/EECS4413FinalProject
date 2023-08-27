package Controller;

import java.io.IOException;
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
import Model.Order;

/**
 * Servlet implementation class HotProducts
 */
@WebServlet("/HotProducts")
public class HotProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HotProducts() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataAccsessMySQL dao = new DataAccsessMySQL();
		
		response.addHeader("Access-Control-Allow-Origin","*");
		DataAccsessMySQL db = new DataAccsessMySQL();
		JsonObject tmp = Utilities.getJsonBody(request);
		
		ArrayList<Item> topItems = new ArrayList<Item>();
		topItems = dao.topItems(4);
		
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(topItems);
		Utilities.outputRes(response, json, 200);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

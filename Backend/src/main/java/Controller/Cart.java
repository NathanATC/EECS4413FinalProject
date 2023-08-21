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

/**
 * Servlet implementation class Cart
 */
@WebServlet("/Cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.addHeader("Access-Control-Allow-Origin","*");
		DataAccsessMySQL db = new DataAccsessMySQL();
		JsonObject tmp = Utilities.getJsonBody(request);
		String username = tmp.get("username").getAsString();
		String method = tmp.get("method").getAsString();
		if(method.equals("ADD")) {
			String id = tmp.get("item").getAsJsonObject().get("id").getAsString();
			Gson gson = new GsonBuilder().create();
			if(db.addToCart(username, id, 1)) {
				Utilities.outputRes(response, gson.toJson("Added To Cart"), 200);
			} else {
				Utilities.outputRes(response, gson.toJson("Error"), 200);
			}
		} else if(method.equals("CLEAR")) {
			Gson gson = new GsonBuilder().create();
			if(db.clearCart(username)) {
				Utilities.outputRes(response, gson.toJson("Cleared!"), 200);
			} else {
				Utilities.outputRes(response, gson.toJson("Error"), 200);
			}
		} else if(method.equals("GET")) {
			ArrayList<Item> cart = db.getCart(username);
			
			Gson gson = new GsonBuilder().create();
			String json = gson.toJson(cart);
			
			Utilities.outputRes(response, json, 200);
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

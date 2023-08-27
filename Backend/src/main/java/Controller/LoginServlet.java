package Controller;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import Controller.DataAccess.UserNotFound;
import Model.Account;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AccessControlServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {



	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//		response.addHeader("Access-Control-Allow-Origin","*");
		//		
		//		PrintWriter out =  response.getWriter();
		//		
		//		if(this.getServletContext().)
		//
		//		DataAccsessMySQL dao = new DataAccsessMySQL();
		//		
		//		String username = request.getParameter("username");	
		//		String password = request.getParameter("password");





	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonObject inputJson = Utilities.getJsonBody((HttpServletRequest)request);
		DataAccsessMySQL dao = new DataAccsessMySQL();
		Gson gson = new Gson();

		String givenUsername = inputJson.get("username").getAsString();
		String givenPassword = inputJson.get("password").getAsString();
		response.addHeader("Access-Control-Allow-Origin","*");

		if(dao.isPasswordCorrect(givenUsername, givenPassword)) {
			Account account = dao.getUser(givenUsername);
			
			HttpSession currSession = request.getSession();
			currSession.setAttribute("account", account);

			String jsonString = gson.toJson(account);
			response.setContentType("application/json");
			
			response.getWriter().print(jsonString);

			response.getWriter().flush();

		}	
		else {
			response.getWriter().print(gson.toJson(new Account()));
			response.getWriter().flush();

		}







	}

}

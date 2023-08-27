package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Model.Account;

/**
 * Servlet implementation class CreateAccount
 */
@WebServlet("/CreateAccount")
public class CreateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.addHeader("Access-Control-Allow-Origin","*");
			
			JsonObject requestJson = Utilities.getJsonBody(request);
			Gson g = new Gson();
			
			Account newUser = new Account();
			
			newUser.setUserName(requestJson.get("username").getAsString());
			newUser.setFirstName(requestJson.get("firstName").getAsString());
			newUser.setLastName(requestJson.get("lastName").getAsString());
			newUser.setPhoneNumber(requestJson.get("phoneNumber").getAsString());
			newUser.setProvince(requestJson.get("province").getAsString());
			newUser.setCountry(requestJson.get("Country").getAsString());
			newUser.setBillingAddress(requestJson.get("billingAddress").getAsString());
			newUser.setAddress(requestJson.get("address").getAsString());
			newUser.setEmail(requestJson.get("email").getAsString());
			newUser.setPostalCode(requestJson.get("postalCode").getAsString());
			
			
			String password = requestJson.get("password").getAsString();
			String premissions = requestJson.get("premissions").getAsString();
			
			
			new DataAccsessMySQL().addUser(newUser, password, premissions);
			
			Utilities.outputRes(response, g.toJson(newUser) , 200);
		} catch (Exception e) {
			response.getWriter().print("somthing went wrong");
			e.printStackTrace();
		}
		
		
		
		
	}

}

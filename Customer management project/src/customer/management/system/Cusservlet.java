package customer.management.system;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.management.system.CustDao;
import customer.management.system.cust;



@WebServlet("/")
public class  Cusservlet extends HttpServlet {
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		String password=request.getParameter("password");
		String gender=request.getParameter("gender");
		String email=request.getParameter("email");
		String address=request.getParameter("address");
	
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String action = request.getServletPath();

			try {
				switch (action) {
				case "/new":
					showNewForm(request, response);
					break;
				case "/insert":
					insertUser(request, response);
					break;
				case "/delete":
					deleteUser(request, response);
					break;
				case "/edit":
					showEditForm(request, response);
					break;
				case "/update":
					updateUser(request, response);
					break;
				default:
					listUser(request, response);
					break;
				}
			} catch (SQLException ex) {
				throw new ServletException(ex);
			}
		}

		private void listUser(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, IOException, ServletException {
			List<cust> listUser = cust.selectAllUsers();
			request.setAttribute("listUser", listUser);
			RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
			dispatcher.forward(request, response);
		}

		private void showNewForm(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
			dispatcher.forward(request, response);
		}

		private void showEditForm(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, ServletException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));
			cust existingUser = CustDao.selectUser(id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
			request.setAttribute("user", existingUser);
			dispatcher.forward(request, response);

		}

		private void insertUser(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String country = request.getParameter("address");
			cust newUser = new cust(firstname,lastname,email,phone,address,postal);
			cust.insertUser(newUser);
			response.sendRedirect("list");
		}

		private void updateUser(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));
			String firstname = request.getParameter("firstname");
			String email = request.getParameter("email");
			String country = request.getParameter("address");

		
		}

		private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));
			cust.deleteUser(id);
			response.sendRedirect("list");

		}

		
		
	


}
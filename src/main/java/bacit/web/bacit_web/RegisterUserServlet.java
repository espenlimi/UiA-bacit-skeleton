package bacit.web.bacit_web;
import bacit.web.bacit_models.RegisterUserModel;
import bacit.web.bacit_utilities.HtmlHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "registerUserServlet", value = "/admin/register_user")
public class RegisterUserServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        writeCreateUserForm(out,null);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        RegisterUserModel user = new RegisterUserModel();
        user.setFullName(request.getParameter("fullName"));
        user.setPhoneNumber(request.getParameter("phoneNumber"));
        PrintWriter out = response.getWriter();
        if(validateUser(user)){
            try{
                writeUserToDb(user, out);
            }
            catch (SQLException ex)
            {
                out.println(ex.getMessage());
            }
            HtmlHelper.writeHtmlStart(out, "Register user response");
            out.println("User with fullname: "+user.getFullName()+" and phone number: "
                    +user.getPhoneNumber()+" registered");
            HtmlHelper.writeHtmlEnd(out);
        }
        else
        {
            writeCreateUserForm(out, "Validation failed");
        }
    }
    private void writeUserToDb(RegisterUserModel user,PrintWriter out) throws SQLException {
        Connection db = DBUtils.getINSTANCE().getConnection(out);
        String insertUserCommand = "insert into users (FullName, PhoneNumber) values(?,?);";
        PreparedStatement statement = db.prepareStatement(insertUserCommand);
        statement.setString(1, user.getFullName());
        statement.setString(2, user.getPhoneNumber());

        statement.executeUpdate();
    }
    private void writeCreateUserForm(PrintWriter out, String errorMessage) {
        HtmlHelper.writeHtmlStart(out, "Registrer bruker, TEST123");
        if(errorMessage!=null)
        {
            out.println("<h3>"+errorMessage+"</h3>");
        }
        out.println("<form action='register_user' method='POST'>");
        out.println("<label for='fullName'>Fullt navn</label> ");
        out.println("<input type='text' name='fullName'/>");
        out.println("<label for='phoneNumber'>Telefonnummer</label> ");
        out.println("<input type='tel' name='phoneNumber'/>");
        out.println("<input type='submit' value='Registrer bruker'/>");
        out.println("</form>");
        HtmlHelper.writeHtmlEnd(out);
    }


    private Boolean validateUser(RegisterUserModel model){
        if(model.getFullName()==null)
            return false;
        if(model.getFullName().trim().equalsIgnoreCase(""))
            return false;
        if(model.getPhoneNumber()==null)
            return false;
        if(model.getPhoneNumber().trim().equalsIgnoreCase(""))
            return false;
        return true;
    }
}

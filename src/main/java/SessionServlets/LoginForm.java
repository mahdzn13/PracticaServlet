package SessionServlets;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;


public class LoginForm extends HttpServlet {
    private final List<User> users = new ArrayList<User>();
    private boolean cont = false;
    public void init()
    {
        users.add(new User("marco","1234","marco@gmail.com"));
        users.add(new User("joshua","abcd","joshua@gmail.com"));
        users.add(new User("pablo","1234","pablo@gmail.com"));
        users.add(new User("joan","abcd","joan@gmail.com"));
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {

    }
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        boolean login = false;
        // Set response content type
        response.setContentType("text/html");

        String email = request.getParameter("email");
        String pwd = request.getParameter("password");
         //null = non-checked - on = checked
        for (User user : users) {
            if(user.getEmail().equals(email) && user.getPassword().equals(pwd)){
                login = true;
                HttpSession session = request.getSession();
                session.setAttribute("email", user.getEmail());
                //setting session to expiry in 15 mins
                session.setMaxInactiveInterval(15*60);

                if (request.getParameterMap().containsKey("remember")) {
                    if (request.getParameter("remember").equals("on")) {
                        Cookie userName = new Cookie("email", user.getEmail());
                        //Week duration 60*60*24*7
                        userName.setMaxAge(60 * 60 * 24 * 7);
                        response.addCookie(userName);
                    }
                }

                //Create and set log for the user
                String userdir = user.getEmail();
                int arrobaPos = 0;
                for (int i = 0; i < userdir.length(); i++) {
                    if (userdir.charAt(i) == '@'){
                        arrobaPos = i;
                    }
                }
                userdir = userdir.substring(0,arrobaPos);

                String strPath = getServletContext().getInitParameter("file-upload") + userdir;
                File strFile = new File(strPath + "\\UserLoggingLog.log");

                String logContent = "Last logging - Date: " + new Date() + " Username: " + user.getUsername() + " Ip: " + request.getRemoteAddr();
                BufferedWriter outStream= new BufferedWriter(new FileWriter(strFile, true));
                outStream.newLine();
                outStream.write(logContent);
                outStream.close();

            }
        }
        if (!login){
            throw new ServletException("Login failed.");
        }



        response.sendRedirect("menu.jsp");
    }
}
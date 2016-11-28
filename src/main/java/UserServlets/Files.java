package UserServlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class Files extends HttpServlet {
    private String path;
    private boolean firstTime = true;
    public void init()
    {
        path = getServletContext().getInitParameter("file-upload");
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        String body = "";
        HttpSession ses =  request.getSession();
        String userdir = (String) ses.getAttribute("email");
        int arrobaPos = 0;
        for (int i = 0; i < userdir.length(); i++) {
            if (userdir.charAt(i) == '@'){
                arrobaPos = i;
            }
        }
        userdir = userdir.substring(0,arrobaPos);

        if (firstTime){
            firstTime = false;
            if (path.charAt(0) == 'C'){
                path += userdir + "\\";
            } else {
                path += userdir + "/";
            }
        }
        File folder = new File(path);
        File[] files = folder.listFiles();


        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (request.getParameterMap().containsKey("delete")){
                    if (Integer.parseInt(request.getParameter("delete")) == i ){
                        files[i].delete();
                        response.sendRedirect("UserServlets.Files");
                        break;
                    }
                }
                //Ocultar log del usuario
                if (!files[i].getName().equals("UserLoggingLog.log")){
                    body += "<p>" + files[i].getName() + " </p><a href='" + "UserServlets.Files?delete="+ i + "'>Delete</a>\n";
                }
            }
        }
        // Set refresh, autoload time as 5 seconds
        response.setIntHeader("Refresh", 5);

        // Set response content type
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String title = "Your files";
        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 " +
                        "transitional//en\">\n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n"+
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                body
        );
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException
    {
        doGet(request,response);
    }
}

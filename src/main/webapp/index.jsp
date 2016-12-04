<%@ page import="java.io.File" %>
<%@ page import="java.io.FileReader" %>
<%@ page import="java.io.BufferedReader" %>
<%
    Cookie[] cookies = request.getCookies();
    String emailCookie = "";

    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("email")){
                emailCookie = cookie.getValue();
            }
        }
    }
%>

<html>
<body>
<%
    String error = "";
    if (request.getSession().getAttribute("loginError") != null){
        error = (String) request.getSession().getAttribute("loginError");
    }
%>
<%= error %>
<form action="Login" method="POST">
    Email: <input type="text" name="email" value="<%= emailCookie%>"/>
    <br />
    Password: <input type="text" name="password" />
    <input type="checkbox" name="remember"> Remember <br>
    <input type="submit" value="Submit" />
</form>

<%


    File file = new File(getServletContext().getInitParameter("file-upload") + "Logs.log");
    FileReader fileReader = new FileReader(file);
    BufferedReader bufferedReader = new BufferedReader(fileReader);

    String temp = "";
    while ((temp = bufferedReader.readLine()) != null) {
        out.println(temp + "<br>");
    }
%>
</body>
</html>

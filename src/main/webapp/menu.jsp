<%@ page import="java.io.File" %>
<%@ page import="java.io.FileReader" %>
<%@ page import="java.io.BufferedReader" %>


<html>
<head>
    <title>User Menu</title>
</head>
<body>
<a href="UserServlets.Files">Consultar archivos</a>
<a href="SessionServlets.Logout">Logout</a>

<h3>File Upload:</h3>
Select a file to upload: <br />
<form action="UserServlets.UploadServlet" method="post"
      enctype="multipart/form-data">
    <input type="file" name="file" size="50" />
    <br />
    <input type="submit" value="Upload File" />
</form>
<%
    HttpSession ses =  request.getSession();
    String userdir = (String) ses.getAttribute("email");
    int arrobaPos = 0;
    for (int i = 0; i < userdir.length(); i++) {
        if (userdir.charAt(i) == '@'){
            arrobaPos = i;
        }
    }
    userdir = userdir.substring(0,arrobaPos);

    File file = new File(getServletContext().getInitParameter("file-upload") + userdir + "\\UserLoggingLog.log");
    FileReader fileReader = new FileReader(file);
    BufferedReader bufferedReader = new BufferedReader(fileReader);

    String temp = null;
    while ((temp = bufferedReader.readLine()) != null) {
        out.println(temp + "<br>");
    }
%>
</body>
</html>

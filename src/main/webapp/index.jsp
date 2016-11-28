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
<form action="Login" method="POST">
    Email: <input type="text" name="email" value="<%= emailCookie%>"/>
    <br />
    Password: <input type="text" name="password" />
    <input type="checkbox" name="remember"> Remember <br>
    <input type="submit" value="Submit" />
</form>
</body>
</html>

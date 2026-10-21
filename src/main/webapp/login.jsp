<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>ShopSphere - Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #eaf1ff;
            margin: 0;
        }
        .navbar {
            background: #192952;
            color: white;
            padding: 20px 6%;
            font-size: 28px;
            font-weight: bold;
        }
        .login-box {
            background: white;
            width: 350px;
            margin: 70px auto;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 15px #ccd3e0;
        }
        h2 {
            text-align: center;
            color: #192952;
        }
        input {
            width: 100%;
            padding: 12px;
            margin: 10px 0;
            box-sizing: border-box;
        }
        button {
            width: 100%;
            padding: 12px;
            background: #2864e8;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
        }
        p {
            text-align: center;
        }
        a {
            color: #2864e8;
        }
    </style>
</head>
<body>
    <div class="navbar">ShopSphere</div>

    <div class="login-box">
        <h2>Login</h2>

        <form action="${pageContext.request.contextPath}/login" method="post"></form>
            <input type="email" name="email"
                   placeholder="Enter Email" required>

            <input type="password" name="password"
                   placeholder="Enter Password" required>

            <button type="submit">Login</button>
        </form>

        <p>Don't have an account?
            <a href="register.jsp">Register</a>
        </p>
        <p><a href="index.jsp">Back to Home</a></p>
    </div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>ShopSphere - Register</title>
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
        .register-box {
            background: white;
            width: 350px;
            margin: 40px auto;
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

    <div class="register-box">
        <h2>Create Account</h2>

        <form action="${pageContext.request.contextPath}/register" method="post">
    <input type="text" name="name" placeholder="Enter Name" required>

    <input type="email" name="email" placeholder="Enter Email" required>

    <input type="password" name="password" placeholder="Enter Password" required>

    <input type="text" name="mobile" placeholder="Enter Mobile Number">

    <button type="submit">Register</button>
</form>

        <p>Already have an account?
            <a href="login.jsp">Login</a>
        </p>

        <p><a href="index.jsp">Back to Home</a></p>
    </div>
</body>
</html>

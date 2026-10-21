
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ShopSphere - Online Shopping</title>

    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f5f6fa;
            color: #222;
        }

        header {
            background-color: #172554;
            color: white;
            padding: 22px 8%;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        header h1 {
            font-size: 28px;
        }

        nav a {
            color: white;
            text-decoration: none;
            margin-left: 20px;
        }

        .hero {
            text-align: center;
            padding: 70px 20px;
            background: linear-gradient(135deg, #dbeafe, #eff6ff);
        }

        .hero h2 {
            font-size: 36px;
            margin-bottom: 15px;
            color: #172554;
        }

        .hero p {
            font-size: 18px;
            margin-bottom: 25px;
        }

        .hero a {
            display: inline-block;
            background-color: #2563eb;
            color: white;
            padding: 12px 25px;
            text-decoration: none;
            border-radius: 6px;
        }

        .products {
            padding: 45px 8%;
            text-align: center;
        }

        .products h2 {
            margin-bottom: 25px;
        }

        .product-list {
            display: flex;
            justify-content: center;
            gap: 20px;
            flex-wrap: wrap;
        }

        .product {
            background-color: white;
            padding: 25px;
            border-radius: 10px;
            width: 220px;
            box-shadow: 0 2px 8px #00000015;
        }

        .product .emoji {
            font-size: 45px;
            margin-bottom: 15px;
        }

        .product h3 {
            margin-bottom: 10px;
        }

        .price {
            color: #2563eb;
            font-weight: bold;
            margin-top: 12px;
        }

        footer {
            background-color: #172554;
            color: white;
            text-align: center;
            padding: 18px;
            margin-top: 30px;
        }
    </style>
</head>

<body>

    <header>
        <h1>ShopSphere</h1>

        <nav>
            <a href="index.jsp">Home</a>
            <a href="#products">Products</a>
            <a href="login.jsp">Login</a>
            <a href="register.jsp">Register</a>
        </nav>
    </header>

    <section class="hero">
        <h2>Welcome to ShopSphere!</h2>
        <p>Your one-stop destination for online shopping.</p>
        <a href="#products">Explore Products</a>
    </section>

    <section class="products" id="products">
        <h2>Featured Categories</h2>

        <div class="product-list">

            <div class="product">
                <div class="emoji">📱</div>
                <h3>Electronics</h3>
                <p>Explore electronic products.</p>
                <p class="price">Starting at ₹999</p>
            </div>

            <div class="product">
                <div class="emoji">👕</div>
                <h3>Fashion</h3>
                <p>Discover your style.</p>
                <p class="price">Starting at ₹499</p>
            </div>

            <div class="product">
                <div class="emoji">📚</div>
                <h3>Books</h3>
                <p>Find your next favourite book.</p>
                <p class="price">Starting at ₹199</p>
            </div>

        </div>
    </section>

    <footer>
        <p>&copy; 2026 ShopSphere. All rights reserved.</p>
    </footer>

</body>
</html>

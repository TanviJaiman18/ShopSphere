# 🛍️ ShopSphere - Java E-Commerce Web Application

ShopSphere is a web-based e-commerce application developed using Java, JSP, Servlets, Maven, and MySQL. It allows users to browse products, manage their shopping cart, maintain a wishlist, and place orders through a simple web interface.

## ✨ Features

### User Features
- User Registration and Login
- Browse Products
- View Product Details
- Add Products to Cart
- Manage Wishlist
- Checkout and Order Management
- View Order History
- Manage User Profile

### Admin Features
- Manage Products
- View and Manage Users
- Access Sales Information

## 💻 Technologies Used

- **Java** - Application Logic
- **JSP** - Dynamic Web Pages
- **Servlets** - Request Handling
- **HTML & CSS** - Frontend Design
- **MySQL** - Database Management
- **Maven** - Build and Dependency Management
- **Apache Tomcat** - Web Application Server
- **Git & GitHub** - Version Control

## 📂 Project Structure

```text
ShopSphere/
├── database/
│   └── shopsphere.sql
├── src/
│   └── main/
│       ├── java/
│       ├── resources/
│       └── webapp/
│           ├── admin/
│           ├── fragments/
│           ├── WEB-INF/
│           ├── index.jsp
│           ├── products.jsp
│           ├── cart.jsp
│           ├── checkout.jsp
│           ├── login.jsp
│           └── register.jsp
├── pom.xml
├── .gitignore
└── README.md
```

## ⚙️ Prerequisites

- Java JDK
- Apache Maven
- Apache Tomcat
- MySQL Server
- MySQL Workbench (Optional)
- Visual Studio Code or IntelliJ IDEA

## 🚀 Installation and Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/TanviJaiman18/ShopSphere.git
   ```

2. Open the project folder in your IDE.

3. Start MySQL Server and import the `database/shopsphere.sql` file.

4. Configure the database connection using your local MySQL credentials.

5. Build the project using Maven:

   ```bash
   mvn clean package
   ```

6. Deploy the generated WAR file to a compatible Apache Tomcat server.

7. Start the server and access the application through your configured deployment.

## 🗄️ Database

The project includes an SQL file at `database/shopsphere.sql`, which contains the database structure and sample data for setting up the application.

## 🎯 Project Objective

The objective of ShopSphere is to demonstrate Java web application development, database integration, CRUD operations, and server-side programming using JSP, Servlets, and MySQL.

## 🔮 Future Enhancements

- Online Payment Integration
- Product Search and Filtering
- Product Reviews and Ratings
- Improved Security
- Responsive User Interface
- Email Notifications

## 👩‍💻 Author

**Tanvi Jaiman**

- GitHub: [TanviJaiman18](https://github.com/TanviJaiman18)
- Repository: [ShopSphere](https://github.com/TanviJaiman18/ShopSphere)

---

⭐ If you find this project useful, consider giving it a star!


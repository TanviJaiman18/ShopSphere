# ShopSphere — RTU Advanced Java E-Commerce Project

This is a working starter implementation of the **core web application** described in the supplied RTU guide: Java 17, Maven WAR, Jakarta Servlet/JSP, MySQL/JDBC, sessions, a role-based admin filter, a session cart, checkout, and admin product management.

## Requirements
- JDK 17 or newer
- Maven 3.9+
- MySQL 8+
- Apache Tomcat 11
- VS Code or IntelliJ/Eclipse

## 1. Import the database
Open MySQL Workbench or the MySQL terminal and run `database/shopsphere.sql`.

The script creates the database, tables, categories, and sample products.

## 2. Configure database credentials
Defaults are `root` with an empty password. If yours differs, set environment variables before starting Maven/Tomcat:
- `SHOPSPHERE_DB_URL` (optional; default is `jdbc:mysql://localhost:3306/shopsphere?useSSL=false&serverTimezone=Asia/Kolkata&allowPublicKeyRetrieval=true`)
- `SHOPSPHERE_DB_USER`
- `SHOPSPHERE_DB_PASSWORD`

Change the defaults in `src/main/java/com/shopsphere/util/DB.java` only if that is easier for your local setup. Do not commit real passwords.

## 3. Build
From the folder containing `pom.xml`, run:

```bash
mvn clean package
```

Expected output: `target/ShopSphere.war`.

If Maven is not installed, install Maven and ensure `mvn -version` works in the terminal.

## 4. Deploy to Tomcat 11
Copy `target/ShopSphere.war` to Tomcat's `webapps` folder and start Tomcat. Open:

`http://localhost:8080/ShopSphere/`

## 5. Create an admin
1. Register a normal account through the website.
2. In MySQL Workbench, run the following with your own registered email:

```sql
USE shopsphere;
UPDATE users SET role='ADMIN' WHERE email='your-email@example.com';
```

3. Log out and log back in. Admin users are redirected to `/admin/products`.

## Implemented
- Responsive product catalogue and search
- Product details
- Registration/login/logout
- SHA-256 password hashing for this student demo
- Session-based cart with quantity updates/removal
- Delivery address and demo Cash-on-Delivery checkout
- Transactional order/order-item creation and stock reduction
- Admin-only product add/hide page
- `PreparedStatement`, ServletContext listener, role filter, JSTL and JSP fragments

## Still to add for full guide coverage
The RTU guide is broader than the one-day core build. Separate remaining modules include Swing desktop admin, Socket/ServerSocket, RMI registry/service, serialization demo, JNDI and URLConnection demonstrations, internationalization, wishlist/reviews/coupons, order history/admin order management, richer categories/dashboard, diagrams, and the report. Build and verify the core flow first, then add these modules.

## Security/academic note
This is a local educational demo, not a production payment system. Payment is mocked (no gateway), the app should be tested locally, and production systems should use a slow salted password hash such as Argon2id/bcrypt, CSRF protection, stricter validation, and externalized secrets.

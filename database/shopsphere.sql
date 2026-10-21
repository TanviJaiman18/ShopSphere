CREATE DATABASE IF NOT EXISTS shopsphere CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE shopsphere;

CREATE TABLE IF NOT EXISTS users (
 user_id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100) NOT NULL,
 email VARCHAR(150) NOT NULL UNIQUE, password_hash VARCHAR(255) NOT NULL,
 mobile VARCHAR(20), role ENUM('CUSTOMER','ADMIN') NOT NULL DEFAULT 'CUSTOMER',
 status TINYINT NOT NULL DEFAULT 1, created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS categories (
 category_id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100) NOT NULL UNIQUE,
 description VARCHAR(255), status TINYINT NOT NULL DEFAULT 1
);
CREATE TABLE IF NOT EXISTS products (
 product_id INT PRIMARY KEY AUTO_INCREMENT, category_id INT NULL,
 name VARCHAR(150) NOT NULL, brand VARCHAR(100), description TEXT,
 price DECIMAL(10,2) NOT NULL, discount DECIMAL(5,2) NOT NULL DEFAULT 0,
 stock INT NOT NULL DEFAULT 0, image_url VARCHAR(500), status TINYINT NOT NULL DEFAULT 1,
 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 FOREIGN KEY (category_id) REFERENCES categories(category_id) ON DELETE SET NULL
);
CREATE TABLE IF NOT EXISTS addresses (
 address_id INT PRIMARY KEY AUTO_INCREMENT, user_id INT NOT NULL,
 address_line VARCHAR(255) NOT NULL, city VARCHAR(100) NOT NULL, state VARCHAR(100) NOT NULL,
 pincode VARCHAR(12) NOT NULL, address_type VARCHAR(30) DEFAULT 'HOME',
 FOREIGN KEY (user_id) REFERENCES users(user_id)
);
CREATE TABLE IF NOT EXISTS cart (
 cart_id INT PRIMARY KEY AUTO_INCREMENT, user_id INT NOT NULL, product_id INT NOT NULL,
 quantity INT NOT NULL DEFAULT 1, UNIQUE KEY uq_cart_user_product(user_id, product_id),
 FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
 FOREIGN KEY (product_id) REFERENCES products(product_id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS wishlist (
 wishlist_id INT PRIMARY KEY AUTO_INCREMENT, user_id INT NOT NULL, product_id INT NOT NULL,
 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, UNIQUE KEY uq_wishlist(user_id, product_id),
 FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
 FOREIGN KEY (product_id) REFERENCES products(product_id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS orders (
 order_id INT PRIMARY KEY AUTO_INCREMENT, user_id INT NOT NULL, address_id INT NULL,
 total_amount DECIMAL(10,2) NOT NULL, discount DECIMAL(10,2) NOT NULL DEFAULT 0,
 payment_method VARCHAR(40) NOT NULL DEFAULT 'COD', payment_status VARCHAR(30) NOT NULL DEFAULT 'PENDING',
 order_status VARCHAR(30) NOT NULL DEFAULT 'PLACED', created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 FOREIGN KEY (user_id) REFERENCES users(user_id), FOREIGN KEY (address_id) REFERENCES addresses(address_id) ON DELETE SET NULL
);
CREATE TABLE IF NOT EXISTS order_items (
 order_item_id INT PRIMARY KEY AUTO_INCREMENT, order_id INT NOT NULL, product_id INT NOT NULL,
 quantity INT NOT NULL, price DECIMAL(10,2) NOT NULL,
 FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
 FOREIGN KEY (product_id) REFERENCES products(product_id)
);
CREATE TABLE IF NOT EXISTS payments (
 payment_id INT PRIMARY KEY AUTO_INCREMENT, order_id INT NOT NULL, payment_method VARCHAR(40),
 transaction_reference VARCHAR(100), amount DECIMAL(10,2), payment_status VARCHAR(30),
 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (order_id) REFERENCES orders(order_id)
);
CREATE TABLE IF NOT EXISTS reviews (
 review_id INT PRIMARY KEY AUTO_INCREMENT, user_id INT NOT NULL, product_id INT NOT NULL,
 rating INT NOT NULL, review_text TEXT, created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 FOREIGN KEY (user_id) REFERENCES users(user_id), FOREIGN KEY (product_id) REFERENCES products(product_id)
);
CREATE TABLE IF NOT EXISTS coupons (
 coupon_id INT PRIMARY KEY AUTO_INCREMENT, code VARCHAR(40) NOT NULL UNIQUE,
 discount_type VARCHAR(20) NOT NULL DEFAULT 'PERCENT', discount_value DECIMAL(10,2) NOT NULL,
 minimum_order DECIMAL(10,2) NOT NULL DEFAULT 0, maximum_discount DECIMAL(10,2) NULL,
 expiry_date DATE NULL, status TINYINT NOT NULL DEFAULT 1
);
CREATE TABLE IF NOT EXISTS coupon_usage (
 usage_id INT PRIMARY KEY AUTO_INCREMENT, coupon_id INT NOT NULL, user_id INT NOT NULL,
 order_id INT NOT NULL, used_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 FOREIGN KEY (coupon_id) REFERENCES coupons(coupon_id), FOREIGN KEY (user_id) REFERENCES users(user_id),
 FOREIGN KEY (order_id) REFERENCES orders(order_id)
);

INSERT IGNORE INTO categories(name,description) VALUES
('Electronics','Smart devices and accessories'),('Fashion','Everyday essentials'),('Home','Useful home products'),('Books','Books and learning');
INSERT INTO products(category_id,name,brand,description,price,discount,stock,image_url)
SELECT c.category_id,'Wireless Headphones','SoundWave','Comfortable wireless headphones with rich sound.',2499,10,25,'https://images.unsplash.com/photo-1505740420928-5e560c06d30e'
FROM categories c WHERE c.name='Electronics' AND NOT EXISTS(SELECT 1 FROM products WHERE name='Wireless Headphones');
INSERT INTO products(category_id,name,brand,description,price,discount,stock,image_url)
SELECT c.category_id,'Smart Watch','Pulse','A smart watch for everyday activity tracking.',3999,15,18,'https://images.unsplash.com/photo-1523275335684-37898b6baf30'
FROM categories c WHERE c.name='Electronics' AND NOT EXISTS(SELECT 1 FROM products WHERE name='Smart Watch');
INSERT INTO products(category_id,name,brand,description,price,discount,stock,image_url)
SELECT c.category_id,'Everyday Backpack','UrbanCarry','A clean, practical backpack for college and travel.',1299,5,30,'https://images.unsplash.com/photo-1553062407-98eeb64c6a62'
FROM categories c WHERE c.name='Fashion' AND NOT EXISTS(SELECT 1 FROM products WHERE name='Everyday Backpack');
INSERT INTO products(category_id,name,brand,description,price,discount,stock,image_url)
SELECT c.category_id,'Desk Lamp','Luma','Minimal desk lamp for focused study sessions.',899,0,12,'https://images.unsplash.com/photo-1507473885765-e6ed057f782c'
FROM categories c WHERE c.name='Home' AND NOT EXISTS(SELECT 1 FROM products WHERE name='Desk Lamp');

-- Demo admin: create an account through Register first, then run:
-- UPDATE users SET role='ADMIN' WHERE email='your-email@example.com';

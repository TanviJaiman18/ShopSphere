package com.shopsphere.model;
import java.io.Serializable;
public class User implements Serializable {
 private int userId; private String name,email,mobile,role;
 public User() {}
 public User(int id,String name,String email,String mobile,String role){this.userId=id;this.name=name;this.email=email;this.mobile=mobile;this.role=role;}
 public int getUserId(){return userId;} public void setUserId(int v){userId=v;}
 public String getName(){return name;} public void setName(String v){name=v;}
 public String getEmail(){return email;} public void setEmail(String v){email=v;}
 public String getMobile(){return mobile;} public void setMobile(String v){mobile=v;}
 public String getRole(){return role;} public void setRole(String v){role=v;}
}

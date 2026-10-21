package com.shopsphere.util;
import java.nio.charset.StandardCharsets;
 import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
public final class Passwords {
 private Passwords(){}
 public static String hash(String raw) {
  try { MessageDigest md=MessageDigest.getInstance("SHA-256"); return "SHA256:"+Base64.getEncoder().encodeToString(md.digest(raw.getBytes(StandardCharsets.UTF_8))); }
  catch(NoSuchAlgorithmException e){throw new IllegalStateException(e);}
 }
}

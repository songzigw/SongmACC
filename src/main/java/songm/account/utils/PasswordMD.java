package songm.account.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * 
 * @author 张松
 *
 */
public class PasswordMD {
	
	public static String md5(String plainText) {
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5"); 
			md.update(plainText.getBytes()); 
			byte b[] = md.digest(); 

			int i; 

			StringBuffer buf = new StringBuffer(""); 
			for (int offset = 0; offset < b.length; offset++) { 
				i = b[offset]; 
				if(i<0) i+= 256; 
				if(i<16) 
					buf.append("0"); 
				buf.append(Integer.toHexString(i)); 
			}

			return buf.toString().substring(8, 24);
			
		} catch (NoSuchAlgorithmException e) { 
			e.printStackTrace(); 
			return null;
		}
	}
	
	@SuppressWarnings("restriction")
    public static String encode64(String str) {
		if (str == null) {
			return "";
		} else {
			String value = null;
			MessageDigest md5 = null;
			try {
				md5 = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException ex) {
			}
			sun.misc.BASE64Encoder baseEncoder = new sun.misc.BASE64Encoder();
			try {
				value = baseEncoder.encode(md5.digest(str.getBytes("utf-8")));
			} catch (Exception ex) {
			}
			return value;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(md5("123456dfsgdf"));
		System.out.println(encode64("123456"));
	}
}
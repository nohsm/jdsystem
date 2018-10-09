package jd.com.util;

import java.security.MessageDigest;
import java.security.SecureRandom;

import org.apache.commons.codec.binary.Base64;

/**
 * <pre>
 * UtilCommon.java
 * </pre>
 *
 * @ClassName   : UtilCommon.java
 * @Description : UtilCommon.java
 * @author SMJ
 * @since 2016. 1. 16.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2016. 1. 16.        SMJ                 CREATE
 * </pre>
 */
public class UtilCommon {

	/**
	 * encryptData
	 *
	 * @param data
	 * @param id
	 * @return String
	 * @throws Exception
	 */
	public static String encryptData(String data, String id) throws Exception {

		if (data == null) {
			return "";
		}

		byte[] hashValue = null; // 해쉬값

		MessageDigest md = MessageDigest.getInstance("SHA-256");

		md.reset();
		md.update(id.getBytes());

		hashValue = md.digest(data.getBytes());

		return new String(Base64.encodeBase64(hashValue));
	}

	/**
	 * temporaryPassword
	 *
	 * @param size
	 * @return String
	 */
	public static String temporaryPassword(int size) {

		StringBuffer buffer = new StringBuffer();

		SecureRandom random = new SecureRandom();

		String chars[] = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,0,1,2,3,4,5,6,7,8,9,#,!,@".split(",");

		for (int i = 0; i < size; i++) {
			buffer.append(chars[random.nextInt(chars.length)]);
		}

		return buffer.toString();
	}
}
package jd.com.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <pre>
 * UtilProperties.java
 * </pre>
 *
 * @ClassName   : UtilProperties.java
 * @Description : UtilProperties.java
 * @author SMJ
 * @since 2016. 1. 14.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2016. 1. 14.        SMJ                 CREATE
 * </pre>
 */
public class UtilProperties {
	
	/** 리소스경로 */
	private String resourcePath = "";

	/**
	 * 생성자
	 *
	 * @param resourcePath 리소스경로
	 */
	public UtilProperties(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	/**
	 * 프로퍼티 값 취득
	 * 
	 * @param key 프로퍼티 키
	 * @return String 프로퍼티 값
	 */
	public String getProperty(String key) {

		Properties prop = new Properties();

		InputStream in = null;

		try {
			in = this.getClass().getResourceAsStream(this.resourcePath);
			prop.load(in);
		} catch (Exception e) {
			e.getMessage();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return (String) prop.getProperty(key);
	}
}
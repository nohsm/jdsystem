package jd.com.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 * UtilSecurity.java
 * </pre>
 *
 * @ClassName   : UtilSecurity.java
 * @Description : UtilSecurity.java
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
@SuppressWarnings({ "unchecked", "rawtypes", "serial", "unused" })
public class UtilSecurity {

	public static HashMap uploadFileExtCheck(String fileName, String param) {

		Pattern pattern = null;
		HashMap result = new HashMap() {
			{
				put("result", "false");
				put("securitySort", "none");
				put("violationChar", "none");
			}
		};
		
		if      (param.equals("uploadExt")   ) pattern = Pattern.compile("([^\\s]+(\\.(?i)(asp|aspx|asa|cdx|cer|htr|jsp|jspx|jsw|jsv|jspf|jar|java|war|cgi|exe|inc|phtml|php|php3|php4|php5|htm|html|hta|htx|mhtm|mhtml|mht|shtml|chm)){1,})");
		else if (param.equals("uploadDetour")) pattern = Pattern.compile("(\\%00|\\%ZZ|\\%0a|\\%2E|\\%2B|\\%5C|\\%20|\\%22|\\%70|\\%3f|#|\\:\\:){1,}");
		
		Matcher matcher = pattern.matcher(fileName);
		
		if (matcher.find()) {
			result.put("result", "true");
			result.put("securitySort", "FileUpload");
			result.put("violationChar", matcher.group());
		}
		
		return result;
	}

	public static String[] convertXSSParams(String values[]) {
		
		StringBuffer strBuff = new StringBuffer();
		
		for (int i = 0; i < values.length; i++)
			if (values[i] != null) {
				for (int j = 0; j < values[i].length(); j++) {
					char c = values[i].charAt(j);
					switch (c) {
					case 39: // '\''
						strBuff.append("&#39;");
						break;

					case 34: // '"'
						strBuff.append("&quot;");
						break;

					case 58: // ':'
						strBuff.append("&#58;");
						break;

					case 59: // ';'
						strBuff.append("&#59;");
						break;

					case 40: // '('
						strBuff.append("&#40;");
						break;

					case 41: // ')'
						strBuff.append("&#41;");
						break;

					case 60: // '<'
						strBuff.append("&lt;");
						break;

					case 62: // '>'
						strBuff.append("&gt;");
						break;

					case 123: // '{'
						strBuff.append("&#123;");
						break;

					case 125: // '}'
						strBuff.append("&#125;");
						break;

					case 35: // '#'
						strBuff.append("&#35;");
						break;

					case 36: // '$'
						strBuff.append("&#36;");
						break;

					case 37: // '%'
						strBuff.append("&#37;");
						break;

					case 38: // '&'
						strBuff.append("&amp;");
						break;

					case 63: // '?'
						strBuff.append("&#63;");
						break;

					case 33: // '!'
						strBuff.append("&#33;");
						break;

					case 64: // '@'
						strBuff.append("&#64;");
						break;

					case 42: // '*'
						strBuff.append("&#42;");
						break;

					case 124: // '|'
						strBuff.append("&#124;");
						break;

					default:
						strBuff.append(c);
						break;
					}
				}

				values[i] = strBuff.toString();
			} else {
				values[i] = null;
			}
		
		return values;
	}

	public static HashMap checkXSSParams(String values) {
		
		HashMap result = new HashMap() {
			{
				put("result", "false");
				put("securitySort", "none");
				put("violationChar", "none");
			}
		};
		
		Pattern pattern = Pattern.compile("(?i)('|\"|:|;|\\(|\\)|<|>|\\[|\\]|\\{|\\}|`|=|#|\\$|%|&|\\?|!|@|\\*|\t|\\||%27|%22|%3a|%3b|%28|%29|%3c|%3e|%5b|%5d|%7b|%7d|%60|%3d|%23|%24|%25|%26|%3f|%21|%40|%2a|%09|%7c|&#x|27;|&#x22;|&#x3a;|&#x3b;|&#x28;|&#x29;|&#x3c;|&#x3e;|&#x5b;|&#x5d;|&#x7b;|&#x7d;|&#x60;|&#x3d;|&#x23;|&#x24;|&#x25;|&#x26;|&#x3f;|&#x21;|&#x40;|&#x2a;|&#x09;|&#x7c;|script|javascript|vbscript|livescript|iframe|mocha|applet|img|embed|object|marquee|qss|body|input|form|div|style|table|isindex|meta|http-equiv|xss|href){1,}");
		Matcher matcher = pattern.matcher(values);
		
		if (matcher.find()) {
			result.put("result"       , "true"		   );
			result.put("securitySort" , "XSS"		   );
			result.put("violationChar", matcher.group());
		}
		
		return result;
	}

	public static HashMap checkSQLInjectionParams(String values) {
		
		HashMap result = new HashMap() {
			{
				put("result"       , "false");
				put("securitySort" , "none" );
				put("violationChar", "none" );
			}
		};
		
		Pattern pattern = Pattern.compile("(?i)('|\"|:|;|\\(|\\)|<|>|\\[|\\]|\\{|\\}|`|=|#|\\$|%|&|\\?|!|@|\\*|\t|\\||%27|%22|%3a|%3b|%28|%29|%3c|%3e|%5b|%5d|%7b|%7d|%60|%3d|%23|%24|%25|%26|%3f|%21|%40|%2a|%09|%7c|&#x|27;|&#x22;|&#x3a;|&#x3b;|&#x28;|&#x29;|&#x3c;|&#x3e;|&#x5b;|&#x5d;|&#x7b;|&#x7d;|&#x60;|&#x3d;|&#x23;|&#x24;|&#x25;|&#x26;|&#x3f;|&#x21;|&#x40;|&#x2a;|&#x09;|&#x7c;|select|union|order by|where|join|create|drop|update|alter|from|and|or|asc|delay|return|instance|version|colnum|declare|then|if|else|end|exec|all|into|null|super|schema|case|case|desc|waitfor|table|having|banner|rownum|varchar|sleep\\(\\)|chr\\(\\)|ascii\\(\\)|substr\\(\\)|bitand\\(\\)|lower\\(\\)|concat\\(\\)|count\\(\\)|distinct\\(\\)|database\\(\\)|end\\(\\)|asciistr\\(\\)|instr\\(\\)|length\\(\\)|tochar\\(\\)){1,}");
		Matcher matcher = pattern.matcher(values);
		
		if (matcher.find()) {
			result.put("result"		  , "true"		   );
			result.put("securitySort" , "SQL Injection");
			result.put("violationChar", matcher.group());
		}
		
		return result;
	}

	public static HashMap checkDownloadParams(String values) throws UnsupportedEncodingException {
		
		Pattern checkPattern = Pattern.compile("(%\\p{Alnum}{1}\\p{Alnum}{1}){1,}");
		Matcher m = checkPattern.matcher(values);
		
		if (m.find()) {
			values = URLDecoder.decode(values, "utf-8");
			values = URLDecoder.decode(values, "utf-8");
		}
		
		HashMap result = new HashMap() {
			{
				put("result"	   , "false");
				put("securitySort" , "none" );
				put("violationChar", "none" );
			}
		};
		
		Pattern pattern = Pattern.compile("(\\.\\.\\\\|\\.\\.\\/|\\.\\.\\.\\.\\/\\/|\\.\\.\\.\\/\\.\\/){1,}");
		Matcher matcher = pattern.matcher(values);
		
		if (matcher.find()) {
			result.put("result"		  , "true"		   );
			result.put("securitySort" , "File Download");
			result.put("violationChar", matcher.group());
		}
		
		return result;
	}

	public static final String UPLOAD_EXT = "uploadExt";
	public static final String UPLOAD_DETOUR = "uploadDetour";
	public static final String DOWNLOAD_EXT = "downloadExt";
	private static final String uploadRegExp = "([^\\s]+(\\.(?i)(asp|aspx|asa|cdx|cer|htr|jsp|jspx|jsw|jsv|jspf|jar|java|war|cgi|exe|inc|phtml|php|php3|php4|php5|htm|html|hta|htx|mhtm|mhtml|mht|shtml|chm)){1,})";
	private static final String uploadDetour = "(\\%00|\\%ZZ|\\%0a|\\%2E|\\%2B|\\%5C|\\%20|\\%22|\\%70|\\%3f|#|\\:\\:){1,}";
	private static final String downloadRegExp = "(\\.\\.\\\\|\\.\\.\\/|\\.\\.\\.\\.\\/\\/|\\.\\.\\.\\/\\.\\/){1,}";
	private static final String xssRegExp = "(?i)('|\"|:|;|\\(|\\)|<|>|\\[|\\]|\\{|\\}|`|=|#|\\$|%|&|\\?|!|@|\\*|\t|\\||%27|%22|%3a|%3b|%28|%29|%3c|%3e|%5b|%5d|%7b|%7d|%60|%3d|%23|%24|%25|%26|%3f|%21|%40|%2a|%09|%7c|&#x|27;|&#x22;|&#x3a;|&#x3b;|&#x28;|&#x29;|&#x3c;|&#x3e;|&#x5b;|&#x5d;|&#x7b;|&#x7d;|&#x60;|&#x3d;|&#x23;|&#x24;|&#x25;|&#x26;|&#x3f;|&#x21;|&#x40;|&#x2a;|&#x09;|&#x7c;|script|javascript|vbscript|livescript|iframe|mocha|applet|img|embed|object|marquee|qss|body|input|form|div|style|table|isindex|meta|http-equiv|xss|href){1,}";
	private static final String sqlInjcRegExp = "(?i)('|\"|:|;|\\(|\\)|<|>|\\[|\\]|\\{|\\}|`|=|#|\\$|%|&|\\?|!|@|\\*|\t|\\||%27|%22|%3a|%3b|%28|%29|%3c|%3e|%5b|%5d|%7b|%7d|%60|%3d|%23|%24|%25|%26|%3f|%21|%40|%2a|%09|%7c|&#x|27;|&#x22;|&#x3a;|&#x3b;|&#x28;|&#x29;|&#x3c;|&#x3e;|&#x5b;|&#x5d;|&#x7b;|&#x7d;|&#x60;|&#x3d;|&#x23;|&#x24;|&#x25;|&#x26;|&#x3f;|&#x21;|&#x40;|&#x2a;|&#x09;|&#x7c;|select|union|order by|where|join|create|drop|update|alter|from|and|or|asc|delay|return|instance|version|colnum|declare|then|if|else|end|exec|all|into|null|super|schema|case|case|desc|waitfor|table|having|banner|rownum|varchar|sleep\\(\\)|chr\\(\\)|ascii\\(\\)|substr\\(\\)|bitand\\(\\)|lower\\(\\)|concat\\(\\)|count\\(\\)|distinct\\(\\)|database\\(\\)|end\\(\\)|asciistr\\(\\)|instr\\(\\)|length\\(\\)|tochar\\(\\)){1,}";
}
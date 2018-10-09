package jd.com.base;

import java.text.MessageFormat;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import jd.com.Constant;
import jd.com.util.UtilProperties;
import jd.com.util.UtilSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

/**
 * <pre>
 * BaseController.java
 * </pre>
 *
 * @ClassName   : BaseController.java
 * @Description : BaseController.java
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
public class BaseController {
	
	/** LOG */
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	/** PROPERTIES */
	private final UtilProperties properties = new UtilProperties(Constant.propertiesBasePath);
	 
	/** SESSION */
	private final UtilSession session = new UtilSession();

	/** Message Source */
	@Inject
	private MessageSource messageSource;
	
	/**
	 * setSession
	 *
	 * @param request
	 * @param sessionObj
	 */
	public void setSession(HttpServletRequest request, Object sessionObj){
		session.setSession(request, getProperty("session.name"), getProperty("session.ip"), sessionObj);
	}
		 
	/**
	 * getSession
	 *
	 * @param request
	 * @return Object Session
	 */
	public Object getSession(HttpServletRequest request){
		return session.getSession(request, getProperty("session.name"));
	}
		 
	/**
	 * removeSession
	 *
	 * @param request
	 */
	public void removeSession(HttpServletRequest request){
		session.removeSession(request, getProperty("session.name"), getProperty("session.ip"));
	}
	
	/**
	 * getProperty
	 *
	 * @param key
	 * @return String Value
	 */
	public String getProperty(String key) {
		return properties.getProperty(key);
	}
	
	/**
	 * getMessage
	 * 
	 * @param code
	 * @return String Message
	 */
	public String getMessage(String code) {
		return messageSource.getMessage(code, null, null);
	}

	/**
	 * getMessage
	 * 
	 * @param code
	 * @param locale
	 * @return String Message
	 */
	public String getMessage(String code, Locale locale) {
		return messageSource.getMessage(code, null, locale);
	}
	
	/**
	 * getMessage
	 * 
	 * @param code
	 * @param arguments
	 * @return String Message
	 */
	public String getMessage(String code, Object ... arguments) {
		return MessageFormat.format(messageSource.getMessage(code, null, null), arguments);
	}

	/**
	 * getMessage
	 *
	 * @param code
	 * @param locale
	 * @param arguments
	 * @return String Message
	 */
	public String getMessage(String code, Locale locale, Object ... arguments) {
		return MessageFormat.format(messageSource.getMessage(code, null, locale), arguments);
	}
}
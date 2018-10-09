package jd.com.base;

import java.text.MessageFormat;
import java.util.Locale;

import javax.inject.Inject;

import jd.com.Constant;
import jd.com.util.UtilProperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

/**
 * <pre>
 * BaseService.java
 * </pre>
 *
 * @ClassName   : BaseService.java
 * @Description : BaseService.java
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
public class BaseService {

	/** LOG */
	protected final Logger log = LoggerFactory.getLogger(getClass());

	/** PROPERTIES */
	private final UtilProperties properties = new UtilProperties(Constant.propertiesBasePath);
	
	/** Message Source */
	@Inject
	private MessageSource messageSource;
	
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
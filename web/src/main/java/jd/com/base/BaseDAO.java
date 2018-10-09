package jd.com.base;

import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import jd.com.Constant;
import jd.com.util.UtilProperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * <pre>
 * BaseDAO.java
 * </pre>
 *
 * @ClassName   : BaseDAO.java
 * @Description : BaseDAO.java
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
public class BaseDAO {
	
	/** LOG */
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	/** PROPERTIES */
	private final UtilProperties properties = new UtilProperties(Constant.propertiesBasePath);

	/** Message Source */
	@Inject
	private MessageSource messageSource;

	/** SqlMapClient */
	@Autowired
	private SqlMapClient sql;
	
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
	 * selectSql
	 * 
	 * @param queryId
	 * @param parameterObject
	 * @return Object
	 * @throws Exception
	 */
	public Object selectSql(String queryId, Object parameterObject) throws Exception {
		return sql.queryForObject(queryId, parameterObject);
	}
	
	/**
	 * selectCountSql
	 *
	 * @param queryId
	 * @param parameterObject
	 * @return Object
	 * @throws Exception
	 */
	public Object selectCountSql(String queryId, Object parameterObject) throws Exception {
		  
		Object obj= sql.queryForObject(queryId, parameterObject);
  
  		if(parameterObject instanceof BaseVO){
	  		((BaseVO) parameterObject).setTotalRecordCount((Integer)obj);
  		}
  
  		return obj;
	}

	/**
	 * listSql
	 * 
	 * @param queryId
	 * @param parameterObject
	 * @return Object
	 * @throws Exception
	 */
	public Object listSql(String queryId, Object parameterObject) throws Exception {
		return sql.queryForList(queryId, parameterObject);
	}

	/**
	 * listWithPagingSql
	 * 
	 * @param queryId
	 * @param parameterObject
	 * @param pageIndex
	 * @param pageSize
	 * @return Object
	 * @throws Exception
	 */
	public Object listWithPagingSql(String queryId, Object parameterObject, int pageIndex, int pageSize) throws Exception {

		int skip = pageIndex * pageSize;
		int max  = pageIndex * pageSize + pageSize;

		return sql.queryForList(queryId, parameterObject, skip, max);
	}

	/**
	 * insertSql
	 * 
	 * @param queryId
	 * @param parameterObject
	 * @return Object
	 * @throws Exception
	 */
	public Object insertSql(String queryId, Object parameterObject) throws Exception {
		return sql.insert(queryId, parameterObject);
	}

	/**
	 * updateSql
	 * 
	 * @param queryId
	 * @param parameterObject
	 * @return int
	 * @throws Exception
	 */
	public int updateSql(String queryId, Object parameterObject) throws Exception {
		return sql.update(queryId, parameterObject);
	}

	/**
	 * deleteSql
	 * 
	 * @param queryId
	 * @param parameterObject
	 * @return int
	 * @throws Exception
	 */
	public int deleteSql(String queryId, Object parameterObject) throws Exception {
		return sql.delete(queryId, parameterObject);
	}

	/**
	 * batch
	 * 
	 * @param queryId
	 * @param list
	 * @return int
	 * @throws Exception
	 */
	public int batch(String queryId, List<?> list) throws Exception {

		sql.startBatch();

		for (int i = 0; i < list.size(); i++) {
			sql.insert(queryId, list.get(i));
		}

		sql.executeBatch();

		return list.size();
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
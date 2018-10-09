package jd.web.com.service;

import java.util.List;

import jd.web.com.vo.CommonVO;
import jd.web.com.vo.EmailVO;
import jd.web.com.vo.SmsVO;

/**
 * <pre>
 * CommonService.java
 * </pre>
 *
 * @ClassName   : CommonService.java
 * @Description : CommonService.java
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
public interface CommonService {
	
	/**
	 * sendMail
	 *
	 * @param vo
	 * @return String
	 * @throws Exception
	 */
	boolean sendMail(EmailVO vo) throws Exception;
	
	/**
	 * sendSms
	 *
	 * @param paramVO
	 * @throws Exception
	 */
	boolean sendSms(SmsVO paramVO) throws Exception;
	
	/**
	 * insert
	 *
	 * @param vo
	 * @return String
	 * @throws Exception
	 */
	String insert(CommonVO vo) throws Exception;
	
	/**
	 * insertCheckTransaction
	 *
	 * @param vo
	 * @return String
	 * @throws Exception
	 */
	String insertCheckTransaction(CommonVO vo) throws Exception;

	/**
	 * list
	 *
	 * @param paramVO
	 * @return List<CommonVO>
	 * @throws Exception
	 */
	List<CommonVO> list(CommonVO paramVO) throws Exception;

	/**
	 * count
	 *
	 * @param paramVO
	 * @return int
	 * @throws Exception
	 */
	int count(CommonVO paramVO) throws Exception;
}
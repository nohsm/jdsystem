package jd.web.com.vo;

import jd.com.base.BaseVO;

/**
 * <pre>
 * EmailVO.java
 * </pre>
 *
 * @ClassName   : EmailVO.java
 * @Description : EmailVO.java
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
public class EmailVO extends BaseVO {
    
    private String[] emailAddr;
    private String subject;
    private String text;

	public String[] getEmailAddr() {
		return emailAddr;
	}
	public void setEmailAddr(String[] emailAddr) {
		this.emailAddr = emailAddr;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
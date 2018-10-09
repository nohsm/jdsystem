package jd.web.com.vo;

import jd.com.base.BaseVO;

/**
 * <pre>
 * SmsVO.java
 * </pre>
 *
 * @ClassName   : SmsVO.java
 * @Description : SmsVO.java
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
public class SmsVO extends BaseVO {
    
    private String[] receiver;
    private String sender;
    private String msg;
    
	public String[] getReceiver() {
		return receiver;
	}
	public void setReceiver(String[] receiver) {
		this.receiver = receiver;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
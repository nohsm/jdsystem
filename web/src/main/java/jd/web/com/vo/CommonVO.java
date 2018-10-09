package jd.web.com.vo;

import jd.com.base.BaseVO;

/**
 * <pre>
 * CommonVO.java
 * </pre>
 *
 * @ClassName   : CommonVO.java
 * @Description : CommonVO.java
 * @author SMJ
 * @since 2016. 1. 12.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2016. 1. 12.        SMJ                 CREATE
 * </pre>
 */
public class CommonVO extends BaseVO {
	
    private String id;
    private String password;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
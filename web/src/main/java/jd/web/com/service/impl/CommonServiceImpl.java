package jd.web.com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import jd.com.base.BaseService;
import jd.web.com.dao.CommonDAO;
import jd.web.com.service.CommonService;
import jd.web.com.vo.CommonVO;
import jd.web.com.vo.EmailVO;
import jd.web.com.vo.SmsVO;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service("commonService")
public class CommonServiceImpl extends BaseService implements CommonService {

	/** commonDAO */
	@Resource(name = "commonDAO")
	private CommonDAO commonDAO;
	
	/** mailSender */
	@Resource(name = "mailSender")
	private MailSender mailSender;

	/*
	 * @see jd.web.com.service.CommonService#sendMail(jd.web.com.vo.EmailVO)
	 */
	@Override
	public boolean sendMail(EmailVO vo) throws Exception {
		
	  	SimpleMailMessage msg = new SimpleMailMessage();
	  	msg.setTo     (vo.getEmailAddr());
	  	msg.setSubject(vo.getSubject()  );
	  	msg.setText   (vo.getText()     );
	   
	  	try {
	      	mailSender.send(msg);
	      	return true;
	  	} catch (MailException e) {
	  		return false;
	  	}
	}
	
	@Override
	public boolean sendSms(SmsVO paramVO) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
	/*
	 * @see jd.web.com.service.CommonService#insert(jd.web.com.vo.CommonVO)
	 */
	@Override
	public String insert(CommonVO vo) throws Exception {
		return commonDAO.insert(vo);
	}
	
	/*
	 * @see jd.web.com.service.CommonService#insertCheckTransaction(jd.web.com.vo.CommonVO)
	 */
	@Override
	public String insertCheckTransaction(CommonVO vo) throws Exception {
	  
		commonDAO.insert(vo);
	  
		CommonVO paramVO2 = new CommonVO();
		paramVO2.setId("1");
		paramVO2.setPassword("1");
		return commonDAO.insert(paramVO2);
	}

	/*
	 * @see jd.web.com.service.CommonService#list(jd.web.com.vo.CommonVO)
	 */
	@Override
	public List<CommonVO> list(CommonVO paramVO) throws Exception {
		return commonDAO.list(paramVO);
	}

	/*
	 * @see jd.web.com.service.CommonService#count(jd.web.com.vo.CommonVO)
	 */
	@Override
	public int count(CommonVO paramVO) throws Exception {
		return commonDAO.count(paramVO);
	}
}
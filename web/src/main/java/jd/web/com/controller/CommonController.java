package jd.web.com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jd.com.base.BaseController;
import jd.com.util.UtilCommon;
import jd.web.com.service.CommonService;
import jd.web.com.vo.CommonVO;
import jd.web.com.vo.EmailVO;
import jd.web.com.vo.SmsVO;

import org.springframework.http.MediaType;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <pre>
 * CommonController.java
 * </pre>
 *
 * @ClassName   : CommonController.java
 * @Description : CommonController.java
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
@Controller
public class CommonController extends BaseController {

	/** commonService */
	@Resource(name = "commonService")
	private CommonService commonService;
		
	@RequestMapping(value = "/loginForm.do", method = RequestMethod.GET)
	public String loginForm(HttpServletRequest request, HttpServletResponse response, Locale locale, Model model, Device device, @ModelAttribute("rParam") CommonVO paramVO) throws Exception {
		
		paramVO.setMobile(device.isMobile());
		
		return device.isMobile() ? "loginMobile" : "login";
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object login(HttpServletRequest request, HttpServletResponse response, Locale locale, Model model, CommonVO paramVO) throws Exception {

		setSession(request, paramVO);

		Map<String, String> map = new HashMap<String, String>();

		map.put("msg", getMessage("msg.login", locale));

		return map;
	}

	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response, Locale locale, Model model, CommonVO paramVO) throws Exception {
		return "login";
	}

	@RequestMapping(value = "/main.do", method = RequestMethod.GET)
	public String main(HttpServletRequest request, HttpServletResponse response, Locale locale, Model model, CommonVO paramVO) throws Exception {

		removeSession(request);

		return "main";
	}

	@RequestMapping(value = "/insert.do", method = RequestMethod.GET)
	public String insert(HttpServletRequest request, HttpServletResponse response, Locale locale, Model model, CommonVO paramVO) throws Exception {

		commonService.insert(paramVO);

		return "main";
	}
	
	@RequestMapping(value = "/checkTransaction.do", method = RequestMethod.GET)
	public String checkTransaction(HttpServletRequest request, HttpServletResponse response, Locale locale, Model model, CommonVO paramVO) throws Exception {

		commonService.insertCheckTransaction(paramVO);

		return "main";
	}
	
	@RequestMapping(value = "/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response, Locale locale, Model model, @ModelAttribute("rParam") CommonVO paramVO) throws Exception {
	  
		List<CommonVO> list = commonService.list(paramVO);
	  	int count = commonService.count(paramVO);
	  
	  	model.addAttribute("list" , list );
	  	model.addAttribute("count", count);
	  
	  	return "list";
	}
	
	@RequestMapping(value = "/sendMail.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object sendMail(HttpServletRequest request, HttpServletResponse response, Locale locale, Model model, EmailVO paramVO) throws Exception {

		Map<String, String> map = new HashMap<String, String>();
		
		paramVO.setEmailAddr(new String[]{"moojin0929@naver.com", "sm8670@naver.com"});
		paramVO.setSubject  (getMessage("mail.subject", locale         						   ));
		paramVO.setText     (getMessage("mail.text"   , locale, UtilCommon.temporaryPassword(8)));
		
		// Send Email
		if(commonService.sendMail(paramVO)){
			map.put("msg", getMessage("msg.mail.ok"  , locale));
		}else{
			map.put("msg", getMessage("msg.mail.fail", locale));
		}
	  
	  	return map;
	}
	
	@RequestMapping(value = "/sendSMS.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void sendMail(HttpServletRequest request, HttpServletResponse response, Locale locale, Model model, SmsVO paramVO) throws Exception {

		paramVO.setReceiver(new String[]{"0101234567"});
		paramVO.setSender  ("0102222222");
		paramVO.setMsg     (getMessage("mail.text"   , locale, UtilCommon.temporaryPassword(8)));
		
		commonService.sendSms(paramVO);
	}
}
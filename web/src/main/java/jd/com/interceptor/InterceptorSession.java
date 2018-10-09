package jd.com.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * <pre>
 * InterceptorSession.java
 * </pre>
 *
 * @ClassName   : InterceptorSession.java
 * @Description : InterceptorSession.java
 * @author SMJ
 * @since 2016. 1. 11.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2016. 1. 11.        SMJ                 CREATE
 * </pre>
 */
public class InterceptorSession extends HandlerInterceptorAdapter{
    
    /** LOG */
	private final Logger log = LoggerFactory.getLogger(getClass());
	/** SESSION NAME */
	private String sessionName;
	/** REDIRECT URL */
	private String redirectUrl;
    
    public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	/*
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {        
        
    	log.debug("InterceptorSession : preHandel");
    	
    	if(sessionName == null || redirectUrl == null) {return true;}
        
        Object sessionObject = request.getSession().getAttribute(sessionName);
        
        if(sessionObject == null){
        	ModelAndView modelAndView = new ModelAndView((new StringBuilder()).append("redirect:").append(redirectUrl).toString());
	        modelAndView.addObject("exception", new Exception("Session expired. Relogin please."));
	        throw new ModelAndViewDefiningException(modelAndView);
        }else{
        	return super.preHandle(request, response, handler);
        }
    }
    
    /*
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
    
    /*
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
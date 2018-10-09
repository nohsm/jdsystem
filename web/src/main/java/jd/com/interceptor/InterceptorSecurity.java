package jd.com.interceptor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jd.com.util.UtilSecurity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * <pre>
 * InterceptorSecurity.java
 * </pre>
 *
 * @ClassName   : InterceptorSecurity.java
 * @Description : InterceptorSecurity.java
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
public class InterceptorSecurity extends HandlerInterceptorAdapter{
    
    /** LOG */
	private final Logger log = LoggerFactory.getLogger(getClass());
    
    /*
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {        
        
    	log.info("InterceptorSecurity preHandle");
        
        HashMap<?,?> securityResult = null;
        
        Map params = request.getParameterMap();
        
        boolean result = false;
       
        String values[] = null;
       
        for(Iterator<?> i$ = params.keySet().iterator(); i$.hasNext();){
    	   
        	String key = (String)i$.next();
           
        	values = (String[])params.get(key);
           
        	if(values != null){
               
        		int i = 0;
               
        		do{
        			if(i >= values.length) break;
        			
        			securityResult = UtilSecurity.checkDownloadParams(values[i]);
        			
        			if("true".equals(securityResult.get("result"))) {
        				result = true;
        				break;
        			}
        			i++;
        		} while(true);
               
        		if(result) throw new Exception((new StringBuilder()).append("Web Security Violation :  ").append((String)securityResult.get("securitySort")).append(", Violation Char:: ' ").append((String)securityResult.get("violationChar")).append("'").toString());
        	}
        }

        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
       
        if(multipartResolver.isMultipart(request)){
    	   
        	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
           
        	for(Iterator<?> iter = multipartRequest.getFileNames(); iter.hasNext();){
        		String uploadFileName = (String)iter.next();
        		MultipartFile file = multipartRequest.getFile(uploadFileName);
        		String fileName = file.getOriginalFilename();
        		securityResult = UtilSecurity.uploadFileExtCheck(fileName, "uploadExt");
        		if("true".equals(securityResult.get("result"))) throw new Exception((new StringBuilder()).append("Web Security Violation ").append((String)securityResult.get("securitySort")).append(", Violation Char:: ' ").append((String)securityResult.get("violationChar")).append("'").toString());
        		securityResult = UtilSecurity.uploadFileExtCheck(fileName, "uploadDetour");
        		if("true".equals(securityResult.get("result"))) throw new Exception((new StringBuilder()).append("Web Security Violation : ").append((String)securityResult.get("securitySort")).append(", Violation Char:: ' ").append((String)securityResult.get("violationChar")).append("'").toString());
        	}

        	Iterator<?> i$ = params.keySet().iterator();
           
        	do{
        		if(!i$.hasNext()){break;}
                   
        		String key = (String)i$.next();
               
        		values = (String[])params.get(key);
               
        		if(values != null){
            	   
        			int i = 0;
                   
        			do{
        				if(i >= values.length) break;
                       
        				securityResult = UtilSecurity.checkDownloadParams(values[i]);
                       
        				if("true".equals(securityResult.get("result"))){
        					result = true;
        					break;
        				}
        				i++;
        			} while(true);
                   
        			if(result) throw new Exception((new StringBuilder()).append("Web Security Violation : ").append((String)securityResult.get("securitySort")).append(", Violation Char:: ' ").append((String)securityResult.get("violationChar")).append("'").toString());
                   
        			values = UtilSecurity.convertXSSParams(values);
        			
        			params.put(key, values);
        		}
        	} while(true);
        }
       
        return super.preHandle(request, response, handler);
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
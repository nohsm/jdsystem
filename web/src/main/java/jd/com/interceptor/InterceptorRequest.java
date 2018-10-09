package jd.com.interceptor;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * <pre>
 * InterceptorRequest.java
 * </pre>
 *
 * @ClassName   : InterceptorRequest.java
 * @Description : InterceptorRequest.java
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
public class InterceptorRequest extends HandlerInterceptorAdapter{
    
    /** LOG */
	private final Logger log = LoggerFactory.getLogger(getClass());
    
    /*
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {        
        
    	log.debug("## Request URI : " + request.getRequestURI());
        
        Map<?,?> params = request.getParameterMap();
        
        String values[] = null;
        
        for(Iterator<?> i$ = params.keySet().iterator(); i$.hasNext();){
        	
            String key = (String)i$.next();
            
            values = (String[])params.get(key);
            
            if(values != null){
            	log.debug("     key : {}, value : {}", key, values);
            }
        }

        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        
        if(multipartResolver.isMultipart(request)){
            
        	Iterator<?> i$ = params.keySet().iterator();
            
        	do{
                if(!i$.hasNext()){
                	break;
                }
                    
                String key = (String)i$.next();
                
                values = (String[])params.get(key);
                
                if(values != null){
                	log.debug("     key : {}, value : {}", key, values);
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
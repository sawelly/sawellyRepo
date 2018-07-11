package com.sawelly.fpog.interceptor;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 * @author sawelly
 *
 */

public interface Preparable {
	/**
	 * Prepares the action.
	 */
	boolean prepare(HttpServletRequest request,
                    HttpServletResponse response);
	void afterCompletion(HttpServletRequest request,
                         HttpServletResponse response, Object handler, Exception ex);
	void postHandle(HttpServletRequest request,
                    HttpServletResponse response, Object handler, ModelAndView modelAndView);
}

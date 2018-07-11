package com.sawelly.fpog.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 实现preparable Time: 2012-10-25 下午02:49:42
 * 
 * Company: www.teacher.com.cn
 */
@Component
public class PreparableInterceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		try {
			if (handler instanceof Preparable) {
				((Preparable) handler).afterCompletion(request, response,
						handler, ex); // 调用afterCompletion
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
		try {
			if (handler instanceof Preparable) {
				((Preparable) handler).postHandle(request, response, handler,
						modelAndView); // 调用postHandle
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
		try {
			if (handler instanceof HandlerMethod) {
				HandlerMethod handlerMethod = (HandlerMethod) handler;
				if (handlerMethod.getBean() instanceof Preparable) {
					return ((Preparable) handlerMethod.getBean()).prepare(
							request, response); // 调用prepare
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return true;

	}
}

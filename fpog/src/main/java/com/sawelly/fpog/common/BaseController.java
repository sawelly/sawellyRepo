package com.sawelly.fpog.common;

import com.sawelly.fpog.common.context.ProjectContext;
import com.sawelly.fpog.common.context.ProjectContextUtils;
import com.sawelly.fpog.interceptor.Preparable;
import com.sawelly.fpog.utils.JsonUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


public abstract class BaseController implements Preparable {

	public HttpServletResponse httpResponse;

	public static final String REQUEST_KEY_REQUEST = "_request";// 当前请求
	public static final String REQUEST_KEY_REQUEST_MAP = "_reqMap";// 当前请求

	public static final String  CONTEXT_PATH="/";



	public boolean prepare(HttpServletRequest request,HttpServletResponse response) {
		httpResponse = response;

		return true;
	}



	public void afterCompletion(HttpServletRequest request,
								HttpServletResponse response, Object handler, Exception ex) {
		// TODO Auto-generated method stub

	}

	public void postHandle(HttpServletRequest request,
						   HttpServletResponse response, Object handler,
						   ModelAndView modelAndView) {
		// TODO Auto-generated method stub

	}

	private Map<String, Object> wrapAllParameter(HttpServletRequest request) {
		Enumeration<?> parameterNames = request.getParameterNames();
		if (null == parameterNames) {
			return null;
		}
		Map<String, Object> reqParameters = new HashMap<String, Object>();
		while (parameterNames.hasMoreElements()) {
			Object element = parameterNames.nextElement();
			reqParameters.put(element.toString(),
					request.getParameter(element.toString()));
		}
		return reqParameters;
	}

//	protected abstract ProjectContext initProjectContext(HttpServletRequest request);

	protected ProjectContext getProjectContext() {
		return ProjectContextUtils.getProjectContext();
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	/**
	 * 提示信息
	 *
	 * @param value
	 */
	protected void addMessage(String type, String value){
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("status", type);
		info.put("message", value);
		String jsonStr = JsonUtils.mapToJson(info);
		Cookie cookie = null;
		try {
			cookie = new Cookie("tp_sys_message", URLEncoder.encode(jsonStr, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		cookie.setPath("/");
		httpResponse.addCookie(cookie);
	}

	protected void addSuccessMessage(String value){
		addMessage("success", value);
	}

	protected void addErrorMessage(String value){
		addMessage("error", value);
	}

}

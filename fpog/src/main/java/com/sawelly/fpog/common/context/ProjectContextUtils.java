package com.sawelly.fpog.common.context;

import com.sawelly.fpog.entity.Project;
import com.sawelly.fpog.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ProjectContextUtils {

	public static final String REQUEST_KEY_USER = "_user";// 当前用户
	public static final String REQUEST_KEY_REQUEST = "_request";// 当前请求
	public static final String REQUEST_KEY_REQUEST_MAP = "_reqMap";// 当前请求
	public static final String REQUEST_KEY_PROJECT_CODE = "_projectCode";// 项目名称
	public static final String REQUEST_KEY_IS_LOGIN = "_isLogin";// 是否登录状态

	public static final ThreadLocal<ProjectContext> projectContextLocal = new ThreadLocal<ProjectContext>();

	private static Logger log = LoggerFactory.getLogger(ProjectContextUtils.class);

	public static void setProjectContext(ProjectContext projectContext) {
		projectContextLocal.set(projectContext);
	}

	public static ProjectContext getProjectContext() {
		return projectContextLocal.get();
	}

	public static User getUser() {
		return getProjectContext().getUser();
	}

	public static Project getProject() {
		return getProjectContext().getProject();
	}


}

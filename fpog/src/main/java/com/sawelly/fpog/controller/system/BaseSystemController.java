package com.sawelly.fpog.controller.system;

import com.sawelly.fpog.common.BaseController;
import com.sawelly.fpog.common.context.ProjectContext;
import com.sawelly.fpog.common.context.ProjectContextUtils;
import com.sawelly.fpog.entity.Project;
import com.sawelly.fpog.entity.User;
import com.sawelly.fpog.service.ProjectService;
import com.sawelly.utils.UrlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

public class BaseSystemController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(BaseSystemController.class);

	@Autowired
	private ProjectService projectService;


	@Override
	public boolean prepare(HttpServletRequest request, HttpServletResponse response) {
		boolean flag = super.prepare(request, response);
		if (!flag) {
			return flag;
		}
		User u = (User) request.getSession().getAttribute("SYSTEM_USER");
		logger.info("loginu ==== "+u);
		if (u == null) {
			sendLogin(request, response);
			return false;
		}
		ProjectContext projectContext = new ProjectContext();
		ProjectContextUtils.setProjectContext(projectContext);

		ProjectContextUtils.getProjectContext().setUserId(u.getId());
		ProjectContextUtils.getProjectContext().setUser(u);
		Project project = projectService.selectByUserId(u.getId());
		if(project != null){
			ProjectContextUtils.getProjectContext().setProject(project);
			ProjectContextUtils.getProjectContext().setProjectCode(project.getProjectCode());
			request.setAttribute(ProjectContextUtils.REQUEST_KEY_PROJECT_CODE,projectContext.getProject().getProjectCode());
		}
		request.setAttribute(ProjectContextUtils.REQUEST_KEY_USER,request.getSession().getAttribute("SYSTEM_USER"));
		request.setAttribute(ProjectContextUtils.REQUEST_KEY_REQUEST, request);
		return flag;
	}

//	@Override
//	protected ProjectContext initProjectContext(HttpServletRequest request) {
//		ProjectContext projectContext = new ProjectContext();
//		return projectContext;
//	}


	protected boolean sendLogin(HttpServletRequest request, HttpServletResponse response) {

		try {
			String reqUri = UrlUtils.buildRequestUrl(request);
			reqUri = URLEncoder.encode(reqUri,"utf8");
			String url = request.getContextPath()+ reqUri;
			response.sendRedirect(request.getContextPath()+"/login?backUrl="+url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}

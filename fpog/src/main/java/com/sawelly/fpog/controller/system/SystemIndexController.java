package com.sawelly.fpog.controller.system;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class SystemIndexController extends BaseSystemController{

	private Logger logger = LoggerFactory.getLogger(SystemIndexController.class);

	@RequestMapping("/")
	public String index(Model model, HttpServletRequest request) {
		System.out.println(request.getSession().getAttribute("_user"));
		logger.info("info==============================");
		logger.error("error==============================");
		logger.debug("debug==============================");
		return "system/index";
	}

}

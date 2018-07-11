package com.sawelly.fpog.controller.interfacee.smallprogramm;

import com.sawelly.fpog.entity.Project;
import com.sawelly.fpog.service.ProjectService;
import com.sawelly.fpog.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/sp/project")
public class SpProjectController {

    private Logger log = LoggerFactory.getLogger(SpProjectController.class);

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/getProjectInfo")
    @ResponseBody
    public String getProjectInfo(Model model, HttpServletRequest request, String code) {
        Project project = projectService.selectByProjectCode(code);
        String jsonStr = JsonUtils.objToJson(project);
        return jsonStr;
    }

}

package com.sawelly.fpog.controller.system;

import com.sawelly.fpog.common.page.Page;
import com.sawelly.fpog.entity.Project;
import com.sawelly.fpog.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/project")
public class ProjectController extends BaseSystemController {

    private Logger log = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/index")
    public String index(Model model, Project project, Page page) {
        page = projectService.selectProjectPage(project, page);
        model.addAttribute("page", page);
        model.addAttribute("project", project);
        return "project/project_list";
    }

    @RequestMapping("/edit")
    public String add(Model model, Project project) {
        int projectIdId = 0;
        if (project.getId() != null) {
            projectIdId = project.getId();
            project = projectService.selectById(project.getId());
        }
        log.info("projectIdIdprojectIdId===" + projectIdId);
        model.addAttribute("project", project);
        model.addAttribute("projectIdId", projectIdId);
        return "project/project_edit";
    }

    @RequestMapping("/save")
    public String save(Model model, Project project, HttpServletResponse httpResponse) {
        projectService.save(project);
        addSuccessMessage("保存成功");
        return "redirect:index";
    }

    @RequestMapping("/del/{id}")
    public String del(@PathVariable("id") Integer id) {
        projectService.del(id);
        addSuccessMessage("删除成功");
        return "redirect:/project/index";
    }
}

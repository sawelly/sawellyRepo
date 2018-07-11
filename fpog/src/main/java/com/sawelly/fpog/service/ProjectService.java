package com.sawelly.fpog.service;

import com.sawelly.fpog.common.page.FastMybatisPageDao;
import com.sawelly.fpog.common.page.Page;
import com.sawelly.fpog.entity.Project;
import com.sawelly.fpog.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService extends BaseService{

    @Autowired
    private ProjectMapper projectMapper;

    public Page selectProjectPage(Project project,Page page) {
        return fastMybatisPageDao.query(project, page.getCurrentPage(),
                page.getPageSize(), "selectProjectPage");
    }


    public Project selectById(Integer id) {
        return projectMapper.selectByPrimaryKey(id);
    }

    public Integer save(Project project) {
        if (project.getId() != null ){
            projectMapper.updateByPrimaryKeySelective(project);
        }else{
            projectMapper.insert(project);
        }
        return project.getId();
    }

    public void del(Integer id) {
        projectMapper.deleteByPrimaryKey(id);
    }

    public Project selectByProjectCode(String projectCode) {
        Project project = projectMapper.selectByProjectCode(projectCode);
        return  project;
    }

    public Project selectByUserId(Integer userId) {
        return  projectMapper.selectByUserId(userId);
    }
}

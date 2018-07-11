package com.sawelly.fpog.common.context;

import com.sawelly.fpog.entity.Project;
import com.sawelly.fpog.entity.User;


public class ProjectContext {
    private User user;
    private Integer userId;
    private String projectCode;
    private Project project;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getUserId() {
        if(user != null){
            this.userId = user.getId();
        }
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getProjectCode() {
        if (project != null) {
            return project.getProjectCode();
        }
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}

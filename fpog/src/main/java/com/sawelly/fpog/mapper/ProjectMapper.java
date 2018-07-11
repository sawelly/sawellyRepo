package com.sawelly.fpog.mapper;

import com.sawelly.fpog.entity.Project;

public interface ProjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);

    Project selectByProjectCode(String projectCode);

    Project selectByUserId(Integer userId);
}
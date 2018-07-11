package com.sawelly.fpog.service;

import com.sawelly.fpog.common.page.FastMybatisPageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;


public  class BaseService {
	@Autowired(required=true)
	protected FastMybatisPageDao fastMybatisPageDao;
	@Autowired
	protected ApplicationContext applicationContext;
}

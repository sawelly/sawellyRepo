package com.sawelly.fpog.service;

import com.sawelly.fpog.common.context.ProjectContextUtils;
import com.sawelly.fpog.common.page.Page;
import com.sawelly.fpog.entity.Article;
import com.sawelly.fpog.entity.ArticleExample;
import com.sawelly.fpog.entity.Project;
import com.sawelly.fpog.mapper.ArticleMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService extends BaseService{

    @Autowired
    private ArticleMapper articleMapper;


    public Page selectArticlePage(Article article, Page page) {
        return fastMybatisPageDao.query(article, page.getCurrentPage(),
                page.getPageSize(), "selectArticlePage");
    }

    public Article selectById(Integer id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    public Integer save(Article article) {
        if (article.getId() != null ){
            articleMapper.updateByPrimaryKeySelective(article);
        }else{
            article.setProjectCode(ProjectContextUtils.getProjectContext().getProjectCode());
            articleMapper.insert(article);
        }
        return article.getId();
    }


    public void del(Integer id) {
        articleMapper.deleteByPrimaryKey(id);
    }

    public List<Article> selectByTypeCode(String projectCode, String typeCode) {
        ArticleExample articleExample = new ArticleExample();
        articleExample.setOrderByClause("create_date desc");
        articleExample.createCriteria().andProjectCodeEqualTo(projectCode)
                .andTypeCodeEqualTo(typeCode);
        return articleMapper.selectByExampleWithBLOBs(articleExample);
    }

    public List<Article> selectByTypeCodeLimit(String projectCode, String typeCode, String count) {
        Integer limit = 0;
        if(StringUtils.isNotBlank(count))limit = Integer.parseInt(count);
        return articleMapper.selectByTypeCodeLimit(projectCode,typeCode,limit);
    }
}

package com.sawelly.fpog.service;

import com.sawelly.fpog.common.context.ProjectContextUtils;
import com.sawelly.fpog.common.page.Page;
import com.sawelly.fpog.entity.ArticleType;
import com.sawelly.fpog.entity.ArticleTypeExample;
import com.sawelly.fpog.mapper.ArticleTypeMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleTypeService extends BaseService {

    @Autowired
    private ArticleTypeMapper articleTypeMapper;


    public Page selectArticleTypePage(ArticleType articleType, Page page) {
        return fastMybatisPageDao.query(articleType, page.getCurrentPage(),
                page.getPageSize(), "selectArticleTypePage");
    }

    public ArticleType selectById(Integer id) {
        return articleTypeMapper.selectByPrimaryKey(id);
    }

    public Integer save(ArticleType articleType) {
        if (articleType.getId() != null) {
            articleTypeMapper.updateByPrimaryKeySelective(articleType);
        } else {
            articleType.setTypeCode(generateTypeCode());
            articleType.setProjectCode(ProjectContextUtils.getProjectContext().getProjectCode());
            articleTypeMapper.insert(articleType);
        }
        return articleType.getId();
    }

    /**
     * typeCode 生成规则：projectCode_序号
     *
     * @return
     */
    private String generateTypeCode() {
        String typeCode = "";
        String projectCode = ProjectContextUtils.getProjectContext().getProjectCode();
        ArticleType articleType = articleTypeMapper.selectArticleTypeMaxCode(projectCode);
        if (articleType != null && StringUtils.isNotBlank(articleType.getTypeCode())) {
            typeCode = articleType.getTypeCode();
            String num = typeCode.substring(typeCode.indexOf("_") + 1, typeCode.length());
            int numi = Integer.parseInt(num) + 1;
            typeCode = projectCode + "_" + numi;
        } else {
            typeCode = projectCode + "_1";
        }
        return typeCode;
    }


    public void del(Integer id) {
        articleTypeMapper.deleteByPrimaryKey(id);
    }

    public List<ArticleType> selectAll() {
        ArticleTypeExample articleTypeExample = new ArticleTypeExample();
        articleTypeExample.createCriteria().andProjectCodeEqualTo(ProjectContextUtils.getProjectContext().getProjectCode());
        return articleTypeMapper.selectByExample(articleTypeExample);
    }

    public ArticleType selectByTypeCode(String typeCode) {
        ArticleTypeExample example = new ArticleTypeExample();
        example.createCriteria()
                .andTypeCodeEqualTo(typeCode)
                .andProjectCodeEqualTo(ProjectContextUtils.getProjectContext().getProjectCode());
        List<ArticleType> lst = articleTypeMapper.selectByExample(example);
        return lst.size()==0?null:lst.get(0);
    }
}

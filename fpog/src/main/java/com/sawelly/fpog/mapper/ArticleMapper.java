package com.sawelly.fpog.mapper;

import com.sawelly.fpog.entity.Article;
import com.sawelly.fpog.entity.ArticleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ArticleMapper {
    long countByExample(ArticleExample example);

    int deleteByExample(ArticleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    List<Article> selectByExampleWithBLOBsWithRowbounds(ArticleExample example, RowBounds rowBounds);

    List<Article> selectByExampleWithBLOBs(ArticleExample example);

    List<Article> selectByExampleWithRowbounds(ArticleExample example, RowBounds rowBounds);

    List<Article> selectByExample(ArticleExample example);

    Article selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Article record, @Param("example") ArticleExample example);

    int updateByExampleWithBLOBs(@Param("record") Article record, @Param("example") ArticleExample example);

    int updateByExample(@Param("record") Article record, @Param("example") ArticleExample example);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

    List<Article> selectByTypeCodeLimit(@Param("projectCode") String projectCode, @Param("typeCode")String typeCode, @Param("limit")Integer limit);
}
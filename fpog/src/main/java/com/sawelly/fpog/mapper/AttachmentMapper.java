package com.sawelly.fpog.mapper;

import com.sawelly.fpog.entity.Attachment;
import com.sawelly.fpog.entity.AttachmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface AttachmentMapper {
    long countByExample(AttachmentExample example);

    int deleteByExample(AttachmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Attachment record);

    int insertSelective(Attachment record);

    List<Attachment> selectByExampleWithRowbounds(AttachmentExample example, RowBounds rowBounds);

    List<Attachment> selectByExample(AttachmentExample example);

    Attachment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Attachment record, @Param("example") AttachmentExample example);

    int updateByExample(@Param("record") Attachment record, @Param("example") AttachmentExample example);

    int updateByPrimaryKeySelective(Attachment record);

    int updateByPrimaryKey(Attachment record);
}
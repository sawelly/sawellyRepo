package com.sawelly.fpog.mapper;

import com.sawelly.fpog.entity.ProductType;
import com.sawelly.fpog.entity.ProductTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ProductTypeMapper {
    long countByExample(ProductTypeExample example);

    int deleteByExample(ProductTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductType record);

    int insertSelective(ProductType record);

    List<ProductType> selectByExampleWithRowbounds(ProductTypeExample example, RowBounds rowBounds);

    List<ProductType> selectByExample(ProductTypeExample example);

    ProductType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductType record, @Param("example") ProductTypeExample example);

    int updateByExample(@Param("record") ProductType record, @Param("example") ProductTypeExample example);

    int updateByPrimaryKeySelective(ProductType record);

    int updateByPrimaryKey(ProductType record);

    List<ProductType> selectAll(String projectCode);

    ProductType selectMaxCode(String projectCode);
}
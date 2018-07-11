package com.sawelly.fpog.service;

import com.sawelly.fpog.common.context.ProjectContextUtils;
import com.sawelly.fpog.common.page.Page;
import com.sawelly.fpog.entity.ProductType;
import com.sawelly.fpog.entity.ProductTypeExample;
import com.sawelly.fpog.mapper.ProductTypeMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeService extends BaseService {

    @Autowired
    private ProductTypeMapper productTypeMapper;


    public Page selectProductTypePage(ProductType productType, Page page) {
        return fastMybatisPageDao.query(productType, page.getCurrentPage(),
                page.getPageSize(), "selectProductTypePage");
    }

    public ProductType selectById(Integer id) {
        return productTypeMapper.selectByPrimaryKey(id);
    }

    public Integer save(ProductType productType) {
        if (productType.getId() != null) {
            productTypeMapper.updateByPrimaryKeySelective(productType);
        } else {
            productType.setTypeCode(generateTypeCode());
            productType.setProjectCode(ProjectContextUtils.getProjectContext().getProjectCode());
            productTypeMapper.insert(productType);
        }
        return productType.getId();
    }

    /**
     * typeCode 生成规则：projectCode_序号
     *
     * @return
     */
    private String generateTypeCode() {
        String typeCode = "";
        String projectCode = ProjectContextUtils.getProjectContext().getProjectCode();
        ProductType productType = productTypeMapper.selectMaxCode(projectCode);
        if (productType != null && StringUtils.isNotBlank(productType.getTypeCode())) {
            typeCode = productType.getTypeCode();
            String num = typeCode.substring(typeCode.indexOf("_") + 1, typeCode.length());
            int numi = Integer.parseInt(num) + 1;
            typeCode = projectCode + "_" + numi;
        } else {
            typeCode = projectCode + "_1";
        }
        return typeCode;
    }


    public void del(Integer id) {
        productTypeMapper.deleteByPrimaryKey(id);
    }

    public List<ProductType> selectAll() {
        return productTypeMapper.selectAll(ProjectContextUtils.getProjectContext().getProjectCode());
    }

    public ProductType selectByTypeCode(String typeCode) {
        ProductTypeExample example = new ProductTypeExample();
        example.createCriteria()
                .andTypeCodeEqualTo(typeCode)
                .andProjectCodeEqualTo(ProjectContextUtils.getProjectContext().getProjectCode());
        List<ProductType> lst = productTypeMapper.selectByExample(example);
        return lst.size() == 0 ? null : lst.get(0);
    }

    public List<ProductType> selectByRemark(String projectCode, String remark) {
        ProductTypeExample productTypeExample = new ProductTypeExample();
        productTypeExample.setOrderByClause(" create_date asc ");
        productTypeExample.createCriteria().andProjectCodeEqualTo(projectCode).andRemarkEqualTo(remark);
        return productTypeMapper.selectByExample(productTypeExample);
    }
}

package com.sawelly.fpog.service;

import com.sawelly.fpog.common.context.ProjectContextUtils;
import com.sawelly.fpog.common.page.Page;
import com.sawelly.fpog.entity.Attachment;
import com.sawelly.fpog.entity.Product;
import com.sawelly.fpog.entity.ProductExample;
import com.sawelly.fpog.mapper.ProductMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService extends BaseService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private AttachmentService attachmentService;


    public Page selectProductPage(Product product, Page page) {
        return fastMybatisPageDao.query(product, page.getCurrentPage(),
                page.getPageSize(), "selectProductPage");
    }

    public Product selectById(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }

    public Integer save(Product product) {
        if (product.getId() != null) {
            String fileids = product.getFileids();
            if (StringUtils.isNotBlank(fileids)) {
                String ids[] = fileids.split(",");
                StringBuffer stringBuffer = new StringBuffer();
                for (String fid : ids) {
                    Attachment attachment = attachmentService.selectById(Integer.parseInt(fid));
                    if (attachment != null) {
                        stringBuffer.append(fid);
                        stringBuffer.append(",");
                    }
                }
                if (stringBuffer.length() > 0) {
                    product.setFileids(stringBuffer.toString().substring(0, stringBuffer.toString().length() - 1));
                } else {
                    product.setFileids("");
                }

            }
            productMapper.updateByPrimaryKeySelective(product);
        } else {
            product.setProjectCode(ProjectContextUtils.getProjectContext().getProjectCode());
            productMapper.insert(product);
        }
        return product.getId();
    }


    public void del(Integer id) {
        productMapper.deleteByPrimaryKey(id);
    }

    public List<Product> selectByTypeCode(String projectCode, String typeCode) {
        ProductExample productExample = new ProductExample();
        productExample.createCriteria().andProjectCodeEqualTo(projectCode).andTypeCodeEqualTo(typeCode);
        return productMapper.selectByExample(productExample);
    }

    /**
     * 根据类别remark属性获取产品
     * @param projectCode
     * @param remark
     * @return
     */
    public List<Product> selectByRemark(String projectCode, String remark) {
        return productMapper.selectByRemark(projectCode,remark);
    }

    public void update(Product product) {
        productMapper.updateByPrimaryKey(product);
    }
}

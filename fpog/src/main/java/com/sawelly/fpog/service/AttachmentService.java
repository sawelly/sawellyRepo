package com.sawelly.fpog.service;

import com.sawelly.fpog.common.CommonConst;
import com.sawelly.fpog.common.context.ProjectContextUtils;
import com.sawelly.fpog.entity.Attachment;
import com.sawelly.fpog.entity.AttachmentExample;
import com.sawelly.fpog.mapper.AttachmentMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Transformer;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class AttachmentService extends BaseService {

    private static Logger log = LoggerFactory.getLogger(AttachmentService.class);

    @Autowired
    private AttachmentMapper attachmentMapper;

    public Attachment selectById(Integer id) {
        return attachmentMapper.selectByPrimaryKey(id);
    }

    public Attachment save(MultipartFile file) {
        if (file != null) {
            String savename = new Date().getTime() + file.getOriginalFilename();
            String u = ProjectContextUtils.getProjectContext().getProjectCode();
            File filedir = new File(CommonConst.uploadRootDir+ProjectContextUtils.getProjectContext().getProjectCode());
            if (!filedir.exists()) {
                filedir.mkdirs();
            }
            File localFile = new File(CommonConst.uploadRootDir+ProjectContextUtils.getProjectContext().getProjectCode() + "/" + savename);
            try {
                String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1, file.getOriginalFilename().length());
                file.transferTo(localFile);
                long size =  file.getSize();
                return saveAttachment(file.getOriginalFilename(), savename, CommonConst.ATTACHMENT_TYPE_IMG, new Long(file.getSize()).intValue(), suffix, ProjectContextUtils.getProjectContext().getProjectCode());
            } catch (IOException e) {
                log.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return null;
    }

    public Attachment saveAttachment(String filename, String savename, int type, int size, String suffix, String filepath) {
        Attachment attachment = new Attachment();
        attachment.setFilename(filename);
        attachment.setSavename(savename);
        attachment.setType(type);
        attachment.setSuffix(suffix);
        attachment.setFilepath(filepath);
        attachment.setSize(size);
        attachment.setProjectCode(ProjectContextUtils.getProjectContext().getProjectCode());
        attachmentMapper.insert(attachment);
        return attachment;
    }

    public void deleteFile(Integer id) {
        attachmentMapper.deleteByPrimaryKey(id);
    }

    public List<Attachment> selectByIds(String fileids) {
        if(StringUtils.isNotBlank(fileids)){
            AttachmentExample attachmentExample = new AttachmentExample();
            AttachmentExample.Criteria criteria = attachmentExample.createCriteria();
            List<String> ids = Arrays.asList(fileids.split(","));
            List<Integer> idi = new ArrayList<Integer>();
            for (String id : ids){
                idi.add(Integer.parseInt(id));
            }
            criteria.andIdIn(idi);
            return attachmentMapper.selectByExample(attachmentExample);
        }
        return null;
    }
}

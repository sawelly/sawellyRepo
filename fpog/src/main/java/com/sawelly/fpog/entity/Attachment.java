package com.sawelly.fpog.entity;

import java.io.Serializable;
import java.util.Date;

public class Attachment extends BaseCommonEntity {
    private Integer id;

    private String projectCode;

    private String filename;

    private String savename;

    private Integer size;

    private Integer type;

    private String filepath;

    private String suffix;

    private Integer creatorId;

    private Date createDate;

    private Integer modifierId;

    private Date modifyDate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode == null ? null : projectCode.trim();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public String getSavename() {
        return savename;
    }

    public void setSavename(String savename) {
        this.savename = savename == null ? null : savename.trim();
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath == null ? null : filepath.trim();
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix == null ? null : suffix.trim();
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getModifierId() {
        return modifierId;
    }

    public void setModifierId(Integer modifierId) {
        this.modifierId = modifierId;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", projectCode=").append(projectCode);
        sb.append(", filename=").append(filename);
        sb.append(", savename=").append(savename);
        sb.append(", size=").append(size);
        sb.append(", type=").append(type);
        sb.append(", filepath=").append(filepath);
        sb.append(", suffix=").append(suffix);
        sb.append(", creatorId=").append(creatorId);
        sb.append(", createDate=").append(createDate);
        sb.append(", modifierId=").append(modifierId);
        sb.append(", modifyDate=").append(modifyDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
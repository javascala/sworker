package com.sworker.entity;

import javax.persistence.*;

import java.io.Serializable;

/**
 * Created by zhangjin on 2014/8/27.
 */
@Entity
@Table(name = "sw_attachment")
public class SwAttachmentEntity implements Serializable{
	
	private SwFileinfoEntity swFileinfoEntity;
	
    private Long id;
    private Long fileid;
    private String name;
    private String suffix;
    private Integer type;
    private String folder;
    private Long businessid;
    private String url;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.TABLE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    @OneToOne(optional=false)
    @JoinColumn(name="FILEID",referencedColumnName = "FILEID", insertable=false, updatable=false)
    public SwFileinfoEntity getSwFileinfoEntity() {
		return swFileinfoEntity;
	}

	public void setSwFileinfoEntity(SwFileinfoEntity swFileinfoEntity) {
		this.swFileinfoEntity = swFileinfoEntity;
	}
	
	@Basic
    @Column(name = "FILEID")
    public Long getFileid() {
        return fileid;
    }

    public void setFileid(Long fileid) {
        this.fileid = fileid;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "SUFFIX")
    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Basic
    @Column(name = "TYPE")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "FOLDER")
    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    @Basic
    @Column(name = "BUSINESSID")
    public Long getBusinessid() {
        return businessid;
    }

    public void setBusinessid(Long businessid) {
        this.businessid = businessid;
    }

    @Basic
    @Column(name = "URL")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        SwAttachmentEntity that = (SwAttachmentEntity) o;
//
//        if (businessid != null ? !businessid.equals(that.businessid) : that.businessid != null) return false;
//        if (fileid != null ? !fileid.equals(that.fileid) : that.fileid != null) return false;
//        if (folder != null ? !folder.equals(that.folder) : that.folder != null) return false;
//        if (id != null ? !id.equals(that.id) : that.id != null) return false;
//        if (name != null ? !name.equals(that.name) : that.name != null) return false;
//        if (suffix != null ? !suffix.equals(that.suffix) : that.suffix != null) return false;
//        if (type != null ? !type.equals(that.type) : that.type != null) return false;
//        if (url != null ? !url.equals(that.url) : that.url != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = id != null ? id.hashCode() : 0;
//        result = 31 * result + (fileid != null ? fileid.hashCode() : 0);
//        result = 31 * result + (name != null ? name.hashCode() : 0);
//        result = 31 * result + (suffix != null ? suffix.hashCode() : 0);
//        result = 31 * result + (type != null ? type.hashCode() : 0);
//        result = 31 * result + (folder != null ? folder.hashCode() : 0);
//        result = 31 * result + (businessid != null ? businessid.hashCode() : 0);
//        result = 31 * result + (url != null ? url.hashCode() : 0);
//        return result;
//    }
}

package com.sworker.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Created by zhangjin on 2014/9/5.
 */
@Entity
@javax.persistence.Table(name = "sw_enterprise_info")
public class SwEnterpriseInfoEntity implements Serializable {
 
	private static final long serialVersionUID = 1L;
	
	private Long enterpiseid;
	
	private Long point;
	
	private Long totalpoint;
	private Long freezepoint;
	private Long growthvalue;
	
	private List<SwExpiredpointEntity> expiredpoint;
	

	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="enterprise")
	public List<SwExpiredpointEntity> getExpiredpoint() {
		return expiredpoint;
	}

	public void setExpiredpoint(List<SwExpiredpointEntity> expiredpoint) {
		this.expiredpoint = expiredpoint;
	}

	@Id
    @javax.persistence.Column(name = "ENTERPISEID")
    @GeneratedValue(strategy = GenerationType.TABLE)
    public Long getEnterpiseid() {
        return enterpiseid;
    }

    public void setEnterpiseid(Long enterpiseid) {
        this.enterpiseid = enterpiseid;
    }

    /**后添加字段*/
    @Basic
	@javax.persistence.Column(name="POINT")
    public Long getPoint() {
		return point;
	}

	public void setPoint(Long point) {
		this.point = point;
	}
	@Basic
	@javax.persistence.Column(name="TOTALPOINT")
	public Long getTotalpoint() {
		return totalpoint;
	}

	public void setTotalpoint(Long totalpoint) {
		this.totalpoint = totalpoint;
	}
	
	@Basic
	@javax.persistence.Column(name="FREEZEPOINT")
	public Long getFreezepoint() {
		return freezepoint;
	}

	public void setFreezepoint(Long freezepoint) {
		this.freezepoint = freezepoint;
	}

	@Basic
	@javax.persistence.Column(name="GROWTHVALUE")
	public Long getGrowthvalue() {
		return growthvalue;
	}

	public void setGrowthvalue(Long growthvalue) {
		this.growthvalue = growthvalue;
	}

    
    
    
    
    private String enterpisename;

    @Basic
    @javax.persistence.Column(name = "ENTERPISENAME")
    public String getEnterpisename() {
        return enterpisename;
    }

    public void setEnterpisename(String enterpisename) {
        this.enterpisename = enterpisename;
    }

    private String logourl;

    @Basic
    @javax.persistence.Column(name = "LOGOURL")
    public String getLogourl() {
        return logourl;
    }

    public void setLogourl(String logourl) {
        this.logourl = logourl;
    }

    private String shortname;

    @Basic
    @javax.persistence.Column(name = "SHORTNAME")
    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    private String location;

    @Basic
    @javax.persistence.Column(name = "LOCATION")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private String industry;

    @Basic
    @javax.persistence.Column(name = "INDUSTRY")
    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    private String homgimageurl;

    @Basic
    @javax.persistence.Column(name = "HOMGIMAGEURL")
    public String getHomgimageurl() {
        return homgimageurl;
    }

    public void setHomgimageurl(String homgimageurl) {
        this.homgimageurl = homgimageurl;
    }

    private Integer status;

    @Basic
    @javax.persistence.Column(name = "STATUS")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private Integer level;

    @Basic
    @javax.persistence.Column(name = "LEVEL")
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    private String version;

    @Basic
    @javax.persistence.Column(name = "VERSION")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    private Long maxusers;

    @Basic
    @javax.persistence.Column(name = "MAXUSERS")
    public Long getMaxusers() {
        return maxusers;
    }

    public void setMaxusers(Long maxusers) {
        this.maxusers = maxusers;
    }

    private Long usageusers;

    @Basic
    @javax.persistence.Column(name = "USAGEUSERS")
    public Long getUsageusers() {
        return usageusers;
    }

    public void setUsageusers(Long usageusers) {
        this.usageusers = usageusers;
    }

    private Long maxsmsamount;

    @Basic
    @javax.persistence.Column(name = "MAXSMSAMOUNT")
    public Long getMaxsmsamount() {
        return maxsmsamount;
    }

    public void setMaxsmsamount(Long maxsmsamount) {
        this.maxsmsamount = maxsmsamount;
    }

    private Long usagesmsamount;

    @Basic
    @javax.persistence.Column(name = "USAGESMSAMOUNT")
    public Long getUsagesmsamount() {
        return usagesmsamount;
    }

    public void setUsagesmsamount(Long usagesmsamount) {
        this.usagesmsamount = usagesmsamount;
    }

    private Long maxspacemount;

    @Basic
    @javax.persistence.Column(name = "MAXSPACEMOUNT")
    public Long getMaxspacemount() {
        return maxspacemount;
    }

    public void setMaxspacemount(Long maxspacemount) {
        this.maxspacemount = maxspacemount;
    }

    private Long usagespacemount;

    @Basic
    @javax.persistence.Column(name = "USAGESPACEMOUNT")
    public Long getUsagespacemount() {
        return usagespacemount;
    }

    public void setUsagespacemount(Long usagespacemount) {
        this.usagespacemount = usagespacemount;
    }

    private Long maxmailamount;

    @Basic
    @javax.persistence.Column(name = "MAXMAILAMOUNT")
    public Long getMaxmailamount() {
        return maxmailamount;
    }

    public void setMaxmailamount(Long maxmailamount) {
        this.maxmailamount = maxmailamount;
    }

    private Long usagemailamount;

    @Basic
    @javax.persistence.Column(name = "USAGEMAILAMOUNT")
    public Long getUsagemailamount() {
        return usagemailamount;
    }

    public void setUsagemailamount(Long usagemailamount) {
        this.usagemailamount = usagemailamount;
    }

    private Long maxwechat;

    @Basic
    @javax.persistence.Column(name = "MAXWECHAT")
    public Long getMaxwechat() {
        return maxwechat;
    }

    public void setMaxwechat(Long maxwechat) {
        this.maxwechat = maxwechat;
    }

    private Long usewechat;

    @Basic
    @javax.persistence.Column(name = "USEWECHAT")
    public Long getUsewechat() {
        return usewechat;
    }

    public void setUsewechat(Long usewechat) {
        this.usewechat = usewechat;
    }

    private String creator;

    @Basic
    @javax.persistence.Column(name = "CREATOR")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    private Timestamp createtime;

    @Basic
    @javax.persistence.Column(name = "CREATETIME")
    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwEnterpriseInfoEntity that = (SwEnterpriseInfoEntity) o;

        if (createtime != null ? !createtime.equals(that.createtime) : that.createtime != null) return false;
        if (creator != null ? !creator.equals(that.creator) : that.creator != null) return false;
        if (enterpiseid != null ? !enterpiseid.equals(that.enterpiseid) : that.enterpiseid != null) return false;
        if (enterpisename != null ? !enterpisename.equals(that.enterpisename) : that.enterpisename != null)
            return false;
        if (homgimageurl != null ? !homgimageurl.equals(that.homgimageurl) : that.homgimageurl != null) return false;
        if (industry != null ? !industry.equals(that.industry) : that.industry != null) return false;
        if (level != null ? !level.equals(that.level) : that.level != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (logourl != null ? !logourl.equals(that.logourl) : that.logourl != null) return false;
        if (maxmailamount != null ? !maxmailamount.equals(that.maxmailamount) : that.maxmailamount != null)
            return false;
        if (maxsmsamount != null ? !maxsmsamount.equals(that.maxsmsamount) : that.maxsmsamount != null) return false;
        if (maxspacemount != null ? !maxspacemount.equals(that.maxspacemount) : that.maxspacemount != null)
            return false;
        if (maxusers != null ? !maxusers.equals(that.maxusers) : that.maxusers != null) return false;
        if (maxwechat != null ? !maxwechat.equals(that.maxwechat) : that.maxwechat != null) return false;
        if (shortname != null ? !shortname.equals(that.shortname) : that.shortname != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (usagemailamount != null ? !usagemailamount.equals(that.usagemailamount) : that.usagemailamount != null)
            return false;
        if (usagesmsamount != null ? !usagesmsamount.equals(that.usagesmsamount) : that.usagesmsamount != null)
            return false;
        if (usagespacemount != null ? !usagespacemount.equals(that.usagespacemount) : that.usagespacemount != null)
            return false;
        if (usageusers != null ? !usageusers.equals(that.usageusers) : that.usageusers != null) return false;
        if (usewechat != null ? !usewechat.equals(that.usewechat) : that.usewechat != null) return false;
        if (version != null ? !version.equals(that.version) : that.version != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = enterpiseid != null ? enterpiseid.hashCode() : 0;
        result = 31 * result + (enterpisename != null ? enterpisename.hashCode() : 0);
        result = 31 * result + (logourl != null ? logourl.hashCode() : 0);
        result = 31 * result + (shortname != null ? shortname.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (industry != null ? industry.hashCode() : 0);
        result = 31 * result + (homgimageurl != null ? homgimageurl.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (maxusers != null ? maxusers.hashCode() : 0);
        result = 31 * result + (usageusers != null ? usageusers.hashCode() : 0);
        result = 31 * result + (maxsmsamount != null ? maxsmsamount.hashCode() : 0);
        result = 31 * result + (usagesmsamount != null ? usagesmsamount.hashCode() : 0);
        result = 31 * result + (maxspacemount != null ? maxspacemount.hashCode() : 0);
        result = 31 * result + (usagespacemount != null ? usagespacemount.hashCode() : 0);
        result = 31 * result + (maxmailamount != null ? maxmailamount.hashCode() : 0);
        result = 31 * result + (usagemailamount != null ? usagemailamount.hashCode() : 0);
        result = 31 * result + (maxwechat != null ? maxwechat.hashCode() : 0);
        result = 31 * result + (usewechat != null ? usewechat.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (createtime != null ? createtime.hashCode() : 0);
        return result;
    }
}

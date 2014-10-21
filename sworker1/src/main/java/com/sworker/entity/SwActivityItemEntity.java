package com.sworker.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhangjin on 2014/9/16.
 */
@Entity
@Table(name = "sw_activity_item")
public class SwActivityItemEntity implements Serializable{
    
	private static final long serialVersionUID = 1L;
	
	private String activityitemkey;
    private Long applicationid;
    private String itemname;
    private Integer displayorder;
    private String description;

    @Id
    @Column(name = "ACTIVITYITEMKEY")
    @GeneratedValue(strategy = GenerationType.TABLE)
    public String getActivityitemkey() {
        return activityitemkey;
    }

    public void setActivityitemkey(String activityitemkey) {
        this.activityitemkey = activityitemkey;
    }

    @Basic
    @Column(name = "APPLICATIONID")
    public Long getApplicationid() {
        return applicationid;
    }

    public void setApplicationid(Long applicationid) {
        this.applicationid = applicationid;
    }

    @Basic
    @Column(name = "ITEMNAME")
    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    @Basic
    @Column(name = "DISPLAYORDER")
    public Integer getDisplayorder() {
        return displayorder;
    }

    public void setDisplayorder(Integer displayorder) {
        this.displayorder = displayorder;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwActivityItemEntity that = (SwActivityItemEntity) o;

        if (activityitemkey != null ? !activityitemkey.equals(that.activityitemkey) : that.activityitemkey != null)
            return false;
        if (applicationid != null ? !applicationid.equals(that.applicationid) : that.applicationid != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (displayorder != null ? !displayorder.equals(that.displayorder) : that.displayorder != null) return false;
        if (itemname != null ? !itemname.equals(that.itemname) : that.itemname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = activityitemkey != null ? activityitemkey.hashCode() : 0;
        result = 31 * result + (applicationid != null ? applicationid.hashCode() : 0);
        result = 31 * result + (itemname != null ? itemname.hashCode() : 0);
        result = 31 * result + (displayorder != null ? displayorder.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}

package com.sworker.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhangjin on 2014/9/16.
 */
@Entity
@Table(name = "sw_activity_set")
public class SwActivitySetEntity implements Serializable{
    
	private static final long serialVersionUID = 1L;
	
	private Long id;
    private Long userid;
    private String itemkey;
    private Integer isreceived;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.TABLE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "USERID")
    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "ITEMKEY")
    public String getItemkey() {
        return itemkey;
    }

    public void setItemkey(String itemkey) {
        this.itemkey = itemkey;
    }

    @Basic
    @Column(name = "ISRECEIVED")
    public Integer getIsreceived() {
        return isreceived;
    }

    public void setIsreceived(Integer isreceived) {
        this.isreceived = isreceived;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwActivitySetEntity that = (SwActivitySetEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (isreceived != null ? !isreceived.equals(that.isreceived) : that.isreceived != null) return false;
        if (itemkey != null ? !itemkey.equals(that.itemkey) : that.itemkey != null) return false;
        if (userid != null ? !userid.equals(that.userid) : that.userid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userid != null ? userid.hashCode() : 0);
        result = 31 * result + (itemkey != null ? itemkey.hashCode() : 0);
        result = 31 * result + (isreceived != null ? isreceived.hashCode() : 0);
        return result;
    }
}

package com.sworker.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhangjin on 2014/9/16.
 */
@Entity
@Table(name = "sw_activity_inbox")
public class SwActivityInboxEntity implements Serializable{
  
	private static final long serialVersionUID = 1L;
	
	private Long id;
    private Long activityid;
    private Long userid;

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
    @Column(name = "ACTIVITYID")
    public Long getActivityid() {
        return activityid;
    }

    public void setActivityid(Long activityid) {
        this.activityid = activityid;
    }

    @Basic
    @Column(name = "USERID")
    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwActivityInboxEntity that = (SwActivityInboxEntity) o;

        if (activityid != null ? !activityid.equals(that.activityid) : that.activityid != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (userid != null ? !userid.equals(that.userid) : that.userid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (activityid != null ? activityid.hashCode() : 0);
        result = 31 * result + (userid != null ? userid.hashCode() : 0);
        return result;
    }
}

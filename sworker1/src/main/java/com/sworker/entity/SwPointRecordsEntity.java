package com.sworker.entity;

import javax.persistence.*;

import java.sql.Timestamp;

/**
 * Created by cuijh on 2014/9/17.
 */
@Entity
@Table(name = "sw_point_records")
public class SwPointRecordsEntity {
    private long id;
    private Long enterpriseid;
    private Integer type;
    private String des;
    //到账时间
    private Timestamp time;
    private Long point;

    
    public SwPointRecordsEntity() {
	
    }

	public SwPointRecordsEntity(Long enterpriseid, Integer type, String des,
			Timestamp time, Long point) {
		super();
		this.enterpriseid = enterpriseid;
		this.type = type;
		this.des = des;
		this.time = time;
		this.point = point;
	}

	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ENTERPRISEID")
    public Long getEnterpriseid() {
        return enterpriseid;
    }

    public void setEnterpriseid(Long enterpriseid) {
        this.enterpriseid = enterpriseid;
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
    @Column(name = "DES")
    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Basic
    @Column(name = "TIME")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "POINT")
    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwPointRecordsEntity that = (SwPointRecordsEntity) o;

        if (id != that.id) return false;
        if (des != null ? !des.equals(that.des) : that.des != null) return false;
        if (enterpriseid != null ? !enterpriseid.equals(that.enterpriseid) : that.enterpriseid != null) return false;
        if (point != null ? !point.equals(that.point) : that.point != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (enterpriseid != null ? enterpriseid.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (des != null ? des.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (point != null ? point.hashCode() : 0);
        return result;
    }
}

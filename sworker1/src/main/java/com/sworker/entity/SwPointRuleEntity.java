package com.sworker.entity;

import javax.persistence.*;

/**
 * Created by cuijh on 2014/9/17.
 */
@Entity
@Table(name = "sw_point_rule")
public class SwPointRuleEntity {
    private long id;
    private Integer type;
    private Long standard;
    private Long point;
    private Integer rank;

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
    @Column(name = "TYPE")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "STANDARD")
    public Long getStandard() {
        return standard;
    }

    public void setStandard(Long standard) {
        this.standard = standard;
    }

    @Basic
    @Column(name = "POINT")
    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    @Basic
    @Column(name = "RANK")
    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwPointRuleEntity that = (SwPointRuleEntity) o;

        if (id != that.id) return false;
        if (point != null ? !point.equals(that.point) : that.point != null) return false;
        if (rank != null ? !rank.equals(that.rank) : that.rank != null) return false;
        if (standard != null ? !standard.equals(that.standard) : that.standard != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (standard != null ? standard.hashCode() : 0);
        result = 31 * result + (point != null ? point.hashCode() : 0);
        result = 31 * result + (rank != null ? rank.hashCode() : 0);
        return result;
    }
}

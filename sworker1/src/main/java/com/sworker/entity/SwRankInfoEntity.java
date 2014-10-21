package com.sworker.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhangjin on 2014/9/11.
 */
@Entity
@Table(name = "sw_rank_info")
public class SwRankInfoEntity implements Serializable{
    private Integer rank;
    private Long pointlower;
    private String rankname;

    @Id
    @Column(name = "RANK")
    @GeneratedValue(strategy = GenerationType.TABLE)
    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Basic
    @Column(name = "POINTLOWER")
    public Long getPointlower() {
        return pointlower;
    }

    public void setPointlower(Long pointlower) {
        this.pointlower = pointlower;
    }

    @Basic
    @Column(name = "RANKNAME")
    public String getRankname() {
        return rankname;
    }

    public void setRankname(String rankname) {
        this.rankname = rankname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwRankInfoEntity that = (SwRankInfoEntity) o;

        if (pointlower != null ? !pointlower.equals(that.pointlower) : that.pointlower != null) return false;
        if (rank != null ? !rank.equals(that.rank) : that.rank != null) return false;
        if (rankname != null ? !rankname.equals(that.rankname) : that.rankname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rank != null ? rank.hashCode() : 0;
        result = 31 * result + (pointlower != null ? pointlower.hashCode() : 0);
        result = 31 * result + (rankname != null ? rankname.hashCode() : 0);
        return result;
    }
}

package com.sworker.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by wangying on 2014/9/9.
 */
@Entity
@Table(name = "sw_notice_receipt")
public class SwNoticeReceiptEntity implements Serializable {
    private long id;
    private Long announcementid;
    private Long receiptpersonid;
    private Integer receiptstatus;
    private Timestamp receipttime;
    private String receiptsource;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ANNOUNCEMENTID")
    public Long getAnnouncementid() {
        return announcementid;
    }

    public void setAnnouncementid(Long announcementid) {
        this.announcementid = announcementid;
    }

    @Basic
    @Column(name = "RECEIPTPERSONID")
    public Long getReceiptpersonid() {
        return receiptpersonid;
    }

    public void setReceiptpersonid(Long receiptpersonid) {
        this.receiptpersonid = receiptpersonid;
    }

    @Basic
    @Column(name = "RECEIPTSTATUS")
    public Integer getReceiptstatus() {
        return receiptstatus;
    }

    public void setReceiptstatus(Integer receiptstatus) {
        this.receiptstatus = receiptstatus;
    }

    @Basic
    @Column(name = "RECEIPTTIME")
    public Timestamp getReceipttime() {
        return receipttime;
    }

    public void setReceipttime(Timestamp receipttime) {
        this.receipttime = receipttime;
    }

    @Basic
    @Column(name = "RECEIPTSOURCE")
    public String getReceiptsource() {
        return receiptsource;
    }

    public void setReceiptsource(String receiptsource) {
        this.receiptsource = receiptsource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SwNoticeReceiptEntity that = (SwNoticeReceiptEntity) o;

        if (id != that.id) return false;
        if (announcementid != null ? !announcementid.equals(that.announcementid) : that.announcementid != null)
            return false;
        if (receiptpersonid != null ? !receiptpersonid.equals(that.receiptpersonid) : that.receiptpersonid != null)
            return false;
        if (receiptsource != null ? !receiptsource.equals(that.receiptsource) : that.receiptsource != null)
            return false;
        if (receiptstatus != null ? !receiptstatus.equals(that.receiptstatus) : that.receiptstatus != null)
            return false;
        if (receipttime != null ? !receipttime.equals(that.receipttime) : that.receipttime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (announcementid != null ? announcementid.hashCode() : 0);
        result = 31 * result + (receiptpersonid != null ? receiptpersonid.hashCode() : 0);
        result = 31 * result + (receiptstatus != null ? receiptstatus.hashCode() : 0);
        result = 31 * result + (receipttime != null ? receipttime.hashCode() : 0);
        result = 31 * result + (receiptsource != null ? receiptsource.hashCode() : 0);
        return result;
    }
}

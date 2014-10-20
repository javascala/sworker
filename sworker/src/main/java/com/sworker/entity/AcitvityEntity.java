package com.sworker.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhangjin on 2014/8/27.
 */
@Entity
@Table(name = "acitvity")
public class AcitvityEntity implements Serializable{
    private int id;
    private String type;
    private String owner;
    private String content;
    private String blogAttr;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.TABLE)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "owner")
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "blogAttr")
    public String getBlogAttr() {
        return blogAttr;
    }

    public void setBlogAttr(String blogAttr) {
        this.blogAttr = blogAttr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AcitvityEntity entity = (AcitvityEntity) o;

        if (id != entity.id) return false;
        if (blogAttr != null ? !blogAttr.equals(entity.blogAttr) : entity.blogAttr != null) return false;
        if (content != null ? !content.equals(entity.content) : entity.content != null) return false;
        if (owner != null ? !owner.equals(entity.owner) : entity.owner != null) return false;
        if (type != null ? !type.equals(entity.type) : entity.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (blogAttr != null ? blogAttr.hashCode() : 0);
        return result;
    }
}

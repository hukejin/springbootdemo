package com.liu.springbootdemo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Course {
    @Id
    String id;
    String coursename;
    String coursecode;
    String coursetype;
    String coursedesc;
    String courselevel;
    String pcoursecode;
    long createtime;
    long updatetime;
    String pagecode;
    String pageitemcode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getCoursecode() {
        return coursecode;
    }

    public void setCoursecode(String coursecode) {
        this.coursecode = coursecode;
    }

    public String getCoursetype() {
        return coursetype;
    }

    public void setCoursetype(String coursetype) {
        this.coursetype = coursetype;
    }

    public String getCoursedesc() {
        return coursedesc;
    }

    public void setCoursedesc(String coursedesc) {
        this.coursedesc = coursedesc;
    }

    public String getCourselevel() {
        return courselevel;
    }

    public void setCourselevel(String courselevel) {
        this.courselevel = courselevel;
    }

    public String getPcoursecode() {
        return pcoursecode;
    }

    public void setPcoursecode(String pcoursecode) {
        this.pcoursecode = pcoursecode;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    public long getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(long updatetime) {
        this.updatetime = updatetime;
    }

    public String getPagecode() {
        return pagecode;
    }

    public void setPagecode(String pagecode) {
        this.pagecode = pagecode;
    }

    public String getPageitemcode() {
        return pageitemcode;
    }

    public void setPageitemcode(String pageitemcode) {
        this.pageitemcode = pageitemcode;
    }
}

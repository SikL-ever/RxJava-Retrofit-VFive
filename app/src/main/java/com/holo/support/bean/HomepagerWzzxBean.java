package com.holo.support.bean;

public class HomepagerWzzxBean {

    public String departmentName;
    public int id;
    public String pic;
    public int rank;
    public int textcolor = 0xff999999;

    public HomepagerWzzxBean(String departmentName, int id, String pic, int rank, int textcolor) {
        this.departmentName = departmentName;
        this.id = id;
        this.pic = pic;
        this.rank = rank;
        this.textcolor = textcolor;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getTextcolor() {
        return textcolor;
    }

    public void setTextcolor(int textcolor) {
        this.textcolor = textcolor;
    }
}

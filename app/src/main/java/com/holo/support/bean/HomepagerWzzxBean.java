package com.holo.support.bean;

public class HomepagerWzzxBean {

    public String departmentName;
    public int id;
    public String pic;
    public int rank;
    public String textcolor ="#f2f2f2";
    public String viewcolor="#f2f2f2";

    public HomepagerWzzxBean(String departmentName, int id, String pic, int rank, String textcolor, String viewcolor) {
        this.departmentName = departmentName;
        this.id = id;
        this.pic = pic;
        this.rank = rank;
        this.textcolor = textcolor;
        this.viewcolor = viewcolor;
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

    public String getTextcolor() {
        return textcolor;
    }

    public void setTextcolor(String textcolor) {
        this.textcolor = textcolor;
    }

    public String getViewcolor() {
        return viewcolor;
    }

    public void setViewcolor(String viewcolor) {
        this.viewcolor = viewcolor;
    }
}

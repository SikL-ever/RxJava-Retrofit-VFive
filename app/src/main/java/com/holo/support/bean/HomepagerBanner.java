package com.holo.support.bean;

public class HomepagerBanner {
    private String imageUrl;
    private String jumpUrl;
    private int rank;
    private String title;

    @Override
    public String toString() {
        return "HomepagerBanner{" +
                "imageUrl='" + imageUrl + '\'' +
                ", jumpUrl='" + jumpUrl + '\'' +
                ", rank=" + rank +
                ", title='" + title + '\'' +
                '}';
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

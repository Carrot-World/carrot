package com.carrot.domain;

public class HartVO {
    private int item_post_id;
    private String user_id;
    private int cnt;

    public HartVO() {
    }

    public HartVO(int item_post_id, String user_id) {
        this.item_post_id = item_post_id;
        this.user_id = user_id;
    }

    public int getItem_post_id() {
        return item_post_id;
    }

    public void setItem_post_id(int item_post_id) {
        this.item_post_id = item_post_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    @Override
    public String toString() {
        return "HartVO{" +
                "item_post_id=" + item_post_id +
                ", user_id='" + user_id + '\'' +
                ", current_cnt=" + cnt +
                '}';
    }
}

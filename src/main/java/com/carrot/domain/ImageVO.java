package com.carrot.domain;

public class ImageVO {
    private int item_post_id;
    private int seq;
    private String url;

    public ImageVO() {
    }

    public ImageVO(int item_post_id, int seq, String url) {
        this.item_post_id = item_post_id;
        this.seq = seq;
        this.url = url;
    }

    public int getItem_post_id() {
        return item_post_id;
    }

    public void setItem_post_id(int item_post_id) {
        this.item_post_id = item_post_id;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ImageVO{" +
                "item_post_id=" + item_post_id +
                ", seq=" + seq +
                ", url='" + url + '\'' +
                '}';
    }
}

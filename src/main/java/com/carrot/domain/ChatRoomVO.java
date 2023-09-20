package com.carrot.domain;

import java.util.Date;

public class ChatRoomVO {
    private int id;
    private int itemPostId;
    private String buyer;
    private String buyerName;
    private String seller;
    private String sellerName;
    private int status;
    private ChatMessageVO lastMessage;
    private int unReadCnt;

    public ChatRoomVO() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemPostId() {
        return itemPostId;
    }

    public void setItemPostId(int itemPostId) {
        this.itemPostId = itemPostId;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ChatMessageVO getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(ChatMessageVO lastMessage) {
        this.lastMessage = lastMessage;
    }

    public int getUnReadCnt() {
        return unReadCnt;
    }

    public void setUnReadCnt(int unReadCnt) {
        this.unReadCnt = unReadCnt;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    @Override
    public String toString() {
        return "ChatRoomVO{" +
                "id=" + id +
                ", itemPostId=" + itemPostId +
                ", buyer='" + buyer + '\'' +
                ", buyerName='" + buyerName + '\'' +
                ", seller='" + seller + '\'' +
                ", sellerName='" + sellerName + '\'' +
                ", status=" + status +
                ", lastMessage=" + lastMessage +
                ", unReadCnt=" + unReadCnt +
                '}';
    }
}

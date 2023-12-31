package com.carrot.domain;

import java.util.Date;

public class ChatMessageVO {
    private int id;
    private String writer;
    private String content;
    private Date createdAt;
    private int roomId;
    private int isRead;

    private String writerName;
    private String time;
    private String destinationId;
    private int postId;
    private String destinationName;

    public ChatMessageVO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    @Override
    public String toString() {
        return "ChatMessageVO{" +
                "id=" + id +
                ", writer='" + writer + '\'' +
                ", writerName='" + writerName + '\'' +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", roomId=" + roomId +
                ", isRead=" + isRead +
                ", time='" + time + '\'' +
                ", destinationId='" + destinationId + '\'' +
                ", postId=" + postId +
                '}';
    }
}

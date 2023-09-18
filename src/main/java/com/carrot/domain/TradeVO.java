package com.carrot.domain;

import java.util.Date;

public class TradeVO {
	
	private String seller_content, buyer_content, seller, buyer;
	private int id, item_post_id;
	private Date created_at;
	public String getSeller_content() {
		return seller_content;
	}
	public void setSeller_content(String seller_content) {
		this.seller_content = seller_content;
	}
	public String getBuyer_content() {
		return buyer_content;
	}
	public void setBuyer_content(String buyer_content) {
		this.buyer_content = buyer_content;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getItem_post_id() {
		return item_post_id;
	}
	public void setItem_post_id(int item_post_id) {
		this.item_post_id = item_post_id;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	@Override
	public String toString() {
		return "TradeVO [seller_content=" + seller_content + ", buyer_content=" + buyer_content + ", seller=" + seller
				+ ", buyer=" + buyer + ", id=" + id + ", item_post_id=" + item_post_id + ", created_at=" + created_at
				+ "]";
	}

}

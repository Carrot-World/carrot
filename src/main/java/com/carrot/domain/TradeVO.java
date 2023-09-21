package com.carrot.domain;

import java.util.Date;

public class TradeVO {
	
	private String seller_content, buyer_content, seller, buyer, sellerName, buyerName, time;
	private String bLoc1, bLoc2, bLoc3, sLoc1, sLoc2, sLoc3, title;
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

	public String getbLoc1() {
		return bLoc1;
	}

	public void setbLoc1(String bLoc1) {
		this.bLoc1 = bLoc1;
	}

	public String getbLoc2() {
		return bLoc2;
	}

	public void setbLoc2(String bLoc2) {
		this.bLoc2 = bLoc2;
	}

	public String getbLoc3() {
		return bLoc3;
	}

	public void setbLoc3(String bLoc3) {
		this.bLoc3 = bLoc3;
	}

	public String getsLoc1() {
		return sLoc1;
	}

	public void setsLoc1(String sLoc1) {
		this.sLoc1 = sLoc1;
	}

	public String getsLoc2() {
		return sLoc2;
	}

	public void setsLoc2(String sLoc2) {
		this.sLoc2 = sLoc2;
	}

	public String getsLoc3() {
		return sLoc3;
	}

	public void setsLoc3(String sLoc3) {
		this.sLoc3 = sLoc3;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "TradeVO{" +
				"seller_content='" + seller_content + '\'' +
				", buyer_content='" + buyer_content + '\'' +
				", seller='" + seller + '\'' +
				", buyer='" + buyer + '\'' +
				", sellerName='" + sellerName + '\'' +
				", buyerName='" + buyerName + '\'' +
				", time='" + time + '\'' +
				", id=" + id +
				", item_post_id=" + item_post_id +
				", created_at=" + created_at +
				'}';
	}
}

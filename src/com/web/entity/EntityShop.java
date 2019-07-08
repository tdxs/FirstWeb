package com.web.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EntityShop {
	private String id;
    private String name;
    private float price;
    private int count;
    private String image;
    private String detailed;
    private String centent;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDetailed() {
		return detailed;
	}
	public void setDetailed(String detailed) {
		this.detailed = detailed;
	}
	public String getCentent() {
		return centent;
	}
	public void setCentent(String centent) {
		this.centent = centent;
	}
	//有参构造
	public EntityShop(String id, String name, float price, int count, String image, String detailed, String centent) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.count = count;
		this.image = image;
		this.detailed = detailed;
		this.centent = centent;
	}
	public EntityShop(ResultSet rs) throws SQLException {
		this.id = rs.getString("id");
		this.name = rs.getString("name");
		this.price = rs.getFloat("price");
		this.count = rs.getInt("count");
		this.image = rs.getString("image");
		this.detailed = rs.getString("detailed");
		this.centent = rs.getString("centent");
	}
    
}

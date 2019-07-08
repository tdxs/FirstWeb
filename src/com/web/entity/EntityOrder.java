package com.web.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class EntityOrder {
	private String id;
    private String address;
    private float money;
    private int count;
    private String discount;
    private float price;
    private String name;
    private String ordercode;
    private String people;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrdercode() {
		return ordercode;
	}
	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}
	public String getPeople() {
		return people;
	}
	public void setPeople(String people) {
		this.people = people;
	}
	@Override
	public String toString() {
		return "EntityOrder [id=" + id + ", address=" + address + ", money=" + money + ", count=" + count
				+ ", discount=" + discount + ", price=" + price + ", name=" + name + ", ordercode=" + ordercode
				+ ", people=" + people + "]";
	}
	public EntityOrder(String id, String address, float money, int count, String discount, float price, String name,
			String ordercode, String people) {
		this.id = id;
		this.address = address;
		this.money = money;
		this.count = count;
		this.discount = discount;
		this.price = price;
		this.name = name;
		this.ordercode = ordercode;
		this.people = people;
	}
	public EntityOrder(String address, float money, int count, String discount, float price, String name,
			String ordercode, String people) {
		this.id = UUID.randomUUID().toString().replace("-", "");
		this.address = address;
		this.money = money;
		this.count = count;
		this.discount = discount;
		this.price = price;
		this.name = name;
		this.ordercode = ordercode;
		this.people = people;
	}
}

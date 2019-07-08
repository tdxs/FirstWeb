package com.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.web.entity.EntityOrder;

public class OrderDao {
	//新增订单
	public void addOrder(EntityOrder entityOrder,Connection connection) {
		
		String sql = "insert into `order` values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,entityOrder.getName());
			preparedStatement.setString(2,entityOrder.getAddress());
			preparedStatement.setFloat(3,entityOrder.getMoney());
			preparedStatement.setInt(4,entityOrder.getCount());
			preparedStatement.setString(5,entityOrder.getDiscount());
			preparedStatement.setFloat(6,entityOrder.getPrice());
			preparedStatement.setString(7,entityOrder.getId());
			preparedStatement.setString(8,entityOrder.getOrdercode());
			preparedStatement.setString(9,entityOrder.getPeople());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

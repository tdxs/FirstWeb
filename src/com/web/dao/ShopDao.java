package com.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.web.entity.EntityShop;

public class ShopDao {
	//商品表
	
//	查询所有商品
	public List<EntityShop> findAllShop(Connection connection) {
		List<EntityShop> entityShops = new ArrayList<EntityShop>();
		String sql = "select * from shop";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				entityShops.add(new EntityShop(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return entityShops;
	}
}

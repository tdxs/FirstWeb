package com.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.web.entity.EntityAddress;

/**
 * 收货地址
 * 
 *
 */
public class AddressDao {
	//获取收货地址


	//查询收货地址
	public List<EntityAddress> getAddress(Connection con) {
		String sql = "select * from address";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<EntityAddress> addresses = new ArrayList<EntityAddress>();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				addresses.add(new EntityAddress(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return addresses;
	}

	//新增收货地址
	public void addAddress(EntityAddress address,Connection con) {
		String sql = "insert into address values(?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = con.prepareStatement(sql);
			//防止sql注入
			preparedStatement.setString(1,address.getName());
			preparedStatement.setString(2,address.getAddress());
			preparedStatement.setString(3,address.getPhone());
			preparedStatement.setString(4,address.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				preparedStatement.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//修改收货地址
	public void updateAddress(EntityAddress address,Connection connection) {

		String sql = "update address set name = ?, address = ?, phone = ? where id = ?";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			//防止sql注入
			preparedStatement.setString(1,address.getName());
			preparedStatement.setString(2,address.getAddress());
			preparedStatement.setString(3,address.getPhone());
			preparedStatement.setString(4,address.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//删除地址
	public void deleteAddress(String id,Connection connection) {
		String sql = "delete from address where id = ?";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,id);
			preparedStatement.execute();
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
	//根据id查询
	public EntityAddress findById(String id,Connection connection) {
		EntityAddress address = null;
		String sql = "select * from address where id = ?";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				address = new EntityAddress(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return address;
	}
}

package com.web.servlet;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.web.dao.AddressDao;
import com.web.dao.ShopDao;
import com.web.db.ConnectionDB;
import com.web.entity.EntityAddress;
/**
 * 购物车表
 *
 *
 */
public class AddEntityAddressServlet implements Servlet{
	//	UUID.randomUUID().toString().replace("-", "")
	@Override
	public void destroy() {

	}
	@Override
	public ServletConfig getServletConfig() {
		return null;
	}

	@Override
	public String getServletInfo() {
		return null;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {

	}
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		System.out.println("地址...");
		//数据库连接
		ConnectionDB connectionDB = new ConnectionDB();
		//dao数据
		AddressDao addressDao = new AddressDao();
		//新增
		addressDao.addAddress(new EntityAddress(UUID.randomUUID().toString().replace("-", ""),
				request.getParameter("name"),
				request.getParameter("address"),
				request.getParameter("phone")),connectionDB.getConnection());
	}
}

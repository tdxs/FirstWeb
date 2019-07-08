package com.web.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.web.dao.AddressDao;
import com.web.db.ConnectionDB;
import com.web.entity.EntityAddress;

public class DeleteAddressServlet implements Servlet{

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
	public void init(ServletConfig arg0) throws ServletException {
		
	}

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		System.out.println("地址删除...");
		//数据库连接
		ConnectionDB connectionDB = new ConnectionDB();
		String id = request.getParameter("id");
		AddressDao addressDao = new AddressDao();
		//删除
		addressDao.deleteAddress(id,connectionDB.getConnection());
		
		
	}

}

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

public class UpdateAddressServlet implements Servlet{

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
		System.out.println("地址修改...");
		EntityAddress address = new EntityAddress();
		//数据库连接
		ConnectionDB connectionDB = new ConnectionDB();
		String id = request.getParameter("id");
		AddressDao addressDao = new AddressDao();
		addressDao.updateAddress(new EntityAddress(id,
				request.getParameter("name"),
				request.getParameter("address"),
				request.getParameter("phone")),connectionDB.getConnection());
//		request.getRequestDispatcher("/Address.jsp").forward(request, response);
		
		
		
		
		
	}

}

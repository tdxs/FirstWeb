package com.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.web.dao.AddressDao;
import com.web.db.ConnectionDB;
import com.web.entity.EntityAddress;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class FindByIdAddressServlet implements Servlet{

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
		//设置编码格式
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		//数据库连接
		ConnectionDB connectionDB = new ConnectionDB();
		//dao数据
		AddressDao addressDao = new AddressDao();
		//查询
		EntityAddress addresses = addressDao.findById(id,connectionDB.getConnection());
		JSONObject jsonObject = JSONObject.fromObject(addresses);
		PrintWriter printWriter = response.getWriter();
		printWriter.print(jsonObject);
	}
}

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

public class FindAllAddressServlet implements Servlet{

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
		System.out.println("查询所有地址...");
		//数据库连接
		ConnectionDB connectionDB = new ConnectionDB();
		//dao数据
		AddressDao addressDao = new AddressDao();
		//查询
		List<EntityAddress> addresses = addressDao.getAddress(connectionDB.getConnection());
		JSONArray array = JSONArray.fromObject(addresses);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("Addresslist",array);
		PrintWriter printWriter = response.getWriter();
		printWriter.print(jsonObject);
		
//		PrintWriter prin = response.getWriter();
//        prin.print(jsonObject);
		
		
	}

}

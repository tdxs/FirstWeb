package com.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.web.dao.OrderDao;
import com.web.db.ConnectionDB;
import com.web.entity.EntityOrder;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 订单表
 * 
 *
 */
public class OrderServlet implements Servlet{

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
	public void service(ServletRequest request, ServletResponse response) throws IOException  {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		System.out.println("订单新增...");
		JSONObject success = new JSONObject();//返回参数设置
		String ordercode = request.getParameter("ordercode");
    	String people = request.getParameter("people");
		String address = request.getParameter("address");
		String info = request.getParameter("info");
		Float float1 = Float.valueOf(request.getParameter("money").toString());
		//数据库连接
		ConnectionDB connectionDB = new ConnectionDB();
		OrderDao dao = new OrderDao();
		//jsonarray解析数据
		JSONArray array = JSONArray.fromObject(info);
		JSONObject object = null;
//		object.get("discount")
		try {
			for (int i = 0; i < array.size(); i++) {
				object = (JSONObject) array.get(i);
				EntityOrder entityOrders = new EntityOrder(address,float1,
								Integer.valueOf(object.get("count").toString()),
								"暂无折扣",
								Float.valueOf(object.get("price").toString()),
								object.get("name").toString(),ordercode,people);
				//新增
				dao.addOrder(entityOrders,connectionDB.getConnection());
				success.put("success",true);
			}
		} catch (Exception e) {
			success.put("success",false);
			e.printStackTrace();
		}
		PrintWriter printWriter = response.getWriter();
		printWriter.print(success);
//		PrintWriter printWriter = response.getWriter();
//		printWriter.print(jsonObject);
//		String address, float money, int count, 
//		String discount, float price, String name,
//		String ordercode, String people
//		request.getParameter("")
	}
}

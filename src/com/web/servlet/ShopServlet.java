package com.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;



import com.web.dao.ShopDao;
import com.web.db.ConnectionDB;
import com.web.entity.EntityShop;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.*;


public class ShopServlet implements Servlet {

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
		System.out.println("商品信息...");
		//数据库连接
		ConnectionDB connectionDB = new ConnectionDB();
		//dao数据
		ShopDao dao = new ShopDao();
		//查询
		List<EntityShop> entityShops = dao.findAllShop(connectionDB.getConnection());
		JSONArray array = JSONArray.fromObject(entityShops);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("commodity",array);
		System.out.println("这是集合"+array);
		PrintWriter prin = response.getWriter();
        prin.print(jsonObject);
		//页面跳转
//		request.getRequestDispatcher("").forward(request, response);
		
	}

}
//public static void writeJson(HttpServletResponse response, Object obj) {
//	
//	String json = JSON.toJSONStringWithDateFormat(obj, WafConstants.FORMAT_DATETIME);
//	
//	response.setContentType("text/html;charset=" + WafConstants.CHARSET_DEFAULT);
//	
//	try (PrintWriter writer = response.getWriter()) {
//
//		writer.write(json);
//		
//	} catch (IOException e) {
//		log.error(e.getLocalizedMessage(), e);
//	}
//}
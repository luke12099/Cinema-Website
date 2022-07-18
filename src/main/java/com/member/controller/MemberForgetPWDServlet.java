package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.member.model.MemberService;

/**
 * Servlet implementation class MemberForgetPWDServlet
 */
@WebServlet("/member/password/forget")
public class MemberForgetPWDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final Gson gson = new GsonBuilder().create();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberForgetPWDServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		Map<String, String> map = new HashMap<>();

		// 獲取前端傳過來訊息
		String usrEmail = null;
		Integer verifyResult = null;
		
		if (!request.getParameter("usrEmail").isEmpty()) {
			usrEmail = request.getParameter("usrEmail");
			MemberService memberService = new MemberService();
			verifyResult = memberService.verifyEmail(usrEmail);
		} 
		if (verifyResult == 0) {
			map.put("stat", "fail");
			pw.print(gson.toJson(map));
			return;
		}
		
		
//		System.out.println("123");
		MemberService memberservice = new MemberService();
		boolean res = memberservice.sendMail(usrEmail, request.getServerName(), String.valueOf(request.getServerPort()));
		
		if (res) {
			map.put("stat", "success");
		} else {
			map.put("stat", "fail");
		}
		
		pw.print(gson.toJson(map));

	}

}
package com.movie.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.movie.model.MovieService;
import com.movie.model.MovieVO;

@WebServlet("/MovieServlet.do")
@MultipartConfig(maxFileSize = 10*1024*1024,maxRequestSize = 50*10*1024*1024)
public class MovieServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
		
		
		if("insert".equals(action)) {
			
			/********************接收請求參數**********************/
			String mvName = req.getParameter("mvName");
			String mvEName = req.getParameter("mvEName");
			
			Integer mvLong=Integer.valueOf(req.getParameter("mvLong").trim());
			Integer mvlevel=Integer.valueOf(req.getParameter("mvLevel").trim());
			/******************處理圖片************************/
			String mvPicture =null;
			if(req.getPart("mvPicture").getSize()==0) {
				mvPicture = "/mvPicture_upload/sample.jpg";
			}else {
				Part photo = req.getPart("mvPicture");
				String dir = getServletContext().getRealPath("/mvPicture_upload");
				File fsaveDirectory = new File(dir);
				if (!fsaveDirectory.exists())
					 fsaveDirectory.mkdirs();
				String filename = getFileNameFromPart(photo);
				photo.write(dir+"/"+filename);
				mvPicture ="/mvPicture_upload/" +filename;
				System.out.println("insert 63行裡面的圖片路徑:"+mvPicture);
			}
			
			/******************************************/
			String mvDt = req.getParameter("mvDt");
			Date mvStDate = java.sql.Date.valueOf(req.getParameter("mvStDate"));
			Date mvEdDate = java.sql.Date.valueOf(req.getParameter("mvEdDate"));
			String mvType = req.getParameter("mvType");
			String mvCast = req.getParameter("mvCast");
			String mvDrt = req.getParameter("mvDt");
			String mvTler = req.getParameter("mvTler");
			
			/*********************************************/
			
			MovieVO movieVO = new MovieVO();
			movieVO.setMvName(mvName);
			movieVO.setMvEName(mvEName);
			movieVO.setMvLong(mvLong);
			movieVO.setMvLevel(mvlevel);
			movieVO.setMvPicture(mvPicture);
			movieVO.setMvDt(mvDt);
			movieVO.setMvStDate(mvStDate);
			movieVO.setMvEdDate(mvEdDate);
			movieVO.setMvType(mvType);
			movieVO.setMvCast(mvCast);
			movieVO.setMvDrt(mvDrt);
			movieVO.setMvTler(mvTler);
			/*********************************************/
			MovieService mvSvc = new MovieService();
			movieVO = mvSvc.insert(mvName, mvEName, mvLong, mvlevel, mvPicture, mvDt, mvStDate, mvEdDate, mvType, mvCast, mvDrt, mvTler);
			/*********************************************/
			String url ="/back_end/ManageMV/manageMV.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
		
		if("getOne_For_Update".equals(action)) {
			
			// 接收來自manageMV.jsp的編輯按鈕傳來的參數
			Integer mvId =Integer.valueOf(req.getParameter("mvId"));
			// New一個VO物件 C使用service方法找到DB內的物件傳入VO
			MovieService mvSvc = new MovieService();
			MovieVO movieVO = mvSvc.findByPrimaryKey(mvId);
			// 把VO存進req準備交換
			req.setAttribute("movieVO", movieVO);
			String url ="/back_end/ManageMV/editMV.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
			
			
		}
		if("update".equals(action)) {
			
			/****************獲取參數*****************/
			Integer mvId =Integer.valueOf(req.getParameter("mvId").trim());
			String mvName = req.getParameter("mvName");
			String mvEName = req.getParameter("mvEName");
			Integer mvLong=Integer.valueOf(req.getParameter("mvLong").trim());
			Integer mvlevel=Integer.valueOf(req.getParameter("mvLevel").trim());
			/********************判斷圖片是否有重新上傳************************************/
			String mvPicture =null;
			// Noreupload預藏了 原先在editMV.jsp調進來的VO照片地址,確保USER不上傳也能保留原先的值
			if(req.getPart("mvPicture").getSize()==0) {
				mvPicture = req.getParameter("Noreupload");
			}else {
				Part photo = req.getPart("mvPicture");
				String dir = getServletContext().getRealPath("/mvPicture_upload");
				File fsaveDirectory = new File(dir);
				if (!fsaveDirectory.exists())
					 fsaveDirectory.mkdirs();
				String filename = getFileNameFromPart(photo);
				photo.write(dir+"/"+filename);
				
				mvPicture = "/mvPicture_upload/"+filename;
			}
			
			/********************************/
			String mvDt = req.getParameter("mvDt");
			Date mvStDate = java.sql.Date.valueOf(req.getParameter("mvStDate"));
			Date mvEdDate = java.sql.Date.valueOf(req.getParameter("mvEdDate"));
			String mvType = req.getParameter("mvType");
			String mvCast = req.getParameter("mvCast");
			String mvDrt = req.getParameter("mvDrt");
			String mvTler = req.getParameter("mvTler");
			
			MovieVO movieVO = new MovieVO();
			movieVO.setMvId(mvId);
			movieVO.setMvName(mvName);
			movieVO.setMvEName(mvEName);
			movieVO.setMvLong(mvLong);
			movieVO.setMvLevel(mvlevel);
			movieVO.setMvPicture(mvPicture);
			movieVO.setMvDt(mvDt);
			movieVO.setMvStDate(mvStDate);
			movieVO.setMvEdDate(mvEdDate);
			movieVO.setMvType(mvType);
			movieVO.setMvCast(mvCast);
			movieVO.setMvDrt(mvDrt);
			movieVO.setMvTler(mvTler);
			/********************************/
			MovieService mvSvc = new MovieService();
			movieVO = mvSvc.update(mvId,mvName, mvEName, mvLong, mvlevel, mvPicture, mvDt, mvStDate, mvEdDate, mvType, mvCast, mvDrt, mvTler);
			req.setAttribute("movieVO", movieVO);
			String url ="/back_end/ManageMV/manageMV.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
		
		if ("listMovie_ByCompositeQuery".equals(action)) {
			// 將資料轉為map
			Map<String, String[]> map = req.getParameterMap();
			// 傳入service
			MovieService mvSvc = new MovieService();
			List<MovieVO> list =mvSvc.compositeQuery_Search(map);
			// 存入Attribute
			req.setAttribute("listMovie_ByCompositeQuery", list);
			RequestDispatcher rd = req.getRequestDispatcher("/back_end/ManageMV/compositeQuery_result.jsp");
			rd.forward(req, res);
			
		}
		
if("getOneForDisplay".equals(action)) {
			
			Integer mvId = Integer.valueOf(req.getParameter("mvId"));
			MovieService mvSvc = new MovieService();
			// 單一電影VO
			MovieVO movieVO = mvSvc.findByPrimaryKey(mvId);
			req.setAttribute("movieVO", movieVO);
			RequestDispatcher rd = req.getRequestDispatcher("/front_end/movieDetail/movie_detail.jsp");
			rd.forward(req, res);
		}
			
	}
	
	int insertSeq = 0;
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		++insertSeq;
//		System.out.println("header=" + header); 
		// 測試用
		// 把路徑傳到file物件再getname 全部瀏覽器都OK
		String filename =insertSeq + new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		//		System.out.println("filename=" + filename); 
		// 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}


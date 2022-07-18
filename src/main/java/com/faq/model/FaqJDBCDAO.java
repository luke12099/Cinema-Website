package com.faq.model;

import java.sql.*;
import java.util.*;

import javax.naming.NamingException;

import com.common.JDBCUtil;
import com.movie.model.MovieVO;

public class FaqJDBCDAO implements FaqDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/movietheater?serverTimezone=Asia/Taipei"; //連結的MySQL路徑
	String userid = "root"; // MySQL 帳號
	String passwd = "password"; // MySQL 密碼

	/* 新增 */
	private static final String INSERT_STMT = // 宣告變數INSERT_STMT，自動編號不用加：FAQ編號，因為自動生成
			"insert into faq" 
			+ " (faq_class, faq_title, faq_content) values" 
			+ " (?, ?, ?)";
	/* 單一查詢 */
	private static final String GET_ONE_STMT = 
			"select faq_no, faq_class, faq_title, faq_content"
			+ " from faq where faq_no = ?";
	/* 全部查詢 */
	private static final String GET_ALL_STMT = 
			"select faq_no, faq_class, faq_title, faq_content"
			+ " from faq order by faq_no";
	/* 修改 */
	private static final String UPDATE = 
			"update faq set"
			+ " faq_class=?, faq_title=?, faq_content=?"
			+ " where faq_no = ?";
	/* 刪除 */
	private static final String DELETE = 
			"delete from faq where faq_no = ?";
	
	/* 會員相關問題單一查詢 */
	private static final String GET_FaqClass1 = 
			"select faq_no, faq_class, faq_title, faq_content"
			+ " from faq where faq_class=0";
	
	/* 影城相關問題單一查詢 */
	private static final String GET_FaqClass2 = 
			"select faq_no, faq_class, faq_title, faq_content"
					+ " from faq where faq_class=1";
	
	/* 電影上映相關問題單一查詢 */
	private static final String GET_FaqClass3 = 
			"select faq_no, faq_class, faq_title, faq_content"
					+ " from faq where faq_class=2";
	
	/* 其他問題單一查詢 */
	private static final String GET_FaqClass4 = 
			"select faq_no, faq_class, faq_title, faq_content"
					+ " from faq where faq_class=3";
	
	@Override /* 新增 */
	public void insert(FaqVO faqVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setByte(1, faqVO.getFaq_class());
			pstmt.setString(2, faqVO.getFaq_title());
			pstmt.setString(3, faqVO.getFaq_content());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			// Handle any SQL errors
		} catch (SQLException e) {
			e.printStackTrace();
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override //修改
	public void update(FaqVO faqVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setByte(1, faqVO.getFaq_class());
			pstmt.setString(2, faqVO.getFaq_title());
			pstmt.setString(3, faqVO.getFaq_content());
			pstmt.setInt(4, faqVO.getFaq_no());


			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			// Handle any SQL errors
		} catch (SQLException e) {
			e.printStackTrace();
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override //刪除
	public void delete(Integer Faq_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, Faq_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			// Handle any SQL errors
		} catch (SQLException e) {
			e.printStackTrace();
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public FaqVO findByPrimaryKey(Integer faq_no) {
		FaqVO faqVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, faq_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// FaqVo 也稱為 Domain objects
				faqVO = new FaqVO();
				faqVO.setFaq_no(rs.getInt("faq_no"));
				faqVO.setFaq_class(rs.getByte("faq_class"));
				faqVO.setFaq_title(rs.getString("faq_title"));
				faqVO.setFaq_content(rs.getString("faq_content"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			// Handle any SQL errors
		} catch (SQLException e) {
			e.printStackTrace();
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return faqVO;
	}

	@Override
	public List<FaqVO> getAll() {
		List<FaqVO> list = new ArrayList<FaqVO>();
		FaqVO faqVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// FaqVo 也稱為 Domain objects
				faqVO = new FaqVO();
				faqVO.setFaq_no(rs.getInt("faq_no"));
				faqVO.setFaq_class(rs.getByte("faq_class"));
				faqVO.setFaq_title(rs.getString("faq_title"));
				faqVO.setFaq_content(rs.getString("faq_content"));
				list.add(faqVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
			e.printStackTrace();
			// Handle any SQL errors
		} catch (SQLException e) {
//			throw new RuntimeException("A database error occured. "
//					+ e.getMessage());
			e.printStackTrace();
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	
	@Override
	public List<FaqVO> getFaqClass1() {
		
		List<FaqVO> list = new ArrayList<FaqVO>();
		FaqVO faqVO = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(GET_FaqClass1);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				faqVO = new FaqVO();
				faqVO.setFaq_no(rs.getInt("faq_no"));
				faqVO.setFaq_class(rs.getByte("faq_class"));
				faqVO.setFaq_title(rs.getString("faq_title"));
				faqVO.setFaq_content(rs.getString("faq_content"));
				list.add(faqVO);
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver."
			+e.getMessage());
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured."
			+e.getMessage());
		} catch (NamingException e) {
			e.printStackTrace();
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public List<FaqVO> getFaqClass2() {
		
		List<FaqVO> list = new ArrayList<FaqVO>();
		FaqVO faqVO = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(GET_FaqClass2);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				faqVO = new FaqVO();
				faqVO.setFaq_no(rs.getInt("faq_no"));
				faqVO.setFaq_class(rs.getByte("faq_class"));
				faqVO.setFaq_title(rs.getString("faq_title"));
				faqVO.setFaq_content(rs.getString("faq_content"));
				list.add(faqVO);
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver."
			+e.getMessage());
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured."
			+e.getMessage());
		} catch (NamingException e) {
			e.printStackTrace();
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public List<FaqVO> getFaqClass3() {
		
		List<FaqVO> list = new ArrayList<FaqVO>();
		FaqVO faqVO = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(GET_FaqClass3);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				faqVO = new FaqVO();
				faqVO.setFaq_no(rs.getInt("faq_no"));
				faqVO.setFaq_class(rs.getByte("faq_class"));
				faqVO.setFaq_title(rs.getString("faq_title"));
				faqVO.setFaq_content(rs.getString("faq_content"));
				list.add(faqVO);
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver."
			+e.getMessage());
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured."
			+e.getMessage());
		} catch (NamingException e) {
			e.printStackTrace();
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public List<FaqVO> getFaqClass4() {
		
		List<FaqVO> list = new ArrayList<FaqVO>();
		FaqVO faqVO = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(GET_FaqClass4);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				faqVO = new FaqVO();
				faqVO.setFaq_no(rs.getInt("faq_no"));
				faqVO.setFaq_class(rs.getByte("faq_class"));
				faqVO.setFaq_title(rs.getString("faq_title"));
				faqVO.setFaq_content(rs.getString("faq_content"));
				list.add(faqVO);
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver."
			+e.getMessage());
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured."
			+e.getMessage());
		} catch (NamingException e) {
			e.printStackTrace();
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	

	public static void main(String[] args) {

		FaqJDBCDAO dao = new FaqJDBCDAO();

		// 新增 OK
//		FaqVO faqVO1 = new FaqVO();
//		faqVO1.setFaq_class("影城相關問題");
//		faqVO1.setFaq_title("嗨邇覓影城公告");
//		faqVO1.setFaq_content("請遵守影片分級制度相關規定。片長超過150分鐘，酌量增加票價，以現場公告為主！");
//		dao.insert(faqVO1);


		// 修改 OK
//		FaqVO faqVO2 = new FaqVO();
//		faqVO2.setFaq_no(2);
//		faqVO2.setFaq_class("影城相關aa");
//		faqVO2.setFaq_title( "嗨邇覓b城公告");
//		faqVO2.setFaq_content("片ddddd為主！");
//		dao.update(faqVO2);


		// 查詢 OK
//		FaqVO faqVO3 = dao.findByPrimaryKey(3);
//			System.out.print(faqVO3.getFaq_no() + ",");
//			System.out.print(faqVO3.getFaq_class() + ",");
//			System.out.print(faqVO3.getFaq_title() + ",");
//			System.out.print(faqVO3.getFaq_content() + ",");
//			System.out.println("---------------------");

		// 全部查詢 OK
//		List<FaqVO> list = dao.getAll();
//		for (FaqVO aFaq : list) {
//			System.out.print(aFaq.getFaq_no() + ",");
//			System.out.print(aFaq.getFaq_class() + ",");
//			System.out.print(aFaq.getFaq_title() + ",");
//			System.out.print(aFaq.getFaq_content() + ",");
//			System.out.println();
//			}
			
		// 刪除
//		dao.delete(5);


	}
}


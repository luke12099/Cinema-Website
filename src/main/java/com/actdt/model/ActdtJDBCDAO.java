package com.actdt.model;

import java.sql.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import oracle.net.aso.a;



public class ActdtJDBCDAO implements ActdtDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/movietheater?serverTimezone=Asia/Taipei"; //連結的MySQL路徑
	String userid = "root"; // MySQL 帳號
	String passwd = "password"; // MySQL 密碼

	/* 新增 */
	private static final String INSERT_STMT = // 宣告變數INSERT_STMT
			"insert into movietheater.activity_detail" 
			+ " (act_id, act_date_start, act_title, act_subtitle, tk_type_id, act_discount, act_coupon, act_status, act_content,act_picture) values" 
			+ " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	/* 活動編號單一查詢 */
	private static final String GET_ONE_STMT = 
			"select act_id, act_date_start, act_title, act_subtitle, tk_type_id, act_discount, act_coupon, act_status, act_content,act_picture"
			+ " from movietheater.activity_detail where act_id = ? and tk_type_id = ? ";
	
	
	/* 活動狀態單一查詢 OK */ 
	private static final String GET_STATUS_STMT = 
			"select act_id, act_date_start, act_title, act_subtitle, tk_type_id, act_discount, act_coupon, act_status, act_content,act_picture"
			+ " from movietheater.activity_detail where act_status = ?";
	
	
	/* 全部查詢 */
	private static final String GET_ALL_STMT = 
			"select act_id, act_date_start, act_title, act_subtitle, tk_type_id, act_discount, act_coupon, act_status, act_content,act_picture"
			+ " from movietheater.activity_detail order by act_id";
	/* 修改 */
	private static final String UPDATE = 
//			"update movietheater.activity_detail set"
//			+ " act_date_start=?, act_title=?, act_subtitle=?, act_discount=?, act_coupon=?, act_status=?, act_content=?,act_picture=?"
//			+ " where act_id = ? and tk_type_id = ?" ;
	
	"update movietheater.activity_detail set"
	+ " act_date_start=?, act_title=?, act_subtitle=?, act_discount=?, act_coupon=?, act_status=?, act_content=?,act_picture=?"
	+ " where act_id = ?" ;
	
	/* 刪除 */
//	private static final String DELETE = 
//			"delete from movietheater.activity_detail where act_id = ? and tk_type_id = ?";
	private static final String DELETE = 
			"delete from movietheater.activity_detail where act_id = ?";
	
	/* 單一活動查詢，不顯示重複值 後台 */ 
	private static final String GET_ONLYACT_STMT = 
			"select act_id, act_date_start, act_title, act_subtitle, tk_type_id, act_discount, act_coupon, act_status, act_content,act_picture"
			+ " from movietheater.activity_detail where act_id = ?";
	
	/* 顯示單一活動清單，不顯示重複值 前台畫面列表 */
	private static final String GET_ACT_STMT = 
			"select *"
			+ "from activity_detail where act_id <>1 group by act_id order by act_id";
//			"select act_id, act_date_start, act_title, act_subtitle, tk_type_id, act_discount, act_coupon, act_status, act_content,act_picture"
//			+ "  from activity_detail where TK_TYPE_ID in (SELECT MAX(TK_TYPE_ID) FROM activity_detail GROUP BY ACT_SUBTITLE) and ACT_TITLE not like '未符合' ";
//	select * from activity_detail where TK_TYPE_ID in (SELECT MAX(TK_TYPE_ID) FROM activity_detail GROUP BY ACT_SUBTITLE);
	
	


	@Override /* 新增 */
	public void insert(ActdtVO actdtVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setInt(1, actdtVO.getAct_id());
			pstmt.setDate(2, actdtVO.getAct_date_start());
			pstmt.setString(3, actdtVO.getAct_title());
			pstmt.setString(4, actdtVO.getAct_subtitle());
			pstmt.setInt(5, actdtVO.getTkTypeID());
			pstmt.setDouble(6, actdtVO.getAct_discount());
			pstmt.setInt(7, actdtVO.getAct_coupon());
			pstmt.setByte(8, actdtVO.getAct_status());
			pstmt.setString(9, actdtVO.getAct_content());
			pstmt.setString(10, actdtVO.getAct_picture());
			
			
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
//	public void update(ActdtVO actdtVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//			
//			
//			pstmt.setDate(1, actdtVO.getAct_date_start());
//			pstmt.setString(2, actdtVO.getAct_title());
//			pstmt.setString(3, actdtVO.getAct_subtitle());
//			pstmt.setDouble(4, actdtVO.getAct_discount());
//			pstmt.setInt(5, actdtVO.getAct_coupon());
//			pstmt.setByte(6, actdtVO.getAct_status());
//			pstmt.setString(7, actdtVO.getAct_content());
//			pstmt.setString(8, actdtVO.getAct_picture());
//			pstmt.setInt(9, actdtVO.getAct_id());
//			pstmt.setInt(10, actdtVO.getTkTypeID());
//
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//			// Handle any SQL errors
//		} catch (SQLException e) {
//			e.printStackTrace();
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//
//	}
	
	public void update(ActdtVO actdtVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setDate(1, actdtVO.getAct_date_start());
			pstmt.setString(2, actdtVO.getAct_title());
			pstmt.setString(3, actdtVO.getAct_subtitle());
			pstmt.setDouble(4, actdtVO.getAct_discount());
			pstmt.setInt(5, actdtVO.getAct_coupon());
			pstmt.setByte(6, actdtVO.getAct_status());
			pstmt.setString(7, actdtVO.getAct_content());
			pstmt.setString(8, actdtVO.getAct_picture());
			pstmt.setInt(9, actdtVO.getAct_id());
		


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
	//public void delete(Integer Actdt_id, Integer tkTypeID) {
	public void delete(Integer Actdt_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, Actdt_id);
			//pstmt.setInt(2, tkTypeID);

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
	public ActdtVO findByPrimaryKey(Integer act_id, Integer tkTypeID) {
		ActdtVO actdtVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, act_id);
			pstmt.setInt(2, tkTypeID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// ActdtVo 也稱為 Domain objects
				actdtVO = new ActdtVO();
				actdtVO.setAct_id(rs.getInt("act_id"));
				actdtVO.setAct_date_start(rs.getDate("act_date_start"));
				actdtVO.setAct_title(rs.getString("act_title"));
				actdtVO.setAct_subtitle(rs.getString("act_subtitle"));
				actdtVO.setTkTypeID(rs.getInt("TK_TYPE_ID"));
				actdtVO.setAct_discount(rs.getDouble("act_discount"));
				actdtVO.setAct_coupon(rs.getInt("act_coupon"));
				actdtVO.setAct_status(rs.getByte("act_status"));
				actdtVO.setAct_content(rs.getString("act_content"));
				actdtVO.setAct_picture(rs.getString("act_picture"));
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
		return actdtVO;
	}

	@Override
	public List<ActdtVO> getAll() {
		List<ActdtVO> list = new ArrayList<ActdtVO>();
		ActdtVO actdtVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// ActdtVo 也稱為 Domain objects
				actdtVO = new ActdtVO();
				actdtVO.setAct_id(rs.getInt("act_id"));
				actdtVO.setAct_date_start(rs.getDate("act_date_start"));
				actdtVO.setAct_title(rs.getString("act_title"));
				actdtVO.setAct_subtitle(rs.getString("act_subtitle"));
				actdtVO.setTkTypeID(rs.getInt("TK_TYPE_ID"));
				actdtVO.setAct_discount(rs.getDouble("act_discount"));
				actdtVO.setAct_coupon(rs.getInt("act_coupon"));
				actdtVO.setAct_status(rs.getByte("act_status"));
				actdtVO.setAct_content(rs.getString("act_content"));
				actdtVO.setAct_picture(rs.getString("act_picture"));
				list.add(actdtVO); // Store the row in the list
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
	public List<ActdtVO> getActdtStatus() {
		List<ActdtVO> list = new ArrayList<ActdtVO>();
		ActdtVO actdtVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_STATUS_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// ActdtVo 也稱為 Domain objects
				actdtVO = new ActdtVO();
				actdtVO.setAct_id(rs.getInt("act_id"));
				actdtVO.setAct_date_start(rs.getDate("act_date_start"));
				actdtVO.setAct_title(rs.getString("act_title"));
				actdtVO.setAct_subtitle(rs.getString("act_subtitle"));
				actdtVO.setTkTypeID(rs.getInt("TK_TYPE_ID"));
				actdtVO.setAct_discount(rs.getDouble("act_discount"));
				actdtVO.setAct_coupon(rs.getInt("act_coupon"));
				actdtVO.setAct_status(rs.getByte("act_status"));
				actdtVO.setAct_content(rs.getString("act_content"));
				actdtVO.setAct_picture(rs.getString("act_picture"));
				list.add(actdtVO); // Store the row in the list
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
	
	/* 活動單一查詢 */
//	@Override
//	public ActdtVO findOneActdt(Integer act_id) {
//		ActdtVO actdtVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ONLYACT_STMT);
//
//			pstmt.setInt(1 , act_id);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// ActdtVo 也稱為 Domain objects
//				actdtVO = new ActdtVO();
//				actdtVO.setAct_id(rs.getInt("act_id"));
//				actdtVO.setAct_date_start(rs.getDate("act_date_start"));
//				actdtVO.setAct_title(rs.getString("act_title"));
//				actdtVO.setAct_subtitle(rs.getString("act_subtitle"));
//				actdtVO.setTkTypeID(rs.getInt("TK_TYPE_ID"));
//				actdtVO.setAct_discount(rs.getDouble("act_discount"));
//				actdtVO.setAct_coupon(rs.getInt("act_coupon"));
//				actdtVO.setAct_status(rs.getByte("act_status"));
//				actdtVO.setAct_content(rs.getString("act_content"));
//				actdtVO.setAct_picture(rs.getString("act_picture"));
//			}
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//			// Handle any SQL errors
//		} catch (SQLException e) {
//			e.printStackTrace();
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return actdtVO;
//	}
	
	/* 活動單一查詢 */
	@Override
	public List<ActdtVO> findOneActdt(Integer act_id) {
		List<ActdtVO> list = new ArrayList<ActdtVO>();
		ActdtVO actdtVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONLYACT_STMT);
			pstmt.setInt(1, act_id);

			//pstmt.setInt(1 , act_id);

			rs = pstmt.executeQuery();


			while (rs.next()) {
				// ActdtVo 也稱為 Domain objects
				actdtVO = new ActdtVO();
				actdtVO.setAct_id(rs.getInt("act_id"));
				actdtVO.setAct_date_start(rs.getDate("act_date_start"));
				actdtVO.setAct_title(rs.getString("act_title"));
				actdtVO.setAct_subtitle(rs.getString("act_subtitle"));
				actdtVO.setTkTypeID(rs.getInt("TK_TYPE_ID"));
				actdtVO.setAct_discount(rs.getDouble("act_discount"));
				actdtVO.setAct_coupon(rs.getInt("act_coupon"));
				actdtVO.setAct_status(rs.getByte("act_status"));
				actdtVO.setAct_content(rs.getString("act_content"));
				actdtVO.setAct_picture(rs.getString("act_picture"));
				list.add(actdtVO); // Store the row in the list
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
	
	
	/* 顯示單一活動不重複 */
	@Override
	public List<ActdtVO> getOnlyAct() {
		List<ActdtVO> list = new ArrayList<ActdtVO>();
		ActdtVO actdtVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ACT_STMT);
						
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// ActdtVo 也稱為 Domain objects
				actdtVO = new ActdtVO();
				actdtVO.setAct_id(rs.getInt("act_id"));
				actdtVO.setAct_date_start(rs.getDate("act_date_start"));
				actdtVO.setAct_title(rs.getString("act_title"));
				actdtVO.setAct_subtitle(rs.getString("act_subtitle"));
				actdtVO.setTkTypeID(rs.getInt("TK_TYPE_ID"));
				actdtVO.setAct_discount(rs.getDouble("act_discount"));
				actdtVO.setAct_coupon(rs.getInt("act_coupon"));
				actdtVO.setAct_status(rs.getByte("act_status"));
				actdtVO.setAct_content(rs.getString("act_content"));
				actdtVO.setAct_picture(rs.getString("act_picture"));
				list.add(actdtVO); // Store the row in the list
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
	


	public static void main(String[] args) {

		ActdtJDBCDAO dao = new ActdtJDBCDAO();
		
		
		// 活動查詢(不顯示重複值) OK
//		ActdtVO actdtVO4 = dao.findOneActdt(3);
//			System.out.print(actdtVO4.getAct_id() + ",");
//			System.out.print(actdtVO4.getAct_date_start() + ",");
//			System.out.print(actdtVO4.getAct_title() + ",");
//			System.out.println(actdtVO4.getAct_subtitle());
//			System.out.print(actdtVO4.getTkTypeID() + ",");
//			System.out.print(actdtVO4.getAct_discount() + ",");
//			System.out.print(actdtVO4.getAct_coupon() + ",");
//		    System.out.print(actdtVO4.getAct_status() + ",");
//		    System.out.println(actdtVO4.getAct_content() + ",");
//		    System.out.println(actdtVO4.getAct_picture() + ",");
//			System.out.println("---------------------");
		

		// 新增 OK
//		ActdtVO actdtVO1 = new ActdtVO();
//		actdtVO1.setAct_id(6);
//		actdtVO1.setAct_date_start(java.sql.Date.valueOf("2022-05-25"));
//		actdtVO1.setAct_title("夏日方案");
//		actdtVO1.setAct_subtitle("夏日觀影趣 樂FUN一下");
//		actdtVO1.setTkTypeID(3);
//		actdtVO1.setAct_discount(1.0);
//		actdtVO1.setAct_coupon(-50);
//		actdtVO1.setAct_status(Byte.valueOf("1"));
//		actdtVO1.setAct_content("凡購買05/25(三)~06/12(日)任一電影票，即享20元折價優惠");
//		actdtVO1.setAct_picture(null);
//		dao.insert(actdtVO1);

		// 修改 OK
//		ActdtVO actdtVO2 = new ActdtVO();
//		actdtVO2.setAct_id(6);
//		actdtVO2.setAct_date_start(java.sql.Date.valueOf("2022-05-25"));
//		actdtVO2.setAct_title("fff夏日方案");
//		actdtVO2.setAct_subtitle("夏日觀影趣 樂FUN一下");
//		actdtVO2.setTkTypeID(3);
//		actdtVO2.setAct_discount(1.0);
//		actdtVO2.setAct_coupon(-50);
//		actdtVO2.setAct_status(Byte.valueOf("1"));
//		actdtVO2.setAct_content("凡購買05/25(三)~06/12(日)任一電影票，即享20元折價優惠");
//		actdtVO2.setAct_picture(null);
//		dao.update(actdtVO2);


		// 查詢 OK
//		ActdtVO actdtVO3 = dao.findByPrimaryKey(3,3);
//			System.out.print(actdtVO3.getAct_id() + ",");
//			System.out.print(actdtVO3.getAct_date_start() + ",");
//			System.out.print(actdtVO3.getAct_title() + ",");
//			System.out.println(actdtVO3.getAct_subtitle());
//			System.out.print(actdtVO3.getTkTypeID() + ",");
//			System.out.print(actdtVO3.getAct_discount() + ",");
//			System.out.print(actdtVO3.getAct_coupon() + ",");
//		    System.out.print(actdtVO3.getAct_status() + ",");
//		    System.out.println(actdtVO3.getAct_content() + ",");
//		    System.out.println(actdtVO3.getAct_picture() + ",");
//			System.out.println("---------------------");


		// 全部查詢 OK
//		List<ActdtVO> list = dao.getAll();
//		for (ActdtVO aActdt : list) {
//			System.out.print(aActdt.getAct_id() + ",");
//			System.out.print(aActdt.getAct_date_start() + ",");
//			System.out.print(aActdt.getAct_title() + ",");
//			System.out.println(aActdt.getAct_subtitle());
//			System.out.print(aActdt.getTkTypeID() + ",");
//			System.out.print(aActdt.getAct_discount() + ",");
//			System.out.print(aActdt.getAct_coupon() + ",");
//		    System.out.print(aActdt.getAct_status() + ",");
//		    System.out.println(aActdt.getAct_content() + ",");
//		    System.out.println(aActdt.getAct_picture() + ",");
//			System.out.println();
//			}
		
		// 單一活動不重複 OK
		List<ActdtVO> list = dao.getOnlyAct();
		for (ActdtVO aActdt : list) {
			System.out.print(aActdt.getAct_id() + ",");
			System.out.print(aActdt.getAct_date_start() + ",");
			System.out.print(aActdt.getAct_title() + ",");
			System.out.println(aActdt.getAct_subtitle());
			System.out.print(aActdt.getTkTypeID() + ",");
			System.out.print(aActdt.getAct_discount() + ",");
			System.out.print(aActdt.getAct_coupon() + ",");
		    System.out.print(aActdt.getAct_status() + ",");
		    System.out.println(aActdt.getAct_content() + ",");
		    System.out.println(aActdt.getAct_picture() + ",");
			System.out.println();
			}
			
		// 刪除
//		dao.delete(5);

	}
}

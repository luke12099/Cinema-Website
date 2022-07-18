package com.showing.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_showing;

public class ShowingDAO implements ShowingDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/HireMe");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	//JDBC
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/movietheater?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	
	

		private static final String INSERT_STMT = 
			"INSERT INTO showing (MV_ID,HL_ID,SH_STATE,SH_SEAT_STATE,SH_TIME,SH_TYPE) VALUES (?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT SH_ID,MV_ID,HL_ID,SH_STATE,SH_SEAT_STATE,SH_TIME,SH_TYPE FROM showing ORDER BY SH_ID";
		private static final String GET_ONE_STMT = 
			"SELECT SH_ID,MV_ID,HL_ID,SH_STATE,SH_SEAT_STATE,SH_TIME,SH_TYPE FROM showing WHERE SH_ID = ?";
		private static final String DELETE = 
			"DELETE FROM showing where SH_ID = ?";
		private static final String UPDATE = 
			"UPDATE showing SET MV_ID=?, HL_ID=?, SH_STATE=?, SH_SEAT_STATE=?, SH_TIME=?, SH_TYPE=? WHERE SH_ID = ?";
		private static final String GET_SHOWING_BY_DATE_STMT =
			"SELECT SH_ID,MV_ID,HL_ID,SH_STATE,SH_SEAT_STATE,SH_TIME,SH_TYPE FROM showing WHERE SH_TIME BETWEEN ? AND ADDTIME( ?, '20:00:00') ORDER BY SH_TIME";

	@Override
	public void insert(ShowingVO showingVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, showingVO.getmvId());
			pstmt.setInt(2, showingVO.getHL_ID());
			pstmt.setInt(3, showingVO.getSH_STATE());
			pstmt.setString(4, showingVO.getSH_SEAT_STATE());
			pstmt.setTimestamp(5, showingVO.getSH_TIME());
			pstmt.setInt(6, showingVO.getSH_TYPE());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(ShowingVO showingVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, showingVO.getmvId());
			pstmt.setInt(2, showingVO.getHL_ID());
			pstmt.setInt(3, showingVO.getSH_STATE());
			pstmt.setString(4,  showingVO.getSH_SEAT_STATE());
			pstmt.setTimestamp(5, showingVO.getSH_TIME());
			pstmt.setInt(6, showingVO.getSH_TYPE());
			pstmt.setInt(7, showingVO.getSH_ID());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer SH_ID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, SH_ID);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public ShowingVO findByPrimaryKey(Integer SH_ID) {

		ShowingVO showingVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, SH_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				showingVO = new ShowingVO();
				showingVO.setSH_ID(rs.getInt("SH_ID"));
				showingVO.setmvId(rs.getInt("MV_ID"));
				showingVO.setHL_ID(rs.getInt("HL_ID"));
				showingVO.setSH_STATE(rs.getInt("SH_STATE"));
				showingVO.setSH_SEAT_STATE(rs.getString("SH_SEAT_STATE"));
				showingVO.setSH_TIME(rs.getTimestamp("SH_TIME"));
				showingVO.setSH_TYPE(rs.getInt("SH_TYPE"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return showingVO;
	}

	@Override
	public List<ShowingVO> getAll() {
		List<ShowingVO> list = new ArrayList<ShowingVO>();
		ShowingVO showingVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// showingVO 也稱為 Domain objects
				showingVO = new ShowingVO();
				showingVO.setSH_ID(rs.getInt("SH_ID"));
				showingVO.setmvId(rs.getInt("MV_ID"));
				showingVO.setHL_ID(rs.getInt("HL_ID"));
				showingVO.setSH_STATE(rs.getInt("SH_STATE"));
				showingVO.setSH_SEAT_STATE(rs.getString("SH_SEAT_STATE"));
				showingVO.setSH_TIME(rs.getTimestamp("SH_TIME"));
				showingVO.setSH_TYPE(rs.getInt("SH_TYPE"));
				list.add(showingVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public List<ShowingVO> getAll(Map<String, String[]> map) {
		List<ShowingVO> list = new ArrayList<ShowingVO>();
		ShowingVO showingVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "select * from showing "
		          + jdbcUtil_CompositeQuery_showing.get_WhereCondition(map)
		          + "order by SH_ID";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				showingVO = new ShowingVO();
				showingVO.setSH_ID(rs.getInt("SH_ID"));
				showingVO.setmvId(rs.getInt("MV_ID"));
				showingVO.setHL_ID(rs.getInt("HL_ID"));
				showingVO.setSH_STATE(rs.getInt("SH_STATE"));
				showingVO.setSH_SEAT_STATE(rs.getString("SH_SEAT_STATE"));
				showingVO.setSH_TIME(rs.getTimestamp("SH_TIME"));
				showingVO.setSH_TYPE(rs.getInt("SH_TYPE"));
				list.add(showingVO); // Store the row in the List
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
//	@Override
//	public List<ShowingVO> getShowingByDate(String SH_TIME){
//		List<ShowingVO> list = new ArrayList<ShowingVO>();
//		ShowingVO showingVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_SHOWING_BY_DATE_STMT);
//
//			pstmt.setString(1, SH_TIME);
//			pstmt.setString(2, SH_TIME);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//
//				showingVO = new ShowingVO();
//				showingVO.setSH_ID(rs.getInt("SH_ID"));
//				showingVO.setmvId(rs.getInt("MV_ID"));
//				showingVO.setHL_ID(rs.getInt("HL_ID"));
//				showingVO.setSH_STATE(rs.getInt("SH_STATE"));
//				showingVO.setSH_SEAT_STATE(rs.getString("SH_SEAT_STATE"));
//				showingVO.setSH_TIME(rs.getTimestamp("SH_TIME"));
//				showingVO.setSH_TYPE(rs.getInt("SH_TYPE"));
//				list.add(showingVO); // Store the row in the List
//			}
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}	
//		
//		return list;
//	}
	
	
	@Override
	public List<ShowingVO> getShowingByDate(String SH_TIME){
		List<ShowingVO> list = new ArrayList<ShowingVO>();
		ShowingVO showingVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_SHOWING_BY_DATE_STMT);
			
			pstmt.setString(1, SH_TIME);
			pstmt.setString(2, SH_TIME);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				showingVO = new ShowingVO();
				showingVO.setSH_ID(rs.getInt("SH_ID"));
				showingVO.setmvId(rs.getInt("MV_ID"));
				showingVO.setHL_ID(rs.getInt("HL_ID"));
				showingVO.setSH_STATE(rs.getInt("SH_STATE"));
				showingVO.setSH_SEAT_STATE(rs.getString("SH_SEAT_STATE"));
				showingVO.setSH_TIME(rs.getTimestamp("SH_TIME"));
				showingVO.setSH_TYPE(rs.getInt("SH_TYPE"));
				list.add(showingVO); // Store the row in the List
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}
	
	
	
	
	
	//test
	public static void main(String[] args) {
		ShowingDAO dao = new ShowingDAO();
		List<ShowingVO> list = dao.getShowingByDate("2022-07-02 09:00:00");
		for(ShowingVO item : list ) {
			System.out.println(item.getSH_ID() + "," + item.getSH_TIME());
		}
	}
	
}
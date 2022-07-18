package com.tk_ord_dt.model;

import java.util.*;
import java.sql.*;

import com.fd_ord_dt.model.FdOrdDtVO;


public class TkOrdDtJDBCDAO implements TkOrdDtDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/movietheater?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	
	private static final String INSERT_STMT = 
			"INSERT INTO tk_ord_dt (TK_ORD_ID,TK_TYPE_ID,ACT_ID,STATE,SEAT,SELL_PRICE) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT TK_DT_ID,TK_ORD_ID,TK_TYPE_ID,ACT_ID,STATE,SEAT,SELL_PRICE FROM tk_ord_dt order by TK_DT_ID";
	private static final String GET_ONE_STMT = 
			"SELECT TK_DT_ID,TK_ORD_ID,TK_TYPE_ID,ACT_ID,STATE,SEAT,SELL_PRICE FROM tk_ord_dt where TK_DT_ID = ?";
	private static final String DELETE = 
			"DELETE FROM tk_ord_dt where TK_DT_ID = ?";
	private static final String UPDATE = 
			"UPDATE tk_ord_dt set TK_ORD_ID=?, TK_TYPE_ID=?, ACT_ID=?, STATE=?, SEAT=?, SELL_PRICE=? where TK_DT_ID = ?";

	@Override
	public void insert(TkOrdDtVO tkOrdDtVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setLong(1, tkOrdDtVO.getTkOrdID());
			pstmt.setInt(2, tkOrdDtVO.getTkTypeID());
			pstmt.setInt(3, tkOrdDtVO.getActID());
			pstmt.setByte(4, tkOrdDtVO.getState());
			pstmt.setString(5, tkOrdDtVO.getSeat());
			pstmt.setInt(6, tkOrdDtVO.getSellPrice());

			pstmt.executeUpdate();

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
	public void update(TkOrdDtVO tkOrdDtVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setLong(1, tkOrdDtVO.getTkOrdID());
			pstmt.setInt(2, tkOrdDtVO.getTkTypeID());
			pstmt.setInt(3, tkOrdDtVO.getActID());
			pstmt.setByte(4, tkOrdDtVO.getState());
			pstmt.setString(5, tkOrdDtVO.getSeat());
			pstmt.setInt(6, tkOrdDtVO.getSellPrice());
			pstmt.setLong(7, tkOrdDtVO.getTkDtID());
			
			
			pstmt.executeUpdate();

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
	public void delete(Long tkDtID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			

			pstmt.setLong(1, tkDtID);
			
			pstmt.executeUpdate();

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
	public TkOrdDtVO findByPrimaryKey(Long tkDtID) {

		TkOrdDtVO tkOrdDtVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);


			pstmt.setLong(1, tkDtID);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// tkOrdDtVO 也稱為 Domain objects
				tkOrdDtVO = new TkOrdDtVO();				
				tkOrdDtVO.setTkDtID(rs.getLong("TK_DT_ID"));
				tkOrdDtVO.setTkOrdID(rs.getLong("TK_ORD_ID"));
				tkOrdDtVO.setTkTypeID(rs.getInt("TK_TYPE_ID"));
				tkOrdDtVO.setActID(rs.getInt("ACT_ID"));
				tkOrdDtVO.setState(rs.getByte("STATE"));
				tkOrdDtVO.setSeat(rs.getString("SEAT"));
				tkOrdDtVO.setSellPrice(rs.getInt("SELL_PRICE"));
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
		return tkOrdDtVO;
	}
	
	@Override
	public List<TkOrdDtVO> getAll() {
		List<TkOrdDtVO> list = new ArrayList<TkOrdDtVO>();
		TkOrdDtVO tkOrdDtVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// tkOrdDtVO 也稱為 Domain objects
				tkOrdDtVO = new TkOrdDtVO();
				tkOrdDtVO.setTkDtID(rs.getLong("TK_DT_ID"));
				tkOrdDtVO.setTkOrdID(rs.getLong("TK_ORD_ID"));
				tkOrdDtVO.setTkTypeID(rs.getInt("TK_TYPE_ID"));
				tkOrdDtVO.setActID(rs.getInt("ACT_ID"));
				tkOrdDtVO.setState(rs.getByte("STATE"));
				tkOrdDtVO.setSeat(rs.getString("SEAT"));
				tkOrdDtVO.setSellPrice(rs.getInt("SELL_PRICE"));
				list.add(tkOrdDtVO); // Store the row in the list
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
	
	@Override
	public void insert2 (TkOrdDtVO tkOrdDtVO , Connection con) {

		PreparedStatement pstmt = null;

		try {

     		pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setLong(1, tkOrdDtVO.getTkOrdID());
			pstmt.setInt(2, tkOrdDtVO.getTkTypeID());
			pstmt.setInt(3, tkOrdDtVO.getActID());
			pstmt.setByte(4, tkOrdDtVO.getState());
			pstmt.setString(5, tkOrdDtVO.getSeat());
			pstmt.setInt(6, tkOrdDtVO.getSellPrice());

			Statement stmt=	con.createStatement();
			//stmt.executeUpdate("set auto_increment_offset=7001;"); //自增主鍵-初始值
			stmt.executeUpdate("set auto_increment_increment=1;");   //自增主鍵-遞增
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
		}

	}
	
	
	
	public static void main(String[] args) {

		TkOrdDtJDBCDAO dao = new TkOrdDtJDBCDAO();

		// 新增
//		TkOrdDtVO tkOrdDtVO1 = new TkOrdDtVO();				
//		tkOrdDtVO1.setTkOrdID(3L);		
//		tkOrdDtVO1.setTkTypeID(5);
//		tkOrdDtVO1.setActID(5);
//		tkOrdDtVO1.setState(Byte.valueOf("0"));
//		tkOrdDtVO1.setSeat("01032");
//		tkOrdDtVO1.setSellPrice(234);
//		dao.insert(tkOrdDtVO1);

		// 修改
//		TkOrdDtVO tkOrdDtVO2 = new TkOrdDtVO();
//		tkOrdDtVO2.setTkDtID(8L);
//		tkOrdDtVO2.setTkOrdID(3L);
//		tkOrdDtVO2.setTkTypeID(5);
//		tkOrdDtVO2.setActID(5);
//		tkOrdDtVO2.setState(Byte.valueOf("0"));
//		tkOrdDtVO2.setSeat("01042");
//		tkOrdDtVO2.setSellPrice(234);
//		dao.update(tkOrdDtVO2);

		// 刪除
//		dao.delete(8L);

		// 查詢
//		TkOrdDtVO tkOrdDtVO3 = dao.findByPrimaryKey(8L);
//		System.out.print(tkOrdDtVO3.getTkDtID() + ",");
//		System.out.print(tkOrdDtVO3.getTkOrdID() + ",");
//		System.out.print(tkOrdDtVO3.getTkTypeID() + ",");
//		System.out.print(tkOrdDtVO3.getActID() + ",");
//		System.out.print(tkOrdDtVO3.getState() + ",");
//		System.out.print(tkOrdDtVO3.getSeat() + ",");
//		System.out.println(tkOrdDtVO3.getSellPrice());
//		System.out.println("---------------------");

		// 查詢
		List<TkOrdDtVO> list = dao.getAll();
		for (TkOrdDtVO aTkOrdDt : list) {
			System.out.print(aTkOrdDt.getTkDtID() + ",");
			System.out.print(aTkOrdDt.getTkOrdID() + ",");
			System.out.print(aTkOrdDt.getTkTypeID() + ",");
			System.out.print(aTkOrdDt.getActID() + ",");
			System.out.print(aTkOrdDt.getState() + ",");
			System.out.print(aTkOrdDt.getSeat() + ",");
			System.out.print(aTkOrdDt.getSellPrice());
			System.out.println();
		}
	}
	
}

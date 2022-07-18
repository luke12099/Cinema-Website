package com.order_detail.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.merchandise_order.model.MerchOrdVO;

public class OrderDetailDAO implements OrderDetailDAO_interface {
//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/movietheater?serverTimezone=Asia/Taipei";
//	String userid = "root";
//	String passwd = "password";
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/HireMe");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO order_detail (MERCH_OR_ID, ITEM, MERCH_ID, OR_COUNT, OR_STATUS, OR_PRICE) Values (?, ?, ?, ?, ?, ?);";
	private static final String GET_ALL_STMT = "SELECT MERCH_OR_ID, ITEM, MERCH_ID, OR_COUNT, OR_STATUS, OR_PRICE FROM order_detail order by MERCH_OR_ID, ITEM;";
	private static final String GET_MerchOrdID_STMT = "SELECT MERCH_OR_ID, ITEM, MERCH_ID, OR_COUNT, OR_STATUS, OR_PRICE FROM order_detail WHERE MERCH_OR_ID = ? order by MERCH_OR_ID, ITEM;";
	private static final String GET_ONE_STMT = "SELECT MERCH_OR_ID, ITEM, MERCH_ID, OR_COUNT, OR_STATUS, OR_PRICE FROM order_detail where MERCH_OR_ID = ? AND ITEM = ? order by ITEM;";
	private static final String DELETE = "DELETE FROM order_detail WHERE MERCH_OR_ID = ? AND ITEM = ?;";
	private static final String UPDATE = "UPDATE order_detail set MERCH_ID=?, OR_COUNT=?, OR_STATUS=?, OR_PRICE=? WHERE MERCH_OR_ID = ? AND ITEM = ?;";
	private static final String UPDATE2 = "UPDATE order_detail set ITEM=? WHERE MERCH_OR_ID = ? AND ITEM = ?;";
	@Override
	public void insert(OrderDetailVO orderDetailVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, orderDetailVo.getMerchOrdID());
			pstmt.setInt(2, orderDetailVo.getItem());
			pstmt.setInt(3, orderDetailVo.getMerchID());
			pstmt.setInt(4, orderDetailVo.getOrdCount());
			pstmt.setByte(5, orderDetailVo.getOrdStatus());
			pstmt.setDouble(6, orderDetailVo.getOrdPrice());
			
			pstmt.executeUpdate();
			
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(OrderDetailVO orderDetailVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, orderDetailVo.getMerchID());
			pstmt.setInt(2, orderDetailVo.getOrdCount());
			pstmt.setByte(3, orderDetailVo.getOrdStatus());
			pstmt.setDouble(4, orderDetailVo.getOrdPrice());
			pstmt.setInt(5, orderDetailVo.getMerchOrdID());
			pstmt.setInt(6, orderDetailVo.getItem());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void resetItem(Integer newItem, OrderDetailVO orderDetailVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE2);
			pstmt.setInt(1, newItem);
			pstmt.setInt(2, orderDetailVo.getMerchOrdID());
			pstmt.setInt(3, orderDetailVo.getItem());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<OrderDetailVO> getAll() {
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		OrderDetailVO orderDetailVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				orderDetailVo = new OrderDetailVO();
				orderDetailVo.setMerchOrdID(rs.getInt("MERCH_OR_ID"));
				orderDetailVo.setItem(rs.getInt("ITEM"));
				orderDetailVo.setMerchID(rs.getInt("MERCH_ID"));
				orderDetailVo.setOrdStatus(rs.getByte("OR_STATUS"));
				orderDetailVo.setOrdPrice(rs.getDouble("OR_PRICE"));
				orderDetailVo.setOrdCount(rs.getInt("OR_COUNT"));
				list.add(orderDetailVo);
			}
		
		
		}catch (SQLException se) {
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
	public List<OrderDetailVO> getAll(Integer merchOrdID) {
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		OrderDetailVO orderDetailVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_MerchOrdID_STMT);
			pstmt.setInt(1, merchOrdID);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				orderDetailVo = new OrderDetailVO();
				orderDetailVo.setMerchOrdID(rs.getInt("MERCH_OR_ID"));
				orderDetailVo.setItem(rs.getInt("ITEM"));
				orderDetailVo.setMerchID(rs.getInt("MERCH_ID"));
				orderDetailVo.setOrdStatus(rs.getByte("OR_STATUS"));
				orderDetailVo.setOrdPrice(rs.getDouble("OR_PRICE"));
				orderDetailVo.setOrdCount(rs.getInt("OR_COUNT"));
				list.add(orderDetailVo);
			}
			
			
		}catch (SQLException se) {
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
	public void delete(Integer merchOrdID, Integer item) {
		Connection con = null;
		PreparedStatement pstmt = null;try {

			con = ds.getConnection();
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, merchOrdID);
			pstmt.setInt(2, item);
			pstmt.executeUpdate();

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
	public OrderDetailVO findByPrimaryKey(Integer merchOrdID, Integer item) {
		OrderDetailVO orderDetailVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, merchOrdID);
			pstmt.setInt(2, item);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				orderDetailVo = new OrderDetailVO();
				orderDetailVo.setMerchOrdID(rs.getInt("MERCH_OR_ID"));
				orderDetailVo.setItem(rs.getInt("ITEM"));
				orderDetailVo.setMerchID(rs.getInt("MERCH_ID"));
				orderDetailVo.setOrdStatus(rs.getByte("OR_STATUS"));
				orderDetailVo.setOrdPrice(rs.getDouble("OR_PRICE"));
				orderDetailVo.setOrdCount(rs.getInt("OR_COUNT"));
			}
		
		
		}catch (SQLException se) {
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
		return orderDetailVo;
	}

	@Override
	public void insert2(OrderDetailVO orderDetailVo, Connection con) {
		
		PreparedStatement pstmt = null;
//		 (MERCH_OR_ID, ITEM, MERCH_ID, OR_COUNT, OR_STATUS, OR_PRICE)
		try {
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, orderDetailVo.getMerchOrdID());
			pstmt.setInt(2, orderDetailVo.getItem());
			pstmt.setInt(3, orderDetailVo.getMerchID());
			pstmt.setInt(4, orderDetailVo.getOrdCount());
			pstmt.setByte(5, orderDetailVo.getOrdStatus());
			pstmt.setDouble(6, orderDetailVo.getOrdPrice());
			
			Statement stmt =con.createStatement();
			
//			stmt.executeUpdate("set auto_increment_offset=7001;"); //自增主鍵-初始值
//			stmt.executeUpdate("set auto_increment_increment=1;");   //自增主鍵-遞增
			pstmt.executeUpdate();
		}catch(SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
//					System.err.println("rolled back-由-emp");
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

}

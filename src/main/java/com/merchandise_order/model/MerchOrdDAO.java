package com.merchandise_order.model;

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

import com.merchandise_inf.model.MerchVO;
import com.order_detail.model.OrderDetailDAO;
import com.order_detail.model.OrderDetailVO;

public class MerchOrdDAO implements MerchOrdDAO_interface {
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
	
	private static final String INSERT_STMT = "INSERT INTO merchandise_order (MEMBER_ID, MERCH_OR_COUNT, MERCH_OR_STATUS) VALUES (?,?,?);";
	private static final String GET_ALL_STMT = "SELECT MERCH_OR_ID, MEMBER_ID, MERCH_OR_DATE, MERCH_OR_COUNT, MERCH_OR_STATUS FROM merchandise_order ORDER BY MERCH_OR_ID;";
	private static final String GET_MEMBER_STMT = "SELECT MERCH_OR_ID, MEMBER_ID, MERCH_OR_DATE, MERCH_OR_COUNT, MERCH_OR_STATUS FROM merchandise_order WHERE MEMBER_ID = ? ORDER BY MERCH_OR_ID desc;";
	private static final String GET_ONE_STMT = "SELECT MERCH_OR_ID, MEMBER_ID, MERCH_OR_DATE, MERCH_OR_COUNT, MERCH_OR_STATUS FROM merchandise_order WHERE MERCH_OR_ID = ?;";
	private static final String DELETE = "DELETE FROM merchandise_order WHERE MERCH_OR_ID = ?;";
	private static final String UPDATE = "UPDATE merchandise_order set MERCH_OR_COUNT = ?, MERCH_OR_STATUS = ? WHERE MERCH_OR_ID = ?;";
	@Override
	public void insert(MerchOrdVO merchOrdVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, merchOrdVo.getMemberID());
			pstmt.setDouble(2, merchOrdVo.getMerchOrdCount());
			pstmt.setByte(3, merchOrdVo.getMerchOrdStatus());
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
	public void update(MerchOrdVO merchOrdVo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setDouble(1, merchOrdVo.getMerchOrdCount());
			pstmt.setByte(2, merchOrdVo.getMerchOrdStatus());
			pstmt.setInt(3, merchOrdVo.getMerchOrdID());
			pstmt.executeUpdate();
			// Handle any driver errors
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
	public void delete(Integer merchOrdID) {
		Connection con = null;
		PreparedStatement pstmt = null;try {

			con = ds.getConnection();
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, merchOrdID);

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
	public MerchOrdVO findByPrimaryKey(Integer merchOrdID) {
		MerchOrdVO merchOrdVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, merchOrdID);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				merchOrdVO = new MerchOrdVO();
				merchOrdVO.setMerchOrdID(rs.getInt("MERCH_OR_ID"));
				merchOrdVO.setMemberID(rs.getInt("MEMBER_ID"));
				merchOrdVO.setMerchOrdDate(rs.getTimestamp("MERCH_OR_DATE"));
				merchOrdVO.setMerchOrdCount(rs.getDouble("MERCH_OR_COUNT"));
				merchOrdVO.setMerchOrdStatus(rs.getByte("MERCH_OR_STATUS"));
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
		return merchOrdVO;
	}

	@Override
	public List<MerchOrdVO> getAll() {
		List<MerchOrdVO> list = new ArrayList<MerchOrdVO>();
		MerchOrdVO merchOrdVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				merchOrdVO = new MerchOrdVO();
				merchOrdVO.setMerchOrdID(rs.getInt("MERCH_OR_ID"));
				merchOrdVO.setMemberID(rs.getInt("MEMBER_ID"));
				merchOrdVO.setMerchOrdDate(rs.getTimestamp("MERCH_OR_DATE"));
				merchOrdVO.setMerchOrdCount(rs.getDouble("MERCH_OR_COUNT"));
				merchOrdVO.setMerchOrdStatus(rs.getByte("MERCH_OR_STATUS"));
				list.add(merchOrdVO);
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
	public List<MerchOrdVO> getAll(Integer memberID) {
		List<MerchOrdVO> list = new ArrayList<MerchOrdVO>();
		MerchOrdVO merchOrdVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_MEMBER_STMT);
			pstmt.setInt(1, memberID);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				merchOrdVO = new MerchOrdVO();
				merchOrdVO.setMerchOrdID(rs.getInt("MERCH_OR_ID"));
				merchOrdVO.setMemberID(rs.getInt("MEMBER_ID"));
				merchOrdVO.setMerchOrdDate(rs.getTimestamp("MERCH_OR_DATE"));
				merchOrdVO.setMerchOrdCount(rs.getDouble("MERCH_OR_COUNT"));
				merchOrdVO.setMerchOrdStatus(rs.getByte("MERCH_OR_STATUS"));
				list.add(merchOrdVO);
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
	public void insertWithOrderDetail(MerchOrdVO merchOrdVo, List<OrderDetailVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			
			//關閉自動確認交易控制,在pstmt.executeUpdate之前
			con.setAutoCommit(false);
			
			//先新增訂單
			String cols[] = {"MERCH_OR_ID"};
			pstmt = con.prepareStatement(INSERT_STMT, cols);
//			MEMBER_ID, MERCH_OR_COUNT, MERCH_OR_STATUS) VALUES (?,?,?)
			pstmt.setInt(1, merchOrdVo.getMemberID());
			pstmt.setDouble(2, merchOrdVo.getMerchOrdCount());
			pstmt.setByte(3, merchOrdVo.getMerchOrdStatus());
			Statement stmt = con.createStatement();
//			stmt.executeUpdate("set auto_increment_offset=10;");    //自增主鍵-初始值
//			stmt.executeUpdate("set auto_increment_increment=10;"); //自增主鍵-遞增
			pstmt.executeUpdate();
			
			//取得對應的自增主鍵值
			Integer next_merchOrdId = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				next_merchOrdId = rs.getInt(1);
				System.out.println("取得自增主鍵值");
			}else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			//再同時新增訂單明細
			OrderDetailDAO dao = new OrderDetailDAO();
			
			for(OrderDetailVO orderDetailVo1: list) {
				orderDetailVo1.setMerchOrdID(next_merchOrdId);
				dao.insert2(orderDetailVo1, con);
			}
			
			// 2●設定於 pstm.executeUpdate()之後
			
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增部門編號" + next_merchOrdId + "時,共有員工" + list.size()
					+ "人同時被新增");
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

}

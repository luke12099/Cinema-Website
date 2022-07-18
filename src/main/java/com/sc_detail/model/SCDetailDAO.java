package com.sc_detail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.order_detail.model.OrderDetailVO;

public class SCDetailDAO implements SCDetailDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/movietheater?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	private static final String INSERT_STMT = "INSERT INTO sc_detail (MEMBER_ID, MERCH_ID, SC_COUNT) Values (?, ?, ?);";
	private static final String GET_ALL_STMT = "SELECT MEMBER_ID, MERCH_ID, SC_COUNT FROM sc_detail order by MEMBER_ID ,MERCH_ID;";
	private static final String GET_ALL_STMT_BY_MEMBERID = "SELECT MEMBER_ID, MERCH_ID, SC_COUNT FROM sc_detail WHERE MEMBER_ID=? order by MEMBER_ID ,MERCH_ID;";
	private static final String GET_ONE_STMT = "SELECT MEMBER_ID, MERCH_ID, SC_COUNT FROM sc_detail where MEMBER_ID=? and MERCH_ID=?;";
	private static final String DELETE = "DELETE FROM sc_detail WHERE MEMBER_ID=? and MERCH_ID=?;";
	private static final String UPDATE = "UPDATE sc_detail set SC_COUNT=? WHERE MEMBER_ID=? and MERCH_ID=?;";

	@Override
	public void insert(SCDetailVO scDetailVo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, scDetailVo.getMemberID());
			pstmt.setInt(2, scDetailVo.getMerchID());
			pstmt.setInt(3, scDetailVo.getScCount());
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
	public void update(SCDetailVO scDetailVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, scDetailVo.getScCount());
			pstmt.setInt(2, scDetailVo.getMemberID());
			pstmt.setInt(3, scDetailVo.getScCount());
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
	public void delete(Integer memberID, Integer merchID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

//			con = ds.getConnection();
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, memberID);
			pstmt.setInt(2, merchID);
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
	public SCDetailVO findByPrimaryKey(Integer memberID, Integer merchID) {
		SCDetailVO scDetailVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
//			con = ds.getConnection();
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, memberID);
			pstmt.setInt(2, merchID);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				scDetailVo = new SCDetailVO();
				scDetailVo.setMemberID(rs.getInt("MEMBER_ID"));
				scDetailVo.setMerchID(rs.getInt("MERCH_ID"));
				scDetailVo.setScCount(rs.getInt("SC_COUNT"));
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
		return scDetailVo;
	}

	@Override
	public List<SCDetailVO> getAll() {
		List<SCDetailVO> list = new ArrayList<SCDetailVO>();
		SCDetailVO scDetailVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
//			con = ds.getConnection();
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				scDetailVo = new SCDetailVO();
				scDetailVo.setMemberID(rs.getInt("MEMBER_ID"));
				scDetailVo.setMerchID(rs.getInt("MERCH_ID"));
				scDetailVo.setScCount(rs.getInt("SC_COUNT"));
				list.add(scDetailVo);
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
	public List<SCDetailVO> getAll(Integer memberID) {
		List<SCDetailVO> list = new ArrayList<SCDetailVO>();
		SCDetailVO scDetailVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
//			con = ds.getConnection();
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT_BY_MEMBERID);
			pstmt.setInt(1, memberID);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				scDetailVo = new SCDetailVO();
				scDetailVo.setMemberID(rs.getInt("MEMBER_ID"));
				scDetailVo.setMerchID(rs.getInt("MERCH_ID"));
				scDetailVo.setScCount(rs.getInt("SC_COUNT"));
				list.add(scDetailVo);
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

}

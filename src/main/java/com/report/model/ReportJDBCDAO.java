package com.report.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.common.JDBCUtil;

public class ReportJDBCDAO implements ReportDAO_interface{
	
	private static final String INSERT_STMT= 
			"INSERT into REPORT (CM_ID, MEMBER_ID, RP_TEXT, RP_TYPE, RP_STATE) "
			+ "values(?,?,?,?,?)";
	
	private static final String UPDATE= "UPDATE REPORT set RP_STATE=? "
			+ "where RP_ID = ?";		
	
	private static final String DELETE= "DELETE FROM REPORT WHERE RP_ID = ?";
	
	private static final String GET_ONE_STMT= "SELECT RP_ID, CM_ID, MEMBER_ID, RP_TEXT, RP_TYPE, RP_STATE, RP_DATE "
			+ "FROM REPORT WHERE RP_ID = ?";
	
	private static final String GET_ALL_STMT= "SELECT RP_ID, CM_ID, MEMBER_ID, RP_TEXT, RP_TYPE, RP_STATE, RP_DATE "
			+ "FROM REPORT order by RP_STATE";
	
	private static final String UPDATE_SAME_RP="UPDATE report SET RP_STATE = 1 WHERE CM_ID = ?";
	
	private static final String COUNT_UNDEAL_RP="SELECT count(*)as undeal FROM report WHERE RP_STATE = 0";
	
	@Override
	public void insert(ReportVO reportVO) {
		Connection conn = null;
		PreparedStatement pstmt = null ;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, reportVO.getCmId());
			pstmt.setInt(2, reportVO.getMemberId());
			pstmt.setString(3, reportVO.getRpText());
			pstmt.setString(4, reportVO.getRpType());
			pstmt.setInt(5, reportVO.getRpState());
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
			+e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
			+se.getMessage());
		} catch (NamingException e) {
			e.printStackTrace();
		}finally {
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
		
	}

	@Override
	public void update(ReportVO reportVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(UPDATE);
			
			pstmt.setInt(1, reportVO.getRpState());
			pstmt.setInt(2, reportVO.getRpId());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+se.getMessage());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
		
	}

	@Override
	public void delete(Integer rpId) {
		Connection conn = null ;
		PreparedStatement pstmt = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setInt(1, rpId);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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

	}

	@Override
	public ReportVO findByPrimaryKey(Integer rpId) {
		ReportVO reportVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, rpId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				reportVO = new ReportVO();
				reportVO.setRpId(rs.getInt("RP_ID"));
				reportVO.setCmId(rs.getInt("CM_ID"));
				reportVO.setMemberId(rs.getInt("MEMBER_ID"));
				reportVO.setRpText(rs.getString("RP_TEXT"));
				reportVO.setRpType(rs.getString("RP_TYPE"));
				reportVO.setRpState(rs.getInt("RP_STATE"));
				reportVO.setRpDate(rs.getTimestamp("RP_DATE"));
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver."
					+e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("Couldn't load database driver."
					+se.getMessage());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return reportVO;
	}

	@Override
	public List<ReportVO> getAll() {
		List<ReportVO> list = new ArrayList<ReportVO>();
		ReportVO reportVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				reportVO = new ReportVO();
				reportVO.setRpId(rs.getInt("RP_ID"));
				reportVO.setCmId(rs.getInt("CM_ID"));
				reportVO.setMemberId(rs.getInt("MEMBER_ID"));
				reportVO.setRpText(rs.getString("RP_TEXT"));
				reportVO.setRpType(rs.getString("RP_TYPE"));
				reportVO.setRpState(rs.getInt("RP_STATE"));
				reportVO.setRpDate(rs.getTimestamp("RP_DATE"));
				
				list.add(reportVO);
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver."
					+e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("Couldn't load database driver."
					+se.getMessage());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void updateSameRP(Integer cmId) {
		Connection conn = null ;
		PreparedStatement pstmt = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(UPDATE_SAME_RP);
			pstmt.setInt(1, cmId);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
		
	}
	@Override
	public Integer countUndealRP() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer undeal = null;
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(COUNT_UNDEAL_RP);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
			undeal = rs.getInt("undeal");
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver."
					+e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("Couldn't load database driver."
					+se.getMessage());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		
		return undeal;
	}
	
	public static void main(String[] args) {
		ReportJDBCDAO dao = new ReportJDBCDAO();
		
		// ?嚙踝蕭嚙�?
//		ReportVO reportVo1 = new ReportVO();
//		reportVo1.setCmId(1);
//		reportVo1.setMemberId(4);
//		reportVo1.setRpText("TEST123");
//		reportVo1.setRpType("TEST3456");
//		reportVo1.setRpState(0);
//		dao.insert(reportVo1);
		// 靽格
//		ReportVO reportVo2 = new ReportVO();
//		reportVo2.setRpState(1);
//		reportVo2.setRpId(1);
//		dao.update(reportVo2);
		
		// ?嚙踝蕭?嚙踝蕭
//		dao.delete(3);
		// GET ONE
//		ReportVo reportVo3 = dao.findByPrimaryKey(3) ;
//		System.out.print(reportVo3.getRpId() + ",");
//		System.out.print(reportVo3.getCmId() + ",");
//		System.out.print(reportVo3.getMemberId() + ",");
//		System.out.print(reportVo3.getRpText() + ",");
//		System.out.print(reportVo3.getRpType() + ",");
//		System.out.print(reportVo3.getRpState() + ",");
//		System.out.print(reportVo3.getRpDate() + ",");
//		System.out.println();
		
		// GET ALL
		
//		List<ReportVO> list = dao.getAll();
//		for(ReportVO report : list) {
//			System.out.println(report.getRpId()+ ",");
//			System.out.println(report.getCmId()+ ",");
//			System.out.println(report.getMemberId()+ ",");
//			System.out.println(report.getRpText()+ ",");
//			System.out.println(report.getRpType()+ ",");
//			System.out.println(report.getRpState()+ ",");
//			System.out.println(report.getRpDate()+ ",");
//		}
		
		System.out.println(dao.countUndealRP());
		
	
	}



	
}

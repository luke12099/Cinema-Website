package com.refundTicket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.common.JDBCUtil;
import com.tk_inf.model.TkInfVO;
import com.tk_ord_dt.model.TkOrdDtVO;

public class RefundTicketDAO implements RefundTicket_interface {

	public static final String GET_DT_BY_ORD=
			"SELECT TK_DT_ID,TK_ORD_ID,TK_TYPE_ID,ACT_ID,STATE,SEAT,SELL_PRICE FROM tk_ord_dt WHERE TK_ORD_ID = ?;";
	
	public static final String GET_TK_NAME=
			"SELECT TK_TYPE FROM tk_inf WHERE TK_TYPE_ID = ?";
	
	public static final String GET_ACT_TITLE=
			"SELECT ACT_SUBTITLE FROM activity_detail WHERE ACT_ID = ?";
	
	public static final String UPDATE_ONE_DT=
			"UPDATE tk_ord_dt SET STATE = ? WHERE TK_DT_ID = ?";
	
	@Override
	public List<TkOrdDtVO> getDtByOrd(Long tkOrdID) {
		List<TkOrdDtVO> list = new ArrayList<TkOrdDtVO>();
		TkOrdDtVO tkOrdDtVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(GET_DT_BY_ORD);
			pstmt.setLong(1, tkOrdID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				tkOrdDtVO = new TkOrdDtVO();				
				tkOrdDtVO.setTkDtID(rs.getLong("TK_DT_ID"));
				tkOrdDtVO.setTkOrdID(rs.getLong("TK_ORD_ID"));
				tkOrdDtVO.setTkTypeID(rs.getInt("TK_TYPE_ID"));
				tkOrdDtVO.setActID(rs.getInt("ACT_ID"));
				tkOrdDtVO.setState(rs.getByte("STATE"));
				tkOrdDtVO.setSeat(rs.getString("SEAT"));
				tkOrdDtVO.setSellPrice(rs.getInt("SELL_PRICE"));
				list.add(tkOrdDtVO);
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
	
	public String getTicketName(Integer tkTypeID) {
		
		String tkType = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(GET_TK_NAME);
			pstmt.setLong(1, tkTypeID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				tkType=rs.getString("TK_TYPE");
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
		return tkType;
	}
	

	@Override
	public String getActTitle(Integer act_id) {
		String act_title = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(GET_ACT_TITLE);
			pstmt.setLong(1, act_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				act_title=rs.getString("ACT_SUBTITLE");
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
		return act_title;
		
	}
	
	@Override
	public void updateOneDt(Integer seatState,Long tkDtID) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(UPDATE_ONE_DT);
			pstmt.setInt(1, seatState);
			pstmt.setLong(2, tkDtID);
			pstmt.executeUpdate();
			
			
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
		
	}
}

package com.tk_folder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import com.common.JDBCUtil;
import com.fd_ord_dt.model.FdOrdDtVO;
import com.tk_ord.model.TkOrdVO;
import com.tk_ord_dt.model.TkOrdDtVO;

public class TkFolderDAO implements TkFolder_interface {
	public static final String GET_ALL_TKORD=
			"SELECT t.TK_ORD_ID,t.MEMBER_ID,t.SH_ID,t.ORD_TIME FROM tk_ord t INNER JOIN showing s on t.SH_ID = s.SH_ID "
			+ "WHERE MEMBER_ID =? order by s.SH_TIME";
	
	public static final String GET_DT_COUNT =
			"SELECT count(TK_DT_ID)as DT_COUNT FROM tk_ord_dt WHERE TK_ORD_ID = ?";
	
	public static final String GET_FOOD_COUNT=
			"SELECT SUM(FD_COUNT)as FD_TOTAL FROM fd_ord_dt WHERE TK_ORD_ID = ?";
	
	public static final String GET_FOOD_ORD_DT=
			"SELECT d.FD_ID,i.FD_NAME,d.FD_COUNT,d.SELL_PRICE,d.FD_STATE "
			+ "FROM fd_ord_dt d INNER join fd_inf i "
			+ "	on d.FD_ID = i.FD_ID "
			+ "WHERE TK_ORD_ID =?";
	
	public static final String UPDATE_FOOD_ORDER=
			"update fd_ord_dt SET FD_STATE =? WHERE TK_ORD_ID = ?";
	
	public static final String VERIFY_EMP_CODE=
			"select * FROM CODE WHERE CODE = ?";
	
	@Override
	public List<TkOrdVO> getAllTkOrd(Integer member_ID) {
		
		List<TkOrdVO> list = new ArrayList<TkOrdVO>();
		TkOrdVO tkOrdVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(GET_ALL_TKORD);
			pstmt.setLong(1, member_ID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				tkOrdVO = new TkOrdVO();
				tkOrdVO.setTkOrdID(rs.getLong("TK_ORD_ID"));
				tkOrdVO.setMemberID(rs.getInt("MEMBER_ID"));
				tkOrdVO.setShID(rs.getInt("SH_ID"));
				list.add(tkOrdVO);
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
	public Integer getDtCount(Long tkOrdID) {
		
		Integer dtCount = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(GET_DT_COUNT);
			pstmt.setLong(1, tkOrdID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dtCount = rs.getInt("DT_COUNT");
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
		
		return dtCount;
	}

	@Override
	public Integer getFoodCount(Long tkOrdID) {
		Integer foodCount = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(GET_FOOD_COUNT);
			pstmt.setLong(1, tkOrdID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				foodCount = rs.getInt("FD_TOTAL");
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
		
		return foodCount;
	}

	@Override
	public Map<String, Object> getFoodOrdDt(Long tkOrdID) {
		
		Map<String,Object> map = new HashMap<String, Object>();
		List<FdOrdDtVO>foodOrdList = new ArrayList<FdOrdDtVO>();
		List<String>foodNameList = new ArrayList<String>();
		
		FdOrdDtVO fdOrdDtVO;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(GET_FOOD_ORD_DT);
			pstmt.setLong(1, tkOrdID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				fdOrdDtVO = new FdOrdDtVO();
				fdOrdDtVO.setFdID(rs.getInt("FD_ID"));
				fdOrdDtVO.setFdCount(rs.getInt("FD_COUNT"));
				fdOrdDtVO.setFdState(rs.getByte("FD_STATE"));
				fdOrdDtVO.setSellPrice(rs.getInt("SELL_PRICE"));
				foodOrdList.add(fdOrdDtVO);
				foodNameList.add(rs.getString("FD_NAME"));
			}
			map.put("foodOrdList", foodOrdList);
			map.put("foodNameList", foodNameList);
			
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
		
		return map;
	}

	@Override
	public void updateFoodOrd(Byte fdState, Long tkOrdID) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(UPDATE_FOOD_ORDER);
			pstmt.setByte(1, fdState);
			pstmt.setLong(2, tkOrdID);
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

	@Override
	public Integer verifyCode(Integer verifyEmpCode) {
		
		Integer verifyResult = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(VERIFY_EMP_CODE);
			pstmt.setLong(1, verifyEmpCode);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				verifyResult = 1;
			}else {
				verifyResult = 0;
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
		
		return verifyResult;
		
	}

	
}

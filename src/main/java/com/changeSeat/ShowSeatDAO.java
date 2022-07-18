package com.changeSeat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.common.JDBCUtil;
import com.hall.model.HallVO;

public class ShowSeatDAO implements ShowSeatDAO_interface {

	private static final String GET_DATE=
			"select date_format(SH_TIME,'%Y-%m-%d')as ShowingDay from showing where HL_ID=? and SH_TIME >= NOW() group by date_format(SH_TIME,'%Y-%m-%d')";
	
	private static final String GET_TIME_BY_DATE=
			"SELECT * FROM showing where HL_ID=? and SH_TIME >= ? and SH_TIME <= ? order by SH_TIME";
	
	private static final String GET_SHOW_BY_TIME=
			"SELECT SH_ID,MV_ID,HL_ID,SH_STATE,SH_SEAT_STATE,SH_TIME,SH_TYPE FROM showing WHERE SH_ID = ?";
	
	private static final String GET_SEAT_BY_HL=
			"SELECT * FROM hall WHERE HL_ID=?";
	
	private static final String UPDATE_SHOW_SEAT=
			"Update showing set SH_SEAT_STATE =? where SH_ID = ?";
	
	// 上線用 內含Now排除已經播完的場次
	// select date_format(SH_TIME,'%Y-%m-%d')ShowingDay from showing where SH_TIME >= NOW() group by date_format(SH_TIME,'%Y-%m-%d') 
	
	@Override
	public ShowSeatVO getShowByTime(Integer SH_ID) {
		
		ShowSeatVO showSeatVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(GET_SHOW_BY_TIME);
			pstmt.setInt(1, SH_ID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				showSeatVO = new ShowSeatVO();
				showSeatVO.setSH_ID(rs.getInt("SH_ID"));
				showSeatVO.setmvId(rs.getInt("MV_ID"));
				showSeatVO.setHL_ID(rs.getInt("HL_ID"));
				showSeatVO.setSH_STATE(rs.getInt("SH_STATE"));
				showSeatVO.setSH_SEAT_STATE(rs.getString("SH_SEAT_STATE"));
				showSeatVO.setSH_TIME(rs.getTimestamp("SH_TIME"));
				showSeatVO.setSH_TYPE(rs.getInt("SH_TYPE"));
				
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
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
		
		return showSeatVO;
	}

	@Override
	public HallVO  getSeatByHL(Integer hlId) {
		
		HallVO hallVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(GET_SEAT_BY_HL);
			pstmt.setInt(1, hlId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				hallVO = new HallVO();
				hallVO.setHlId(rs.getInt("HL_ID"));
				hallVO.setHlName(rs.getString("HL_NAME"));
				hallVO.setHlSeat(rs.getString("HL_SEAT"));
				hallVO.setHlRow(rs.getInt("HL_ROW"));
				hallVO.setHlCol(rs.getInt("HL_COL"));
				hallVO.setHlType(rs.getInt("HL_TYPE"));
				hallVO.setHlSeatCount(rs.getInt("HL_SEATCOUNT"));
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
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
		
		return hallVO;
	}

	@Override
	public List getDate(Integer hlId) {
		List list = new ArrayList();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(GET_DATE);
			pstmt.setInt(1, hlId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				String dateOption = rs.getString("ShowingDay");
				
				list.add(dateOption); 
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
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
	public List<ShowSeatVO> getTimeByDate(Integer hlId, String dateOption) {
		
		List<ShowSeatVO> list = new ArrayList<ShowSeatVO>();
		ShowSeatVO showSeatVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(GET_TIME_BY_DATE);
			pstmt.setInt(1, hlId);
			
			String dateStart = dateOption + " 00:00:00";
			String dateEnd = dateOption + " 23:59:59";
			
			pstmt.setString(2, dateStart);
			pstmt.setString(3, dateEnd);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				showSeatVO = new ShowSeatVO();
				showSeatVO.setSH_ID(rs.getInt("SH_ID"));
				showSeatVO.setmvId(rs.getInt("MV_ID"));
				showSeatVO.setHL_ID(rs.getInt("HL_ID"));
				showSeatVO.setSH_STATE(rs.getInt("SH_STATE"));
				showSeatVO.setSH_SEAT_STATE(rs.getString("SH_SEAT_STATE"));
				showSeatVO.setSH_TIME(rs.getTimestamp("SH_TIME"));
				showSeatVO.setSH_TYPE(rs.getInt("SH_TYPE"));
				list.add(showSeatVO); 
				
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
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
	
	public void updateShowSeat (String SH_SEAT_STATE,Integer SH_ID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(UPDATE_SHOW_SEAT);
			
			pstmt.setString(1,SH_SEAT_STATE);
			pstmt.setInt(2,SH_ID);
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver."
					+e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured."
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

	public static void main(String[] args) {
		
		ShowSeatDAO dao = new ShowSeatDAO();
		
//		List list = dao.getDate(1);
//		
//		for(Object date : list) {
//			System.out.println(date);
//		}
		
		List<ShowSeatVO> list1 = dao.getTimeByDate(1, "2022-06-01"); 
		for(ShowSeatVO showSeatVO : list1) {
		System.out.println(showSeatVO.getSH_ID());
		System.out.println(showSeatVO.getHL_ID());
		System.out.println(showSeatVO.getSH_TIME());
		System.out.println("-------------");
	}
	}

	
}



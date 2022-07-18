package com.hall.model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.NamingException;

import com.common.JDBCUtil;

public class HallJDBCDAO implements HallDAO_interface {
	
	private static final String INSERT_STMT=
			"INSERT into Hall(HL_NAME, HL_SEAT, HL_ROW, HL_COL, HL_TYPE, HL_SEATCOUNT) "
			+ "values(?,?,?,?,?,?)";
	
	private static final String UPDATE=
			"UPDATE Hall set "
			+ "HL_NAME=?, HL_SEAT=?, HL_ROW=?, HL_COL=?, HL_TYPE=?, HL_SEATCOUNT=? "
			+ "where HL_ID = ?; ";
	
	private static final String DELETE=
			"DELETE from Hall where HL_ID = ?;";
	
	private static final String GET_ONE_STMT=
			"SELECT HL_ID, HL_NAME, HL_SEAT, HL_ROW, HL_COL, HL_TYPE, HL_SEATCOUNT FROM HALL "
			+ "where HL_ID = ?";
	
	private static final String GET_ALL_STMT=
			"SELECT HL_ID, HL_NAME, HL_SEAT, HL_ROW, HL_COL, HL_TYPE, HL_SEATCOUNT FROM HALL order by HL_ID";
	
	
	@Override
	public void insert(HallVO hallVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,hallVO.getHlName());
			pstmt.setString(2,hallVO.getHlSeat());
			pstmt.setInt(3, hallVO.getHlRow());
			pstmt.setInt(4, hallVO.getHlCol());
			pstmt.setInt(5, hallVO.getHlType());
			pstmt.setInt(6, hallVO.getHlSeatCount());
			
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

	@Override
	public void update(HallVO hallVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(UPDATE);
			
			pstmt.setString(1,hallVO.getHlName());
			pstmt.setString(2,hallVO.getHlSeat());
			pstmt.setInt(3, hallVO.getHlRow());
			pstmt.setInt(4, hallVO.getHlCol());
			pstmt.setInt(5, hallVO.getHlType());
			pstmt.setInt(6, hallVO.getHlSeatCount());
			pstmt.setInt(7, hallVO.getHlId());
			
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

	@Override
	public void delete(Integer hlId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(DELETE);
			
			pstmt.setInt(1, hlId);
		
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver."
					+e.getMessage());
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured."
					+e.getMessage());
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
	public HallVO findByPrimaryKey(Integer hlId) {
		
		HallVO hallVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1,hlId);
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
			throw new RuntimeException("Couldn't load database driver."
					+e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured."
					+se.getMessage());
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
	public List<HallVO> getAll() {
		
		List<HallVO> list = new ArrayList<HallVO>();
		HallVO hallVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(GET_ALL_STMT);
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
				
				list.add(hallVO);
			}
			
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
	
	
	public static void main(String[] args) {
		HallJDBCDAO dao = new HallJDBCDAO();
		
		// ?嚙踝蕭嚙�?
		
//		HallVO hallVO1 = new HallVO();
//		hallVO1.setHlName("HALL111");
//		hallVO1.setHlSeat("123123123");
//		hallVO1.setHlRow(10);
//		hallVO1.setHlCol(10);
//		hallVO1.setHlType(1);
//		hallVO1.setHlSeatCount(100);
//		
//		dao.insert(hallVO1);
//		
		// 靽格
		
		HallVO hallVO2 = new HallVO();
		hallVO2.setHlId(2);
		hallVO2.setHlName("TEST HALL");
		hallVO2.setHlSeat("123123123");
		hallVO2.setHlRow(10);
		hallVO2.setHlCol(10);
		hallVO2.setHlType(1);
		hallVO2.setHlSeatCount(100);
		
		dao.update(hallVO2);
		
		// ?嚙踝蕭?嚙踝蕭
		
//		dao.delete(7);
		
		// GET ONE
//		
//		HallVO hallVO3 = dao.findByPrimaryKey(1);
//		System.out.print(hallVO3.getHlId() + ",");
//		System.out.print(hallVO3.getHlName() + ",");
//		System.out.print(hallVO3.getHlSeat() + ",");
//		System.out.print(hallVO3.getHlRow() + ",");
//		System.out.print(hallVO3.getHlCol() + ",");
//		System.out.print(hallVO3.getHlType() + ",");
//		System.out.print(hallVO3.getHlSeatCount() + ",");
//		System.out.println();
//		
		// GET ALL
		
//		List<HallVO> list = dao.getAll();
//		for (HallVO hallVO : list) {
//			System.out.print(hallVO.getHlId() + ",");
//			System.out.print(hallVO.getHlName() + ",");
//			System.out.print(hallVO.getHlSeat() + ",");
//			System.out.print(hallVO.getHlRow() + ",");
//			System.out.print(hallVO.getHlCol() + ",");
//			System.out.print(hallVO.getHlType() + ",");
//			System.out.print(hallVO.getHlSeatCount() + ",");
//			System.out.println();
//		}
		
	}

	
}

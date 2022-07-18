package com.fd_inf.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FdInfJDBCDAO implements FdInfDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/movietheater?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	
	private static final String INSERT_STMT = 
			"INSERT INTO fd_inf (FD_TYPE,FD_NAME,FD_PRICE,FD_DT,FD_PICTURE,FD_STATE) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT FD_ID,FD_TYPE,FD_NAME,FD_PRICE,FD_DT,FD_PICTURE,FD_STATE FROM fd_inf order by FD_ID desc";
	private static final String GET_ONE_STMT = 
			"SELECT FD_ID,FD_TYPE,FD_NAME,FD_PRICE,FD_DT,FD_PICTURE,FD_STATE FROM fd_inf where FD_ID = ?";
	private static final String DELETE = 
			"DELETE FROM fd_inf where FD_ID = ?";
	private static final String UPDATE = 
			"UPDATE fd_inf set FD_TYPE=?, FD_NAME=?, FD_PRICE=?, FD_DT=?, FD_PICTURE=?, FD_STATE=? where FD_ID = ?";
	
	
	
	private static final String SELECT_ON_OR_OFF_STMT = 
			"select IF(EXISTS(select * from fd_inf where FD_ID = ? and FD_STATE = 1 ),'ON','OFF' )as `action`";
	private static final String UPDATE_FOOD_STATUS_ON_STMT = 
			"update fd_inf set FD_STATE = 1 where FD_ID = ?";
	private static final String UPDATE_FOOD_STATUS_OFF_STMT = 
			"update fd_inf set FD_STATE = 0 where FD_ID = ?";
	
	@Override
	public void onOrDownFoodStatus(Integer FD_ID) {

		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;	
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(url, userid, passwd);			

			pstmt1 = con.prepareStatement(SELECT_ON_OR_OFF_STMT);	
			pstmt1.setInt(1, FD_ID);

			rs = pstmt1.executeQuery();
			
			//再判斷是要上架還是下架
			rs.next();
			if(rs.getString("action").equals("ON")) {
				pstmt2 = con.prepareStatement(UPDATE_FOOD_STATUS_OFF_STMT);			
				pstmt2.setInt(1, FD_ID);
				pstmt2.executeUpdate();	
			}else {
				pstmt2 = con.prepareStatement(UPDATE_FOOD_STATUS_ON_STMT);	
				pstmt2.setInt(1, FD_ID);
				pstmt2.executeUpdate();	
			}
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
			if (pstmt2 != null) {
				try {
					pstmt2.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt1 != null) {
				try {
					pstmt1.close();
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
	public void insert(FdInfVO fdInfVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);


			pstmt.setByte(1, fdInfVO.getFdType());
			pstmt.setString(2, fdInfVO.getFdName());
			pstmt.setInt(3, fdInfVO.getFdprice());
			pstmt.setString(4, fdInfVO.getFdDT());
			pstmt.setBytes(5, fdInfVO.getFdPicture() );
			pstmt.setByte(6, fdInfVO.getFdState());
			
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
	public void update(FdInfVO fdInfVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			
			
			pstmt.setByte(1, fdInfVO.getFdType());
			pstmt.setString(2, fdInfVO.getFdName());
			pstmt.setInt(3, fdInfVO.getFdprice());
			pstmt.setString(4, fdInfVO.getFdDT());
			pstmt.setBytes(5, fdInfVO.getFdPicture() );
			pstmt.setByte(6, fdInfVO.getFdState());
			pstmt.setInt(7, fdInfVO.getFdID());
			
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
	public void delete(Integer fdID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, fdID);

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
	public FdInfVO findByPrimaryKey(Integer fdID) {

		FdInfVO fdInfVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, fdID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// fdInfVO 也稱為 Domain objects
				fdInfVO = new FdInfVO();
				fdInfVO.setFdID(rs.getInt("FD_ID"));
				fdInfVO.setFdType(rs.getByte("FD_TYPE"));
				fdInfVO.setFdName(rs.getString("FD_NAME"));
				fdInfVO.setFdprice(rs.getInt("FD_PRICE"));
				fdInfVO.setFdDT(rs.getString("FD_DT"));
				fdInfVO.setFdPicture(rs.getBytes("FD_PICTURE"));
				fdInfVO.setFdState(rs.getByte("FD_STATE"));
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
		return fdInfVO;
	}
	
	@Override
	public List<FdInfVO> getAll() {
		List<FdInfVO> list = new ArrayList<FdInfVO>();
		FdInfVO fdInfVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// fdInfVO 也稱為 Domain objects
				fdInfVO = new FdInfVO();
				fdInfVO.setFdID(rs.getInt("FD_ID"));
				fdInfVO.setFdType(rs.getByte("FD_TYPE"));
				fdInfVO.setFdName(rs.getString("FD_NAME"));
				fdInfVO.setFdprice(rs.getInt("FD_PRICE"));
				fdInfVO.setFdDT(rs.getString("FD_DT"));
				fdInfVO.setFdPicture(rs.getBytes("FD_PICTURE"));
				fdInfVO.setFdState(rs.getByte("FD_STATE"));
				list.add(fdInfVO); // Store the row in the list
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
	public static void main(String[] args) {

		FdInfJDBCDAO dao = new FdInfJDBCDAO();

		// 新增
//		FdInfVO fdInfVO1 = new FdInfVO();
//		fdInfVO1.setFdType(Byte.valueOf("1"));
//		fdInfVO1.setFdName("熱狗");
//		fdInfVO1.setFdprice(100);
//		fdInfVO1.setFdDT("90克");
//		fdInfVO1.setFdPicture("");
//		fdInfVO1.setFdState(Byte.valueOf("0"));
//		dao.insert(fdInfVO1);

		// 修改
//		FdInfVO fdInfVO2 = new FdInfVO();
//		fdInfVO2.setFdID(7);
//		fdInfVO2.setFdType(Byte.valueOf("1"));
//		fdInfVO2.setFdName("熱狗");
//		fdInfVO2.setFdprice(100);
//		fdInfVO2.setFdDT("90克");
//		fdInfVO2.setFdPicture("");
//		fdInfVO2.setFdState(Byte.valueOf("1"));
//		dao.update(fdInfVO2);

		
		// 刪除 記得重設 指令為 ALTER TABLE movietheater.code AUTO_INCREMENT = 2;
		// 刪除
//		dao.delete(7);

		// 查詢
//		FdInfVO fdInfVO3 = dao.findByPrimaryKey(7);
//		System.out.print(fdInfVO3.getFdID() + ",");
//		System.out.print(fdInfVO3.getFdType() + ",");
//		System.out.print(fdInfVO3.getFdName() + ",");
//		System.out.print(fdInfVO3.getFdprice() + ",");
//		System.out.print(fdInfVO3.getFdDT() + ",");
//		System.out.print(fdInfVO3.getFdPicture() + ",");
//		System.out.println(fdInfVO3.getFdState());
//		System.out.println("---------------------");

		// 查詢
		List<FdInfVO> list = dao.getAll();
		for (FdInfVO aFdInf : list) {
			System.out.print(aFdInf.getFdID() + ",");
			System.out.print(aFdInf.getFdType() + ",");
			System.out.print(aFdInf.getFdName() + ",");
			System.out.print(aFdInf.getFdprice() + ",");
			System.out.print(aFdInf.getFdDT() + ",");
			System.out.print(aFdInf.getFdPicture() + ",");
			System.out.print(aFdInf.getFdState());
			System.out.println();
		}
	}

}

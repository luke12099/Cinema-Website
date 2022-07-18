package com.tk_inf.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TkInfJDBCDAO implements TkInfDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/movietheater?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = 
			"INSERT INTO tk_inf (TK_TYPE,TK_PRICE,TK_DI,TK_TYPE_DT) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT TK_TYPE_ID,TK_TYPE,TK_PRICE,TK_DI,TK_TYPE_DT FROM tk_inf order by TK_TYPE_ID";
	private static final String GET_ONE_STMT = 
			"SELECT TK_TYPE_ID,TK_TYPE,TK_PRICE,TK_DI,TK_TYPE_DT FROM tk_inf where TK_TYPE_ID = ?";
	private static final String DELETE = 
			"DELETE FROM tk_inf where TK_TYPE_ID = ?";
	private static final String UPDATE = 
			"UPDATE tk_inf set TK_TYPE=?, TK_PRICE=?, TK_DI=?, TK_TYPE_DT=? where TK_TYPE_ID = ?";

	@Override
	public void insert(TkInfVO tkInfVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, tkInfVO.getTkType());
			pstmt.setInt(2, tkInfVO.getTkPrice());
			pstmt.setByte(3, tkInfVO.getTkDI());
			pstmt.setString(4, tkInfVO.getTkTypeDT());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
	public void update(TkInfVO tkInfVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, tkInfVO.getTkType());
			pstmt.setInt(2, tkInfVO.getTkPrice());
			pstmt.setByte(3, tkInfVO.getTkDI());
			pstmt.setString(4, tkInfVO.getTkTypeDT());
			pstmt.setInt(5, tkInfVO.getTkTypeID());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
	public void delete(Integer tkTypeID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, tkTypeID);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
	public TkInfVO findByPrimaryKey(Integer tkTypeID) {

		TkInfVO tkInfVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, tkTypeID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// TkInfVO 也稱為 Domain objects
				tkInfVO = new TkInfVO();
				tkInfVO.setTkTypeID(rs.getInt("TK_TYPE_ID"));
				tkInfVO.setTkType(rs.getString("TK_TYPE"));
				tkInfVO.setTkPrice(rs.getInt("TK_PRICE"));
				tkInfVO.setTkDI(rs.getByte("TK_DI"));
				tkInfVO.setTkTypeDT(rs.getString("TK_TYPE_DT"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return tkInfVO;
	}

	@Override
	public List<TkInfVO> getAll() {
		List<TkInfVO> list = new ArrayList<TkInfVO>();
		TkInfVO tkInfVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// TkInfVO 也稱為 Domain objects
				tkInfVO = new TkInfVO();
				tkInfVO.setTkTypeID(rs.getInt("TK_TYPE_ID"));
				tkInfVO.setTkType(rs.getString("TK_TYPE"));
				tkInfVO.setTkPrice(rs.getInt("TK_PRICE"));
				tkInfVO.setTkDI(rs.getByte("TK_DI"));
				tkInfVO.setTkTypeDT(rs.getString("TK_TYPE_DT"));
				list.add(tkInfVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

		TkInfJDBCDAO dao = new TkInfJDBCDAO();

		// 新增
//		TkInfVO tkInfVO1 = new TkInfVO();
//		tkInfVO1.setTkType("軍警票");
//		tkInfVO1.setTkPrice(260);
//		tkInfVO1.setTkDI(Byte.valueOf("0"));
//		tkInfVO1.setTkTypeDT("數位");
//		dao.insert(tkInfVO1);

		// 修改
//		TkInfVO tkInfVO2 = new TkInfVO();
//		tkInfVO2.setTkTypeID(7);
//		tkInfVO2.setTkType("軍警票");
//		tkInfVO2.setTkPrice(280);
//		tkInfVO2.setTkDI(Byte.valueOf("0"));
//		tkInfVO2.setTkTypeDT("數位");
//		dao.update(tkInfVO2);

		// 刪除
//		dao.delete(7);

		// 查詢
//		TkInfVO tkInfVO3 = dao.findByPrimaryKey(6);
//		System.out.print(tkInfVO3.getTkTypeID() + ",");
//		System.out.print(tkInfVO3.getTkType() + ",");
//		System.out.print(tkInfVO3.getTkPrice() + ",");
//		System.out.print(tkInfVO3.getTkDI() + ",");
//		System.out.print(tkInfVO3.getTkTypeDT() + ",");
//		System.out.println("---------------------");

		// 查詢
		List<TkInfVO> list = dao.getAll();
		for (TkInfVO aTkInf : list) {
			System.out.print(aTkInf.getTkTypeID() + ",");
			System.out.print(aTkInf.getTkType() + ",");
			System.out.print(aTkInf.getTkPrice() + ",");
			System.out.print(aTkInf.getTkDI() + ",");
			System.out.print(aTkInf.getTkTypeDT() + ",");
			System.out.println();
		}
	}
}

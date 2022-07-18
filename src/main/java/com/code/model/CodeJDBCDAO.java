package com.code.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CodeJDBCDAO implements CodeDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/movietheater?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	
	private static final String GET_ALL_STMT = 
			"SELECT CODE FROM code order by CODE";
	private static final String GET_ONE_STMT = 
			"SELECT CODE FROM code where CODE = ?";
	private static final String UPDATE = 
			"UPDATE code set CODE=?";
	
	
	@Override
	public void update(CodeVO codeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, codeVO.getCode());

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
	public CodeVO findByPrimaryKey(Integer code) {

		CodeVO codeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, code);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// CodeVO 也稱為 Domain objects
				codeVO = new CodeVO();
				codeVO.setCode(rs.getInt("CODE"));

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
		return codeVO;
	}

	@Override
	public List<CodeVO> getAll() {
		List<CodeVO> list = new ArrayList<CodeVO>();
		CodeVO codeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// codeVO 也稱為 Domain objects
				codeVO = new CodeVO();
				codeVO.setCode(rs.getInt("CODE"));
				list.add(codeVO); // Store the row in the list
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

		CodeJDBCDAO dao = new CodeJDBCDAO();
		
		// 修改
//		CodeVO codeVO2 = new CodeVO();
//		codeVO2.setCode(2222);
//		dao.update(codeVO2);



		// 查詢
//		CodeVO codeVO3 = dao.findByPrimaryKey(2222);
//		System.out.print(codeVO3.getCode() + ",");
//		System.out.println("---------------------");

		// 查詢
		List<CodeVO> list = dao.getAll();
		for (CodeVO aCode : list) {
			System.out.print(aCode.getCode() + ",");
			System.out.println();
		}
	}
}

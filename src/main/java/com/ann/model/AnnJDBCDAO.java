package com.ann.model;

import java.sql.*;
import java.util.*;



public class AnnJDBCDAO implements AnnDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/movietheater?serverTimezone=Asia/Taipei"; //連結的MySQL路徑
	String userid = "root"; // MySQL 帳號
	String passwd = "password"; // MySQL 密碼
	
	/* 新增 */
	private static final String INSERT_STMT = // 宣告變數INSERT_STMT，自動編號不用加：Ann編號不用，因為自動生成
			"insert into movietheater.announcement" 
			+ " (ann_date, ann_title, ann_content, ann_picture) values" 
			+ " (?, ?, ?, ?)";
	/* 單一查詢 */
	private static final String GET_ONE_STMT = 
			"select ann_no, ann_date, ann_title, ann_content, ann_picture"
			+ " from movietheater.announcement where ann_no = ?";
	/* 全部查詢 */
	private static final String GET_ALL_STMT = 
			"select ann_no, ann_date, ann_title, ann_content, ann_picture"
			+ " from movietheater.announcement order by ann_no";
	
	/* 全部查詢 */
	private static final String GET_ALL_ANNFRONT = 
			"select ann_no, ann_date, ann_title, ann_content, ann_picture"
			+ " from movietheater.announcement order by ann_no";
	
	/* 修改 */
	private static final String UPDATE = 
			"update movietheater.announcement set"
			+ " ann_date=?, ann_title=?, ann_content=?, ann_picture=?"
			+ " where ann_no = ?";
	/* 刪除 */
	private static final String DELETE = 
			"delete from movietheater.announcement where ann_no = ?";
	
	@Override /* 新增 */
	public void insert(AnnVO annVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setDate(1, annVO.getAnn_date());
			pstmt.setString(2, annVO.getAnn_title());
			pstmt.setString(3, annVO.getAnn_content());
			pstmt.setString(4, annVO.getAnn_picture());


			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			// Handle any SQL errors
		} catch (SQLException e) {
			e.printStackTrace();
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override //修改
	public void update(AnnVO annVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setDate(1, annVO.getAnn_date());
			pstmt.setString(2, annVO.getAnn_title());
			pstmt.setString(3, annVO.getAnn_content());
			pstmt.setString(4, annVO.getAnn_picture());
			pstmt.setInt(5, annVO.getAnn_no());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			// Handle any SQL errors
		} catch (SQLException e) {
			e.printStackTrace();
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override //刪除
	public void delete(Integer Ann_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, Ann_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			// Handle any SQL errors
		} catch (SQLException e) {
			e.printStackTrace();
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public AnnVO findByPrimaryKey(Integer ann_no) {
		AnnVO annVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, ann_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// AnnVo 也稱為 Domain objects
				annVO = new AnnVO();
				annVO.setAnn_no(rs.getInt("ann_no"));
				annVO.setAnn_date(rs.getDate("ann_date"));
				annVO.setAnn_title(rs.getString("ann_title"));
				annVO.setAnn_content(rs.getString("ann_content"));
				annVO.setAnn_picture(rs.getString("ann_picture"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			// Handle any SQL errors
		} catch (SQLException e) {
			e.printStackTrace();
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return annVO;
	}

	@Override
	public List<AnnVO> getAll() {
		List<AnnVO> list = new ArrayList<AnnVO>();
		AnnVO annVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// AnnVo 也稱為 Domain objects
				annVO = new AnnVO();
				annVO.setAnn_no(rs.getInt("ann_no"));
				annVO.setAnn_date(rs.getDate("ann_date"));
				annVO.setAnn_title(rs.getString("ann_title"));
				annVO.setAnn_content(rs.getString("ann_content"));
				annVO.setAnn_picture(rs.getString("ann_picture"));
				list.add(annVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
			e.printStackTrace();
			// Handle any SQL errors
		} catch (SQLException e) {
//			throw new RuntimeException("A database error occured. "
//					+ e.getMessage());
			e.printStackTrace();
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	@Override
	public List<AnnVO> getAnnfront() {
		List<AnnVO> list = new ArrayList<AnnVO>();
		AnnVO annVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_ANNFRONT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// AnnVo 也稱為 Domain objects
				annVO = new AnnVO();
				annVO.setAnn_no(rs.getInt("ann_no"));
				annVO.setAnn_date(rs.getDate("ann_date"));
				annVO.setAnn_title(rs.getString("ann_title"));
				annVO.setAnn_content(rs.getString("ann_content"));
				annVO.setAnn_picture(rs.getString("ann_picture"));
				list.add(annVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
			e.printStackTrace();
			// Handle any SQL errors
		} catch (SQLException e) {
//			throw new RuntimeException("A database error occured. "
//					+ e.getMessage());
			e.printStackTrace();
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	public static void main(String[] args) {

//		AnnJDBCDAO dao = new AnnJDBCDAO();

		// 新增 OK
//		AnnVO annVO1 = new AnnVO();
//		annVO1.setAnn_date(java.sql.Date.valueOf("2022-06-07"));
//		annVO1.setAnn_title("社交距離vvvv");
//		annVO1.setAnn_content("戴口罩");
//		annVO1.setAnn_picture(null);
//		dao.insert(annVO1);

		// 修改 OK
//		AnnVO annVO2 = new AnnVO();
//		annVO2.setAnn_no(1);
//		annVO2.setAnn_date(java.sql.Date.valueOf("2022-06-07"));
//		annVO2.setAnn_title("社交距離111");
//		annVO2.setAnn_content("戴口罩");
//		annVO2.setAnn_picture(null);
//		dao.update(annVO2);


		// 查詢 OK
//		AnnVO annVO3 = dao.findByPrimaryKey(1);
//			System.out.print(annVO3.getAnn_no() + ",");
//			System.out.print(annVO3.getAnn_date() + ",");
//			System.out.print(annVO3.getAnn_title() + ",");
//			System.out.print(annVO3.getAnn_content() + ",");
//			System.out.print(annVO3.getAnn_picture() + ",");
//			System.out.println("---------------------");

		// 全部查詢 OK
//		List<AnnVO> list = dao.getAll();
//		for (AnnVO aAnn : list) {
//			System.out.print(aAnn.getAnn_no() + ",");
//			System.out.print(aAnn.getAnn_date() + ",");
//			System.out.print(aAnn.getAnn_title() + ",");
//			System.out.print(aAnn.getAnn_content() + ",");
//			System.out.print(aAnn.getAnn_picture() + ",");
//			System.out.println();
//			}
//			
		// 刪除
//		dao.delete(5);


	}
}
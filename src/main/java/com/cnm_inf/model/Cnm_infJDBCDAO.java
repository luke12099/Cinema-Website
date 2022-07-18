package com.cnm_inf.model;

import java.util.*;
import java.sql.*;

public class Cnm_infJDBCDAO implements Cnm_infDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/movietheater?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = 
		"INSERT INTO cnm_inf (CNM_DT,CNM_TEL,CNM_EM,CNM_LC,CNM_TRP) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT CNM_INF_ID,CNM_DT,CNM_TEL,CNM_EM,CNM_LC,CNM_TRP FROM cnm_inf order by CNM_INF_ID";
	private static final String GET_ONE_STMT = 
		"SELECT CNM_INF_ID,CNM_DT,CNM_TEL,CNM_EM,CNM_LC,CNM_TRP FROM cnm_inf where CNM_INF_ID = ?";
	private static final String DELETE = 
		"DELETE FROM cnm_inf where CNM_INF_ID = ?";
	private static final String UPDATE = 
		"UPDATE cnm_inf set CNM_DT=?, CNM_TEL=?, CNM_EM=?, CNM_LC=?, CNM_TRP=? where CNM_INF_ID = ?";

	@Override
	public void insert(Cnm_infVO cnm_infVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, cnm_infVO.getCNM_DT());
			pstmt.setString(2, cnm_infVO.getCNM_TEL());
			pstmt.setString(3, cnm_infVO.getCNM_EM());
			pstmt.setString(4, cnm_infVO.getCNM_LC());
			pstmt.setString(5, cnm_infVO.getCNM_TRP());

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
	public void update(Cnm_infVO cnm_infVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, cnm_infVO.getCNM_DT());
			pstmt.setString(2, cnm_infVO.getCNM_TEL());
			pstmt.setString(3, cnm_infVO.getCNM_EM());
			pstmt.setString(4, cnm_infVO.getCNM_LC());
			pstmt.setString(5, cnm_infVO.getCNM_TRP());
			pstmt.setInt(6, cnm_infVO.getCNM_INF_ID());


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
	public void delete(Integer CNM_INF_ID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, CNM_INF_ID);

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
	public Cnm_infVO findByPrimaryKey(Integer CNM_INF_ID) {

		Cnm_infVO cnm_infVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, CNM_INF_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				cnm_infVO = new Cnm_infVO();
				cnm_infVO.setCNM_INF_ID(rs.getInt("CNM_INF_ID"));
				cnm_infVO.setCNM_DT(rs.getString("CNM_DT"));
				cnm_infVO.setCNM_TEL(rs.getString("CNM_TEL"));
				cnm_infVO.setCNM_EM(rs.getString("CNM_EM"));
				cnm_infVO.setCNM_LC(rs.getString("CNM_LC"));
				cnm_infVO.setCNM_TRP(rs.getString("CNM_TRP"));
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
		return cnm_infVO;
	}

	@Override
	public List<Cnm_infVO> getAll() {
		List<Cnm_infVO> list = new ArrayList<Cnm_infVO>();
		Cnm_infVO cnm_infVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// cnm_infVO 也稱為 Domain objects
				cnm_infVO = new Cnm_infVO();
				cnm_infVO.setCNM_INF_ID(rs.getInt("CNM_INF_ID"));
				cnm_infVO.setCNM_DT(rs.getString("CNM_DT"));
				cnm_infVO.setCNM_TEL(rs.getString("CNM_TEL"));
				cnm_infVO.setCNM_EM(rs.getString("CNM_EM"));
				cnm_infVO.setCNM_LC(rs.getString("CNM_LC"));
				cnm_infVO.setCNM_TRP(rs.getString("CNM_TRP"));
				list.add(cnm_infVO); // Store the row in the list
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

		Cnm_infJDBCDAO dao = new Cnm_infJDBCDAO();

		// 新增
		Cnm_infVO cnm_infVO1 = new Cnm_infVO();
		cnm_infVO1.setCNM_DT("testtesttesttest");
		cnm_infVO1.setCNM_TEL("03-1234567");
		cnm_infVO1.setCNM_EM("123@123.com.tw");
		cnm_infVO1.setCNM_LC("台北市某某區提拔米路981號");
		cnm_infVO1.setCNM_TRP("捷運: AA站B號出口; 公車: ABC/DEF號至提拔米路口下車");
		dao.insert(cnm_infVO1);

		// 修改
		Cnm_infVO cnm_infVO2 = new Cnm_infVO();
		cnm_infVO2.setCNM_INF_ID(new Integer(1));
		cnm_infVO2.setCNM_DT("共計5個影廳，2個一般數位廳、2個IMAX巨幕影廳及1個尊爵天龍頂級影廳，共計詐854個座位。\r\n"
					+ "		全影城放映機皆為4K雷射投影，皆採高對比雷射放映機。\r\n"
					+ "		音響部分則全是隱藏式 7.1 環繞音響，將呈現精緻達到4迴路音響效果，影迷入場後看不到配置音響，會更感受到整座影廳的氣派空間感。座椅全採用尊榮皮質座椅，讓影迷看電影簡直就像在搭乘頭等艙。\r\n"
					+ "		為滿足VIP客人，本影城推出的『IMAX尊爵天龍廳』，提供超越想像舒適的皮革尊榮座椅，與精緻餐點及專人服務。");
		cnm_infVO2.setCNM_TEL("02-9876543");
		cnm_infVO2.setCNM_EM("staff_01@hireme.com");
		cnm_infVO2.setCNM_LC("台北市某某區提拔米路10號");
		cnm_infVO2.setCNM_TRP("111111");
		dao.update(cnm_infVO2);

		// 刪除
//		dao.delete(new Integer(2));

		// 查詢
		Cnm_infVO cnm_infVO3 = dao.findByPrimaryKey(new Integer(1));
		System.out.print(cnm_infVO3.getCNM_INF_ID() + ",");
		System.out.print(cnm_infVO3.getCNM_DT() + ",");
		System.out.print(cnm_infVO3.getCNM_TEL() + ",");
		System.out.print(cnm_infVO3.getCNM_EM() + ",");
		System.out.print(cnm_infVO3.getCNM_LC() + ",");
		System.out.println(cnm_infVO3.getCNM_TRP() + ",");
		System.out.println("---------------------");

		// 查詢
		List<Cnm_infVO> list = dao.getAll();
		for (Cnm_infVO aCnm_inf : list) {
			System.out.print(aCnm_inf.getCNM_INF_ID() + ",");
			System.out.print(aCnm_inf.getCNM_DT() + ",");
			System.out.print(aCnm_inf.getCNM_TEL() + ",");
			System.out.print(aCnm_inf.getCNM_EM() + ",");
			System.out.print(aCnm_inf.getCNM_LC() + ",");
			System.out.print(aCnm_inf.getCNM_TRP() + ",");
			System.out.println();
		}
	}
}
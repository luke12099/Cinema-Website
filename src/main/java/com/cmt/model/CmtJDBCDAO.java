package com.cmt.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.cmt.model.CmtJDBCDAO;
import com.cmt.model.CmtVO;
import com.movie.model.MovieVO;

public class CmtJDBCDAO implements CmtDAO_interface{

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/movietheater?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = 
		"INSERT INTO cmt (MEMBER_ID, MV_ID, CM_TEXT, CM_LIKE, CM_STAR, CM_STATE, CM_DATE) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT CM_ID, MEMBER_ID, MV_ID, CM_TEXT, CM_LIKE, CM_STAR, CM_STATE, CM_DATE FROM cmt order by CM_ID";
	private static final String GET_ONE_STMT = 
		"SELECT CM_ID, MEMBER_ID, MV_ID, CM_TEXT, CM_LIKE, CM_STAR, CM_STATE, CM_DATE FROM cmt where CM_ID = ?";
	private static final String DELETE = 
		"DELETE FROM cmt where CM_ID = ?";
	private static final String UPDATE = 
		"UPDATE cmt set MEMBER_ID=?, MV_ID=?, CM_TEXT=?, CM_LIKE=?, CM_STAR=?, CM_STATE=?, CM_DATE=? where CM_ID = ?";
	private static final String UPDATE_CMTSTATE =
		"UPDATE cmt SET CM_STATE =?  WHERE CM_ID =? ;";
	private static final String GET_CMTS_BY_MV_ID_STMT =
			"SELECT CM_ID, MEMBER_ID, MV_ID, CM_TEXT, CM_LIKE, CM_STAR, CM_STATE, CM_DATE FROM cmt where MV_ID = ?";
	private static final String GET_CMTS_BY_member_ID_STMT =
			"SELECT CM_ID, MEMBER_ID, MV_ID, CM_TEXT, CM_LIKE, CM_STAR, CM_STATE, CM_DATE FROM cmt where MEMBER_ID = ?";
	private static final String UPDATE_MOVIE_TT_STMT = 
			"UPDATE movie SET MV_TT_CM=?, MV_TT_STAR=? WHERE MV_ID=?";
	
	
	@Override
	public void insert(CmtVO cmtVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, cmtVO.getMEMBER_ID());
			pstmt.setInt(2, cmtVO.getMV_ID());
			pstmt.setString(3, cmtVO.getCM_TEXT());
			pstmt.setInt(4, cmtVO.getCM_LIKE());
			pstmt.setInt(5, cmtVO.getCM_STAR());
			pstmt.setInt(6, cmtVO.getCM_STATE());
			pstmt.setTimestamp(7, cmtVO.getCM_DATE());

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
	public void update(CmtVO cmtVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, cmtVO.getMEMBER_ID());
			pstmt.setInt(2, cmtVO.getMV_ID());
			pstmt.setString(3, cmtVO.getCM_TEXT());
			pstmt.setInt(4, cmtVO.getCM_LIKE());
			pstmt.setInt(5, cmtVO.getCM_STAR());
			pstmt.setInt(6, cmtVO.getCM_STATE());
			pstmt.setTimestamp(7, cmtVO.getCM_DATE());
			pstmt.setInt(8, cmtVO.getCM_ID());

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
	public void delete(Integer CM_ID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, CM_ID);

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
	public CmtVO findByPrimaryKey(Integer CM_ID) {

		CmtVO cmtVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, CM_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				cmtVO = new CmtVO();
				cmtVO.setCM_ID(rs.getInt("CM_ID"));
				cmtVO.setMEMBER_ID(rs.getInt("MEMBER_ID"));
				cmtVO.setMV_ID(rs.getInt("MV_ID"));
				cmtVO.setCM_TEXT(rs.getString("CM_TEXT"));
				cmtVO.setCM_LIKE(rs.getInt("CM_LIKE"));
				cmtVO.setCM_STAR(rs.getInt("CM_STAR"));
				cmtVO.setCM_STATE(rs.getInt("CM_STATE"));
				cmtVO.setCM_DATE(rs.getTimestamp("CM_DATE"));
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
		return cmtVO;
	}

	@Override
	public List<CmtVO> getAll() {
		List<CmtVO> list = new ArrayList<CmtVO>();
		CmtVO cmtVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// cmtVO 也稱為 Domain objects
				cmtVO = new CmtVO();
				cmtVO.setCM_ID(rs.getInt("CM_ID"));
				cmtVO.setMEMBER_ID(rs.getInt("MEMBER_ID"));
				cmtVO.setMV_ID(rs.getInt("MV_ID"));
				cmtVO.setCM_TEXT(rs.getString("CM_TEXT"));
				cmtVO.setCM_LIKE(rs.getInt("CM_LIKE"));
				cmtVO.setCM_STAR(rs.getInt("CM_STAR"));
				cmtVO.setCM_STATE(rs.getInt("CM_STATE"));
				cmtVO.setCM_DATE(rs.getTimestamp("CM_DATE"));
				list.add(cmtVO); // Store the row in the list
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
	
	@Override
	public void updateCmtState(Integer CM_ID,Integer CM_STATE) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_CMTSTATE);

			pstmt.setInt(1, CM_STATE);
			pstmt.setInt(2, CM_ID);
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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
	public List<CmtVO> getCmtsByMV_ID(Integer MV_ID) {
		List<CmtVO> list = new ArrayList<CmtVO>();
		CmtVO cmtVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_CMTS_BY_MV_ID_STMT);
			pstmt.setInt(1, MV_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// cmtVO 也稱為 Domain objects
				cmtVO = new CmtVO();
				cmtVO.setCM_ID(rs.getInt("CM_ID"));
				cmtVO.setMEMBER_ID(rs.getInt("MEMBER_ID"));
				cmtVO.setMV_ID(rs.getInt("MV_ID"));
				cmtVO.setCM_TEXT(rs.getString("CM_TEXT"));
				cmtVO.setCM_LIKE(rs.getInt("CM_LIKE"));
				cmtVO.setCM_STAR(rs.getInt("CM_STAR"));
				cmtVO.setCM_STATE(rs.getInt("CM_STATE"));
				cmtVO.setCM_DATE(rs.getTimestamp("CM_DATE"));
				list.add(cmtVO); // Store the row in the list
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
	
	@Override
	public List<CmtVO> getCmtsBymember_ID(Integer member_ID) {
		List<CmtVO> list = new ArrayList<CmtVO>();
		CmtVO cmtVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_CMTS_BY_member_ID_STMT);
			pstmt.setInt(1, member_ID);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// cmtVO 也稱為 Domain objects
				cmtVO = new CmtVO();
				cmtVO.setCM_ID(rs.getInt("CM_ID"));
				cmtVO.setMEMBER_ID(rs.getInt("MEMBER_ID"));
				cmtVO.setMV_ID(rs.getInt("MV_ID"));
				cmtVO.setCM_TEXT(rs.getString("CM_TEXT"));
				cmtVO.setCM_LIKE(rs.getInt("CM_LIKE"));
				cmtVO.setCM_STAR(rs.getInt("CM_STAR"));
				cmtVO.setCM_STATE(rs.getInt("CM_STATE"));
				cmtVO.setCM_DATE(rs.getTimestamp("CM_DATE"));
				list.add(cmtVO); // Store the row in the list
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
	
	
	@Override
	public MovieVO updateMovieTT(MovieVO movieVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_MOVIE_TT_STMT);

			pstmt.setInt(1, movieVO.getMvTtCm());
			pstmt.setInt(2, movieVO.getMvTtStar());
			pstmt.setInt(3, movieVO.getMvId());
			

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
		return movieVO;
	}


	public static void main(String[] args) {

		CmtJDBCDAO dao = new CmtJDBCDAO();

		// 新增
		CmtVO cmtVO1 = new CmtVO();
		cmtVO1.setMEMBER_ID(new Integer(7));
		cmtVO1.setMV_ID(new Integer(9));
		cmtVO1.setCM_TEXT("看到哭");
		cmtVO1.setCM_LIKE(new Integer(231));
		cmtVO1.setCM_STAR(new Integer(5));
		cmtVO1.setCM_STATE(new Integer(0));
		cmtVO1.setCM_DATE(new Timestamp(System.currentTimeMillis()));
		dao.insert(cmtVO1);

		// 修改
		CmtVO cmtVO2 = new CmtVO();
		cmtVO2.setCM_ID(new Integer(3));
		cmtVO2.setMEMBER_ID(new Integer(3));
		cmtVO2.setMV_ID(new Integer(6));
		cmtVO2.setCM_TEXT("大哥輸ㄌQQ");
		cmtVO2.setCM_LIKE(new Integer(50));
		cmtVO2.setCM_STAR(new Integer(4));
		cmtVO2.setCM_STATE(new Integer(1));
		cmtVO2.setCM_DATE(new Timestamp(System.currentTimeMillis()));
		dao.update(cmtVO2);

		// 刪除
//		dao.delete(new Integer(1));

		// 查詢
		CmtVO cmtVO3 = dao.findByPrimaryKey(new Integer(3));
		System.out.print(cmtVO3.getCM_ID() + ",");
		System.out.print(cmtVO3.getMEMBER_ID() + ",");
		System.out.print(cmtVO3.getMV_ID() + ",");
		System.out.print(cmtVO3.getCM_TEXT() + ",");
		System.out.print(cmtVO3.getCM_LIKE() + ",");
		System.out.print(cmtVO3.getCM_STAR() + ",");
		System.out.print(cmtVO3.getCM_STATE());
		System.out.println(cmtVO3.getCM_DATE());
		System.out.println("---------------------");

		// 查詢
		List<CmtVO> list = dao.getAll();
		for (CmtVO aCmt : list) {
			System.out.print(aCmt.getCM_ID() + ",");
			System.out.print(aCmt.getMEMBER_ID() + ",");
			System.out.print(aCmt.getMV_ID() + ",");
			System.out.print(aCmt.getCM_TEXT() + ",");
			System.out.print(aCmt.getCM_LIKE() + ",");
			System.out.print(aCmt.getCM_STAR() + ",");
			System.out.print(aCmt.getCM_STATE());
			System.out.print(aCmt.getCM_DATE());
			System.out.println();
		}
	}

	
}

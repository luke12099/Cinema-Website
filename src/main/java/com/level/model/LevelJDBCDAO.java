package com.level.model;

import java.util.*;

import com.member.model.MemberDAO_interface;

import java.sql.*;

public class LevelJDBCDAO implements LevelDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/movietheater?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	    private static final String INSERT_STMT = 
	    	"INSERT INTO level_data "
	    	+"(MEMBER_LEVEL,MAX_COUNT,MIN_COUNT,LEVEL_DESCRIPTION)VALUES "
	    	+"(?, ?, ?, ?);";	

		private static final String DELETE = 
			"delete from level_data where MEMBER_LEVEL = ?;";	
		private static final String UPDATE = 
			"UPDATE movietheater.level_data set "
			+ "MAX_COUNT = ? , MIN_COUNT = ? , LEVEL_DESCRIPTION = ?"
			+ "where MEMBER_LEVEL = ?;";

	
	
		public void insert(LevelVO levelVO) {

			Connection con = null;          //宣告連線物件，以便可以在finally中關閉
			PreparedStatement pstmt = null; //宣告語句物件，以便可以在finally中關閉

			try {

				Class.forName(driver); //反射機制  載入驅動類，不同資料庫軟體驅動類不同
				con = DriverManager.getConnection(url, userid, passwd);//使用DriverManager獲得連線物件，其中url每個資料庫不同
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, levelVO.getMember_level());
				pstmt.setInt(2, levelVO.getMax_count());
				pstmt.setInt(3, levelVO.getMin_count());
				pstmt.setString(4, levelVO.getLevel_description());
			

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


		public void update(LevelVO levelVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);

				
				pstmt.setInt(1, levelVO.getMax_count());
				pstmt.setInt(2, levelVO.getMin_count());
				pstmt.setString(3, levelVO.getLevel_description());
				pstmt.setString(4, levelVO.getMember_level());

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

//		@Override
		public void delete(String member_level) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, member_level);

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

		
//		public Level findByPrimaryKey(String member_level) {
//
//			LevelVO  levelVO = null;
//			Connection con = null;
//			PreparedStatement pstmt = null;
//			ResultSet rs = null;
//
//			try {
//
//				Class.forName(driver);
//				con = DriverManager.getConnection(url, userid, passwd);
//				pstmt = con.prepareStatement(GET_ALL_STMT);
//
//				pstmt.setInt(1, empno2);
//
//				rs = pstmt.executeQuery();
//
//				while (rs.next()) {
//					// empVo �]�٬� Domain objects
//					empVO = new EmpVO3();
//					empVO.setMemberID(rs.getInt("MEMBER_ID"));
//					empVO.setMemberLevel(rs.getString("MEMBER_LEVEL"));
//					
//				}
//
//				// Handle any driver errors
//			} catch (ClassNotFoundException e) {
//				throw new RuntimeException("Couldn't load database driver. "
//						+ e.getMessage());
//				// Handle any SQL errors
//			} catch (SQLException se) {
//				throw new RuntimeException("A database error occured. "
//						+ se.getMessage());
//				// Clean up JDBC resources
//			} finally {
//				if (rs != null) {
//					try {
//						rs.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//				if (pstmt != null) {
//					try {
//						pstmt.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//				if (con != null) {
//					try {
//						con.close();
//					} catch (Exception e) {
//						e.printStackTrace(System.err);
//					}
//				}
//			}
//			return empVO;
//		}
//		@Override
//		public List<EmpVO> getAll() {
//			List<EmpVO> list = new ArrayList<EmpVO>();
//			EmpVO empVO = null;
//
//			Connection con = null;
//			PreparedStatement pstmt = null;
//			ResultSet rs = null;
//
//			try {
//
//				Class.forName(driver);
//				con = DriverManager.getConnection(url, userid, passwd);
//				pstmt = con.prepareStatement(GET_ALL_STMT);
//				rs = pstmt.executeQuery();
//
//				while (rs.next()) {
//					// empVO �]�٬� Domain objects
//					empVO = new EmpVO();
//					empVO.setEmpno(rs.getInt("empno"));
//					empVO.setEname(rs.getString("ename"));
//					empVO.setJob(rs.getString("job"));
//					empVO.setHiredate(rs.getDate("hiredate"));
//					empVO.setSal(rs.getDouble("sal"));
//					empVO.setComm(rs.getDouble("comm"));
//					empVO.setDeptno(rs.getInt("deptno"));
//					list.add(empVO); // Store the row in the list
//				}
//
//				// Handle any driver errors
//			} catch (ClassNotFoundException e) {
//				throw new RuntimeException("Couldn't load database driver. "
//						+ e.getMessage());
//				// Handle any SQL errors
//			} catch (SQLException se) {
//				throw new RuntimeException("A database error occured. "
//						+ se.getMessage());
//				// Clean up JDBC resources
//			} finally {
//				if (rs != null) {
//					try {
//						rs.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//				if (pstmt != null) {
//					try {
//						pstmt.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//				if (con != null) {
//					try {
//						con.close();
//					} catch (Exception e) {
//						e.printStackTrace(System.err);
//					}
//				}
//			}
//			return list;
//		}

		public static void main(String[] args) {

			LevelJDBCDAO dao = new LevelJDBCDAO();

			// 新增
			LevelVO levelVO = new LevelVO();
			levelVO.setMember_level("D");
			levelVO.setMax_count(110);
			levelVO.setMin_count(101);
			levelVO.setLevel_description("該會員累積購買票數最大110~101之間為D等級");
			
			dao.insert(levelVO);

			// 修改
			levelVO = new LevelVO();
			levelVO.setMember_level("D");
			levelVO.setMax_count(120);
			levelVO.setMin_count(101);
			levelVO.setLevel_description("該會員累積購買票數最大120~101之間為D等級");

			dao.update(levelVO);

			// 刪除
			dao.delete("D");

	
		}
	}


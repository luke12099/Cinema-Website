package com.member.model;

import java.util.*;

import java.sql.*;

public class MemberJDBCDAO implements MemberDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/movietheater?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT =             // 新增資料
			"insert into `member`"
					+ "(MEMBER_LEVEL,MEMBER_EMAIL,MEMBER_PASSWORD,MEMBER_NAME,MEMBER_PHONE,MEMBER_ADDRESS,MEMBER_PIC,MEMBER_STATUS,WISH_TICKET,BONUS_POINTS,SUM_COUNT) VALUES"
					+ "(?,?,?,?,?,?,?,?,?,?,?) ;";

	private static final String GET_ALL_MEM =        // 查詢全部資料
			"select MEMBER_ID,MEMBER_LEVEL,MEMBER_EMAIL,MEMBER_PASSWORD,MEMBER_NAME,MEMBER_PHONE,MEMBER_ADDRESS,MEMBER_PIC,MEMBER_STATUS,WISH_TICKET,BONUS_POINTS,SUM_COUNT "
					+ "from member order by MEMBER_ID ";
	private static final String GET_ONE_STMT =       // 查詢單筆資料
			"select MEMBER_ID,MEMBER_LEVEL,MEMBER_EMAIL,MEMBER_PASSWORD,MEMBER_NAME,MEMBER_PHONE,MEMBER_ADDRESS,MEMBER_PIC,MEMBER_STATUS,WISH_TICKET,BONUS_POINTS,SUM_COUNT "
					+ "from member where (MEMBER_ID = ?);";
	private static final String UPDATE =             // 前台修改資料
			"UPDATE `member`"
					+ "set  MEMBER_PASSWORD = ?, MEMBER_NAME = ?, MEMBER_PHONE = ?, MEMBER_ADDRESS = ?,MEMBER_PIC= ?"
					+ "where (MEMBER_ID = ?);";
	private static final String UPDATE_STATUS =      // 修改會員資狀態
			"UPDATE `member` " + " set  MEMBER_STATUS = ?" + " where (MEMBER_ID = ?);";
	private static final String UPDATE_WISH_TICKET = // 修改會員許願票
			"update `member` set WISH_TICKET = ? where MEMBER_ID = ?;";
	private static final String DELETE =             // 刪除資料
			"DELETE FROM member where MEMBER_ID = ?";

	private static final String loginMember =        // 會員登入
			"select MEMBER_ID,member_Status " + "from member where (MEMBER_EMAIL = ? && MEMBER_PASSWORD = ?);";
	
	private static final String Register =           //會員註冊時會傳送信件，並修改會員狀態(藉由Email搜尋會員ID)
			"select MEMBER_ID " + "from member where (MEMBER_EMAIL = ? );";
	
	private static final String verifyEmail =     //會員忘記密碼
			"SELECT MEMBER_EMAIL FROM MEMBER WHERE MEMBER_EMAIL =?";
		

//新增資料	
	public void insert(MemberVO memberVO) {

		Connection con = null; // 宣告連線物件，以便可以在finally中關閉
		PreparedStatement pstmt = null; // 宣告語句物件，以便可以在finally中關閉

		try {

			Class.forName(driver); // 反射機制 載入驅動類，不同資料庫軟體驅動類不同
			con = DriverManager.getConnection(url, userid, passwd);// 使用DriverManager獲得連線物件，其中url每個資料庫不同
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, memberVO.getMember_Level());
			pstmt.setString(2, memberVO.getMember_Email());
			pstmt.setString(3, memberVO.getMember_Password());
			pstmt.setString(4, memberVO.getMember_Name());
			pstmt.setString(5, memberVO.getMember_Phone());
			pstmt.setString(6, memberVO.getMember_Address());
			pstmt.setString(7, memberVO.getMember_Pic());
			pstmt.setInt(8, memberVO.getMember_Status());
			pstmt.setInt(9, memberVO.getWish_Ticket());
			pstmt.setInt(10, memberVO.getBonus_Points());
			pstmt.setInt(11, memberVO.getSum_Count());

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

//修改會員資料	
	public void update(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memberVO.getMember_Password());
			pstmt.setString(2, memberVO.getMember_Name());
			pstmt.setString(3, memberVO.getMember_Phone());
			pstmt.setString(4, memberVO.getMember_Address());
			pstmt.setString(5, memberVO.getMember_Pic());// 照片處理
			pstmt.setInt(6, memberVO.getMember_ID());

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
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}

	// wish
	@Override
	public void updateWishTicket(Integer member_id, Integer wish_ticket) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_WISH_TICKET);

			pstmt.setInt(1, wish_ticket);
			pstmt.setInt(2, member_id);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

//修改/修正 會員狀態
     public void updateStatus(Integer member_ID, Integer member_Status) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STATUS);
			pstmt.setInt(1, member_Status);
			pstmt.setInt(2, member_ID);
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
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}

//刪除會員，利用會員編號	
	public void delete(Integer member_ID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, member_ID);

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

//單筆查詢
	public MemberVO findByPrimaryKey(Integer member_ID) {

		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, member_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				memberVO = new MemberVO();
				memberVO.setMember_ID(rs.getInt("member_ID"));
				memberVO.setMember_Level(rs.getString("member_Level"));
				memberVO.setMember_Email(rs.getString("member_Email"));
				memberVO.setMember_Password(rs.getString("member_Password"));
				memberVO.setMember_Name(rs.getString("member_Name"));
				memberVO.setMember_Phone(rs.getString("member_Phone"));
				memberVO.setMember_Address(rs.getString("member_Address"));
				memberVO.setMember_Pic(rs.getString("member_Pic"));
				memberVO.setMember_Status(rs.getInt("member_Status"));
				memberVO.setWish_Ticket(rs.getInt("wish_Ticket"));
				memberVO.setBonus_Points(rs.getInt("bonus_Points"));
				memberVO.setSum_Count(rs.getInt("sum_Count"));
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
		return memberVO;
	}

//@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_MEM);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				memberVO = new MemberVO();
				memberVO.setMember_ID(rs.getInt("member_ID"));
				memberVO.setMember_Level(rs.getString("member_Level"));
				memberVO.setMember_Email(rs.getString("member_Email"));
				memberVO.setMember_Password(rs.getString("member_Password"));
				memberVO.setMember_Name(rs.getString("member_Name"));
				memberVO.setMember_Phone(rs.getString("member_Phone"));
				memberVO.setMember_Address(rs.getString("member_Address"));
				memberVO.setMember_Pic(rs.getString("member_Pic"));
				memberVO.setMember_Status(rs.getInt("member_Status"));
				memberVO.setWish_Ticket(rs.getInt("wish_Ticket"));
				memberVO.setBonus_Points(rs.getInt("bonus_Points"));
				memberVO.setSum_Count(rs.getInt("sum_Count"));
				list.add(memberVO); // Store the row in the list
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

//會員登入
	public MemberVO loginMember(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(loginMember);
			pstmt.setString(1, memberVO.getMember_Email());
			pstmt.setString(2, memberVO.getMember_Password());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memberVO 也稱為 Domain objects
				memberVO.setMember_ID(rs.getInt("member_ID"));
				memberVO.setMember_Status(rs.getInt("member_Status"));
			}
			System.out.println(memberVO.getMember_ID());
			System.out.println(memberVO.getMember_Status());
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
		return memberVO;
	}
	
//會員註冊時會傳送信件，並修改會員狀態(藉由Email搜尋會員ID)
		public  MemberVO register(MemberVO memberVO) {

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(Register);
				pstmt.setString(1, memberVO.getMember_Email());

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// memberVO 也稱為 Domain objects
					memberVO.setMember_ID(rs.getInt("member_ID"));
				}
				System.out.println(memberVO.getMember_ID());
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
			return memberVO;
		}
		
		
//會員忘記密碼
		public Integer verifyEmail(String usrEmail) {

			Integer verifyResult = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(verifyEmail);

				pstmt.setString(1, usrEmail);

				rs = pstmt.executeQuery();

				if (rs.next()) {
					verifyResult = 1;
				}else {
					verifyResult = 0;
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
			return verifyResult;
		}

		
		
	
	
	


	public static void main(String[] args) {
		MemberJDBCDAO dao = new MemberJDBCDAO();

		// 新增
//				MemberVO memberVO = new MemberVO();
//				memberVO.setMember_Email("wanluy1996@gmail.com");
//				memberVO.setMember_Password("rt678345");
//				memberVO.setMember_Name("王移加");
//				memberVO.setMember_Phone("0967854321");
//				memberVO.setMember_Address("桃園市中壢區");
//				memberVO.setMember_Pic(null);
//				memberVO.setMember_Level("C");
//				memberVO.setMember_Status(0);
//				memberVO.setWish_Ticket(3);
//				memberVO.setBonus_Points(5);
//				memberVO.setSum_Count(10);
//				dao.insert(memberVO);

		// 修改

//		MemberVO memberVO = new MemberVO();
//		memberVO.setMember_Password("WE456789");
//		memberVO.setMember_Name("王嘉惠");
//		memberVO.setMember_Phone("0915775234");
//		memberVO.setMember_Address("桃園市平鎮區");
//		memberVO.setMember_Pic("");	
//		memberVO.setMember_ID(11);
//		dao.update(memberVO);

		// 刪除
//		dao.delete(11);

//		 查詢
//		MemberVO member = dao.findByPrimaryKey();
//		System.out.print(member.getMember_ID() + ",");
//		System.out.print(member.getMember_Level() + ",");
//		System.out.print(member.getMemeber_Email() + ",");
//		System.out.print(member.getMember_Password() + ",");
//		System.out.print(member.getMember_Name() + ",");
//		System.out.print(member.getMember_Phone() + ",");
//		System.out.print(member.getMember_Address() + ",");
//		System.out.print(member.getMember_Pic() + ",");
//		System.out.print(member.getMember_Status() + ",");
//		System.out.print(member.getWish_Ticket() + ",");
//		System.out.print(member.getBonus_Points() + ",");
//		System.out.println(member.getSum_Count());
//		System.out.println("---------------------");

		// 查詢
//		List<MemberVO> list = dao.getAll();
//		for (MemberVO aMem : list) {
//			System.out.print(aMem.getMember_ID() + ",");
//			System.out.print(aMem.getMember_Level() + ",");
//			System.out.print(aMem.getMemeber_Email() + ",");
//			System.out.print(aMem.getMember_Password() + ",");
//			System.out.print(aMem.getMember_Name() + ",");
//			System.out.print(aMem.getMember_Phone() + ",");
//			System.out.print(aMem.getMember_Address() + ",");
//			System.out.print(aMem.getMember_Pic() + ",");
//			System.out.print(aMem.getMember_Status() + ",");
//			System.out.print(aMem.getWish_Ticket() + ",");
//			System.out.print(aMem.getBonus_Points() + ",");
//			System.out.print(aMem.getSum_Count());
//			System.out.println();
//		}

//		dao.updateMemberStatus(Integer mem_id,Integer status);
//		dao.updateStatus(10,1);

		// dao.loginMember(member_Email,member_Password);

	}

}

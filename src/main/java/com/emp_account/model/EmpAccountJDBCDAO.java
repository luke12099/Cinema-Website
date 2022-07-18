package com.emp_account.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import com.common.JDBCUtil;
import com.emp_privilege.model.EmpPrivilegeJDBCDAO;
import com.emp_privilege.model.EmpPrivilegeVO;

public class EmpAccountJDBCDAO implements EmpAccountDAO_interface{
	
	private static final String INSERT = 
			"insert into emp_account "
			+ "	(EMP_EMAIL, EMP_PASSWORD, EMP_NAME, EMP_PHONE, EMP_ADDRESS, EMP_PHOTO, EMP_STATUS) VALUES "
			+ "	(?, ?, ?, ?, ?, ?, ?)";
	private static final String READ_ONE =
			"select EMP_NO, EMP_EMAIL, EMP_PASSWORD, EMP_NAME, EMP_PHONE, EMP_ADDRESS, EMP_PHOTO, EMP_STATUS "
			+ "from emp_account where EMP_NO = ?";
	private static final String READ_ALL =
			"select EMP_NO, EMP_EMAIL, EMP_PASSWORD, EMP_NAME, EMP_PHONE, EMP_ADDRESS, EMP_PHOTO, EMP_STATUS "
			+ "from emp_account order by EMP_NO";
	private static final String READ_ALL_DESC =
			"select EMP_NO, EMP_EMAIL, EMP_PASSWORD, EMP_NAME, EMP_PHONE, EMP_ADDRESS, EMP_PHOTO, EMP_STATUS "
			+ "from emp_account order by EMP_NO desc";
	private static final String UPDATE =
			"update emp_account set "
			+ "EMP_EMAIL = ?, EMP_PASSWORD = ?, EMP_NAME = ?, EMP_PHONE = ?, EMP_ADDRESS = ?, EMP_PHOTO = ?, EMP_STATUS = ? "
			+ "where (EMP_NO = ?)";
	private static final String UPDATE_PW =
			"update emp_account set EMP_PASSWORD = ? where (EMP_NO = ?)";
	private static final String UPDATE_STATUS =
			"update emp_account set EMP_STATUS = ? where (EMP_NO = ?)";
	private static final String DELETE =
			"delete from emp_account where (EMP_NO = ?)";
	private static final String GET_NEXT_ID =
			"select AUTO_INCREMENT from information_schema.tables where table_name = 'emp_account' and table_schema = 'movietheater' ";
	private static final String GET_PASSWORD = 
			"select EMP_PASSWORD from emp_account where EMP_NO = ?";
	
	@Override
	public Integer insert(EmpAccountVO empAccountVO) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer emp_no = null;
		try {
			con = JDBCUtil.getConnection();
			String columns[] = { "emp_id" };
			ps = con.prepareStatement(INSERT, columns);
			
			ps.setString(1, empAccountVO.getEmp_email());
			ps.setString(2, empAccountVO.getEmp_password());
			ps.setString(3, empAccountVO.getEmp_name());
			ps.setString(4, empAccountVO.getEmp_phone());
			ps.setString(5, empAccountVO.getEmp_address());
			ps.setBytes(6, empAccountVO.getEmp_photo());
			ps.setInt(7, empAccountVO.getEmp_status());
			
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys(); // 取得自動編號的號碼
			if (rs.next()) {
				emp_no = rs.getInt(1); // 第一欄
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return emp_no;
	}

	@Override
	public void delete(Integer empAccountNo) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(DELETE);
			
			ps.setInt(1, empAccountNo);
			
			ps.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void update(EmpAccountVO empAccountVO) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(UPDATE);
			
			ps.setString(1, empAccountVO.getEmp_email());
			ps.setString(2, empAccountVO.getEmp_password());
			ps.setString(3, empAccountVO.getEmp_name());
			ps.setString(4, empAccountVO.getEmp_phone());
			ps.setString(5, empAccountVO.getEmp_address());
			ps.setBytes(6, empAccountVO.getEmp_photo());
			ps.setInt(7, empAccountVO.getEmp_status());
			ps.setInt(8, empAccountVO.getEmp_no());
			
			ps.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void updatePassword(Integer empAccountNo, String newPassword) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(UPDATE_PW);
			
			ps.setString(1, newPassword);
			ps.setInt(2, empAccountNo);
			
			ps.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void updateStatus(Integer empAccountNo, Integer empStatus) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(UPDATE_STATUS);
			
			ps.setInt(1, empStatus);
			ps.setInt(2, empAccountNo);
			
			ps.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public EmpAccountVO findByPrimaryKey(Integer empAccountNo) {
		EmpAccountVO empAccountVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(READ_ONE);
			
			ps.setInt(1, empAccountNo);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				empAccountVO = new EmpAccountVO();
				empAccountVO.setEmp_no(rs.getInt("EMP_NO"));
				empAccountVO.setEmp_email(rs.getString("EMP_EMAIL"));
				empAccountVO.setEmp_password(rs.getString("EMP_PASSWORD"));
				empAccountVO.setEmp_name(rs.getString("EMP_NAME"));
				empAccountVO.setEmp_phone(rs.getString("EMP_PHONE"));
				empAccountVO.setEmp_address(rs.getString("EMP_ADDRESS"));
				empAccountVO.setEmp_photo(rs.getBytes("EMP_PHOTO"));
				empAccountVO.setEmp_status(rs.getInt("EMP_STATUS"));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return empAccountVO;
	}

	@Override
	public List<EmpAccountVO> getAll() {
		List<EmpAccountVO> list = new ArrayList<EmpAccountVO>();
		EmpAccountVO empAccountVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(READ_ALL);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				empAccountVO = new EmpAccountVO();
				empAccountVO.setEmp_no(rs.getInt("EMP_NO"));
				empAccountVO.setEmp_email(rs.getString("EMP_EMAIL"));
				empAccountVO.setEmp_password(rs.getString("EMP_PASSWORD"));
				empAccountVO.setEmp_name(rs.getString("EMP_NAME"));
				empAccountVO.setEmp_phone(rs.getString("EMP_PHONE"));
				empAccountVO.setEmp_address(rs.getString("EMP_ADDRESS"));
				empAccountVO.setEmp_photo(rs.getBytes("EMP_PHOTO"));
				empAccountVO.setEmp_status(rs.getInt("EMP_STATUS"));
				list.add(empAccountVO);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
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
	public List<EmpAccountVO> getAllDesc() {
		List<EmpAccountVO> list = new ArrayList<EmpAccountVO>();
		EmpAccountVO empAccountVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(READ_ALL_DESC);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				empAccountVO = new EmpAccountVO();
				empAccountVO.setEmp_no(rs.getInt("EMP_NO"));
				empAccountVO.setEmp_email(rs.getString("EMP_EMAIL"));
				empAccountVO.setEmp_password(rs.getString("EMP_PASSWORD"));
				empAccountVO.setEmp_name(rs.getString("EMP_NAME"));
				empAccountVO.setEmp_phone(rs.getString("EMP_PHONE"));
				empAccountVO.setEmp_address(rs.getString("EMP_ADDRESS"));
				empAccountVO.setEmp_photo(rs.getBytes("EMP_PHOTO"));
				empAccountVO.setEmp_status(rs.getInt("EMP_STATUS"));
				list.add(empAccountVO);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
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
	public List<EmpAccountVO> getAll(Map<String, String[]> map) {
		String sql = "select EMP_NO, EMP_EMAIL, EMP_PASSWORD, EMP_NAME, EMP_PHONE, EMP_ADDRESS, EMP_PHOTO, EMP_STATUS "
						+ "from emp_account ";
		
//		String[] val = {"EMP_NO", "1", "EMP_EMAIL", "xxx", "EMP_PASSWORD", "", "EMP_NAME", "a", "EMP_PHONE", "12", "EMP_ADDRESS", "���c", "EMP_STATUS", "1"};
		// add sql
		int countStatement = 0;
		for(int i = 1; i < map.get("multiSearch").length; i = i + 2) {
			if(map.get("multiSearch")[i].length() != 0) {
				if(countStatement == 0) { // �Ĥ@�ӱ���e���[ where
					sql += "where " + map.get("multiSearch")[i - 1] + " like ? ";
				} else { // ���᪺����e���[ and
					sql += "and " + map.get("multiSearch")[i - 1] + " like ? ";
				}
				countStatement++;
			}
		}
				
		sql += "order by EMP_NO;";
		System.out.println(sql);
	
		List<EmpAccountVO> list = new ArrayList<EmpAccountVO>();
		EmpAccountVO empAccountVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			// set ?
			int countTimes = 1;
			for(int i = 1; i < map.get("multiSearch").length; i = i + 2) {
				if(map.get("multiSearch")[i].length() != 0) {
					// �i��� metadata + switch
					if(i == 1 || i == map.get("multiSearch").length - 1) { // EMP_NO, EMP_STATUS �ǤJ Integer
						ps.setInt(countTimes, Integer.parseInt(map.get("multiSearch")[i]));
					} else { // ��L�ǤJ String
						ps.setString(countTimes, "%" + map.get("multiSearch")[i] + "%");
					}
					countTimes++;
				}
			}
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				empAccountVO = new EmpAccountVO();
				empAccountVO.setEmp_no(rs.getInt("EMP_NO"));
				empAccountVO.setEmp_email(rs.getString("EMP_EMAIL"));
				empAccountVO.setEmp_password(rs.getString("EMP_PASSWORD"));
				empAccountVO.setEmp_name(rs.getString("EMP_NAME"));
				empAccountVO.setEmp_phone(rs.getString("EMP_PHONE"));
				empAccountVO.setEmp_address(rs.getString("EMP_ADDRESS"));
				empAccountVO.setEmp_photo(rs.getBytes("EMP_PHOTO"));
				empAccountVO.setEmp_status(rs.getInt("EMP_STATUS"));
				list.add(empAccountVO);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
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
	public Integer getNextId() {
		Integer nextId = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(GET_NEXT_ID);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				nextId = rs.getInt("AUTO_INCREMENT");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return nextId;
	}

	@Override
	public String getPassword(Integer empAcocuntNo) {
		String password = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(GET_PASSWORD);
			
			ps.setInt(1, empAcocuntNo);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				password = rs.getString("EMP_PASSWORD");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return password;
	}

	@Override
	public Integer insertWithFunction(EmpAccountVO empAccountVO, List<EmpPrivilegeVO> lists) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer emp_no = null;
		try {
			con = JDBCUtil.getConnection();
			
			con.setAutoCommit(false);
			
			String columns[] = { "emp_id" };
			ps = con.prepareStatement(INSERT, columns);
			
			ps.setString(1, empAccountVO.getEmp_email());
			ps.setString(2, empAccountVO.getEmp_password());
			ps.setString(3, empAccountVO.getEmp_name());
			ps.setString(4, empAccountVO.getEmp_phone());
			ps.setString(5, empAccountVO.getEmp_address());
			ps.setBytes(6, empAccountVO.getEmp_photo());
			ps.setInt(7, empAccountVO.getEmp_status());
			
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys(); // 取得自動編號的號碼
			if (rs.next()) {
				emp_no = rs.getInt(1); // 第一欄
			}
			
			rs.close();
			
			// 同時新增權限
			EmpPrivilegeJDBCDAO dao = new EmpPrivilegeJDBCDAO();
			for(EmpPrivilegeVO empPrivilegeVO: lists) {
				empPrivilegeVO.setEmp_no(emp_no);
				dao.insert(empPrivilegeVO, con);
			}
			
			con.commit();
			con.setAutoCommit(true);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
			if(con != null) {
				try {
					con.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return emp_no;
	}

	public static void main(String[] args) {
		EmpAccountJDBCDAO dao = new EmpAccountJDBCDAO();
		
		// C
//		try (FileInputStream in = new FileInputStream("emp01.png");){
//			byte[] imageb;
//			imageb = new byte[in.available()];
//			in.read(imageb);
////			Blob blob = new SerialBlob(imageb);
//			EmpAccountVO empAccountVO1 = new EmpAccountVO();
//			empAccountVO1.setEmp_email("test3@gmail.com");
//			empAccountVO1.setEmp_password("testtest");
//			empAccountVO1.setEmp_name("TESTNAME");
//			empAccountVO1.setEmp_phone("0922-222222");
//			empAccountVO1.setEmp_address("aaaaa");
//			empAccountVO1.setEmp_photo(imageb);
//			empAccountVO1.setEmp_status(1);
//			Integer new_emp_no = dao.insert(empAccountVO1);
//			System.out.println(new_emp_no);
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		} 
		
		// R-ONE
//		EmpAccountVO empAccountVO3 = dao.findByPrimaryKey(2);
//		System.out.print(empAccountVO3.getEmp_no() + ", ");
//		System.out.print(empAccountVO3.getEmp_email() + ", ");
//		System.out.print(empAccountVO3.getEmp_password() + ", ");
//		System.out.print(empAccountVO3.getEmp_name() + ", ");
//		System.out.print(empAccountVO3.getEmp_phone() + ", ");
//		System.out.print(empAccountVO3.getEmp_address() + ", ");
//		System.out.print(empAccountVO3.getEmp_photo() + ", ");
//		System.out.println(empAccountVO3.getEmp_status());

		// R-ALL
//		List<EmpAccountVO> list = dao.getAll();
//		for(EmpAccountVO e: list) {
//			System.out.print(e.getEmp_no() + ", ");
//			System.out.print(e.getEmp_email() + ", ");
//			System.out.print(e.getEmp_password() + ", ");
//			System.out.print(e.getEmp_name() + ", ");
//			System.out.print(e.getEmp_phone() + ", ");
//			System.out.print(e.getEmp_address() + ", ");
//			System.out.print(e.getEmp_photo() + ", ");
//			System.out.println(e.getEmp_status());
//		}
		
		// R-�ƦX
		
//		Map<String, String[]> map = new HashMap();
//		String[] val = {"EMP_NO", "6", "EMP_EMAIL", "zzz", "EMP_PASSWORD", "jj", 
//				"EMP_NAME", "F", "EMP_PHONE", "0967", "EMP_ADDRESS", "�x��", "EMP_STATUS", "0"};
//		map.put("multiSearch", val);
//		
//		List<EmpAccountVO> list = dao.getAll(map);
//		for(EmpAccountVO e: list) {
//			System.out.print(e.getEmp_no() + ", ");
//			System.out.print(e.getEmp_email() + ", ");
//			System.out.print(e.getEmp_password() + ", ");
//			System.out.print(e.getEmp_name() + ", ");
//			System.out.print(e.getEmp_phone() + ", ");
//			System.out.print(e.getEmp_address() + ", ");
//			System.out.print(e.getEmp_photo() + ", ");
//			System.out.println(e.getEmp_status());
//		}
		
		// U
//		EmpAccountVO empAccountVO2 = new EmpAccountVO();
//		empAccountVO2.setEmp_no(2);
//		empAccountVO2.setEmp_email("belle@xxx.com");
//		empAccountVO2.setEmp_password("testtest");
//		empAccountVO2.setEmp_name("Belle");
//		empAccountVO2.setEmp_phone("0922-222222");
//		empAccountVO2.setEmp_address("�s�˥��˥_��");
//		
//		try (FileInputStream in = new FileInputStream("emp1.jpg");){
//			byte[] imageb;
//			imageb = new byte[in.available()];
//			in.read(imageb);
//			Blob blob = new SerialBlob(imageb);
//			empAccountVO2.setEmp_photo(blob);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (SerialException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		empAccountVO2.setEmp_status(0);
//		dao.update(empAccountVO2);
		
		// D
//		dao.delete(7);
		
		// C with privilege
//		List<EmpPrivilegeVO> lists = new ArrayList<EmpPrivilegeVO>();
//		
//		EmpAccountVO empAccountVO = new EmpAccountVO();
//		empAccountVO.setEmp_name("test1");
//		empAccountVO.setEmp_email("sss");
//		empAccountVO.setEmp_phone("sss");
//		empAccountVO.setEmp_address("sss");
//		empAccountVO.setEmp_password("sss");
//		empAccountVO.setEmp_status(2);
//		
//		EmpPrivilegeVO empPriviege1 = new EmpPrivilegeVO();
//		empPriviege1.setFc_no(1);
//		lists.add(empPriviege1);
//		EmpPrivilegeVO empPriviege2 = new EmpPrivilegeVO();
//		empPriviege2.setFc_no(2);
//		lists.add(empPriviege2);
//		EmpPrivilegeVO empPriviege3 = new EmpPrivilegeVO();
//		empPriviege3.setFc_no(3);
//		lists.add(empPriviege3);
//		EmpPrivilegeVO empPriviege4 = new EmpPrivilegeVO();
//		empPriviege4.setFc_no(5);
//		lists.add(empPriviege4);
//		
//		dao.insertWithFunction(empAccountVO, lists);
	}

}

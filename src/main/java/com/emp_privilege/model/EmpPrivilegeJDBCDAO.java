package com.emp_privilege.model;

import java.util.*;

import javax.naming.NamingException;

import com.common.JDBCUtil;

import java.sql.*;

public class EmpPrivilegeJDBCDAO implements EmpPrivilegeDAO_interface{
	
	private static final String INSERT = 
			"insert into emp_privilege (EMP_NO, FC_NO) values (?, ?)";
	private static final String READ_ONE =
			"select EMP_NO, FC_NO from emp_privilege where EMP_NO = ? order by EMP_NO";
	private static final String READ_ALL =
			"select EMP_NO, FC_NO from emp_privilege order by EMP_NO";
	private static final String DELETE =
			"delete from emp_privilege where EMP_NO = ? and FC_NO = ?";

	@Override
	public void insert(EmpPrivilegeVO empPrivilegeVO) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(INSERT);
			
			ps.setInt(1, empPrivilegeVO.getEmp_no());
			ps.setInt(2, empPrivilegeVO.getFc_no());
			ps.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
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
		
	}

	@Override
	public void insert(EmpPrivilegeVO empPrivilegeVO, Connection con) {
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement(INSERT);
			
			ps.setInt(1, empPrivilegeVO.getEmp_no());
			ps.setInt(2, empPrivilegeVO.getFc_no());
			ps.executeUpdate();
			
		} catch (SQLException e) {
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
		}
	}

	@Override
	public void delete(EmpPrivilegeVO empPrivilegeVO) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(DELETE);
			
			ps.setInt(1, empPrivilegeVO.getEmp_no());
			ps.setInt(2, empPrivilegeVO.getFc_no());
			ps.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
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
	}

	@Override
	public void delete(EmpPrivilegeVO empPrivilegeVO, Connection con) {
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement(DELETE);
			
			ps.setInt(1, empPrivilegeVO.getEmp_no());
			ps.setInt(2, empPrivilegeVO.getFc_no());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(List<EmpPrivilegeVO> empPrivilegeVOs) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = JDBCUtil.getConnection();
			
			con.setAutoCommit(false);
			
			// 查詢現有權限
			Integer emp_no = empPrivilegeVOs.get(0).getEmp_no();
			EmpPrivilegeJDBCDAO dao = new EmpPrivilegeJDBCDAO();
			List<EmpPrivilegeVO> deletePrivilege = dao.findByEmpNo(emp_no);
			// 刪除所有權限
			for(EmpPrivilegeVO empPrivilegeVO: deletePrivilege) {
				dao.delete(empPrivilegeVO, con);
			}
			// 新增新的權限
			for(EmpPrivilegeVO empPrivilege: empPrivilegeVOs) {
				dao.insert(empPrivilege, con);
			}
			
			con.commit();
			con.setAutoCommit(true);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
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
	}

	@Override
	public List<EmpPrivilegeVO> findByEmpNo(Integer empNo) {
		List<EmpPrivilegeVO> list = new ArrayList<EmpPrivilegeVO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(READ_ONE);
			
			ps.setInt(1, empNo);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				EmpPrivilegeVO empPrivilegeVO = new EmpPrivilegeVO();
				empPrivilegeVO.setEmp_no(rs.getInt("EMP_NO"));
				empPrivilegeVO.setFc_no(rs.getInt("FC_NO"));
				list.add(empPrivilegeVO);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
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
	public List<EmpPrivilegeVO> getAll() {
		List<EmpPrivilegeVO> list = new ArrayList<EmpPrivilegeVO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(READ_ALL);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				EmpPrivilegeVO empPrivilegeVO = new EmpPrivilegeVO();
				empPrivilegeVO.setEmp_no(rs.getInt("EMP_NO"));
				empPrivilegeVO.setFc_no(rs.getInt("FC_NO"));
				list.add(empPrivilegeVO);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
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
	
	public static void main(String[] args) {
		EmpPrivilegeJDBCDAO dao = new EmpPrivilegeJDBCDAO();
		// C
//		EmpPrivilegeVO empPrivilegeVO1 = new EmpPrivilegeVO();
//		empPrivilegeVO1.setEmp_no(2);
//		empPrivilegeVO1.setFc_no(8);
//		dao.insert(empPrivilegeVO1);
		
		// R_ONE
//		List<EmpPrivilegeVO> list = dao.findByEmpNo(5);
//		for(EmpPrivilegeVO ep: list) {
//			System.out.print(ep.getEmp_no() + ", ");
//			System.out.println(ep.getFc_no());
//		}
		// R_ALL
//		List<EmpPrivilegeVO> list = dao.getAll();
//		for(EmpPrivilegeVO ep: list) {
//			System.out.print(ep.getEmp_no() + ", ");
//			System.out.println(ep.getFc_no());
//		}
		
		// U X
		
		// D
//		dao.delete(2, 8);
	}

}

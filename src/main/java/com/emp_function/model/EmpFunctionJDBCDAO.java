package com.emp_function.model;

import java.sql.*;
import java.util.*;

import javax.naming.NamingException;

import com.common.JDBCUtil;

public class EmpFunctionJDBCDAO implements EmpFunctionDAO_interface{
	
	private static final String READ_ONE =
			"select FC_NO, FC_NAME, FC_DESCRIPTION, FC_CATEGORY from emp_function where FC_NO = ?";
	private static final String READ_ALL =
			"select FC_NO, FC_NAME, FC_DESCRIPTION, FC_CATEGORY from emp_function order by FC_NO";

	@Override
	public EmpFunctionVO findByPrimaryKey(Integer empFunctionNo) {
		EmpFunctionVO empFunctionVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(READ_ONE);
			
			ps.setInt(1, empFunctionNo);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				empFunctionVO = new EmpFunctionVO();
				empFunctionVO.setFc_no(rs.getInt("FC_NO"));
				empFunctionVO.setFc_name(rs.getString("FC_NAME"));
				empFunctionVO.setFc_description(rs.getString("FC_DESCRIPTION"));
				empFunctionVO.setFc_category(rs.getInt("FC_CATEGORY"));
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
		return empFunctionVO;
	}

	@Override
	public List<EmpFunctionVO> getAll() {
		List<EmpFunctionVO> list = new ArrayList<EmpFunctionVO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(READ_ALL);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				EmpFunctionVO empFunctionVO = new EmpFunctionVO();
				empFunctionVO.setFc_no(rs.getInt("FC_NO"));
				empFunctionVO.setFc_name(rs.getString("FC_NAME"));
				empFunctionVO.setFc_description(rs.getString("FC_DESCRIPTION"));
				empFunctionVO.setFc_category(rs.getInt("FC_CATEGORY"));
				list.add(empFunctionVO);
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
	
	public static void main(String[] args) {
		EmpFunctionJDBCDAO dao = new EmpFunctionJDBCDAO();
		
		// R_ONE
//		EmpFunctionVO empFunctionVO = dao.findByPrimaryKey(2);
//		System.out.print(empFunctionVO.getFc_no() + ", ");
//		System.out.print(empFunctionVO.getFc_name() + ", ");
//		System.out.print(empFunctionVO.getFc_description() + ", ");
//		System.out.println(empFunctionVO.getFc_category());
		
		// R_ALL
		List<EmpFunctionVO> list = dao.getAll();
		for(EmpFunctionVO ef: list) {
			System.out.print(ef.getFc_no() + ", ");
			System.out.print(ef.getFc_name() + ", ");
			System.out.print(ef.getFc_description() + ", ");
			System.out.println(ef.getFc_category());
		}
	}
}

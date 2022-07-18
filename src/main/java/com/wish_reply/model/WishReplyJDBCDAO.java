package com.wish_reply.model;

import java.util.*;

import javax.naming.NamingException;

import com.common.JDBCUtil;

import java.sql.*;

public class WishReplyJDBCDAO implements WishReplyDAO_interface{
	private static final String INSERT =
			"insert into wish_reply (WISH_NO, MEMBER_ID, WISH_MSG) values "
			+ "(?, ?, ?)";
	private static final String READ_ONE =
			"select WISH_REONO, WISH_NO, MEMBER_ID, WISH_MSG from wish_reply where WISH_NO = ? order by WISH_NO";
	private static final String READ_ALL =
			"select WISH_REONO, WISH_NO, MEMBER_ID, WISH_MSG from wish_reply order by WISH_NO";
	private static final String DELETE =
			"delete from wish_reply where WISH_REONO = ?";

	@Override
	public void insert(WishReplyVO wishReplyVO) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(INSERT);
			
			ps.setInt(1, wishReplyVO.getWish_no());
			ps.setInt(2, wishReplyVO.getMember_id());
			ps.setString(3, wishReplyVO.getWish_msg());
			
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
	public void delete(Integer replyNo) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(DELETE);
			
			ps.setInt(1, replyNo);
			
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
	public List<WishReplyVO> findByWishNo(Integer wishNo) {
		List<WishReplyVO> list = new ArrayList<WishReplyVO>();		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(READ_ONE);
			
			ps.setInt(1, wishNo);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				WishReplyVO wishReplyVO = new WishReplyVO();
				wishReplyVO.setWish_reono(rs.getInt("WISH_REONO"));
				wishReplyVO.setWish_no(rs.getInt("WISH_NO"));
				wishReplyVO.setMember_id(rs.getInt("MEMBER_ID"));
				wishReplyVO.setWish_msg(rs.getString("WISH_MSG"));
				list.add(wishReplyVO);
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
	public List<WishReplyVO> getAll() {
		List<WishReplyVO> list = new ArrayList<WishReplyVO>();		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(READ_ALL);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				WishReplyVO wishReplyVO = new WishReplyVO();
				wishReplyVO.setWish_reono(rs.getInt("WISH_REONO"));
				wishReplyVO.setWish_no(rs.getInt("WISH_NO"));
				wishReplyVO.setMember_id(rs.getInt("MEMBER_ID"));
				wishReplyVO.setWish_msg(rs.getString("WISH_MSG"));
				list.add(wishReplyVO);
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
		WishReplyJDBCDAO dao = new WishReplyJDBCDAO();
		
		// C
//		WishReplyVO wishReplyVO1 = new WishReplyVO();
//		wishReplyVO1.setWish_no(1);
//		wishReplyVO1.setMember_id(10);
//		wishReplyVO1.setWish_msg("TEST");
//		dao.insert(wishReplyVO1);
		
		// R_ONE
//		List<WishReplyVO> list = dao.findByWishNo(2);	
//		for(WishReplyVO wr: list) {
//			System.out.print(wr.getWish_reono() + ", ");
//			System.out.print(wr.getWish_no() + ", ");
//			System.out.print(wr.getMember_id() + ", ");
//			System.out.println(wr.getWish_msg());
//		}
		
		// R_ALL
//		List<WishReplyVO> list = dao.getAll();	
//		for(WishReplyVO wr: list) {
//			System.out.print(wr.getWish_reono() + ", ");
//			System.out.print(wr.getWish_no() + ", ");
//			System.out.print(wr.getMember_id() + ", ");
//			System.out.println(wr.getWish_msg());
//		}
		
		// U X
		
		// D
//		dao.delete(16);
	}
}

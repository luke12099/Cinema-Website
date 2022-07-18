package com.wishing_pond.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.NamingException;

import com.common.JDBCUtil;
import com.common.JedisPoolUtil;
import com.wishing_list.model.WishingListJDBCDAO;
import com.wishing_list.model.WishingListVO;

import redis.clients.jedis.Jedis;

public class WishingPondJDBCDAO implements WishingPondDAO_interface{
	
	private static final String INSERT =
			"insert into wishing_pond (WISH_NAME, WISH_START, WISH_END) values "
			+ "(?, ?, ?)";
	private static final String READ_ONE =
			"select WISH_NO, WISH_NAME, WISH_START, WISH_END, TOP_ONE from wishing_pond where WISH_NO = ?";
	private static final String READ_ALL =
			"select WISH_NO, WISH_NAME, WISH_START, WISH_END, TOP_ONE from wishing_pond";
	private static final String READ_AVALIABLE =
			"select * from wishing_pond where DATEDIFF( NOW(), WISH_START) >= 0 and DATEDIFF( NOW(), WISH_END) <= 0; ";
	private static final String UPDATE =
			"update wishing_pond set WISH_NAME = ?, WISH_START = ?, WISH_END = ?  "
			+ "where WISH_NO = ?";
	private static final String UPDATE_TOP_ONE =
			"update wishing_pond set TOP_ONE = ? where WISH_NO = ?";
	private static final String DELETE =
			"delete from wishing_pond where WISH_NO = ?";
	private static final String UPDATABLE = 
			"select ? < (select WISH_START from wishing_pond where WISH_NO = ?) ";
	private static final String GET_NEXT_ID =
			"select AUTO_INCREMENT from information_schema.tables where table_name = 'wishing_pond' and table_schema = 'movietheater' ";
	
	@Override
	public void insert(WishingPondVO wishingPondVO) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(INSERT);
			
			ps.setString(1, wishingPondVO.getWish_name());
			ps.setDate(2, wishingPondVO.getWish_start());
			ps.setDate(3, wishingPondVO.getWish_end());
			
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
	public Integer insertWithOptions(WishingPondVO wishingPondVO, List<WishingListVO> list) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer wish_no = null;
		
		try {
			con = JDBCUtil.getConnection();
			
			con.setAutoCommit(false);
			
			String col[] = { "WISH_NO" };
			ps = con.prepareStatement(INSERT, col);
			
			ps.setString(1, wishingPondVO.getWish_name());
			ps.setDate(2, wishingPondVO.getWish_start());
			ps.setDate(3, wishingPondVO.getWish_end());
			
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys(); // 取得自動編號的號碼
			if (rs.next()) {
				wish_no = rs.getInt(1); // 第一欄
			}
			
			rs.close();
			
//			 同時新增項次
			WishingListJDBCDAO dao = new WishingListJDBCDAO();
			for(WishingListVO wishingListVO: list) {
				wishingListVO.setWish_no(wish_no);
				dao.insert(wishingListVO, con);
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
		return wish_no;
	}
	@Override
	public void delete(Integer wishNo) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(DELETE);
			
			ps.setInt(1, wishNo);
			
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
	public void update(WishingPondVO wishingPondVO) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(UPDATE);
			
			ps.setString(1, wishingPondVO.getWish_name());
			ps.setDate(2, wishingPondVO.getWish_start());
			ps.setDate(3, wishingPondVO.getWish_end());
			ps.setInt(4, wishingPondVO.getWish_no());
			
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
	public Integer updateWithOptions(WishingPondVO wishingPondVO, List<WishingListVO> list) {
		Connection con = null;
		PreparedStatement ps = null;
		Integer emp_no = null;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(UPDATE);
			
			ps.setString(1, wishingPondVO.getWish_name());
			ps.setDate(2, wishingPondVO.getWish_start());
			ps.setDate(3, wishingPondVO.getWish_end());
			ps.setInt(4, wishingPondVO.getWish_no());
			
			ps.executeUpdate();
			
			// 刪除並新增選項
			WishingListJDBCDAO wishListDao = new WishingListJDBCDAO();
			List<WishingListVO> deleteList = wishListDao.findByWishNo(wishingPondVO.getWish_no());
			for(WishingListVO deleteVO: deleteList) {
				wishListDao.delete(deleteVO, con);
			}
			for(WishingListVO insertVO: list) {
				wishListDao.insert(insertVO, con);
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
		return emp_no;
	}
	
	@Override
	public void updateTopOne(Integer wishNo, Integer topOne) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(UPDATE_TOP_ONE);
			
			ps.setInt(1, topOne);
			ps.setInt(2, wishNo);
			
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
	public WishingPondVO findByWishNo(Integer wishNo) {
		WishingPondVO wishingPondVO = new WishingPondVO();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(READ_ONE);
			
			ps.setInt(1, wishNo);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				wishingPondVO.setWish_no(rs.getInt("WISH_NO"));
				wishingPondVO.setWish_name(rs.getString("WISH_NAME"));
				wishingPondVO.setWish_start(rs.getDate("WISH_START"));
				wishingPondVO.setWish_end(rs.getDate("WISH_END"));
				wishingPondVO.setTop_one(rs.getInt("TOP_ONE"));
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
		return wishingPondVO;
	}
	@Override
	public List<WishingPondVO> getAll() {
		List<WishingPondVO> list = new ArrayList<WishingPondVO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(READ_ALL);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				WishingPondVO wishingPondVO = new WishingPondVO();
				wishingPondVO.setWish_no(rs.getInt("WISH_NO"));
				wishingPondVO.setWish_name(rs.getString("WISH_NAME"));
				wishingPondVO.setWish_start(rs.getDate("WISH_START"));
				wishingPondVO.setWish_end(rs.getDate("WISH_END"));
				wishingPondVO.setTop_one(rs.getInt("TOP_ONE"));
				list.add(wishingPondVO);
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
	public List<WishingPondVO> getAvaliable() {
		List<WishingPondVO> list = new ArrayList<WishingPondVO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(READ_AVALIABLE);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				WishingPondVO wishingPondVO = new WishingPondVO();
				wishingPondVO.setWish_no(rs.getInt("WISH_NO"));
				wishingPondVO.setWish_name(rs.getString("WISH_NAME"));
				wishingPondVO.setWish_start(rs.getDate("WISH_START"));
				wishingPondVO.setWish_end(rs.getDate("WISH_END"));
				wishingPondVO.setTop_one(rs.getInt("TOP_ONE"));
				list.add(wishingPondVO);
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
	public List<WishingPondVO> getAll(Map<String, String[]> map) {
		String sql = "select WISH_NO, WISH_NAME, WISH_START, WISH_END, TOP_ONE "
				+ "from wishing_pond ";
//		System.out.println(map.get("searchName")[0]);
//		System.out.println(map.get("searchPeriod")[0]);
//		System.out.println(map.get("start_date")[0]);
//		System.out.println(map.get("end_date")[0]);

		// add sql
		int countStatement = 0;
		
		// date condition
		if(map.get("start_date")[0].length() != 0 && map.get("end_date")[0].length() != 0){
			sql += "where " + map.get("searchPeriod")[0] + " between ? and ? "; 
			countStatement++;
		} else if(map.get("start_date")[0].length() != 0) {
			sql += "where " + map.get("searchPeriod")[0] + " >= ? "; 
			countStatement++;
		} else if(map.get("end_date")[0].length() != 0) {
			sql += "where " + map.get("searchPeriod")[0] + " <= ? ";
			countStatement++;
		}
		// key word
		if(map.get("searchName")[0].length() != 0) {
			if(countStatement == 0) {
				sql += "where WISH_NAME like ? ";
			} else { 
				sql += "and WISH_NAME like ? ";
			}
		}
		// end		
		sql += "order by WISH_NO ";
//		System.out.println(sql);
		
		List<WishingPondVO> list = new ArrayList<WishingPondVO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			// set ?
			int countTimes = 1;
			Set<String> keys = map.keySet();
			for(String key: keys) {
				String value = map.get(key)[0];
				if(value.length() != 0) {
					if("start_date".equals(key) || "end_date".equals(key)) { 
						ps.setDate(countTimes, java.sql.Date.valueOf(value));
						countTimes++;
					} else if("searchName".equals(key)) { 
						ps.setString(countTimes, "%" + value + "%");
						countTimes++;
					}
				}
			}
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				WishingPondVO wishingPondVO = new WishingPondVO();
				wishingPondVO.setWish_no(rs.getInt("WISH_NO"));
				wishingPondVO.setWish_name(rs.getString("WISH_NAME"));
				wishingPondVO.setWish_start(rs.getDate("WISH_START"));
				wishingPondVO.setWish_end(rs.getDate("WISH_END"));
				wishingPondVO.setTop_one(rs.getInt("TOP_ONE"));
				list.add(wishingPondVO);
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
	public boolean updatable(Integer wishNo) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(UPDATABLE);
			
			ps.setDate(1, new java.sql.Date(java.lang.System.currentTimeMillis()));
			ps.setInt(2, wishNo);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				if(rs.getInt(1) == 1) {
					return true;
				}
			}
			
			rs.close();
			
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
		return false;
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
			
			rs.close();
			
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
	public static void main(String[] args) {
		WishingPondJDBCDAO dao = new WishingPondJDBCDAO();
		
		// C
//		WishingPondVO wishingPondVO1 = new WishingPondVO();
//		wishingPondVO1.setWish_name("端午特選");
//		wishingPondVO1.setWish_start(Timestamp.valueOf("2022-06-01 00:00:00"));
//		wishingPondVO1.setWish_end(Timestamp.valueOf("2022-07-31 23:59:59"));
//		dao.insert(wishingPondVO1);
		
		// C with Option
//		List<WishingListVO> list = new ArrayList<WishingListVO>();
//		WishingListVO wl1 = new WishingListVO();
//		wl1.setMv_id(2);
//		list.add(wl1);
//		WishingListVO wl2 = new WishingListVO();
//		wl2.setMv_id(5);
//		list.add(wl2);
//		WishingListVO wl3 = new WishingListVO();
//		wl3.setMv_id(8);
//		list.add(wl3);
//		
//		WishingPondVO wishingPondVO1 = new WishingPondVO();
//		wishingPondVO1.setWish_name("假日延選");
//		wishingPondVO1.setWish_start(Date.valueOf("2022-06-01"));
//		wishingPondVO1.setWish_end(Date.valueOf("2022-07-31"));
//		dao.insertWithOptions(wishingPondVO1, list);
		
		// R_ONE
//		List<WishingPondVO> list = dao.findByWishNo(1);
//		for(WishingPondVO wp: list) {
//			System.out.print(wp.getWish_no() + ", ");
//			System.out.print(wp.getWish_name() + ", ");
//			System.out.print(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(wp.getWish_start()) + ", ");
//			System.out.print(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(wp.getWish_end()) + ", ");
//			System.out.println(wp.getTop_one());
//		}
		
		// R_ALL
//		List<WishingPondVO> list = dao.getAllFromNow();
//		for(WishingPondVO wp: list) {
//			System.out.print(wp.getWish_no() + ", ");
//			System.out.print(wp.getWish_name() + ", ");
//			System.out.print(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(wp.getWish_start()) + ", ");
//			System.out.print(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(wp.getWish_end()) + ", ");
//			System.out.println(wp.getTop_one());
//		}
		
		//R_MUL
//		Map<String, String[]> map = new LinkedHashMap<String, String[]>();
//		map.put("searchPeriod", new String[] {"WISH_END"});
//		map.put("start_date", new String[] {"2022-05-28"});
//		map.put("end_date", new String[] {"2022-08-01"});
//		map.put("searchName", new String[] {"活動"});
//		
//		List<WishingPondVO> list = dao.getAll(map);
//		for(WishingPondVO wp: list) {
//			System.out.print(wp.getWish_no() + ", ");
//			System.out.print(wp.getWish_name() + ", ");
//			System.out.print(wp.getWish_start().toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ", ");
////			System.out.println(wp.getWish_end().toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//			System.out.print(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(wp.getWish_end()) + ", ");
//			System.out.println(wp.getTop_one());
//		}
		
		// U
//		WishingPondVO wishingPondVO2 = new WishingPondVO();
//		wishingPondVO2.setWish_no(3);
//		wishingPondVO2.setWish_name("端午節特選");
//		wishingPondVO2.setWish_start(Timestamp.valueOf("2022-08-01 00:00:00"));
//		wishingPondVO2.setWish_end(Timestamp.valueOf("2022-09-31 23:59:59"));
//		dao.update(wishingPondVO2);
		
		// D
//		dao.delete(3);
		
		// UPDATABLE?
//		System.out.println(dao.updatable(4));
	}

}

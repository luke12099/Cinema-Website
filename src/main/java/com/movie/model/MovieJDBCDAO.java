package com.movie.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.NamingException;
import javax.servlet.jsp.tagext.TryCatchFinally;

import com.common.JDBCUtil;
import com.showing.model.ShowingVO;

public class MovieJDBCDAO implements MovieDAO_interface{
	
	private static final String INSERT_STMT =
			"INSERT into Movie"
			+ "(MV_NAME,MV_E_NAME,MV_LONG,MV_LEVEL,MV_PICTURE,MV_DT, "
			+ "MV_ST_DATE,MV_ED_DATE,MV_TYPE,MV_CAST,MV_DRT,MV_TLER) "
			+ "values(?,?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String UPDATE =
			"UPDATE Movie set "
			+"MV_NAME=?, MV_E_NAME=?, MV_LONG=?, MV_LEVEL=?, MV_PICTURE=?, MV_DT=?, "
			+"MV_ST_DATE=?, MV_ED_DATE=?, MV_TYPE=?, MV_CAST=?, MV_DRT=?, MV_TLER=? "
			+"where MV_ID = ?";
	
	private static final String DELETE =
			"DELETE FROM Movie where MV_ID = ?";
	
	private static final String GET_ONE_STMT =
			"SELECT MV_ID,MV_NAME,MV_E_NAME,MV_LONG,MV_LEVEL,MV_PICTURE,MV_DT, "
			+"MV_ST_DATE,MV_ED_DATE,MV_TYPE,MV_CAST,MV_DRT,MV_TLER,MV_TT_CM,MV_TT_STAR "
			+"FROM Movie where MV_ID = ?";
	
	private static final String GET_ALL_STMT=
			"SELECT MV_ID,MV_NAME,MV_E_NAME,MV_LONG,MV_LEVEL,MV_PICTURE,MV_DT, "
			+"MV_ST_DATE,MV_ED_DATE,MV_TYPE,MV_CAST,MV_DRT,MV_TLER,MV_TT_CM,MV_TT_STAR "
			+"FROM Movie order by MV_ID";
	
	private static final String GET_SHOWING_MV=
			"select MV_ID,MV_NAME,MV_E_NAME,MV_LEVEL,MV_PICTURE,MV_ST_DATE,MV_ED_DATE,MV_TT_CM,MV_TT_STAR from movie "
			+ "where MV_ST_DATE <= CURRENT_DATE and MV_ED_DATE > CURRENT_DATE order by MV_ST_DATE desc";
	
	private static final String GET_COMING_MV=
			"select MV_ID,MV_NAME,MV_E_NAME,MV_LEVEL,MV_PICTURE,MV_ST_DATE from movie "
			+ "where MV_ST_DATE > CURRENT_DATE order by MV_ST_DATE asc";
	
	private static final String GET_Showings_BymvId_STMT = 
			"SELECT SH_ID,MV_ID,HL_ID,SH_STATE,SH_SEAT_STATE,SH_TIME,SH_TYPE FROM showing where MV_ID = ? order by MV_ID";
	
	@Override
	public void insert(MovieVO movieVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,movieVO.getMvName());
			pstmt.setString(2,movieVO.getMvEName());
			pstmt.setInt(3,movieVO.getMvLong());
			pstmt.setInt(4, movieVO.getMvLevel());
			pstmt.setString(5,movieVO.getMvPicture());
			pstmt.setString(6,movieVO.getMvDt());
			pstmt.setDate(7,movieVO.getMvStDate());
			pstmt.setDate(8,movieVO.getMvEdDate());
			pstmt.setString(9,movieVO.getMvType());
			pstmt.setString(10,movieVO.getMvCast());
			pstmt.setString(11,movieVO.getMvDrt());
			pstmt.setString(12,movieVO.getMvTler());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// Class.forname嚙賠出 ClassNotFound嚙課外
			throw new RuntimeException("Couldn't load database driver."
			+e.getMessage());
		} catch (SQLException e) {
			// DriverManager 嚙賠出 SQL嚙課外
			throw new RuntimeException("A database error occured."
			+e.getMessage());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void update(MovieVO movieVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(UPDATE);
			
			pstmt.setString(1,movieVO.getMvName());
			pstmt.setString(2,movieVO.getMvEName());
			pstmt.setInt(3,movieVO.getMvLong());
			pstmt.setInt(4, movieVO.getMvLevel());
			pstmt.setString(5,movieVO.getMvPicture());
			pstmt.setString(6,movieVO.getMvDt());
			pstmt.setDate(7,movieVO.getMvStDate());
			pstmt.setDate(8,movieVO.getMvEdDate());
			pstmt.setString(9,movieVO.getMvType());
			pstmt.setString(10,movieVO.getMvCast());
			pstmt.setString(11,movieVO.getMvDrt());
			pstmt.setString(12,movieVO.getMvTler());
			pstmt.setInt(13, movieVO.getMvId());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// Class.forname嚙賠出 ClassNotFound嚙課外
			throw new RuntimeException("Couldn't load database driver."
			+e.getMessage());
		} catch (SQLException e) {
			// DriverManager 嚙賠出 SQL嚙課外
			throw new RuntimeException("A database error occured."
			+e.getMessage());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void delete(Integer mvId) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(DELETE);

			pstmt.setInt(1, mvId);

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
		} catch (NamingException e) {
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public MovieVO findByPrimaryKey(Integer mvId) {
		
		MovieVO movieVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, mvId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				movieVO = new MovieVO();
				movieVO.setMvId(rs.getInt("MV_ID"));
				movieVO.setMvName(rs.getString("MV_NAME"));
				movieVO.setMvEName(rs.getString("MV_E_NAME"));
				movieVO.setMvLong(rs.getInt("MV_LONG"));
				movieVO.setMvLevel(rs.getInt("MV_LEVEL"));
				movieVO.setMvPicture(rs.getString("MV_PICTURE"));
				movieVO.setMvDt(rs.getString("MV_DT"));
				movieVO.setMvStDate(rs.getDate("MV_ST_DATE"));
				movieVO.setMvEdDate(rs.getDate("MV_ED_DATE"));
				movieVO.setMvType(rs.getString("MV_TYPE"));
				movieVO.setMvCast(rs.getString("MV_CAST"));
				movieVO.setMvDrt(rs.getString("MV_DRT"));
				movieVO.setMvTler(rs.getString("MV_TLER"));
				movieVO.setMvTtCm(rs.getInt("MV_TT_CM"));
				movieVO.setMvTtStar(rs.getInt("MV_TT_STAR"));
			}
			
			
		} catch (ClassNotFoundException e) {
			// Class.forname嚙賠出 ClassNotFound嚙課外
			throw new RuntimeException("Couldn't load database driver."
			+e.getMessage());
		} catch (SQLException se) {
			// DriverManager 嚙賠出 SQL嚙課外
			throw new RuntimeException("A database error occured."
			+se.getMessage());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return movieVO;
	}

	@Override
	public List<MovieVO> getAll() {
		List<MovieVO> list = new ArrayList<MovieVO>();
		MovieVO movieVO = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				movieVO = new MovieVO();
				movieVO.setMvId(rs.getInt("MV_ID"));
				movieVO.setMvName(rs.getString("MV_NAME"));
				movieVO.setMvEName(rs.getString("MV_E_NAME"));
				movieVO.setMvLong(rs.getInt("MV_LONG"));
				movieVO.setMvLevel(rs.getInt("MV_LEVEL"));
				movieVO.setMvPicture(rs.getString("MV_PICTURE"));
				movieVO.setMvDt(rs.getString("MV_DT"));
				movieVO.setMvStDate(rs.getDate("MV_ST_DATE"));
				movieVO.setMvEdDate(rs.getDate("MV_ED_DATE"));
				movieVO.setMvType(rs.getString("MV_TYPE"));
				movieVO.setMvCast(rs.getString("MV_CAST"));
				movieVO.setMvDrt(rs.getString("MV_DRT"));
				movieVO.setMvTler(rs.getString("MV_TLER"));
				movieVO.setMvTtCm(rs.getInt("MV_TT_CM"));
				movieVO.setMvTtStar(rs.getInt("MV_TT_STAR"));
				list.add(movieVO);
			}
			
		} catch (ClassNotFoundException e) {
			// Class.forname嚙賠出 ClassNotFound嚙課外
			throw new RuntimeException("Couldn't load database driver."
			+e.getMessage());
		} catch (SQLException e) {
			// DriverManager 嚙賠出 SQL嚙課外
			throw new RuntimeException("A database error occured."
			+e.getMessage());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}
	
	@Override
	public List<MovieVO> getShowingMV() {
		List<MovieVO> list = new ArrayList<MovieVO>();
		MovieVO movieVO = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(GET_SHOWING_MV);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				movieVO = new MovieVO();
				movieVO.setMvId(rs.getInt("MV_ID"));
				movieVO.setMvName(rs.getString("MV_NAME"));
				movieVO.setMvEName(rs.getString("MV_E_NAME"));
				movieVO.setMvLevel(rs.getInt("MV_LEVEL"));
				movieVO.setMvPicture(rs.getString("MV_PICTURE"));
				movieVO.setMvStDate(rs.getDate("MV_ST_DATE"));
				movieVO.setMvEdDate(rs.getDate("MV_ED_DATE"));
				movieVO.setMvTtCm(rs.getInt("MV_TT_CM"));
				movieVO.setMvTtStar(rs.getInt("MV_TT_STAR"));
				list.add(movieVO);
			}
			
		} catch (ClassNotFoundException e) {
			// Class.forname嚙賠出 ClassNotFound嚙課外
			throw new RuntimeException("Couldn't load database driver."
			+e.getMessage());
		} catch (SQLException e) {
			// DriverManager 嚙賠出 SQL嚙課外
			throw new RuntimeException("A database error occured."
			+e.getMessage());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public List<MovieVO> getComingMV() {
		
		List<MovieVO> list = new ArrayList<MovieVO>();
		MovieVO movieVO = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(GET_COMING_MV);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				movieVO = new MovieVO();
				movieVO.setMvId(rs.getInt("MV_ID"));
				movieVO.setMvName(rs.getString("MV_NAME"));
				movieVO.setMvEName(rs.getString("MV_E_NAME"));
				movieVO.setMvLevel(rs.getInt("MV_LEVEL"));
				movieVO.setMvPicture(rs.getString("MV_PICTURE"));
				movieVO.setMvStDate(rs.getDate("MV_ST_DATE"));
				list.add(movieVO);
			}
			
		} catch (ClassNotFoundException e) {
			// Class.forname嚙賠出 ClassNotFound嚙課外
			throw new RuntimeException("Couldn't load database driver."
			+e.getMessage());
		} catch (SQLException e) {
			// DriverManager 嚙賠出 SQL嚙課外
			throw new RuntimeException("A database error occured."
			+e.getMessage());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	
	@Override
	public Set<ShowingVO> getShowingsBymvId(Integer mvId) {
		Set<ShowingVO> set = new LinkedHashSet<ShowingVO>();
		ShowingVO showingVO = null;
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(GET_Showings_BymvId_STMT);
			pstmt.setInt(1, mvId);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				showingVO = new ShowingVO();
				showingVO.setSH_ID(rs.getInt("SH_ID"));
				showingVO.setmvId(rs.getInt("MV_ID"));
				showingVO.setHL_ID(rs.getInt("HL_ID"));
				showingVO.setSH_STATE(rs.getInt("SH_STATE"));
				showingVO.setSH_SEAT_STATE(rs.getString("SH_SEAT_STATE"));
				showingVO.setSH_TIME(rs.getTimestamp("SH_TIME"));
				showingVO.setSH_TYPE(rs.getInt("SH_TYPE"));
				set.add(showingVO); // Store the row in the vector
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver."
					+e.getMessage());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return set;
	}
	
	
	@Override
	 public List<MovieVO> compositeQuery_Search(Map<String, String[]> map) {
	  
	  List<MovieVO> list = new ArrayList<MovieVO>();
	  MovieVO movieVO = null;
	  
	  Connection conn = null;
	  ResultSet rs =null;
	  PreparedStatement pstmt = null;
	  
	  try {
	   conn = JDBCUtil.getConnection();
	   String finalSQL=
	     "SELECT * FROM MOVIE"
	     + CompositeQuery_movie.get_WhereCondition(map)
	     + " order by MV_ID";
	   pstmt = conn.prepareStatement(finalSQL);
	   System.out.println(finalSQL);
	   rs = pstmt.executeQuery();
	   
	   while(rs.next()) {
	    movieVO = new MovieVO();
	    movieVO.setMvId(rs.getInt("MV_ID"));
	    movieVO.setMvName(rs.getString("MV_NAME"));
	    movieVO.setMvEName(rs.getString("MV_E_NAME"));
	    movieVO.setMvLevel(rs.getInt("MV_LEVEL"));
	    movieVO.setMvType(rs.getString("MV_TYPE"));
	    
	    list.add(movieVO);
	   }
	   
	  } catch (SQLException se) {
	   throw new RuntimeException("A database error occured. "
	     + se.getMessage());
	  } catch (ClassNotFoundException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  } catch (NamingException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
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
	   if (conn != null) {
	    try {
	     conn.close();
	    } catch (Exception e) {
	     e.printStackTrace(System.err);
	    }
	   }
	  }
	  
	  return list;
	 }
		
	
	
	public static void main(String[] args) {
		MovieJDBCDAO dao = new MovieJDBCDAO();
		
		// ?嚙踝蕭嚙�?
		
//		MovieVO movieVO1 = new MovieVO();
//		movieVO1.setMvName("AAA");
//		movieVO1.setMvEName("AAA");
//		movieVO1.setMvLong(120);
//		movieVO1.setMvLevel(2);
//		movieVO1.setMvPicture("AAA");
//		movieVO1.setMvDt("AAA");
//		movieVO1.setMvStDate(java.sql.Date.valueOf("2005-01-01"));
//		movieVO1.setMvEdDate(java.sql.Date.valueOf("2005-02-01"));
//		movieVO1.setMvType("AAA");
//		movieVO1.setMvCast("AAA");
//		movieVO1.setMvDrt("AAA");
//		movieVO1.setMvTler("AAA");
//		dao.insert(movieVO1);
//		
		// 靽格
		
//		MovieVO movieVo2 = new MovieVO();
//		movieVo2.setMvId(15);
//		movieVo2.setMvName("AAA");
//		movieVo2.setMvEName("AAA");
//		movieVo2.setMvLong(120);
//		movieVo2.setMvLevel(2);
//		movieVo2.setMvPicture("AAA");
//		movieVo2.setMvDt("AAA");
//		movieVo2.setMvStDate(java.sql.Date.valueOf("2005-01-01"));
//		movieVo2.setMvEdDate(java.sql.Date.valueOf("2005-02-01"));
//		movieVo2.setMvType("AAA");
//		movieVo2.setMvCast("AAA");
//		movieVo2.setMvDrt("AAA");
//		movieVo2.setMvTler("AAA");
//		dao.update(movieVo2);
		
		// ?嚙踝蕭?嚙踝蕭
		
//		dao.delete(14);
		
		// get one
//		MovieVO movieVO3 = dao.findByPrimaryKey(1);
//		System.out.print(movieVO3.getMvId() + ",");
//		System.out.print(movieVO3.getMvName() + ",");
//		System.out.print(movieVO3.getMvEName() + ",");
//		System.out.print(movieVO3.getMvLong() + ",");
//		System.out.print(movieVO3.getMvLevel() + ",");
//		System.out.print(movieVO3.getMvPicture() + ",");
//		System.out.print(movieVO3.getMvDt() + ",");
//		System.out.print(movieVO3.getMvStDate() + ",");
//		System.out.print(movieVO3.getMvEdDate() + ",");
//		System.out.print(movieVO3.getMvType() + ",");
//		System.out.print(movieVO3.getMvCast() + ",");
//		System.out.print(movieVO3.getMvDrt() + ",");
//		System.out.print(movieVO3.getMvTler() + ",");
//		System.out.print(movieVO3.getMvTtCm() + ",");
//		System.out.print(movieVO3.getMvTtStar() + ",");
//		System.out.println();
		
		// getAll
//		List<MovieVO> list = dao.getAll();
//		for(MovieVO movie : list) {
//			System.out.print(movie.getMvId() + ",");
//			System.out.print(movie.getMvName() + ",");
//			System.out.print(movie.getMvEName() + ",");
//			System.out.print(movie.getMvLong() + ",");
//			System.out.print(movie.getMvLevel() + ",");
//			System.out.print(movie.getMvPicture() + ",");
//			System.out.print(movie.getMvDt() + ",");
//			System.out.print(movie.getMvStDate() + ",");
//			System.out.print(movie.getMvEdDate() + ",");
//			System.out.print(movie.getMvType() + ",");
//			System.out.print(movie.getMvCast() + ",");
//			System.out.print(movie.getMvDrt() + ",");
//			System.out.print(movie.getMvTler() + ",");
//			System.out.print(movie.getMvTtCm() + ",");
//			System.out.print(movie.getMvTtStar() + ",");
//			System.out.println();
//		}
		
		// 皜祈岫銝�葉
//		List<MovieVO> list = dao.getShowingMV();
//		for(MovieVO movieVO : list) {
//			System.out.println(movieVO.getMvName());
//			System.out.println(movieVO.getMvEName());
//			System.out.println(movieVO.getMvLevel());
//			System.out.println(movieVO.getMvPicture());
//			System.out.println(movieVO.getMvStDate());
//			System.out.println(movieVO.getMvTtCm());
//			System.out.println(movieVO.getMvTtStar());
//		}
		// 皜祈岫�撠���
//		List<MovieVO> list2 = dao.getComingMV();
//		for(MovieVO movieVO : list2) {
//			System.out.println(movieVO.getMvName());
//			System.out.println(movieVO.getMvEName());
//			System.out.println(movieVO.getMvLevel());
//			System.out.println(movieVO.getMvPicture());
//			System.out.println(movieVO.getMvStDate());
//		}
		
	}

	
	}


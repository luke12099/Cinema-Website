package com.merchandise_inf.model;

import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MerchDAO implements MerchDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/HireMe");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/movietheater?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	private static final String INSERT_STMT = "INSERT INTO merchandise_inf (MERCH_NAME, MERCH_DT, MERCH_PIC1, MERCH_PIC2, MERCH_PIC3, MERCH_PIC4, MERCH_PIC5, MERCH_PRICE, MERCH_CLASS, SOLD_TOTAL, MERCH_STATUS, MERCH_STOCK) Values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String GET_ALL_STMT = "SELECT MERCH_ID, MERCH_NAME, MERCH_DT, MERCH_PIC1, MERCH_PIC2, MERCH_PIC3, MERCH_PIC4, MERCH_PIC5, MERCH_DATE, MERCH_PRICE, MERCH_CLASS, SOLD_TOTAL, MERCH_STATUS, MERCH_STOCK FROM merchandise_inf order by MERCH_ID;";
	private static final String GET_ONE_STMT = "SELECT MERCH_ID, MERCH_NAME, MERCH_DT, MERCH_PIC1, MERCH_PIC2, MERCH_PIC3, MERCH_PIC4, MERCH_PIC5, MERCH_DATE, MERCH_PRICE, MERCH_CLASS, SOLD_TOTAL, MERCH_STATUS, MERCH_STOCK FROM merchandise_inf where MERCH_ID = ?;";
	private static final String DELETE = "DELETE FROM merchandise_inf WHERE MERCH_ID = ?;";
	private static final String UPDATE = "UPDATE merchandise_inf set MERCH_NAME=?, MERCH_DT=?, MERCH_PIC1=?, MERCH_PIC2=?, MERCH_PIC3=?, MERCH_PIC4=?, MERCH_PIC5=?, MERCH_PRICE=?, MERCH_CLASS=?, SOLD_TOTAL=?, MERCH_STATUS=?, MERCH_STOCK=? WHERE MERCH_ID = ?;";
	private static final String GET_FROM_SEARCH = "SELECT MERCH_ID, MERCH_NAME, MERCH_DT, MERCH_PIC1, MERCH_PIC2, MERCH_PIC3, MERCH_PIC4, MERCH_PIC5, MERCH_DATE, MERCH_PRICE, MERCH_CLASS, SOLD_TOTAL, MERCH_STATUS, MERCH_STOCK FROM merchandise_inf where MERCH_NAME LIKE ?;";
	private static final String GET_FROM_INDEX_SEARCH = "SELECT MERCH_ID, MERCH_NAME, MERCH_DT, MERCH_PIC1, MERCH_PIC2, MERCH_PIC3, MERCH_PIC4, MERCH_PIC5, MERCH_DATE, MERCH_PRICE, MERCH_CLASS, SOLD_TOTAL, MERCH_STATUS, MERCH_STOCK FROM merchandise_inf where MERCH_NAME LIKE ?;";
	private static final String GET_FROM_HOTSELL = "SELECT MERCH_ID, MERCH_NAME, MERCH_DT, MERCH_PIC1, MERCH_PIC2, MERCH_PIC3, MERCH_PIC4, MERCH_PIC5, MERCH_DATE, MERCH_PRICE, MERCH_CLASS, SOLD_TOTAL, MERCH_STATUS, MERCH_STOCK FROM merchandise_inf where MERCH_STATUS = 2 AND (MERCH_PRICE BETWEEN ? AND ?) order by merch_date desc limit 8;";
	private static final String GET_FROM_NEWEST = "SELECT MERCH_ID, MERCH_NAME, MERCH_DT, MERCH_PIC1, MERCH_PIC2, MERCH_PIC3, MERCH_PIC4, MERCH_PIC5, MERCH_DATE, MERCH_PRICE, MERCH_CLASS, SOLD_TOTAL, MERCH_STATUS, MERCH_STOCK FROM merchandise_inf where (MERCH_STATUS = 1 OR MERCH_STATUS = 2) AND (MERCH_PRICE BETWEEN ? AND ?) order by merch_date desc limit 8;";
	private static final String GET_FROM_MOSTSOLD = "SELECT MERCH_ID, MERCH_NAME, MERCH_DT, MERCH_PIC1, MERCH_PIC2, MERCH_PIC3, MERCH_PIC4, MERCH_PIC5, MERCH_DATE, MERCH_PRICE, MERCH_CLASS, SOLD_TOTAL, MERCH_STATUS, MERCH_STOCK FROM merchandise_inf where (MERCH_STATUS = 1 OR MERCH_STATUS = 2) AND (MERCH_PRICE BETWEEN ? AND ?) order by SOLD_TOTAL desc limit 8;";
	private static final String GET_FROM_CLASS = "SELECT MERCH_ID, MERCH_NAME, MERCH_DT, MERCH_PIC1, MERCH_PIC2, MERCH_PIC3, MERCH_PIC4, MERCH_PIC5, MERCH_DATE, MERCH_PRICE, MERCH_CLASS, SOLD_TOTAL, MERCH_STATUS, MERCH_STOCK FROM merchandise_inf where (MERCH_STATUS = 2 OR MERCH_STATUS = 1) and MERCH_CLASS =? order by MERCH_STATUS desc, MERCH_DATE desc;";
	
	@Override
	public void insert(MerchVO merchVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
//			pstmt.setBinaryStream(7, in5, in5.available());
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, merchVo.getMerchName());
			pstmt.setString(2, merchVo.getMerchDT());
			pstmt.setBlob(3, merchVo.getMerchPic1());
			pstmt.setBlob(4, merchVo.getMerchPic2());
			pstmt.setBlob(5, merchVo.getMerchPic3());
			pstmt.setBlob(6, merchVo.getMerchPic4());
			pstmt.setBlob(7, merchVo.getMerchPic5());
			pstmt.setDouble(8, merchVo.getMerchPrice());
			pstmt.setString(9, merchVo.getMerchClass());
			pstmt.setInt(10, merchVo.getSoldTotal());
			pstmt.setByte(11, merchVo.getMerchStatus());
			pstmt.setInt(12, merchVo.getMerchStock());

			pstmt.executeUpdate();

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

	@Override
	public void update(MerchVO merchVo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
//			FileInputStream in1 = new FileInputStream("C:\\CGA102_WebApp\\eclipse_WTP_workspace1\\CGA102_Group1\\src\\main\\java\\com\\merch\\model\\image1.jpg");
//			FileInputStream in2 = new FileInputStream("C:\\CGA102_WebApp\\eclipse_WTP_workspace1\\CGA102_Group1\\src\\main\\java\\com\\merch\\model\\image2.jpg");
//			FileInputStream in3 = new FileInputStream("C:\\CGA102_WebApp\\eclipse_WTP_workspace1\\CGA102_Group1\\src\\main\\java\\com\\merch\\model\\image3.jpg");
//			FileInputStream in4 = new FileInputStream("C:\\CGA102_WebApp\\eclipse_WTP_workspace1\\CGA102_Group1\\src\\main\\java\\com\\merch\\model\\image4.jpg");
//			FileInputStream in5 = new FileInputStream("C:\\CGA102_WebApp\\eclipse_WTP_workspace1\\CGA102_Group1\\src\\main\\java\\com\\merch\\model\\image5.jpg");
			con = ds.getConnection();
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, merchVo.getMerchName());
			pstmt.setString(2, merchVo.getMerchDT());
			pstmt.setBlob(3, merchVo.getMerchPic1());
			pstmt.setBlob(4, merchVo.getMerchPic2());
			pstmt.setBlob(5, merchVo.getMerchPic3());
			pstmt.setBlob(6, merchVo.getMerchPic4());
			pstmt.setBlob(7, merchVo.getMerchPic5());
//			if (in1 != null)
//				pstmt.setBinaryStream(3, in1, in1.available());
//			if (in2 != null)
//				pstmt.setBinaryStream(4, in2, in2.available());
//			if (in3 != null)
//				pstmt.setBinaryStream(5, in3, in3.available());
//			if (in4 != null)
//				pstmt.setBinaryStream(6, in4, in4.available());
//			if (in5 != null)
//				pstmt.setBinaryStream(7, in5, in5.available());
			pstmt.setDouble(8, merchVo.getMerchPrice());
			pstmt.setString(9, merchVo.getMerchClass());
			pstmt.setInt(10, merchVo.getSoldTotal());
			pstmt.setByte(11, merchVo.getMerchStatus());
			pstmt.setInt(12, merchVo.getMerchStock());
			pstmt.setInt(13, merchVo.getMerchID());
			
			pstmt.executeUpdate();
			// Handle any driver errors
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

	@Override
	public void delete(Integer merchID) {
		Connection con = null;
		PreparedStatement pstmt = null;try {

			con = ds.getConnection();
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, merchID);

			pstmt.executeUpdate();

			// Handle any driver errors
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
//	private Byte[] transfer(byte[] bytes) {
//		Byte[] Bytes = new Byte[bytes.length];
//		for(byte b: bytes) {
//			int i = 0;
//			Bytes[i++] = b;
//		}
//		return Bytes;
//	}

	@Override
	public MerchVO findByPrimaryKey(Integer merchID) {
		MerchVO merchVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, merchID);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				merchVo = new MerchVO();
				merchVo.setMerchID(rs.getInt("MERCH_ID"));
				merchVo.setMerchName(rs.getString("MERCH_NAME"));
				merchVo.setMerchDT(rs.getString("MERCH_DT"));
				merchVo.setMerchPic1(rs.getBlob("MERCH_PIC1"));
				merchVo.setMerchPic2(rs.getBlob("MERCH_PIC2"));
				merchVo.setMerchPic3(rs.getBlob("MERCH_PIC3"));
				merchVo.setMerchPic4(rs.getBlob("MERCH_PIC4"));
				merchVo.setMerchPic5(rs.getBlob("MERCH_PIC5"));
//				merchVo.setMerchPic1(transfer(rs.getBytes("MERCH_PIC1")));
//				merchVo.setMerchPic2(transfer(rs.getBytes("MERCH_PIC2")));
//				merchVo.setMerchPic3(transfer(rs.getBytes("MERCH_PIC3")));
//				merchVo.setMerchPic4(transfer(rs.getBytes("MERCH_PIC4")));
//				merchVo.setMerchPic5(transfer(rs.getBytes("MERCH_PIC5")));
				merchVo.setMerchDate(rs.getTimestamp("MERCH_DATE"));
				merchVo.setMerchPrice(rs.getDouble("MERCH_PRICE"));
				merchVo.setMerchClass(rs.getString("MERCH_CLASS"));
				merchVo.setSoldTotal(rs.getInt("SOLD_TOTAL"));
				merchVo.setMerchStatus(rs.getByte("MERCH_STATUS"));
				merchVo.setMerchStock(rs.getInt("MERCH_STOCK"));
			}
		
		
		}catch (SQLException se) {
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
		return merchVo;
	}
	

	@Override
	public List<MerchVO> getAll() {
		List<MerchVO> list = new ArrayList<MerchVO>();
		MerchVO merchVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				merchVo = new MerchVO();
				merchVo.setMerchID(rs.getInt("MERCH_ID"));
				merchVo.setMerchName(rs.getString("MERCH_NAME"));
				merchVo.setMerchDT(rs.getString("MERCH_DT"));
				merchVo.setMerchPic1(rs.getBlob("MERCH_PIC1"));
				merchVo.setMerchPic2(rs.getBlob("MERCH_PIC2"));
				merchVo.setMerchPic3(rs.getBlob("MERCH_PIC3"));
				merchVo.setMerchPic4(rs.getBlob("MERCH_PIC4"));
				merchVo.setMerchPic5(rs.getBlob("MERCH_PIC5"));
				merchVo.setMerchDate(rs.getTimestamp("MERCH_DATE"));
				merchVo.setMerchPrice(rs.getDouble("MERCH_PRICE"));
				merchVo.setMerchClass(rs.getString("MERCH_CLASS"));
				merchVo.setSoldTotal(rs.getInt("SOLD_TOTAL"));
				merchVo.setMerchStatus(rs.getByte("MERCH_STATUS"));
				merchVo.setMerchStock(rs.getInt("MERCH_STOCK"));
				list.add(merchVo);
			}
		
		
		}catch (SQLException se) {
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
	public List<MerchVO> getAll(Double minPrice, Double maxPrice) {
		List<MerchVO> list = new ArrayList<MerchVO>();
		MerchVO merchVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_FROM_HOTSELL);
			pstmt.setDouble(1, minPrice);
			pstmt.setDouble(2, maxPrice);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				merchVo = new MerchVO();
				merchVo.setMerchID(rs.getInt("MERCH_ID"));
				merchVo.setMerchName(rs.getString("MERCH_NAME"));
				merchVo.setMerchDT(rs.getString("MERCH_DT"));
				merchVo.setMerchPic1(rs.getBlob("MERCH_PIC1"));
				merchVo.setMerchPic2(rs.getBlob("MERCH_PIC2"));
				merchVo.setMerchPic3(rs.getBlob("MERCH_PIC3"));
				merchVo.setMerchPic4(rs.getBlob("MERCH_PIC4"));
				merchVo.setMerchPic5(rs.getBlob("MERCH_PIC5"));
				merchVo.setMerchDate(rs.getTimestamp("MERCH_DATE"));
				merchVo.setMerchPrice(rs.getDouble("MERCH_PRICE"));
				merchVo.setMerchClass(rs.getString("MERCH_CLASS"));
				merchVo.setSoldTotal(rs.getInt("SOLD_TOTAL"));
				merchVo.setMerchStatus(rs.getByte("MERCH_STATUS"));
				merchVo.setMerchStock(rs.getInt("MERCH_STOCK"));
				list.add(merchVo);
			}
			
			
		}catch (SQLException se) {
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
	public List<MerchVO> getMostSell(Double minPrice, Double maxPrice) {
		List<MerchVO> list = new ArrayList<MerchVO>();
		MerchVO merchVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_FROM_MOSTSOLD);
			pstmt.setDouble(1, minPrice);
			pstmt.setDouble(2, maxPrice);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				merchVo = new MerchVO();
				merchVo.setMerchID(rs.getInt("MERCH_ID"));
				merchVo.setMerchName(rs.getString("MERCH_NAME"));
				merchVo.setMerchDT(rs.getString("MERCH_DT"));
				merchVo.setMerchPic1(rs.getBlob("MERCH_PIC1"));
				merchVo.setMerchPic2(rs.getBlob("MERCH_PIC2"));
				merchVo.setMerchPic3(rs.getBlob("MERCH_PIC3"));
				merchVo.setMerchPic4(rs.getBlob("MERCH_PIC4"));
				merchVo.setMerchPic5(rs.getBlob("MERCH_PIC5"));
				merchVo.setMerchDate(rs.getTimestamp("MERCH_DATE"));
				merchVo.setMerchPrice(rs.getDouble("MERCH_PRICE"));
				merchVo.setMerchClass(rs.getString("MERCH_CLASS"));
				merchVo.setSoldTotal(rs.getInt("SOLD_TOTAL"));
				merchVo.setMerchStatus(rs.getByte("MERCH_STATUS"));
				merchVo.setMerchStock(rs.getInt("MERCH_STOCK"));
				list.add(merchVo);
			}
			
			
		}catch (SQLException se) {
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
	public List<MerchVO> getByclass(String merchClass) {
		List<MerchVO> list = new ArrayList<MerchVO>();
		MerchVO merchVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_FROM_CLASS);
			pstmt.setString(1, merchClass);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				merchVo = new MerchVO();
				merchVo.setMerchID(rs.getInt("MERCH_ID"));
				merchVo.setMerchName(rs.getString("MERCH_NAME"));
				merchVo.setMerchDT(rs.getString("MERCH_DT"));
				merchVo.setMerchPic1(rs.getBlob("MERCH_PIC1"));
				merchVo.setMerchPic2(rs.getBlob("MERCH_PIC2"));
				merchVo.setMerchPic3(rs.getBlob("MERCH_PIC3"));
				merchVo.setMerchPic4(rs.getBlob("MERCH_PIC4"));
				merchVo.setMerchPic5(rs.getBlob("MERCH_PIC5"));
				merchVo.setMerchDate(rs.getTimestamp("MERCH_DATE"));
				merchVo.setMerchPrice(rs.getDouble("MERCH_PRICE"));
				merchVo.setMerchClass(rs.getString("MERCH_CLASS"));
				merchVo.setSoldTotal(rs.getInt("SOLD_TOTAL"));
				merchVo.setMerchStatus(rs.getByte("MERCH_STATUS"));
				merchVo.setMerchStock(rs.getInt("MERCH_STOCK"));
				list.add(merchVo);
			}
			
			
		}catch (SQLException se) {
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
	public List<MerchVO> getNewest(Double minPrice, Double maxPrice) {
		List<MerchVO> list = new ArrayList<MerchVO>();
		MerchVO merchVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_FROM_NEWEST);
			pstmt.setDouble(1, minPrice);
			pstmt.setDouble(2, maxPrice);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				merchVo = new MerchVO();
				merchVo.setMerchID(rs.getInt("MERCH_ID"));
				merchVo.setMerchName(rs.getString("MERCH_NAME"));
				merchVo.setMerchDT(rs.getString("MERCH_DT"));
				merchVo.setMerchPic1(rs.getBlob("MERCH_PIC1"));
				merchVo.setMerchPic2(rs.getBlob("MERCH_PIC2"));
				merchVo.setMerchPic3(rs.getBlob("MERCH_PIC3"));
				merchVo.setMerchPic4(rs.getBlob("MERCH_PIC4"));
				merchVo.setMerchPic5(rs.getBlob("MERCH_PIC5"));
				merchVo.setMerchDate(rs.getTimestamp("MERCH_DATE"));
				merchVo.setMerchPrice(rs.getDouble("MERCH_PRICE"));
				merchVo.setMerchClass(rs.getString("MERCH_CLASS"));
				merchVo.setSoldTotal(rs.getInt("SOLD_TOTAL"));
				merchVo.setMerchStatus(rs.getByte("MERCH_STATUS"));
				merchVo.setMerchStock(rs.getInt("MERCH_STOCK"));
				list.add(merchVo);
			}
			
			
		}catch (SQLException se) {
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
	
	public List<MerchVO> getByName(String merchName) {
		List<MerchVO> list = new ArrayList<MerchVO>();
		MerchVO merchVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_FROM_SEARCH);
			String Name1 = "%" + merchName + "%";
			pstmt.setString(1, Name1);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				merchVo = new MerchVO();
				merchVo.setMerchID(rs.getInt("MERCH_ID"));
				merchVo.setMerchName(rs.getString("MERCH_NAME"));
				merchVo.setMerchDT(rs.getString("MERCH_DT"));
				merchVo.setMerchPic1(rs.getBlob("MERCH_PIC1"));
				merchVo.setMerchPic2(rs.getBlob("MERCH_PIC2"));
				merchVo.setMerchPic3(rs.getBlob("MERCH_PIC3"));
				merchVo.setMerchPic4(rs.getBlob("MERCH_PIC4"));
				merchVo.setMerchPic5(rs.getBlob("MERCH_PIC5"));
				merchVo.setMerchDate(rs.getTimestamp("MERCH_DATE"));
				merchVo.setMerchPrice(rs.getDouble("MERCH_PRICE"));
				merchVo.setMerchClass(rs.getString("MERCH_CLASS"));
				merchVo.setSoldTotal(rs.getInt("SOLD_TOTAL"));
				merchVo.setMerchStatus(rs.getByte("MERCH_STATUS"));
				merchVo.setMerchStock(rs.getInt("MERCH_STOCK"));
				list.add(merchVo);
			}
			
			
		}catch (SQLException se) {
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
	public List<MerchVO> getBySearch(String merchName, Double min, Double max) {
		List<MerchVO> list = new ArrayList<MerchVO>();
		MerchVO merchVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String totalSearch = "select a.* from (SELECT MERCH_ID, MERCH_NAME, MERCH_DT, MERCH_PIC1, MERCH_PIC2, MERCH_PIC3, MERCH_PIC4, MERCH_PIC5, MERCH_DATE, MERCH_PRICE, MERCH_CLASS, SOLD_TOTAL, MERCH_STATUS, MERCH_STOCK FROM merchandise_inf where MERCH_NAME LIKE '%";
			String union = "%' union SELECT MERCH_ID, MERCH_NAME, MERCH_DT, MERCH_PIC1, MERCH_PIC2, MERCH_PIC3, MERCH_PIC4, MERCH_PIC5, MERCH_DATE, MERCH_PRICE, MERCH_CLASS, SOLD_TOTAL, MERCH_STATUS, MERCH_STOCK FROM merchandise_inf where MERCH_NAME LIKE '%";
			String finalsearch = "一二三四五六七八九%') as a where a.MERCH_PRICE between ? and ?";
			for (int m = merchName.length(); m > 0; m--) {
				for (int i = 0; i < merchName.length(); i++) {
					if( i + m <= merchName.length()) {
					totalSearch += merchName.substring(i, m + i) + union;
					}
				}
			}
			String Searchstmt = totalSearch + finalsearch;
			System.out.println(Searchstmt);
			con = ds.getConnection();
//			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Searchstmt);;
			pstmt.setDouble(1, min);
			pstmt.setDouble(2, max);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				merchVo = new MerchVO();
				merchVo.setMerchID(rs.getInt("MERCH_ID"));
				merchVo.setMerchName(rs.getString("MERCH_NAME"));
				merchVo.setMerchDT(rs.getString("MERCH_DT"));
				merchVo.setMerchPic1(rs.getBlob("MERCH_PIC1"));
				merchVo.setMerchPic2(rs.getBlob("MERCH_PIC2"));
				merchVo.setMerchPic3(rs.getBlob("MERCH_PIC3"));
				merchVo.setMerchPic4(rs.getBlob("MERCH_PIC4"));
				merchVo.setMerchPic5(rs.getBlob("MERCH_PIC5"));
				merchVo.setMerchDate(rs.getTimestamp("MERCH_DATE"));
				merchVo.setMerchPrice(rs.getDouble("MERCH_PRICE"));
				merchVo.setMerchClass(rs.getString("MERCH_CLASS"));
				merchVo.setSoldTotal(rs.getInt("SOLD_TOTAL"));
				merchVo.setMerchStatus(rs.getByte("MERCH_STATUS"));
				merchVo.setMerchStock(rs.getInt("MERCH_STOCK"));
				list.add(merchVo);
			}
			
			
		}catch (SQLException se) {
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

}

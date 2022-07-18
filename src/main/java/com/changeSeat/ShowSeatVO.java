package com.changeSeat;
import java.sql.Timestamp;

public class ShowSeatVO implements java.io.Serializable{
	private Integer SH_ID;
	private Integer mvId;
	private Integer HL_ID;
	private Integer SH_STATE;
	private String SH_SEAT_STATE;
	private Timestamp SH_TIME;
	private Integer SH_TYPE;
	
	
	public Integer getSH_ID() {
		return SH_ID;
	}
	public void setSH_ID(Integer SH_ID) {
		this.SH_ID = SH_ID;
	}
	
	
	public Integer getmvId() {
		return mvId;
	}
	public void setmvId(Integer mvId) {
		this.mvId = mvId;
	}
	
	
	public Integer getHL_ID() {
		return HL_ID;
	}
	public void setHL_ID(Integer HL_ID) {
		this.HL_ID = HL_ID;
	}
	
	
	public Integer getSH_STATE() {
		return SH_STATE;
	}
	public void setSH_STATE(Integer SH_STATE) {
		this.SH_STATE = SH_STATE;
	}
	
	
	public String getSH_SEAT_STATE() {
		return SH_SEAT_STATE;
	}
	public void setSH_SEAT_STATE(String SH_SEAT_STATE) {
		this.SH_SEAT_STATE = SH_SEAT_STATE;
	}
	
	
	public Timestamp getSH_TIME() {
		return SH_TIME;
	}
	public void setSH_TIME(Timestamp SH_TIME) {
		this.SH_TIME = SH_TIME;
	}
	
	
	public Integer getSH_TYPE() {
		return SH_TYPE;
	}
	public void setSH_TYPE(Integer SH_TYPE) {
		this.SH_TYPE = SH_TYPE;
	}
	
	
    // for join MV_NAME from mvId
    public com.movie.model.MovieVO getMovieVO() {
	    com.movie.model.MovieService movieSvc = new com.movie.model.MovieService();
	    com.movie.model.MovieVO movieVO = movieSvc.getOneMovie(mvId);
	    return movieVO;
    }

}

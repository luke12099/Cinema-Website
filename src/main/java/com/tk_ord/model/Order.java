package com.tk_ord.model;



public class Order {
		private TKorder[] TKorder;
		private FDorder[] FDorder;
		private String[] seatindex;
		private Integer MemberID;
		private Integer SH_ID;
		public TKorder[] getTKorders() {
			return TKorder;
		}
		public void setTKorder(TKorder[] tKorder) {
			TKorder = tKorder;
		}
		public FDorder[] getFDorders() {
			return FDorder;
		}
		public void setFDorder(FDorder[] fDorder) {
			FDorder = fDorder;
		}
		public String[] getSeatindex() {
			return seatindex;
		}
		public void setSeatindex(String[] seatindex) {
			this.seatindex = seatindex;
		}
		public Integer getMemberID() {
			return MemberID;
		}
		public void setMemberID(Integer memberID) {
			MemberID = memberID;
		}
		public Integer getSH_ID() {
			return SH_ID;
		}
		public void setSH_ID(Integer sH_ID) {
			SH_ID = sH_ID;
		}
		
		
}

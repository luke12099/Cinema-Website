package com.merchandise_inf.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

public class teeet {
	public static void main(String[] args) throws SQLException, IOException {
//		File fi = new File("C:\\CGA102_WebApp\\eclipse_WTP_workspace1\\CGA102_Group1\\src\\main\\java\\com\\merchandise_inf\\model\\image1.jpg");
		for(int x = 1; x < 999; x++) {
			String dir = "C:\\Users\\Jason\\Desktop\\photo\\" + x;
			File dirs = new File(dir);
			if(!dirs.exists()) {
				break;
			}
			for(int i = 1; i < 6; i++) {
			String url = "C:\\Users\\Jason\\Desktop\\photo\\"+ x +"\\"+ i + ".png";
			File photo = new File(url);
			if(!photo.exists()) {
				break;
			}
			FileInputStream fis = new FileInputStream(photo);
			byte[] byteArray = new byte[fis.available()];
			fis.read(byteArray);
			Blob blob = new SerialBlob(byteArray);
			MerchService merchSvc = new MerchService();
			MerchVO merchVo = merchSvc.getOneMerch(x);
			switch (i) {
			case 1:
				merchVo.setMerchPic1(blob);
				break;
			case 2:
				merchVo.setMerchPic2(blob);
				break;
			case 3:
				merchVo.setMerchPic3(blob);
				break;
			case 4:
				merchVo.setMerchPic4(blob);
				break;
			case 5:
				merchVo.setMerchPic5(blob);
				break;
			default:
				break;
			}
			merchSvc.updateMerch(merchVo);
			fis.close();
			}
			
		}	
	}
}

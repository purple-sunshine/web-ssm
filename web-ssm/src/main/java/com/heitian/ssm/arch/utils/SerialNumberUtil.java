package com.heitian.ssm.arch.utils;

import java.util.Calendar;
import java.util.Date;

public class SerialNumberUtil {

	/*
	 * 订单编号自增长（两位数字）
	 */
	public static String codeStringNextValue(String codeString) {
		if(codeString == null || codeString.equals("")) {
			return "01";
		}else {
			Integer value = Integer.parseInt(codeString);
			value = value +1;
			if(value < 100 && value >= 10) {
				return value+"";
			}else{
				return "0"+value;
			}
		}
	}

	public static String getStrokeNo(){
		int random = (int) (Math.random() * 900) + 100;
        Calendar c = Calendar.getInstance();
        String date = DateUtils.format(new Date(), "yyyy-MM-dd").replace("-", "");
        String time = date + c.get(Calendar.HOUR_OF_DAY) + c.get(Calendar.MINUTE) + c.get(Calendar.SECOND);
        String num = time + random;
        return num;
	}

}

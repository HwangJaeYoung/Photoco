package com.continueing.photoco.reuse.etc;

/* 남은 기간에 따라 색을 바꿀때 사용한다. */

public class ReturnDurationColor {
	public static String returnColor(String aDuration) {
		int value = Integer.valueOf(aDuration);

		if (value < 1440)
			return "#ff6868";
		else if (value < 2880)
			return "#ffc868";
		else
			return "#abd856";
	}
}
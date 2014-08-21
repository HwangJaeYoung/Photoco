package com.continueing.photoco.reuse.etc;

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
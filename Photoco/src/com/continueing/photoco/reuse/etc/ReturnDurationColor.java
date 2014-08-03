package com.continueing.photoco.reuse.etc;

public class ReturnDurationColor {
	public static String returnColor(String aDuration) {
		int value = Integer.valueOf(aDuration);

		if (value < 24)
			return "#ff6868";
		else if (value < 48)
			return "#ffc868";
		else
			return "#abd856";
	}
}
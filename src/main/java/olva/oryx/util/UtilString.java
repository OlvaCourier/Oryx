package olva.oryx.util;

import org.apache.commons.lang.StringUtils;

public class UtilString {
	
	public static String rightPad(String str, int n){
		return StringUtils.rightPad(str, n).substring(0, n);
	}

	public static void main(String[] args) {
		System.out.println(rightPad("carlos",30)+";");
	}

	public static String leftPad(String str, int n){
		return StringUtils.leftPad(str, n).substring(0, n);
	}
	
	public static String addEmptyLines(int n){
		String str= "";
		for (int i = 1; i < n; i++) {
			str+= Constants.SALTO;
		}
		return str;
	}
	


}

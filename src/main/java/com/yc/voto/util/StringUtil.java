package com.yc.voto.util;

public class StringUtil {
	/*
	 * 判空
	 */
	public static boolean checkNull(String ... strs) {
		if(strs == null || strs.length <= 0) {
			return true;
		}
		for(String str : strs) {
			if(str == null || "".equals(str)) {
				return true;
			}
		}
		return false;
	}
}

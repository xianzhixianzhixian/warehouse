package util;

import javax.swing.JOptionPane;

public class StringUtil {
	public static Boolean isEmpty(String str){
	if("".equals(str)||str==null){

		return true;
		}
		else return false;
	}
	public static Boolean isNotEmpty(String str){
		if("".equals(str)||str==null){

			return false;
			}
		else return true;
		}
}

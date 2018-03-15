package tickets.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonService {
	
	public static String getRandomString(int strLen, List<String> exceptList){
		String result = "";
		for( int i=0; i<strLen; i++ ){
			int random = (int)(Math.random()*10.5-1);
			if( random<0 ){
				random = 0;
			}
			result += random;
		}
		if( result.length()>7 ){
			result = result.substring(0, 7);
		}
		while( exceptList.contains(result) ){
			int random = (int)(Math.random()*10.99-1);
			if( random<0 ){
				random = 0;
			}
			result.substring(1);
			result += random;
		}
		return result;
	}
	
	public static String unifyStr(String str){
		String result = "";
		
		Pattern patternAvailable = Pattern.compile("[a]");
		Matcher matcher1 = patternAvailable.matcher(str);
		result = matcher1.replaceAll("1");
		
		Pattern patternNone = Pattern.compile("[_]");
		Matcher matcher2 = patternNone.matcher(result);
		result = matcher2.replaceAll("0");
		
		Pattern patternChoose = Pattern.compile("[c]");
		Matcher matcher3 = patternChoose.matcher(result);
		result = matcher3.replaceAll("2");
		
		Pattern patternSell = Pattern.compile("[s]");
		Matcher matcher4 = patternSell.matcher(result);
		result = matcher4.replaceAll("3");
		return result;
	}
	
	public static String unifyStrBack(String str){
		String result = "";
		
		Pattern patternAvailable = Pattern.compile("[1]");
		Matcher matcher1 = patternAvailable.matcher(str);
		result = matcher1.replaceAll("a");
		
		Pattern patternNone = Pattern.compile("[0]");
		Matcher matcher2 = patternNone.matcher(result);
		result = matcher2.replaceAll("_");
		
		Pattern patternChoose = Pattern.compile("[2]");
		Matcher matcher3 = patternChoose.matcher(result);
		result = matcher3.replaceAll("c");
		
		Pattern patternSell = Pattern.compile("[3]");
		Matcher matcher4 = patternSell.matcher(result);
		result = matcher4.replaceAll("s");
		return result;
	}
	
	public static String oneZeroToHexadecimal(String str){
		Map<String, String> exchange = new HashMap<>();
		exchange.put("0000","0");
		exchange.put("0001","1");
		exchange.put("0010","2");
		exchange.put("0011","3");
		exchange.put("0100","4");
		exchange.put("0101","5");
		exchange.put("0110","6");
		exchange.put("0111","7");
		exchange.put("1000","8");
		exchange.put("1001","9");
		exchange.put("1010","A");
		exchange.put("1011","B");
		exchange.put("1100","C");
		exchange.put("1101","D");
		exchange.put("1110","E");
		exchange.put("1111","F");
		while( str.length()%4!=0 ){
			str += "0";
		}
		String result = "";
		for( int i=0; i<str.length(); i=i+4 ){
			result += exchange.get(str.substring(i, i+4));
		}
		return result;
	}
	
	public static String hexadecimalToOneZero(String str){
		Map<String, String> exchange = new HashMap<>();
		exchange.put("0", "0000");
		exchange.put("1", "0001");
		exchange.put("2", "0010");
		exchange.put("3", "0011");
		exchange.put("4", "0100");
		exchange.put("5", "0101");
		exchange.put("6", "0110");
		exchange.put("7", "0111");
		exchange.put("8", "1000");
		exchange.put("9", "1001");
		exchange.put("A", "1010");
		exchange.put("B", "1011");
		exchange.put("C", "1100");
		exchange.put("D", "1101");
		exchange.put("E", "1110");
		exchange.put("F", "1111");
		
		String result = "";
		for( int i=0; i<str.length(); i++ ){
			result += exchange.get(str.substring(i, i+1));
		}
		return result;
	}
	
	public static String fourToHexadecimal(String str){
		Map<String, String> exchange = new HashMap<>();
		exchange.put("00","0");
		exchange.put("01","1");
		exchange.put("02","2");
		exchange.put("03","3");
		exchange.put("10","4");
		exchange.put("11","5");
		exchange.put("12","6");
		exchange.put("13","7");
		exchange.put("20","8");
		exchange.put("21","9");
		exchange.put("22","A");
		exchange.put("23","B");
		exchange.put("30","C");
		exchange.put("31","D");
		exchange.put("32","E");
		exchange.put("33","F");
		while( str.length()%2!=0 ){
			str += "0";
		}
		String result = "";
		for( int i=0; i<str.length(); i=i+2 ){
			result += exchange.get(str.substring(i, i+2));
		}
		return result;
	}
	
	public static String hexadecimalToFour(String str){
		Map<String, String> exchange = new HashMap<>();
		exchange.put("0", "00");
		exchange.put("1", "01");
		exchange.put("2", "02");
		exchange.put("3", "03");
		exchange.put("4", "10");
		exchange.put("5", "11");
		exchange.put("6", "12");
		exchange.put("7", "13");
		exchange.put("8", "20");
		exchange.put("9", "21");
		exchange.put("A", "22");
		exchange.put("B", "23");
		exchange.put("C", "30");
		exchange.put("D", "31");
		exchange.put("E", "32");
		exchange.put("F", "33");
		
		String result = "";
		for( int i=0; i<str.length(); i++ ){
			result += exchange.get(str.substring(i, i+1));
		}
		return result;
	}
	
}

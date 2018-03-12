package tickets.serviceImpl;

import java.util.List;

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
	
}

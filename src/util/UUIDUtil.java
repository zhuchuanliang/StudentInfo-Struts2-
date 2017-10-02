package util;

import java.util.UUID;

public class UUIDUtil {
	
	public static String getUUID(){
		UUID uuid=UUID.randomUUID();
		return uuid.toString();
	}
	public static void main(String agrs[]){
		System.out.println(UUIDUtil.getUUID());
	}

}

package ir.wikilinux.present.api.jwt;

import java.util.Base64;
import java.util.Base64.Decoder;

public class JWTUtil {
	
	private static Decoder decoder = Base64.getDecoder();
	
	public static String getPayload(String jwtString) {
		
		
		
		String chunk[] = jwtString.split("\\.");
		String payload = new String(decoder.decode(chunk[1]));
		
		return payload;
		
	}

}

package ir.wikilinux.present.api.jwt;

import java.util.Base64;

public class JWTValidator {

	private static final JWTGenerator jwtGenerator = new JWTGenerator();

	
	public static boolean validator(String jwt) {
		
		Base64.Decoder decoder = Base64.getUrlDecoder();
		
		jwt = jwt.trim();
		String[] jwtStrings = jwt.split("\\.");

		String jwtHeaderString = "";
		String jwtPayloadString = "";
		String jwtGenerated = "";
		if (jwtStrings.length >= 3) {
			 jwtHeaderString = new String(decoder.decode(jwtStrings[0]));
			 jwtPayloadString = new String(decoder.decode(jwtStrings[1]));
			 jwtGenerated = jwtGenerator.getJwt(jwtHeaderString, jwtPayloadString);				
 
			
		}else {
			return false;

		}
 
		
		
		if (jwtGenerated.equals(jwt)) {
			
			return true;
		}
		
		
		return false;
	}
}

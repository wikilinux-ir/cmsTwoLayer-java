package ir.wikilinux.jwt;

import java.util.Base64;

/*
 * 
 * this class validate jwt
 * you should pass a JWTGenerator to class constructor 
 * 
 * @since 0.1
 * 
 * author M.R.H
 * 
 * */



public class JWTValidator {

	private final JWTGenerator jwtGenerator ;

	
	public JWTValidator(JWTGenerator generator) {
		
		this.jwtGenerator =  generator;
	}
	
	/*
	 * 
	 * this method give a jwt String and validate your jwt string if 
	 * is valid @return true else return false
	 * 
	 * @param String jwt
	 * */
	
	public boolean validator(String jwt) {
		
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

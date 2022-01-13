package ir.wikilinux.present.api.jwt;

public class Test {

	public static void main (String[] args) {
		
//		boolean is = JwtValidator.validator(null);
		JWTGenerator jwtGenerator = new JWTGenerator();
		
		String jwtString= jwtGenerator.getJwt("naaaame","ali");
		 
		
		System.out.println(jwtString);

//String jwString = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";		
System.out.println(JWTValidator.validator("bmFtZQ==.YWxp.SuYKXSPX56krLcmNFzQCs7EpKUuZpKwqAYlrAJ8Z+hb4RYw7SXvSqpl9A/WDOxU6uCDwPAIhGGLSB5c2LMn0hw=="));
//		System.out.println(JwtValidator.validator(jwtString));
	}
	
}

package ir.wikilinux.present.api.jwt;

public class Test {

	public static void main (String[] args) {
		
//		boolean is = JwtValidator.validator(null);
		JWTGenerator jwtGenerator = new JWTGenerator();
		
		String jwtString= jwtGenerator.getJwt("{\"alg\":\"HS512\"}"
				,"{\"role\":\"user\"}");
		 
		
		System.out.println(jwtString);

//String jwString = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";		
System.out.println(JWTValidator.validator("eyJhbGciOiJIUzUxMiJ9.eyJhbGciOiJIUzUxMiJ9.1JxgDC1BjjkTNTFy20qUeoc5GCE1NIjIAHud5oP/bd8"));
//		System.out.println(JwtValidator.validator(jwtString));
	}
	
}

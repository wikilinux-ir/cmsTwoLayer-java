package ir.wikilinux.jwt;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;



/*
 * 
 * this class Generate jwt
 * you should pass ALGRITHM  and SALT string to class constructor 
 * 
 * @since 0.1
 * 
 * @param String ALG,SALT
 * 
 * author M.R.H
 * 
 * */

public class JWTGenerator {
	
	private  final String SIGNATURE_ALG ;
	private  final MessageDigest MD ;
	private  final byte[] SALT ;

	public JWTGenerator(String Alg, String saltString) {
		
		this.SIGNATURE_ALG = Alg;
		this.SALT = saltString.getBytes(StandardCharsets.UTF_8);
		this.MD = getMessageDigestInstance();

	}
	
	
	/*
	 * 
	 * get MessageDgest instance based on ALG
	 * 
	 * */
	
	private MessageDigest getMessageDigestInstance() {

		try {
			
			return MessageDigest.getInstance(SIGNATURE_ALG);
			
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	
	/*
	 * 
	 * 
	 *@param String jsonHeader 
	 *@param String jsonPayload 
	 *
	 *@return String JWT 
	 * 
	 */
	
	
	public String getJwt(String jsonHeader,String jsonPayload ) {
		
		Base64.Encoder encoder = Base64.getEncoder();
		
		byte[] jsonHeaderBytes = jsonHeader.getBytes(StandardCharsets.UTF_8);
		byte[] jsonPayloadBytes = jsonPayload.getBytes(StandardCharsets.UTF_8);

		jsonHeader = encoder.encodeToString(jsonHeaderBytes);
		jsonPayload = encoder.encodeToString(jsonPayloadBytes);
		String concatString = jsonHeader+jsonPayload;
		MD.update(SALT);
		
		byte[] signatureBytes = MD.digest((concatString).getBytes(StandardCharsets.UTF_8));
		byte[] signatureEncode = encoder.encode(signatureBytes);
		
		String signatureString = new String(signatureEncode);
		String token = jsonHeader+"."+jsonPayload+"."+signatureString;
		
		return token;
		
	
	}
	
	
}

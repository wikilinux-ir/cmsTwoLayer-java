package ir.wikilinux.present.api.jwt;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class JWTGenerator {
	
	private  final String SIGNATURE_ALG = "SHA-512";
	private  final MessageDigest MD ;
	private final byte[] SALT = "it's a beta version".getBytes(StandardCharsets.UTF_8);

	public JWTGenerator() {
		
		this.MD = getMessageDigestInstance();
		
	}
	
	
	private MessageDigest getMessageDigestInstance() {

		try {
			
			return MessageDigest.getInstance(SIGNATURE_ALG);
			
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
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

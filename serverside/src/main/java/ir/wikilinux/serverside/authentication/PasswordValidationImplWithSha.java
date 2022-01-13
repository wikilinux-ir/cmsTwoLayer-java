package ir.wikilinux.serverside.authentication;

import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordValidationImplWithSha extends UnicastRemoteObject implements PasswordValidation{
	
	
	private final String ALG = "SHA-512";
	private final byte[] SALT = "!@#$$%%^as1545as4sa".getBytes(StandardCharsets.UTF_8);
	private static PasswordValidationImplWithSha instance = null;
	
	public  PasswordValidationImplWithSha() throws RemoteException {
	
	}

	
	
	public static PasswordValidationImplWithSha getInstance() {
		
		if (instance == null) 
		{
			
			try {
				instance = new PasswordValidationImplWithSha();
			} catch (RemoteException e) {

				System.out.println(e.getMessage());
				
			}
		}
		
		return instance;
	}
	
	@Override
	public boolean isValidPassword(String password, String dbHash) {
		
		
		boolean isValid = false;
		
		Base64.Encoder encoderToStringByte = Base64.getEncoder();
		try {
			MessageDigest md = MessageDigest.getInstance(ALG);
			
			//update my salt
			
			md.update(SALT);
			
			// get hashed password byte and convert to string understand byte to convert byte to string
			
			byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
			byte[] encoded = encoderToStringByte.encode(hashedPassword);

			if (dbHash.equals(new String(encoded))) {
				
				return true;
			}
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		
		return isValid;
		
	}
}

import java.rmi.RemoteException;

import ir.wikilinux.serverside.authentication.PasswordValidation;
import ir.wikilinux.serverside.authentication.PasswordValidationImplWithSha;

public class Test {

	
	
	public static void main(String[] args) throws RemoteException {
		
		PasswordValidation validation = PasswordValidationImplWithSha.getInstance();
		
		boolean result =  validation.isValidPassword("nu11device", "57CO/sa4zR/fTttCCYdXGl6boDswLbmFRv0b4i7sCrrt268QWjt44Kgz2FLfRJurdLw6ny3AB1y5dYNvnos26A==");
		
		System.out.println(result);
		
	}
	
}

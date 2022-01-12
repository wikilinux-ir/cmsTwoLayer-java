package ir.wikilinux.serverside.authentication;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PasswordValidation extends Remote {
	
	
	 boolean isValidPassword(String password,String dbHash) throws RemoteException;
	
}

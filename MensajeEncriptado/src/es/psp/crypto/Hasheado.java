package es.psp.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Hasheado {
	
	// GENERAMOS HASH A PARTIR DE UNA STRING CONTRASEÑA
	public String generarHash(String password) throws NoSuchAlgorithmException {
		byte[] passH = password.getBytes();
		System.out.println("Creando Hash de -> " + password);
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update(passH);
		String passHashBase64 = Base64.getEncoder().encodeToString(md.digest());
		System.out.println("Resumen hash: " + passHashBase64);
		return passHashBase64;
	}
}

package cifrado;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import bbdd.Usuario;
//import javax.crypto.KeyGenerator;
import bbdd.UsuarioImp;

public class Cifrado {

	// GENERAMOS HASH A PARTIR DE UNA STRING CONTRASEÑA
	public String generarHash(String password) throws NoSuchAlgorithmException {
		byte[] passH = password.getBytes();
		System.out.println("Creando Hash de -> " + password);
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update(passH);
		byte[] resumen = md.digest();
		String mensaje = new String(resumen);
		System.out.println("Resumen hash: " + mensaje);
		return mensaje;
	}

	// METODO PARA CIFRAR STRING AES
	public void cifrar(String frase) {
		try {
			// Generador de claves simetricas AES
			KeyGenerator generador = KeyGenerator.getInstance("AES");
			// generamos la claveSecreta
			SecretKey claveSecreta = generador.generateKey();
			// Objeto que nos permitira encriptar o desencriptar a partir de una
			Cipher cifrado = Cipher.getInstance("AES");
			// Ahora el cifrador lo configuramos para que use la clave simetrica
			// para encriptar
			cifrado.init(Cipher.ENCRYPT_MODE, claveSecreta);
			byte[] bytesmensajeOriginal = frase.getBytes();
			byte[] bytemensajeCifrado = cifrado.doFinal(bytesmensajeOriginal);
			System.out.println(new String(bytemensajeCifrado));
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
package cifrado;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class Descifrado {
	
	//Objeto que nos permitira encriptar
	static Cipher descifrador;
	
	public static Cipher getDescifrador() {
		return descifrador;
	}

	public Descifrado(SecretKey claveSecreta) {
		try {
			//Ahora hacemos lo mismo pero esta vez se encargara de descifrar los mensajes
			descifrador = Cipher.getInstance("DES");
			//Le pasamos el modo desencriptar con la clavesecreta
			descifrador.init(Cipher.DECRYPT_MODE, claveSecreta);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

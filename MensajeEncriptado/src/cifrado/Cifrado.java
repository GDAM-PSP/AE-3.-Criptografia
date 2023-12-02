package cifrado;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class Cifrado {
	
	//Objeto que nos permitira encriptar
	static Cipher cifrador;
	
	public static Cipher getCifrador() {
		return cifrador;
	}

	public Cifrado(SecretKey claveSecreta) {
		try {
			cifrador = Cipher.getInstance("DES");
			//Ahora el cifrador lo configuramos para que use la clave simetrica para encriptar
			cifrador.init(Cipher.ENCRYPT_MODE, claveSecreta);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}

package main;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class Main {
	public static void main(String args[]) {
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
			// Ahora hacemos lo mismo pero esta vez se encargara de descifrar los mensajes
			Cipher descifrador = Cipher.getInstance("AES");
			// Le pasamos el modo desencriptar con la clavesecreta
			descifrador.init(Cipher.DECRYPT_MODE, claveSecreta);

			Scanner sc = new Scanner(System.in);

			int opcion;
			String mensaje = "";
			System.out.println("Comienza el programa de encriptación");
			byte[] bytemensajeCifrado = null;
			do {
				menu();
				opcion = sc.nextInt();
				switch (opcion) {
				case 1:
					sc.nextLine();
					System.out.println("Escribe la frase que quieres encriptar");
					mensaje = sc.nextLine();
					 byte[] bytesmensajeOriginal = mensaje.getBytes();
					 bytemensajeCifrado = cifrado.doFinal(bytesmensajeOriginal);
					System.out.println(new String(bytemensajeCifrado));
					break;
				case 2:
					sc.nextLine();
					 byte[] mensajeDescifradoBytes = descifrador.doFinal(bytemensajeCifrado);
					 System.out.println(new String(mensajeDescifradoBytes));
					break;
				}
			} while (opcion != 0);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Finaliza el programa de encriptación");

	}

	public static void menu() {
		System.out.println("0->Salir del programa");
		System.out.println("1->Encriptar frase");
		System.out.println("2->Desencriptar frase");

	}
}

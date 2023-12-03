package main;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import cifrado.Cifrado;
import cifrado.Descifrado;

public class Main {
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		try {
			//Generador de claves simetricas DES
			KeyGenerator generador = KeyGenerator.getInstance("DES");
			//Generamos la claveSecreta
			SecretKey claveSecreta = generador.generateKey();
			//Generamos la clase cifradora
			new Cifrado(claveSecreta);
			//Generamos la clase descifradora
			new Descifrado(claveSecreta);
			
			int opcion;
			String mensaje;
			System.out.println("Comienza el programa de encriptación");
			byte[] byteMensajeCifrado = null;
			
			do {
				menu();
				opcion = sc.nextInt();
				switch(opcion) {
					case 1:
						sc.nextLine();
						System.out.println("Escribe la frase que quieres encriptar");
						mensaje = sc.nextLine();
						byte[] bytesmensajeOriginal = mensaje.getBytes();
						byteMensajeCifrado = Cifrado.getCifrador().doFinal(bytesmensajeOriginal);
						String mensajeCifradoBase64 = Base64.getEncoder().encodeToString(byteMensajeCifrado);
						System.out.println(mensajeCifradoBase64);
						break;
					case 2:
						sc.nextLine();
						byte[] mensajeDescifradoBytes = Descifrado.getDescifrador().doFinal(byteMensajeCifrado);
						System.out.println(new String(mensajeDescifradoBytes));
						break;
				}
			}while(opcion!=0);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sc.close();
		System.out.println("Finaliza el programa de encriptación");
	}

	public static void menu() {
		System.out.println("0->Salir del programa");
		System.out.println("1->Encriptar frase");
		System.out.println("2->Desencriptar frase");
	}
}

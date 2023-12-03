package es.psp.main;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import es.psp.crypto.Cifrado;
import es.psp.crypto.Descifrado;
import es.psp.modelo.UsuarioImp;

public class Main {
	
	public static void main(String args[]) {
		// DECLARACION DE VARIABLES INICIALES
		boolean sesion = false;
		int intentos = 3;
		String user;
		String password;
		UsuarioImp userImp = new UsuarioImp();
		Scanner sc = new Scanner(System.in);
		
		try {
			menuLogin();
			// CARGAMOS USUARIOS EN MEMORIA
			userImp.cargarUsuarios();
			// HASHEAMOS CONTRASEÑA
			userImp.cifrarPassword();
			
			do {
				// SOLICITAMOS LOS DATOS DE ACCESO
				System.out.println("**                            **");
				System.out.println("**-INTRODUZCA USUARIO:        **");
				user = sc.next();
				System.out.println("**-Introduzca Contraseña:     **");
				password = sc.next();
				
				// VALIDACION DE LOS DATOS RECIBIDA CON LOS CARGADOS EN MEMORIA
				if (!userImp.validarDatos(user, password)) {
					System.out.println("Datos incorrectos.");
					try {
						TimeUnit.SECONDS.sleep(2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					intentos--;
					System.out.println("TE QUEDAN: " + intentos + " INTENTOS");
				} else {
					//Generador de claves simetricas
					KeyGenerator generador = KeyGenerator.getInstance("AES");
					//Generamos la claveSecreta
					SecretKey claveSecreta = generador.generateKey();
					//Generamos la clase cifradora
					Cifrado cifrador = new Cifrado(claveSecreta);
					//Generamos la clase descifradora
					Descifrado descifrador = new Descifrado(claveSecreta);
					
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
								byteMensajeCifrado = cifrador.getCifrador().doFinal(bytesmensajeOriginal);
								String mensajeCifradoBase64 = Base64.getEncoder().encodeToString(byteMensajeCifrado);
								System.out.println(mensajeCifradoBase64);
								break;
							case 2:
								sc.nextLine();
								byte[] mensajeDescifradoBytes = descifrador.getDescifrador().doFinal(byteMensajeCifrado);
								System.out.println(new String(mensajeDescifradoBytes));
								break;
						}
					}while(opcion!=0);
				}
			} while (intentos > 0 && !sesion);
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
	
	public static void menuLogin() {
		System.out.println("*------------------------------------*");
		System.out.println("*------------------------------------*");
		System.out.println("*------------------------------------*");
		System.out.println("*              BIENVENIDO            *");
		System.out.println("*(Los cifrados se muestran en Base64)*");
		System.out.println("");
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void menu() {
		System.out.println("0->Salir del programa");
		System.out.println("1->Encriptar frase");
		System.out.println("2->Desencriptar frase");
	}
}

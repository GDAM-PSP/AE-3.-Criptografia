package main;

import java.util.Scanner;

import javax.crypto.Cipher;

import bbdd.UsuarioImp;
import cifrado.Cifrado;

public class Main2 {
	public static void main(String args[]) {
		boolean sesion = false;
		int intentos = 3;
		String user;
		String password;
		UsuarioImp userz = new UsuarioImp();
		Cifrado cfr = new Cifrado();
		Scanner sc = new Scanner(System.in);

		// CARGAMOS USUARIOS EN MEMORIA
		userz.cargarUsuarios();

		// HASHEAMOS CONTRASEÑA
		userz.cifrarPassword();

		// TRAZABILIDAD DE LOS USUARIOS CREADOS
		// userz.getAllUsers();

		do {
			// INICIALIZAMOS PROGRAMA
			menuLogin();
			// SOLICITAMOS LOS DATOS DE ACCESO
			System.out.println("**                            **");
			System.out.println("*-INTRODUZCA USUARIO:         *");
			user = sc.next();
			System.out.println("**                            **");
			System.out.println("*-Introduzca Contraseña:       *");
			password = sc.next();
			// VALIDACION DE LOS DATOS RECIBIDA CON LOS CARGADOS EN MEMORIA
			if (!userz.validarDatos(user, password)) {
				System.out.println("datos invalidos");
				intentos--;
				System.out.println("TE QUEDAN: " + intentos + " INTENTOS");
			} else {
				System.out.println("USUARIO VALIDO");
				System.out.println("");
				// CONTINUA
				int opcion;
				do {
					menu();
					opcion = sc.nextInt();
					switch (opcion) {
					case 1:
						sc.nextLine();
						System.out.println("Escribe la frase que quieres encriptar");
						String mensaje = sc.nextLine();
						cfr.cifrar(mensaje);
						break;
					case 2:
						sc.nextLine();
						break;
					}
				} while (opcion != 0);
			}
		} while (intentos > 0 && !sesion);
		System.err.print("*SIN INTENTOS RESTANTES: ****");
		System.err.print("*CERRANDO PROGRAMA....   ****");
	}

	public static void menuLogin() {
		System.out.println("*----------------------------*");
		System.out.println("*----------------------------*");
		System.out.println("*----------------------------*");
		System.out.println("*          BIENVENIDO        *");

	}

	public static void menu() {
		System.out.println("**                    **");
		System.out.println("Comienza el programa de encriptación ----------");
		System.out.println("*ELIJA UNA OPCION      *");
		System.out.println("1->Encriptar frase");
		System.out.println("2->Desencriptar frase");
		System.out.println("0->Salir del programa");
	}
}

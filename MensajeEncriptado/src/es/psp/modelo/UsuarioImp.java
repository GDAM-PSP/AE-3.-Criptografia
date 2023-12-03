package es.psp.modelo;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import es.psp.crypto.Hasheado;

public class UsuarioImp {
	
	//Inicializamos la clase hasheadora
	Hasheado hasheador = new Hasheado();

	// SE GENERA UN ARRAYLIST DE USUARIOS PARA CARGARLOS EN MEMORIA
	ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	Usuario user1 = new Usuario("admin", "admin");
	Usuario user2 = new Usuario("usuario", "usuario");
	Usuario user3 = new Usuario("felix", "felix");

	public UsuarioImp() {
	}

	// SE AGREGAN LOS USUARIOS AL ARRAY
	public void cargarUsuarios() {
		usuarios.add(user1);
		usuarios.add(user2);
		usuarios.add(user3);
	}

	// SE LISTAN LOS USUARIOS
	public void getAllUsers() {
		for (Usuario user : usuarios) {
			user.getUsuario();
			System.out.println(user.getUsuario());
		}
	}

	// CREAMOS HASH DE LAS CONTRASEÑAS DE USUARIOS EN MEMORIA
	public void cifrarPassword() {
		String hashPass;
		System.out.println("**  CIFRANDO CONTRASEÑAS DE LOS USUARIOS....  **");
		System.out.println("");
		for (Usuario usuario : usuarios) {
			try {
				hashPass = hasheador.generarHash(usuario.getPassword());
				usuario.setPassword(hashPass);
				updateUsuario(usuario);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// SE VALIDA CON LOS DATOS RECIBIDOS SI LA CONTRASEÑA GENERA EL MISMO HASH
	public boolean validarDatos(String user, String password) {
		String hashPassword;
		try {
			hashPassword = hasheador.generarHash(password);
			for (Usuario us : usuarios) {
				if (us.getUsuario().equals(user)) {
					if (us.getPassword().equals(hashPassword)) {
						System.out.println("ENHORABUENA EL HASH ES IGUAL");
						return true;
					} else {
						System.out.println("HASH INVALIDO");
					}
				}
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// SE ACTUALIZA LA CONTRASEÑA DEL USUARIO GUARDADO EN MEMORIA POR EL HASH
	public boolean updateUsuario(Usuario user) {
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getUsuario().equals(user.getUsuario())) {
				usuarios.get(i).setPassword(user.getPassword());
				System.out.println("La contraseña del usuario " + user.getUsuario() + " ha sido cambiada.");
				System.out.println("");
				return true;
			}
		}
		return false;
	}
}
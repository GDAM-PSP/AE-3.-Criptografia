package bbdd;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import cifrado.Cifrado;

public class UsuarioImp {

	// SE GENERA UN ARRAYLIST DE USUARIOS PARA CARGARLOS EN MEMORIA

	ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	Usuario user1 = new Usuario("admin", "hola");
	Usuario user2 = new Usuario("demo", "5678");
	Usuario user3 = new Usuario("fake", "asdf");
	Cifrado cifr;

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
		cifr = new Cifrado();
		String mensaje1;
		System.out.println("**  CIFRANDO CONTRASEÑAS DE LOS USUARIOS....  **");
		System.out.println("");
		for (Usuario us : usuarios) {
			try {
				mensaje1 = cifr.generarHash(us.getPassword());
				us.setPassword(mensaje1);
				updateUsuario(us);
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
			hashPassword = cifr.generarHash(password);
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

	// SE ACTUALIZA USUARIO
	public boolean updateUsuario(Usuario user) {
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getUsuario().equals(user.getUsuario())) {
				usuarios.get(i).setPassword(user.getPassword());
				System.out.println("La contraseña del usuario " + user.getUsuario() + " ha sido cambiada/Hash:");
				System.out.println("");
				return true;
			}
		}
		return false;
	}
}
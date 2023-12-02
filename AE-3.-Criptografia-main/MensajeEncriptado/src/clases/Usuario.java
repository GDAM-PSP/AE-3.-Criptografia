package clases;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Usuario {
	private String nombre;
	private String contrasena;
	
	
	public Usuario() {
		super();
	}
	
	public Usuario(String nombre, String contrasena) throws NoSuchAlgorithmException {
		this.nombre = nombre;
		this.contrasena = cifrarContrasena(contrasena);
	}
	
	public String cifrarContrasena(String contrasena) {
		MessageDigest cifrar = MessageDigest.getInstance("SHA-512");
		cifrar.update(contrasena.getBytes());
		System.out.println("hasheando la contraseña");
		byte[] resumen = cifrar.digest();
		String mensaje = new String(resumen);
		System.out.println("Resumen hash: " + mensaje);
		
		System.out.println("Creando el resumen hash en base 64");
		String mensajeHashBase64 = Base64.getEncoder().encodeToString(resumen);
		System.out.println("Resumen hash BASE 64: " + mensajeHashBase64);
	}
	
	
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getContrasena() {
		return contrasena;
	}


	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}


	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", contrasena=" + contrasena + "]";
	}


	


}

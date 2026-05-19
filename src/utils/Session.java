package utils;

import models.Usuario;
public class Session {

	private static Usuario usuarioTemporal;
	
	public static void login(Usuario usuarioLogeado) {
		usuarioTemporal = usuarioLogeado;
	}
	
	public static Usuario getUsuarioInicializado() {
		return usuarioTemporal;
	}
	
	public static String getRolUsuario() {
		return usuarioTemporal.getRol();
	}
	
	public static void logout() {
		usuarioTemporal = null;
	}
}

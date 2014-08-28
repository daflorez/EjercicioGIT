package controlador;

import java.io.Serializable;
import java.util.ArrayList;

import modelo.Usuario;

import javax.faces.bean.ManagedBean;

import accesodatos.conexion;

@ManagedBean
public class SaludaUsuarioMB implements Serializable {

	private Usuario usuario;
	private conexion conn;

	public SaludaUsuarioMB() {

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public String saludar() {
		conexion conn = new conexion();
		ArrayList<modelo.Usuario> DatosFTRAM = new ArrayList<modelo.Usuario>();
		DatosFTRAM = conn.mostrarDatos("1035230664");
		usuario = DatosFTRAM.get(0);
		return "saludo";
	}

}
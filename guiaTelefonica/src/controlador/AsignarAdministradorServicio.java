package controlador;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Busqueda;
import modelo.Personal;
import conexion.Conexion;
import conexion.ConexionLocal;

public class AsignarAdministradorServicio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AsignarAdministradorServicio() {
	}

	/*
	 * Metodo para asignar Administradores.
	 */
	public void guardarAdministrador(Personal administrador,
			List<Busqueda> sedes) {

		ConexionLocal cn = new ConexionLocal();
		ResultSet lastRegistro = null;

		lastRegistro = cn.consultaFindPersonal(administrador);
		if (lastRegistro != null) {
			guardarAdminSedes(administrador, sedes, cn);
		} else {
			int confirma = cn.guardarPersonal(administrador);

			if (confirma != 1) {
				System.out.println("Error Dato de Personal no guardado");

			} else {
				guardarAdminSedes(administrador, sedes, cn);

			}
		}
	}

	/* *
	 * Metodo para guardar la informacion para la Guia.
	 */
	private void guardarAdminSedes(Personal administrador,
			List<Busqueda> sedes, ConexionLocal cn) {

		int actividad = cn.guardarAdministrador(administrador);

		if (actividad == 1) {
			guardarRelAdminSedes(administrador, sedes, cn);

		} else {
			guardarRelAdminSedes(administrador, sedes, cn);
		}

	}

	private void guardarRelAdminSedes(Personal administrador,
			List<Busqueda> sedes, ConexionLocal cn) {
		for (Busqueda busqueda : sedes) {
			cn.guardarSedes(busqueda);
		}
		for (Busqueda busqueda : sedes) {
			cn.guardarRelAdminSede(administrador, busqueda);
		}
	}

	/*
	 * Metodo para obtener Sedes registrados en Banner.
	 */
	public List<Busqueda> obtenerSedes() {

		Conexion cn = new Conexion();
		ResultSet res = cn.consultaSedes();
		List<Busqueda> sedes = new ArrayList<Busqueda>();

		if (res == null) {
			System.out.println("Error No Hay Datos");
		} else {

			try {
				while (res.next()) {

					sedes.add(new Busqueda(res.getString("CODESEDE"), res
							.getString("DESCRIP")));
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}

		}

		return sedes;
	}
}

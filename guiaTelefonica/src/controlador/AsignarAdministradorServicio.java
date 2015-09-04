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
	 * 7A
	 */
	private static final long serialVersionUID = 1L;

	public AsignarAdministradorServicio() {
	}

	/*
	 * Metodo para asignar Administradores.
	 */
	public void guardarAdministrador(Personal administrador, String[] sedes) {

		ConexionLocal cn = new ConexionLocal();
		ResultSet lastRegistro = cn.consultaFindPersonal(administrador);

		try {
			if (lastRegistro.next()) {
				guardarAdminSedes(administrador, sedes, cn);
			} else {

				int confirma = cn.guardarPersonal(administrador);

				if (confirma != 1) {
					System.out.println("Error Dato de Personal no guardado");

				} else {
					guardarAdminSedes(administrador, sedes, cn);

				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* *
	 * Metodo para guardar la informacion para la Guia.
	 */
	private void guardarAdminSedes(Personal administrador, String[] sedes,
			ConexionLocal cn) {

		ResultSet lastRegistro = cn.consultaFindAdministrador(administrador
				.getIdDocente());

		try {
			if (lastRegistro.next()) {
				guardarRelAdminSedes(administrador, sedes, cn);
				System.out.println("Administrador ya esta registrado");
			} else {
				int actividad = cn.guardarAdministrador(administrador);

				if (actividad != 1) {

					System.out.println("Error de Guardar Administrador");
				} else {
					guardarRelAdminSedes(administrador, sedes, cn);
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void guardarRelAdminSedes(Personal administrador, String[] sedes,
			ConexionLocal cnLocal) {

		Conexion cnRemoto = new Conexion();
		ResultSet registroSede = null;

		List<Busqueda> listaDeSedes = new ArrayList<Busqueda>();

		for (String string : sedes) {
			registroSede = cnRemoto.consultaSedePorId(string);
			try {
				while (registroSede.next()) {
					listaDeSedes.add(new Busqueda(registroSede
							.getString("CODESEDE"), registroSede
							.getString("DESCRIP")));
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}

		}

		// Guardar las Sedes en la Base Local si esta no exite.
		for (Busqueda busqueda : listaDeSedes) {
			registroSede = cnLocal.consultaFindSede(busqueda.getValor());

			try {
				if (registroSede.next()) {
					System.out.println("Sede Ya existe");

				} else {
					cnLocal.guardarSedes(busqueda);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// Guardar la relacion entre Administradores y Sedes.
		for (Busqueda busqueda : listaDeSedes) {
			registroSede = cnLocal.consultaFindRelaAdminSede(
					administrador.getIdDocente(), busqueda.getValor());

			try {
				if (registroSede.next()) {

					System.out.println("Sede_Admin Ya existe");
				} else {
					cnLocal.guardarRelAdminSede(administrador, busqueda);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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

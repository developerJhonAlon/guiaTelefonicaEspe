package controlador;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Busqueda;
import conexion.Conexion;

public class AsignarAdministradorServicio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AsignarAdministradorServicio() {
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

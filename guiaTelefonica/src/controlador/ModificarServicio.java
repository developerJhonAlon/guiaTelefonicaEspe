package controlador;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.ConexionLocal;
import modelo.VistaBusqueda;

public class ModificarServicio implements Serializable {

	/**
	 * E
	 */
	private static final long serialVersionUID = 1L;

	public List<VistaBusqueda> obtenerExtension(String valorRecibido) {
		ConexionLocal cn = new ConexionLocal();

		ResultSet res = cn.consultaPorNombre(valorRecibido);

		List<VistaBusqueda> vistaBusqueda = new ArrayList<VistaBusqueda>();
		if (res == null) {
			System.out.println("Error No Hay Datos");
		} else {
			try {
				while (res.next()) {
					vistaBusqueda.add(new VistaBusqueda(res
							.getString("UZGTPERSON_ID"), res
							.getString("UZGTPERSON_UNIDAD"), res
							.getString("UZGTPERSON_SEDE"), res
							.getString("UZGTPERSON_PUEST"), res
							.getString("UZGTPERSON_NOMBRE"), res
							.getString("UZGTEXTE_NUM_EXTENSION"), res
							.getString("UZGTTELE_NUM_TELEFONO"), res
							.getString("UZGTPERSON_CORR"), res
							.getLong("UZGTEXTE_ID")));
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}

		}
		return vistaBusqueda;
	}

	/* *
	 * Metodo para guardar la informacion de un Personal con su Extension
	 * asignada
	 */
	public List<VistaBusqueda> modificaExtOrFono(long idExtension,
			String idPersonal, String fono, String exte) {

		ConexionLocal cn = new ConexionLocal();
		ResultSet res = null;
		List<VistaBusqueda> vistaBusqueda = new ArrayList<VistaBusqueda>();

		if (fono != "") {
			cn.modificarTelefono(idExtension, fono);

		}
		if (exte != "") {
			cn.modificarExtension(idExtension, exte);

		}

		res = cn.consultaPorId(idExtension);

		if (res == null) {
			System.out.println("Error No Hay Datos modificaExtOrFono");
		} else {
			try {
				while (res.next()) {

					vistaBusqueda.add(new VistaBusqueda(res
							.getString("UZGTPERSON_ID"), res
							.getString("UZGTPERSON_UNIDAD"), res
							.getString("UZGTPERSON_SEDE"), res
							.getString("UZGTPERSON_PUEST"), res
							.getString("UZGTPERSON_NOMBRE"), res
							.getString("UZGTEXTE_NUM_EXTENSION"), res
							.getString("UZGTTELE_NUM_TELEFONO"), res
							.getString("UZGTPERSON_CORR"), res
							.getLong("UZGTEXTE_ID")));
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}

		}

		return vistaBusqueda;

	}

	/* *
	 * Metodo para guardar la informacion de un Personal con su Extension
	 * asignada.
	 */
	public boolean eliminaRegistro(VistaBusqueda datoDelete) {

		ConexionLocal cn = new ConexionLocal();
		ResultSet res = null;

		res = cn.consultaRelaAdminPersonal(datoDelete);
		try {
			if(res.next()){
				cn.eliminarRelacion(datoDelete);
				cn.eliminarExtension(datoDelete);
				cn.eliminarTelefono(datoDelete);
				return true;
			}else{
				cn.eliminarRelacion(datoDelete);
				cn.eliminarExtension(datoDelete);
				cn.eliminarTelefono(datoDelete);
				cn.eliminarIdPersonal(datoDelete);
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;

	}
}

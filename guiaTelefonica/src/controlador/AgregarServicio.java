package controlador;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Personal;
import conexion.Conexion;
import conexion.ConexionLocal;

public class AgregarServicio implements Serializable {

	/* *
	 */
	private static final long serialVersionUID = 1L;

	public AgregarServicio() {
	}

	/* *
	 * Metodo para obtener los datos del personal del Banner.
	 */
	public List<Personal> buscarPersonal(String textoIngresado,
			List<String> sedesCodigos) {

		Conexion cn = new Conexion();

		ResultSet consultaIDM = null;

		ResultSet consultaDatos = null;

		List<String> pidm = new ArrayList<String>();
		List<Personal> personal = new ArrayList<Personal>();

		for (String sedecodigo : sedesCodigos) {

			consultaIDM = cn.consultaPorNombre(textoIngresado, sedecodigo);

			// Obtencion de todos los IDM del Banner en relacion a su Apellido.
			try {
				while (consultaIDM.next()) {
					pidm.add(consultaIDM.getString("IDM"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error SQL IDM de Ban:" + e.getCause());
				e.printStackTrace();
			}
		}

		for (String pidm2 : pidm) {
			consultaDatos = cn.consultaDatosDePersonal(Integer.parseInt(pidm2));
			// Obtencion de todos los Datos en relacion al IDM
			if (consultaDatos == null) {
				System.out.println("Error No Hay Datos, IDM Ban:");
			} else {
				try {
					while (consultaDatos.next()) {
						personal.add(new Personal(consultaDatos
								.getString("NOMBRES"), consultaDatos
								.getString("CEDULA"), consultaDatos
								.getString("ID_DOCENTE"), consultaDatos
								.getString("PUESTO"), consultaDatos
								.getString("UNIDAD"), consultaDatos
								.getString("SEDE_EMPL"), consultaDatos
								.getString("CODE_SEDE"), consultaDatos
								.getString("DIR_INST"), consultaDatos
								.getString("CIU_LAB"), consultaDatos
								.getString("CORREO_INST")));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Error obtener Datos Personal Ban:"
							+ e.getCause());
					e.printStackTrace();
				}

			}
		}

		return personal;

	}

	/* *
	 * Metodo para guardar la informacion de un Personal si este Existe o No.
	 */
	public void guardarInformacion(Personal personal, String fono, String exte) {

		ConexionLocal cn = new ConexionLocal();
		ResultSet lastRegistro = null;

		lastRegistro = cn.consultaFindPersonal(personal);
		try {
			if (lastRegistro.next()) {
				guardarDataGuia(personal, fono, exte, cn);
			} else {
				int confirma = cn.guardarPersonal(personal);

				if (confirma != 1) {
					System.out.println("Error Dato de Personal no guardado");

				} else {
					guardarDataGuia(personal, fono, exte, cn);

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
	private void guardarDataGuia(Personal personal, String fono, String exte,
			ConexionLocal cn) {
		long lastIdTemp = 0;
		ResultSet lastRegistro = null;

		cn.guardarTelefono(fono);

		lastRegistro = cn.consultaLastTelefono();
		if (lastRegistro == null) {
			System.out.println("Error ultimo registro no Encontrado:");
		} else {
			try {
				while (lastRegistro.next()) {
					lastIdTemp = lastRegistro.getLong("ID_TELE");
				}
				cn.guardarExtension(exte, lastIdTemp);

				lastRegistro = cn.consultaLastExte();
				while (lastRegistro.next()) {
					lastIdTemp = lastRegistro.getLong("ID_EXTE");
				}
				cn.guardarRelPersonaExt(personal, lastIdTemp);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error Guardar Personal Extension: "
						+ e.getCause());
				e.printStackTrace();
			}

		}
	}

}

package conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Busqueda;
import modelo.Personal;
import modelo.VistaBusqueda;

public class ConexionLocal {
	private Connection cnn;
	private Statement state;
	private ResultSet res;
	private int conf = 0;

	public ConexionLocal() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			cnn = java.sql.DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "ESPEGUIA3",
					"ESPEGUIA3");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closeConexion() throws SQLException {
		cnn.close();
	}

	/*
	 * Metodo SQL para obtener las Sedes.
	 */
	public ResultSet consultaSedes() {
		String query = "SELECT DISTINCT UPPER(UZGTPERSON_SEDE_CODE) AS CODIGO, UZGTPERSON_SEDE FROM UZGTPERSON";
		try {
			state = cnn.createStatement();
			res = state.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	/*
	 * Metodo SQL para obtener las Unidades a partir de la Sede Selecciona.
	 */
	public ResultSet consultaUnidades(String sedeCode) {

		String query = "SELECT DISTINCT UPPER(UZGTPERSON_UNIDAD) AS UNIDAD FROM UZGTPERSON WHERE UZGTPERSON_SEDE_CODE='"
				+ sedeCode + "'";
		try {
			state = cnn.createStatement();
			res = state.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	/*
	 * Metodo SQL para obtener un Personal con su Id Unico.
	 */
	public ResultSet consultaPorId(String valorId) {
		String query = "SELECT * FROM VW_UZGTGUIA WHERE PK_UZGTPERSON_ID='"
				+ valorId + "'";

		try {
			state = cnn.createStatement();
			res = state.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public ResultSet consultaPorNombre(String valorTexto) {
		String query = "SELECT * FROM VW_UZGTGUIA WHERE UZGTPERSON_NOMBRE LIKE UPPER('%"
				+ valorTexto + "%')";

		try {
			state = cnn.createStatement();
			res = state.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public ResultSet consultaPorTelefono(String valorTexto) {
		String query = "SELECT * FROM VW_UZGTGUIA WHERE UZGTTELE_NUM_TELEFONO LIKE'%"
				+ valorTexto + "%' ";

		try {
			state = cnn.createStatement();
			res = state.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public ResultSet consultaPorExtension(String valorTexto) {
		String query = "SELECT * FROM VW_UZGTGUIA WHERE UZGTEXTE_NUM_EXTENSION LIKE'%"
				+ valorTexto + "%' ";

		try {
			state = cnn.createStatement();
			res = state.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public ResultSet consultaPorIdAsignado(VistaBusqueda datoDelete) {
		String query = "SELECT * FROM VW_UZGTGUIA WHERE PK_UZGTEXTE_ID="
				+ datoDelete.getIdAsignacion() + "";

		try {
			state = cnn.createStatement();
			res = state.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public ResultSet consultaPorSedeUnidad(String codeSede, String codeUnidad,
			String valorTexto) {
		String query = "SELECT * FROM VW_UZGTGUIA WHERE UZGTPERSON_SEDE_CODE='"
				+ codeSede + "' AND UZGTPERSON_UNIDAD='" + codeUnidad
				+ "' AND UZGTPERSON_NOMBRE LIKE'%" + valorTexto + "%' ";

		try {
			state = cnn.createStatement();
			res = state.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	/*
	 * Metodos para consultar Todos los miembros de una Sede con extensiones.
	 */
	public ResultSet consultaPorSedeTodos(String codeSede, String valorTexto) {
		String query = "SELECT * FROM VW_UZGTGUIA WHERE UZGTPERSON_SEDE_CODE='"
				+ codeSede + "' AND UZGTPERSON_NOMBRE LIKE'%" + valorTexto
				+ "%' ";

		try {
			state = cnn.createStatement();
			res = state.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	/*
	 * Metodos para consultar el ultimo registro de Extensiones.
	 */
	public ResultSet consultaLastExte() {
		String query = "SELECT max(PK_UZGTEXTE_ID) AS ID_EXTE FROM UZGTEXTE";

		try {
			state = cnn.createStatement();
			res = state.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	/*
	 * Metodos para consultar el ultimo registro de Telefonos.
	 */
	public ResultSet consultaLastTelefono() {
		String query = "SELECT max(PK_UZGTTELE_ID) AS ID_TELE FROM UZGTTELE";

		try {
			state = cnn.createStatement();
			res = state.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	/*
	 * Metodo para consultar la existencia de un ID de Personal.
	 */
	public ResultSet consultaFindPersonal(Personal personal) {
		String query = "SELECT PK_UZGTPERSON_ID AS IDPERSONAL FROM UZGTPERSON WHERE PK_UZGTPERSON_ID='"
				+ personal.getIdDocente() + "'";

		try {
			state = cnn.createStatement();
			res = state.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	/*
	 * Metodo para consultar la existencia de un ID en la relacion con una
	 * Extension.
	 */
	public ResultSet consultaFindRelacion(VistaBusqueda personal) {
		String query = "SELECT PK_UZGTPERSON_ID AS IDRELACION FROM UZGTEXTE_UZGTPERSON WHERE PK_UZGTPERSON_ID='"
				+ personal.getIdPersonal() + "'";

		try {
			state = cnn.createStatement();
			res = state.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	/*
	 * Metodo para consultar la existencia de un Administrador.
	 */
	public ResultSet consultaFindAdmin(Personal personal) {
		String query = "SELECT PK_UZGTPERSON_ID AS ADMINISTRADOR FROM UZGTADMI WHERE PK_UZGTPERSON_ID='"
				+ personal.getIdDocente() + "'";

		try {
			state = cnn.createStatement();
			res = state.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	/*
	 * Metodo para guardar una nueva extension.
	 */
	public int guardarPersonal(Personal personal) {
		String query = "insert into UZGTPERSON(PK_UZGTPERSON_ID,UZGTPERSON_NOMBRE,UZGTPERSON_PUEST,UZGTPERSON_UNIDAD,UZGTPERSON_DIREC,UZGTPERSON_CIUDAD,UZGTPERSON_CORR,UZGTPERSON_SEDE,UZGTPERSON_SEDE_CODE) "
				+ "values('"
				+ personal.getIdDocente()
				+ "','"
				+ personal.getNombres()
				+ "','"
				+ personal.getPuesto()
				+ "','"
				+ personal.getUnidad()
				+ "','"
				+ personal.getDirecInstitu()
				+ "','"
				+ personal.getCiudadLabora()
				+ "','"
				+ personal.getCorreo()
				+ "','"
				+ personal.getSede()
				+ "','"
				+ personal.getSedeCode() + "')";

		try {
			state = cnn.createStatement();
			conf = state.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conf;
	}

	/*
	 * Metodo para guardar un Administrador.
	 */
	public int guardarAdministrador(Personal admin) {
		String query = "insert into UZGTADMI(PK_UZGTPERSON_ID,UZGTADMI_ESTADO) "
				+ "values('" + admin.getIdDocente() + "',1)";

		try {
			state = cnn.createStatement();
			conf = state.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conf;
	}

	/*
	 * Metodo para guardar Sedes Administradas.
	 */
	public int guardarSedes(Busqueda sedes) {
		String query = "insert into UZGTSEDE(PK_UZGTSEDE_ID,UZGTSEDE_NOMBRE) "
				+ "values('" + sedes.getValor() + "','"
				+ sedes.getDescripcion() + "')";

		try {
			state = cnn.createStatement();
			conf = state.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conf;
	}

	/*
	 * Metodo para guardar la Relacion de Sede - Administrador.
	 */
	public int guardarRelAdminSede(Personal administrador, Busqueda sedes) {
		String query = "insert into UZGTADMI_UZGTSEDE(PK_UZGTPERSON_ID,PK_UZGTSEDE_ID) "
				+ "values('"
				+ administrador.getIdDocente()
				+ "','"
				+ sedes.getValor() + "')";

		try {
			state = cnn.createStatement();
			conf = state.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conf;
	}

	public int guardarTelefono(String telefono) {
		String query = "insert into UZGTTELE(PK_UZGTTELE_ID,UZGTTELE_NUM_TELEFONO) values(IN_TELE.nextval,'"
				+ telefono + "')";

		try {
			state = cnn.createStatement();
			conf = state.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conf;
	}

	public int guardarExtension(String exte, long idTelefono) {
		String query = "insert into UZGTEXTE(PK_UZGTEXTE_ID,PK_UZGTTELE_ID,UZGTEXTE_NUM_EXTENSION,UZGTEXTE_ESTADO) values(IN_EXT.nextval,"
				+ idTelefono + ",'" + exte + "',1)";

		try {
			state = cnn.createStatement();
			conf = state.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conf;
	}

	public int guardarRelPersonaExt(Personal persona, long exte) {
		String query = "insert into UZGTEXTE_UZGTPERSON(PK_UZGTPERSON_ID,PK_UZGTEXTE_ID) values('"
				+ persona.getIdDocente() + "'," + exte + ")";

		try {
			state = cnn.createStatement();
			conf = state.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conf;
	}

	/*
	 * Metodos para modificar un Telefono.
	 */
	public int modificarTelefono(long idAsignado, String nuevoFono) {
		String query = "Update UZGTTELE set UZGTTELE_NUM_TELEFONO='"
				+ nuevoFono + "' where PK_UZGTTELE_ID=" + idAsignado + "";

		try {
			state = cnn.createStatement();
			conf = state.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conf;
	}

	/*
	 * Metodos para modificar una Extension.
	 */
	public int modificarExtension(long idAsignado, String nuevaExten) {
		String query = "Update UZGTEXTE set UZGTEXTE_NUM_EXTENSION='"
				+ nuevaExten + "' where PK_UZGTEXTE_ID=" + idAsignado + "";

		try {
			state = cnn.createStatement();
			conf = state.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conf;
	}

	public int eliminarRelacion(VistaBusqueda eliminar) {
		String query = "DELETE FROM UZGTEXTE_UZGTPERSON WHERE PK_UZGTEXTE_ID="
				+ eliminar.getIdAsignacion() + "";

		try {
			state = cnn.createStatement();
			conf = state.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conf;
	}

	public int eliminarExtension(VistaBusqueda eliminar) {
		String query = "DELETE FROM UZGTEXTE WHERE PK_UZGTEXTE_ID="
				+ eliminar.getIdAsignacion() + "";

		try {
			state = cnn.createStatement();
			conf = state.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conf;
	}

	public int eliminarTelefono(VistaBusqueda eliminar) {
		String query = "DELETE FROM UZGTTELE WHERE PK_UZGTTELE_ID="
				+ eliminar.getIdAsignacion() + "";

		try {
			state = cnn.createStatement();
			conf = state.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conf;
	}

	public int eliminarIdPersonal(VistaBusqueda eliminar) {
		String query = "DELETE FROM UZGTPERSON WHERE PK_UZGTPERSON_ID='"
				+ eliminar.getIdPersonal() + "'";

		try {
			state = cnn.createStatement();
			conf = state.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conf;
	}
}

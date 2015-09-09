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
					"jdbc:oracle:thin:@10.1.0.188:1521:DESARROLLO", "GUIAT",
					"gu1at2015");

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
		String query = "SELECT DISTINCT UPPER(UZGTPERSON_SEDE_CODE) AS CODIGO_SEDE, UZGTPERSON_SEDE AS NOMBRE_SEDE FROM UZGVGUIA";
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
		String query = "SELECT * FROM UZGVGUIA WHERE UZGTPERSON_ID='"
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
		String query = "SELECT upper(UZGTPERSON_NOMBRE)as UZGTPERSON_NOMBRE ,UPPER(UZGTPERSON_PUEST)as UZGTPERSON_PUEST , "
				+ "UPPER(UZGTPERSON_UNIDAD)as UZGTPERSON_UNIDAD ,UPPER(UZGTPERSON_SEDE) as UZGTPERSON_SEDE ,"
				+ "UPPER(UZGTPERSON_SEDE_CODE)as UZGTPERSON_SEDE_CODE ,UPPER(UZGTPERSON_DIREC) as UZGTPERSON_DIREC ,"
				+ "UPPER(UZGTPERSON_CIUDAD) as UZGTPERSON_CIUDAD ,UZGTPERSON_CORR,UZGTPERSON_ID,"
				+ "UZGTEXTE_NUM_EXTENSION,UZGTTELE_NUM_TELEFONO,UZGTEXTE_ESTADO,UZGTEXTE_ID FROM UZGVGUIA WHERE UZGTPERSON_NOMBRE LIKE UPPER('%"
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
	
	public ResultSet consultaAdminConSedes(String valorTexto) {
		String query = "SELECT UZGTPERSON_ID AS IDADMIN, UZGTPERSON_NOMBRE AS NOMBADMIN, UZGTSEDE_NOMBRE AS NOMBSEDE, UZGTSEDE_ID AS IDSEDE FROM UZGVADMINSEDE WHERE UZGTPERSON_ID='"+valorTexto+"'";

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
		String query = "SELECT * FROM UZGVGUIA WHERE UZGTTELE_NUM_TELEFONO LIKE'%"
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
		String query = "SELECT * FROM UZGVGUIA WHERE UZGTEXTE_NUM_EXTENSION LIKE'%"
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
		String query = "SELECT * FROM UZGVGUIA WHERE UZGTEXTE_ID="
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
		String query = "SELECT * FROM UZGVGUIA WHERE UZGTPERSON_SEDE_CODE='"
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
		String query = "SELECT * FROM UZGVGUIA WHERE UZGTPERSON_SEDE_CODE='"
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
	 * Metodo para consultar el ultimo registro de Extensiones.
	 */
	public ResultSet consultaLastExte() {
		String query = "SELECT max(UZGTEXTE_ID) AS ID_EXTE FROM UZGTEXTE";

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
	 * Metodo para consultar la Relacion entre Administrador y Sedes.
	 */
	public ResultSet consultaFindRelaAdminSede(String codigAdmin,
			String codigSede) {
		String query = "SELECT UZGTPERSON_ID AS CODADMIN, UZGTSEDE_ID AS CODSEDE FROM UZGTADMINSEDE WHERE UZGTPERSON_ID='"
				+ codigAdmin + "' AND UZGTSEDE_ID='" + codigSede + "'";

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
	 * Metodo para consultar registros de Sedes.
	 */
	public ResultSet consultaFindSede(String codeSede) {
		String query = "SELECT UZGTSEDE_ID AS CODIGO, UZGTSEDE_NOMBRE AS NOMBRE FROM UZGTSEDE WHERE UZGTSEDE_ID='"
				+ codeSede + "'";

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
		String query = "SELECT max(UZGTTELE_ID) AS ID_TELE FROM UZGTTELE";

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
	 * Metodos para consultar la existencia de un Admininistrador.
	 */
	public ResultSet consultaFindAdministrador(String codeAdministrador) {
		String query = "SELECT UZGTPERSON_ID AS ID_ADMIN FROM UZGTADMIN WHERE UZGTPERSON_ID='"
				+ codeAdministrador + "'";

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
		String query = "SELECT UZGTPERSON_ID AS IDPERSONAL FROM UZGTPERSON WHERE UZGTPERSON_ID='"
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
		String query = "SELECT UZGTPERSON_ID AS IDRELACION FROM UZGTEXTEPERSON WHERE UZGTPERSON_ID='"
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
	 * Metodo para guardar una nueva extension.
	 */
	public int guardarPersonal(Personal personal) {
		String query = "insert into UZGTPERSON(UZGTPERSON_ID,UZGTPERSON_NOMBRE,UZGTPERSON_PUEST,UZGTPERSON_UNIDAD,UZGTPERSON_DIREC,UZGTPERSON_CIUDAD,UZGTPERSON_CORR,UZGTPERSON_SEDE,UZGTPERSON_SEDE_CODE) "
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
		String query = "INSERT INTO UZGTADMIN(UZGTPERSON_ID,UZGTADMIN_ESTADO) "
				+ "VALUES('" + admin.getIdDocente() + "',1)";

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
		String query = "insert into UZGTSEDE(UZGTSEDE_ID,UZGTSEDE_NOMBRE) "
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
		String query = "insert into UZGTADMINSEDE(UZGTPERSON_ID,UZGTSEDE_ID) "
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
		String query = "insert into UZGTTELE(UZGTTELE_ID,UZGTTELE_NUM_TELEFONO) values(UZGSTELE.nextval,'"
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
		String query = "insert into UZGTEXTE(UZGTEXTE_ID,UZGTTELE_ID,UZGTEXTE_NUM_EXTENSION,UZGTEXTE_ESTADO) values(UZGSEXTE.nextval,"
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
		String query = "insert into UZGTEXTEPERSON(UZGTPERSON_ID,UZGTEXTE_ID) values('"
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
				+ nuevoFono + "' where UZGTTELE_ID=" + idAsignado + "";

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
				+ nuevaExten + "' where UZGTEXTE_ID=" + idAsignado + "";

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
		String query = "DELETE FROM UZGTEXTEPERSON WHERE UZGTEXTE_ID="
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
		String query = "DELETE FROM UZGTEXTE WHERE UZGTEXTE_ID="
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
		String query = "DELETE FROM UZGTTELE WHERE UZGTTELE_ID="
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
		String query = "DELETE FROM UZGTPERSON WHERE UZGTPERSON_ID='"
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
	
	//************************************ METODOS MODIFICAR ADMINISTRADORES****************************************

	public ResultSet consultaAdministradores () {
		String query = "SELECT DISTINCT UZGTPERSON_NOMBRE, UZGTPERSON_ID FROM UZGVADMINSEDE";

		try {
			state = cnn.createStatement();
			res = state.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public ResultSet consultaUnAdministrador (String valor) {
		String query = "SELECT * FROM UZGVADMINSEDE WHERE UZGTPERSON_ID = '"+valor+"'";
		try {
			state = cnn.createStatement();
			res = state.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
		
	public ResultSet compararSedes(String codigo) {
		String query = "SELECT * FROM UZGTSEDE WHERE UZGTSEDE_ID='"+codigo+"'";
		try {
			state = cnn.createStatement();
			res = state.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public int guardarSedeInexistente(String sede, String codigo) {
		String query = "insert into UZGTSEDE values('"+ codigo + "','" + sede + "')";
		try {
			state = cnn.createStatement();
			conf = state.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conf;
	}
	
	public int eliminarRelacionSedeAdmin(String codigo) {

		String query = "DELETE FROM UZGTADMINSEDE WHERE UZGTPERSON_ID='"+ codigo+ "'";

		try {
			state = cnn.createStatement();
			conf = state.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conf;
	}
	
	public int guardarRelacionSedeAdmin(String person_id, String sede_id) {
		String query = "insert into UZGTADMINSEDE values('"+ person_id + "','" + sede_id + "')";
		try {
			state = cnn.createStatement();
			conf = state.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conf;
	}
	
	public int eliminarAdmin(String codigo) {

		String query = "DELETE FROM UZGTADMIN WHERE UZGTPERSON_ID='"+ codigo+ "'";

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

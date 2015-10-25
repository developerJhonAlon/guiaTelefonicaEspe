package conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Busqueda;
import modelo.Personal;
import modelo.Proveedor;
import modelo.VistaBusqueda;
import modelo.VistaProveedor;

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
		String query = "SELECT DISTINCT UPPER(UZGTPERSON_SEDE_CODE) AS CODIGO_SEDE, UZGTPERSON_SEDE AS NOMBRE_SEDE FROM UZGVEXTEPERSON";
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

		String query = "SELECT DISTINCT UPPER(UZGTPERSON_UNIDAD) AS UNIDAD FROM UZGVEXTEPERSON WHERE UZGTPERSON_SEDE_CODE='"
				+ sedeCode + "' ORDER BY UNIDAD ASC";
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
	public ResultSet consultaPorId(long identificador) {
		String query = "SELECT * FROM UZGVEXTEPERSON WHERE UZGTPERSON_ID="
				+ identificador + "";

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
	public ResultSet consultaPorIdExterno(long identificador) {
		String query = "SELECT * FROM UZGVEXTEPRO WHERE UZGTPRO_ID="
				+ identificador + "";

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
				+ "UZGTEXTE_NUM_EXTENSION,UZGTTELE_NUM_TELEFONO,UZGTEXTE_ESTADO,UZGTEXTE_ID FROM UZGVEXTEPERSON WHERE UZGTPERSON_NOMBRE LIKE UPPER('%"
				+ valorTexto + "%') ORDER BY UZGTPERSON_NOMBRE ASC";

		try {
			state = cnn.createStatement();
			res = state.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	
	
	public ResultSet consultaPorNombreExterno(String valorTexto) {
		String query = "SELECT upper(UZGTPRO_NOMBRES)as UZGTPRO_NOMBRES ,UPPER(UZGTPRO_AREA)as UZGTPRO_AREA , "
				+ "UPPER(UZGTPRO_UNIDAD)as UZGTPRO_UNIDAD ,UPPER(UZGTPRO_CAMPUS) as UZGTPRO_CAMPUS, UZGTPRO_ID,"
				+ "UZGTEXTE_NUM_EXTENSION,UZGTTELE_NUM_TELEFONO,UZGTEXTE_ID FROM UZGVEXTEPRO WHERE UZGTPRO_NOMBRES LIKE UPPER('%"
				+ valorTexto + "%') ORDER BY UZGTPRO_NOMBRES ASC";

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
		String query = "SELECT UZGTPERSON_ID_PERSONA AS IDADMIN, UZGTPERSON_NOMBRE AS NOMBADMIN, UZGTSEDE_NOMBRE AS NOMBSEDE, UZGTSEDE_ID AS IDSEDE FROM UZGVADMINSEDE WHERE UZGTPERSON_ID_PERSONA='"
				+ valorTexto + "'";

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
		String query = "SELECT * FROM UZGVEXTEPERSON WHERE UZGTTELE_NUM_TELEFONO LIKE'%"
				+ valorTexto + "%' ORDER BY UZGTPERSON_NOMBRE ASC";

		try {
			state = cnn.createStatement();
			res = state.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	
	public ResultSet consultaPorTelefonoExterno(String valorTexto) {
		String query = "SELECT * FROM UZGVEXTEPRO WHERE UZGTTELE_NUM_TELEFONO LIKE'%"
				+ valorTexto + "%' ORDER BY UZGTPRO_NOMBRES ASC";

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
		String query = "SELECT * FROM UZGVEXTEPERSON WHERE UZGTEXTE_NUM_EXTENSION LIKE'%"
				+ valorTexto + "%' ORDER BY UZGTPERSON_NOMBRE ASC";

		try {
			state = cnn.createStatement();
			res = state.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public ResultSet consultaPorExtensionExterno(String valorTexto) {
		String query = "SELECT * FROM UZGVEXTEPRO WHERE UZGTEXTE_NUM_EXTENSION LIKE'%"
				+ valorTexto + "%' ORDER BY UZGTPRO_NOMBRES ASC";

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
		String query = "SELECT * FROM UZGVEXTEPERSON WHERE UZGTEXTE_ID="
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

	/*
	 * Metodo SQL para obtener las personas que tienen Extension en Relacion a
	 * las Unidades.
	 */
	public ResultSet consultaUnidadesConExtension(String codeSede,
			String codeUnidad) {
		String query = "SELECT * FROM UZGVEXTEPERSON WHERE UZGTPERSON_SEDE_CODE='"
				+ codeSede
				+ "' AND UZGTPERSON_UNIDAD='"
				+ codeUnidad
				+ "' ORDER BY UZGTEXTE_NUM_EXTENSION ASC";

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
	 * Metodo SQL para obtener las Extensiones por medio de la Unidad y Sede
	 * seleccionada.
	 */
	public ResultSet consultaExtensionesExternasPorUnidad(String codigoSede,
			String codigoUnidad, String responsable) {
		String query = "SELECT * FROM UZGVEXTEPRO WHERE UZGTPRO_CAMPUS='"
				+ codigoSede + "' AND UZGTPRO_UNIDAD='" + codigoUnidad
				+ "' AND UZGTPRO_RESPONSABLE='"+responsable+"' ORDER BY UZGTEXTE_NUM_EXTENSION ASC";

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
	 * Metodo Sql para obtener las Unidades con la seleccion de una Sede.
	 */
	public ResultSet consultarSedesUnidades(String codigoSede) {

		String query = "SELECT distinct(UZGTPERSON_UNIDAD) AS CODIGO_UNIDAD FROM UZGTPERSON WHERE UZGTPERSON_SEDE_CODE='"
				+ codigoSede + "' ORDER BY CODIGO_UNIDAD ASC";

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
	 * Metodo Sql para obtener las Sedes de los Proveedores en relacion a el Id
	 * del Administrador.
	 */
	public ResultSet consultaListaSedeExterna(String codigoAdministrador) {

		String query = "SELECT distinct(UZGTPRO_CAMPUS) AS CODIGO_SEDE_EXTE FROM UZGTPRO WHERE UZGTPRO_RESPONSABLE='"
				+ codigoAdministrador + "' ORDER BY CODIGO_SEDE_EXTE ASC";

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
	 * Metodo Sql para obtener las Unidades de los Proveedores en relacion a el
	 * Id de la Sede.
	 */
	public ResultSet consultaListaUnidadExterna(String codigoSede) {

		String query = "SELECT distinct(UZGTPRO_UNIDAD) AS CODIGO_UNIDAD_EXTE FROM UZGTPRO WHERE UZGTPRO_CAMPUS='"
				+ codigoSede + "' ORDER BY CODIGO_UNIDAD_EXTE ASC";

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
		String query = "SELECT * FROM UZGVEXTEPERSON WHERE UZGTPERSON_SEDE_CODE='"
				+ codeSede
				+ "' AND UZGTPERSON_UNIDAD='"
				+ codeUnidad
				+ "' AND UZGTPERSON_NOMBRE LIKE'%"
				+ valorTexto
				+ "%' ORDER BY UZGTPERSON_NOMBRE ASC";

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
		String query = "SELECT * FROM UZGVEXTEPERSON WHERE UZGTPERSON_SEDE_CODE='"
				+ codeSede
				+ "' AND UZGTPERSON_NOMBRE LIKE'%"
				+ valorTexto
				+ "%' ORDER BY UZGTPERSON_NOMBRE ASC";

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
	 * Metodos para consultar el Personal con Extension de una Sede Determinada.
	 */
	public ResultSet consultaUsuarioExtensionPorSede(String valorTexto,
			String codeSede) {
		String query = "SELECT * FROM UZGVEXTEPERSON WHERE UZGTPERSON_SEDE_CODE='"
				+ codeSede
				+ "' AND UZGTPERSON_NOMBRE LIKE'%"
				+ valorTexto
				+ "%' ORDER BY UZGTEXTE_NUM_EXTENSION ASC";

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
	 * Metodos para consultar un usuario Externo con Extension de una Sede
	 * Determinada.
	 */
	public ResultSet consultaExtensionExternaPorSede(String valorTexto,
			String codeSede) {
		String query = "SELECT * FROM UZGVEXTEPRO WHERE UZGTPRO_CAMPUS='"
				+ codeSede + "' AND UZGTPRO_NOMBRES LIKE'%" + valorTexto
				+ "%' ORDER BY UZGTEXTE_NUM_EXTENSION ASC";

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
	public ResultSet consultaFindRelaAdminSede(long codigAdmin, String codigSede) {
		String query = "SELECT UZGTPERSON_ID AS CODADMIN, UZGTSEDE_ID AS CODSEDE FROM UZGTADMINSEDE WHERE UZGTPERSON_ID="
				+ codigAdmin + " AND UZGTSEDE_ID='" + codigSede + "'";

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
	 * Metodos para consultar la existencia de un Admininistrador por ID del
	 * Registro.
	 */
	public ResultSet consultaFindAdministrador(long identificador) {
		String query = "SELECT UZGTPERSON_ID AS ID_ADMIN FROM UZGTADMIN WHERE UZGTPERSON_ID="
				+ identificador + "";

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
	public ResultSet consultarAdministradorFind(String identificador) {
		String query = "SELECT UZGTPERSON_ID_PERSONA AS ID_ADMIN FROM UZGVADMINSEDE WHERE UZGTPERSON_ID_PERSONA='"
				+ identificador + "'";

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
	 * Metodo para consultar la existencia de un ID de Personal dentro de Tabla
	 * Vista.
	 */
	public ResultSet consultaFindPersonal(Personal personal, String codeSede,
			String codeUnidad) {
		String query = "SELECT UZGTPERSON_ID AS IDENTIDAD FROM UZGVEXTEPERSON WHERE UZGTPERSON_ID_PERSONA='"
				+ personal.getIdDocente()
				+ "' AND UZGTPERSON_SEDE_CODE='"
				+ codeSede + "' AND UZGTPERSON_UNIDAD='" + codeUnidad + "'";

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
	 * Metodo para consultar la existencia de un Proveedor Exteno dentro de
	 * Tabla Vista.
	 */
	public ResultSet consultaProveedor(VistaProveedor vistaProveedor) {
		String query = "SELECT UZGTPRO_ID AS IDENTIDAD FROM UZGVEXTEPRO WHERE UZGTPRO_NOMBRES='"
				+ vistaProveedor.getNombreProvee()
				+ "' AND UZGTPRO_CAMPUS='"
				+ vistaProveedor.getSedeNomb()
				+ "' AND UZGTPRO_UNIDAD='"
				+ vistaProveedor.getUnidadNomb() + "'";

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
	 * Metodo para consultar la existencia de un registro de Personal dentro de
	 * Tabla Personal.
	 */
	public ResultSet consultaFindRegistroAuxiliar(Personal personal) {
		String query = "SELECT UZGTPERSON_ID AS IDENTIDAD FROM UZGVEXTEPERSON WHERE UZGTPERSON_ID_PERSONA='"
				+ personal.getIdDocente()
				+ "' AND UZGTPERSON_SEDE_CODE='"
				+ personal.getSedeCode()
				+ "' AND UZGTPERSON_UNIDAD='"
				+ personal.getUnidad() + "'";

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
	 * Metodo para consultar la existencia de un ID de Personal en la Tabla para
	 * la Edicion.
	 */
	public ResultSet consultaFindPersonalEdicion(VistaBusqueda vista,
			String codeSede, String codeUnidad) {
		String query = "SELECT UZGTPERSON_ID AS IDENTIDAD FROM UZGVEXTEPERSON WHERE UZGTPERSON_ID_PERSONA='"
				+ vista.getIdPersonal()
				+ "' AND UZGTPERSON_SEDE_CODE='"
				+ codeSede
				+ "' AND UZGTPERSON_UNIDAD='"
				+ codeUnidad
				+ "' AND UZGTPERSON_ID<>" + vista.getIdentidad() + "";

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
	 * Metodo para consultar la existencia de un ID de Personal en la Tabla para
	 * la Edicion.
	 */
	public ResultSet consultaFindExternoEdicion(VistaProveedor vistaProveedor) {
		String query = "SELECT UZGTPRO_ID AS IDENTIDAD FROM UZGVEXTEPRO WHERE UZGTPRO_NOMBRES='"
				+ vistaProveedor.getNombreProvee()
				+ "' AND UZGTPRO_CAMPUS='"
				+ vistaProveedor.getSedeNomb()
				+ "' AND UZGTPRO_UNIDAD='"
				+ vistaProveedor.getUnidadNomb()
				+ "' AND UZGTPRO_ID<>"
				+ vistaProveedor.getIdentidad() + "";

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
	 * Metodo para consultar la existencia de un ID de Personal para la
	 * Administracion.
	 */
	public ResultSet consultaFindPersonalAdmin(Personal personal) {
		String query = "SELECT UZGTPERSON_ID AS IDENTIDAD FROM UZGTPERSON WHERE UZGTPERSON_ID_PERSONA='"
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
	 * Metodo para consultar la existencia de un ID de Personal para la
	 * Administracion.
	 */
	public ResultSet consultaExiteciaAdmin(Personal personal) {
		String query = "SELECT UZGTPERSON_ID_PERSONA FROM UZGVADMINSEDE WHERE UZGTPERSON_ID_PERSONA='"
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
	 * Metodos para consultar el ultimo registro de una Persona.
	 */
	public ResultSet consultaLastPersonal() {
		String query = "SELECT max(UZGTPERSON_ID) AS IDREGISTRO FROM UZGTPERSON";

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
	 * Metodos para consultar el ultimo registro de un Proveedor Externo.
	 */
	public ResultSet consultaLastProveedor() {
		String query = "SELECT max(UZGTPERSON_ID) AS IDREGISTRO FROM UZGTPERSON";

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
	 * Metodo para consultar la existencia de Administrador como parte de este
	 * ID para no eliminar la referencia del ID.
	 */
	public ResultSet consultaRelaAdminPersonal(VistaBusqueda personal) {

		String query = "SELECT UZGTPERSON_ID_PERSONA AS ID_ADMIN FROM UZGVADMINSEDE WHERE UZGTPERSON_ID_PERSONA='"
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
	 * Metodos para modificar un Telefono.
	 */
	public int modificarPersonal(long identidad, String codeSede,
			String nombreSede, String codeUnidad) {
		String query = "Update UZGTPERSON set UZGTPERSON_SEDE_CODE='"
				+ codeSede + "', UZGTPERSON_SEDE='" + nombreSede
				+ "', UZGTPERSON_UNIDAD='" + codeUnidad
				+ "' where UZGTPERSON_ID=" + identidad + "";

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
	 * Metodos para modificar un Extension Exteno.
	 */
	public int modificarExtensionExterno(VistaProveedor vistaProveedor) {
		String query = "Update UZGTPRO set UZGTPRO_CAMPUS='"
				+ vistaProveedor.getSedeNomb() + "', UZGTPRO_UNIDAD='"
				+ vistaProveedor.getUnidadNomb() + "', UZGTPRO_ABRE='"
				+ vistaProveedor.getAbreviat() + "' , UZGTPRO_AREA='"
				+ vistaProveedor.getAreaNomb() + "' , UZGTPRO_TRATO='"
				+ vistaProveedor.getTrato() + "' , UZGTPRO_NOMBRES='"
				+ vistaProveedor.getNombreProvee() + "' where UZGTPRO_ID="
				+ vistaProveedor.getIdentidad() + "";

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
	 * Metodo para guardar una nueva extension.
	 */
	public int guardarPersonal(Personal personal, String codeSede,
			String nombreSede, String codeUnidad) {
		String query = "insert into UZGTPERSON(UZGTPERSON_ID,UZGTPERSON_ID_PERSONA,UZGTPERSON_NOMBRE,UZGTPERSON_PUEST,UZGTPERSON_UNIDAD,UZGTPERSON_DIREC,UZGTPERSON_CIUDAD,UZGTPERSON_CORR,UZGTPERSON_SEDE,UZGTPERSON_SEDE_CODE) "
				+ "values(UZGSPERSON.nextval,'"
				+ personal.getIdDocente()
				+ "','"
				+ personal.getNombres()
				+ "','"
				+ personal.getPuesto()
				+ "','"
				+ codeUnidad
				+ "','"
				+ personal.getDirecInstitu()
				+ "','"
				+ personal.getCiudadLabora()
				+ "','"
				+ personal.getCorreo()
				+ "','"
				+ nombreSede
				+ "','"
				+ codeSede
				+ "')";

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
	 * Metodo para guardar el Historico de Cambios.
	 */
	public int guardarHistorico(String idPersona, String nombre,
			String unidad, String sede, String fono, String exte, String fecha, String idRespo, String respo) {
		String query = "insert into UZGTREPORT(UZGTREPORT_ID_REPORT,UZGTREPORT_ID_PERSONA,UZGTREPORT_NOMBRE,UZGTREPORT_UNIDAD,UZGTREPORT_SEDE,UZGTREPORT_TELEFONO,UZGTREPORT_EXTENSION,UZGTREPORT_FECHA_MOD,UZGTREPORT_ID_RESPONSABLE,UZGTREPORT_RESPONSABLE) "
				+ "values(UZGSREPORT.nextval,'"
				+ idPersona
				+ "','"
				+ nombre
				+ "','"
				+ unidad
				+ "','"
				+ sede
				+ "','"
				+ fono
				+ "','"
				+ exte
				+ "','"
				+ fecha
				+ "','"
				+ idRespo
				+ "','"
				+ respo
				+ "')";

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
	 * Metodo para guardar una nuevo administrador.
	 */
	public int guardarPersonalAdmin(Personal personal) {
		String query = "insert into UZGTPERSON(UZGTPERSON_ID,UZGTPERSON_ID_PERSONA,UZGTPERSON_NOMBRE,UZGTPERSON_PUEST,UZGTPERSON_UNIDAD,UZGTPERSON_DIREC,UZGTPERSON_CIUDAD,UZGTPERSON_CORR,UZGTPERSON_SEDE,UZGTPERSON_SEDE_CODE) "
				+ "values(UZGSPERSON.nextval,'"
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
				+ personal.getSedeCode()
				+ "')";

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
	public int guardarAdministrador(long idRegistro) {
		String query = "INSERT INTO UZGTADMIN(UZGTPERSON_ID,UZGTADMIN_ESTADO) "
				+ "VALUES(" + idRegistro + ",1)";

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
	public int guardarRelAdminSede(long admin, Busqueda sedes) {
		String query = "insert into UZGTADMINSEDE(UZGTPERSON_ID,UZGTSEDE_ID) "
				+ "values(" + admin + ",'" + sedes.getValor() + "')";
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

	public int guardarRelPersonaExt(long idRegistro, long exte) {
		String query = "insert into UZGTEXTEPERSON(UZGTPERSON_ID,UZGTEXTE_ID) values('"
				+ idRegistro + "'," + exte + ")";

		try {
			state = cnn.createStatement();
			conf = state.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conf;
	}

	public int guardarRelProvedorExtension(VistaProveedor proveedor,
			String responsable, long exte) {
		String query = "insert into UZGTPRO(UZGTPRO_ID,UZGTEXTE_ID,UZGTPRO_CAMPUS,UZGTPRO_UNIDAD,UZGTPRO_ABRE,UZGTPRO_AREA,UZGTPRO_TRATO,UZGTPRO_NOMBRES,UZGTPRO_RESPONSABLE) "
				+ "values(UZGSPRO.nextval,"
				+ exte
				+ ",'"
				+ proveedor.getSedeNomb()
				+ "','"
				+ proveedor.getUnidadNomb()
				+ "','"
				+ proveedor.getAbreviat()
				+ "','"
				+ proveedor.getAreaNomb()
				+ "','"
				+ proveedor.getTrato()
				+ "','"
				+ proveedor.getNombreProvee()
				+ "','"
				+ responsable
				+ "')";

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
		String query = "DELETE FROM UZGTEXTEPERSON WHERE UZGTPERSON_ID="
				+ eliminar.getIdentidad() + "";

		try {
			state = cnn.createStatement();
			conf = state.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conf;
	}

	public int eliminarRelacionExtesionExterno(VistaProveedor vistaProveedor) {
		String query = "DELETE FROM UZGTPRO WHERE UZGTPRO_ID="
				+ vistaProveedor.getIdentidad() + "";

		try {
			state = cnn.createStatement();
			conf = state.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conf;
	}

	public int eliminarExtension(long identificador) {
		String query = "DELETE FROM UZGTEXTE WHERE UZGTEXTE_ID="
				+ identificador + "";

		try {
			state = cnn.createStatement();
			conf = state.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conf;
	}

	public int eliminarTelefono(long identificador) {
		String query = "DELETE FROM UZGTTELE WHERE UZGTTELE_ID="
				+ identificador + "";

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
		String query = "DELETE FROM UZGTPERSON WHERE UZGTPERSON_ID="
				+ eliminar.getIdentidad() + "";

		try {
			state = cnn.createStatement();
			conf = state.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conf;
	}

	// ************************************ METODOS MODIFICAR
	// ADMINISTRADORES****************************************

	public ResultSet consultaAdministradores() {
		String query = "SELECT DISTINCT UZGTPERSON_NOMBRE AS NOMBREADMIN, UZGTPERSON_ID_PERSONA AS IDADMIN FROM UZGVADMINSEDE";

		try {
			state = cnn.createStatement();
			res = state.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public ResultSet consultaUnAdministrador(String valor) {
		String query = "SELECT UZGTSEDE_NOMBRE, UZGTSEDE_ID FROM UZGVADMINSEDE WHERE UZGTPERSON_ID_PERSONA = '"
				+ valor + "'";
		try {
			state = cnn.createStatement();
			res = state.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public ResultSet consultaIdRegistro(String valor) {
		String query = "SELECT UZGTPERSON_ID AS IDREGISTRO FROM UZGVADMINSEDE WHERE UZGTPERSON_ID_PERSONA = '"
				+ valor + "'";
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
		String query = "SELECT * FROM UZGTSEDE WHERE UZGTSEDE_ID='" + codigo
				+ "'";
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
		String query = "insert into UZGTSEDE values('" + codigo + "','" + sede
				+ "')";
		try {
			state = cnn.createStatement();
			conf = state.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conf;
	}

	public int eliminarRelacionSedeAdmin(long codigo) {
		String query = "DELETE FROM UZGTADMINSEDE WHERE UZGTPERSON_ID="
				+ codigo + "";

		try {
			state = cnn.createStatement();
			conf = state.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conf;
	}

	public int guardarRelacionSedeAdmin(long idRegistro, String idSede) {
		String query = "insert into UZGTADMINSEDE values(" + idRegistro + ",'"
				+ idSede + "')";
		try {
			state = cnn.createStatement();
			conf = state.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conf;
	}

	public int eliminarAdmin(long codigo) {

		String query = "DELETE FROM UZGTADMIN WHERE UZGTPERSON_ID=" + codigo
				+ "";

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
	 * Metodos para consultar ID de Admininistrador Master.
	 */

	public ResultSet consultarMaster(String identificador) {
		String query = "SELECT UZGTPERSON_ID, UZGTPERSON_NOMBRE FROM UZGTPERSON WHERE UZGTPERSON_ID_PERSONA='"
				+ identificador + "' ORDER BY UZGTPERSON_ID ASC";
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
	 * Metodos para consultar existencia de Admininistrador Master.
	 */
	public ResultSet consultarMasterExis(long val) {
		String query = "SELECT UZGTROL_ID FROM UZGTPERSONROL WHERE UZGTPERSON_ID="
				+ val + "";
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
	 * Metodos para consultar Nombre de Admininistrador Master.
	 */
	public ResultSet consultarNomMaster(String identificador) {

		String query = "SELECT UZGTPERSON_NOMBRE FROM UZGTPERSON WHERE UZGTPERSON_ID_PERSONA='"
				+ identificador + "'";
		try {
			state = cnn.createStatement();
			res = state.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

}

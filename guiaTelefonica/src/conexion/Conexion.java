package conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
	
	private Connection cnn;
	private Statement state;
	private ResultSet res;
	
	
	public Conexion(){
			try {
				Class.forName("oracle.jdbc.OracleDriver");
				cnn = java.sql.DriverManager.getConnection(
						"jdbc:oracle:thin:@10.1.0.113:1521:PAS8", "USERCONSULTA",
						"consulta");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	}
	
	public void closeConexion() throws SQLException{
		cnn.close();
	}
	
	
	/* 
	 * Metodo Sql para obtener los datos del Personal en Banner.
	 * */
	public ResultSet consultaDatosDePersonal(long IDM){
		
		String query = "select DISTINCT  pebempl.pebempl_bcat_code, "
				+ "(SELECT (spriden_first_name||' ' || spriden_last_name)  FROM SPRIDEN WHERE spriden_pidm = pebempl.pebempl_pidm AND spriden_change_ind IS NULL) AS NOMBRES, "
				+ "(SELECT spbpers_ssn FROM SPBPERS WHERE spbpers_pidm = pebempl.pebempl_pidm) AS CEDULA, "
				+ "SUBSTR(f_getspridenid(pebempl_pidm),1,12) AS ID_DOCENTE, "
				+ "(SELECT nbbposn.nbbposn_title  FROM NBRBJOB, NBBPOSN WHERE nbrbjob_pidm = pebempl.pebempl_pidm AND nbrbjob.nbrbjob_posn = NBBPOSN_POSN  AND nbrbjob.nbrbjob_contract_type = 'P' AND nbrbjob_end_date IS NULL) AS PUESTO, "
				+ "(SELECT ftvorgn_title  FROM FTVORGN WHERE ftvorgn_orgn_code = pebempl.pebempl_ORGN_code_home) AS UNIDAD, "
				+ "(SELECT DISTINCT PTRJBLN_DESC FROM PTRJBLN WHERE PTRJBLN_CODE=pebempl_jbln_code) as SEDE_EMPL,"
				+ "(SELECT DISTINCT PTRJBLN_CODE   FROM PTRJBLN WHERE PTRJBLN_CODE=pebempl_jbln_code) as CODE_SEDE,"
				+ "(SELECT PTRJBLN_ADDRESS1   FROM PTRJBLN WHERE PTRJBLN_CODE=pebempl_jbln_code) as DIR_INST, "
				+ "(SELECT PTRJBLN_CITY   FROM PTRJBLN WHERE PTRJBLN_CODE=pebempl_jbln_code) as CIU_LAB,"
				+ "(SELECT goremal_email_address FROM GOREMAL WHERE goremal_pidm = "+IDM+" AND goremal_emal_code = 'STAN' AND goremal_status_ind = 'A' ) AS CORREO_INST "
				+ "from pebempl where pebempl_empl_status = 'A' and pebempl.pebempl_pidm ="+IDM;
		
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
	 * Metodo Sql para obtener el ID en relacion al Apellido en Banner.
	 * */
	public ResultSet consultaPorNombre(String textoIngresado){
		
		String query = "select spriden.spriden_pidm AS IDM from SPRIDEN, pebempl where spriden.spriden_pidm = pebempl.pebempl_pidm AND spriden_last_name LIKE '"+textoIngresado+"%' and spriden_change_ind is null" ;
		
		try {
			state = cnn.createStatement();
			res = state.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public ResultSet consultaSedes(){
		String query = "SELECT PTRJBLN_CODE AS CODESEDE, PTRJBLN_DESC AS DESCRIP FROM PTRJBLN";
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

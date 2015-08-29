package controlador;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Personal;
import conexion.ConexionLocal;

public class LoginServicio implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public LoginServicio(){}
	
	/* *
	 * Metodo para comprobar la Existencia de un Administrador. 
	 */
	public boolean comprobarAdmin(String idAdministrador){
		
//		ConexionLocal cn = new ConexionLocal();
//		ResultSet lastRegistro = null;
//		
//		lastRegistro = cn.consultaFindPersonal(personal);
//		try {
//			if(lastRegistro.next()){
//				guardarDataGuia(personal, fono, exte, cn);
//			}else{
//				int confirma = cn.guardarPersonal(personal);
//
//				if(confirma!=1){
//					System.out.println("Error Dato de Personal no guardado");
//					
//				}else{	
//					guardarDataGuia(personal, fono, exte, cn);
//
//				}
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		return false;
	}
}

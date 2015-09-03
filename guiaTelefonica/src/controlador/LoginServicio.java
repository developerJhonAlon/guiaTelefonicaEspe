package controlador;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Administrador;
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
		
		ConexionLocal cn = new ConexionLocal();
		ResultSet lastRegistro = null;
		
		lastRegistro = cn.consultaFindAdministrador(idAdministrador);
		try {
			if(lastRegistro.next()){
				return true;
			}else{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Administrador> obtenerAdminConSedes(String idAdministrador){
		ConexionLocal cn = new ConexionLocal();
		ResultSet res = cn.consultaAdminConSedes(idAdministrador);
		
				
		List<Administrador> adminSedes = new ArrayList<Administrador>();
		if(res == null){
			 System.out.println("Error No Hay Datos");
		}else{
			try{
				while(res.next()){
					adminSedes.add(new Administrador(res.getString("IDADMIN"),res.getString("NOMBADMIN"),
							res.getString("NOMBSEDE"),res.getString("IDSEDE"))
					);
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
			
		}
		return adminSedes;
	}
}

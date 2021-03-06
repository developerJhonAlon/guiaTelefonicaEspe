package controlador;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Busqueda;
import modelo.Personal;
import modelo.VistaAdministradores;
import conexion.Conexion;
import conexion.ConexionLocal;
public class ModificarAdmin implements Serializable{

	private static final long serialVersionUID = 1L;
	private List <String> nombres = new ArrayList<String>();	
	
	public List<String> getNombres() {
		return nombres;
	}

	
	public void setNombres(List<String> nombres) {
		this.nombres = nombres;
	}

	public List<VistaAdministradores> obtenerAdmin() {
		
		ConexionLocal cn = new ConexionLocal();
		ResultSet res = cn.consultaAdministradores(); 
		List<VistaAdministradores> vistaAdmin = new ArrayList<VistaAdministradores>();

		if (res == null) {
			System.out.println("Error No Hay Datos");
		} else {
			try {
				while (res.next()) {
					vistaAdmin.add(new VistaAdministradores(res
							.getString("UZGTPERSON_NOMBRE"), res
							.getString("UZGTPERSON_ID")));
				}
				

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return vistaAdmin;
	}

		public List<Busqueda> obtenerUnAdmin(String valor) {
			
			ConexionLocal cn = new ConexionLocal();
			ResultSet res = cn.consultaUnAdministrador(valor); 
			List<Busqueda> vistaAdmin = new ArrayList<Busqueda>();
			if (res == null) {
				System.out.println("Error No Hay Datos");
			} else {
				try {
					while (res.next()) {
						vistaAdmin.add(new Busqueda(res
								.getString("UZGTSEDE_NOMBRE"), res
								.getString("UZGTSEDE_ID")));
					}		
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return vistaAdmin;
		}
		
		public void modificar(VistaAdministradores admin, List<String> sedes) {
			ConexionLocal cn = new ConexionLocal();
			Conexion cnn= new Conexion();
			String nombreSede="";
			for (String codSede : sedes) {
				
				ResultSet lastRegistro = cn.compararSedes(codSede); 
				try {
					if(lastRegistro.next())
					{	
						System.out.println("Sede ya existe... ");
					}else 
						{
						ResultSet s= cnn.consultaSede(codSede); 
						s.next();
						nombreSede=s.getString("DESCRIP");
						cn.guardarSedeInexistente(nombreSede, codSede); 
						}							
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			cn.eliminarRelacionSedeAdmin(admin.getId_persona());
			for (String unaSede : sedes) {
				cn.guardarRelacionSedeAdmin(admin.getId_persona(),unaSede);
			}
		}	
		
		public void eliminar(VistaAdministradores admin)
		{
			ConexionLocal cn = new ConexionLocal();
			cn.eliminarRelacionSedeAdmin(admin.getId_persona()); 
			cn.eliminarAdmin(admin.getId_persona()); 
		}
}
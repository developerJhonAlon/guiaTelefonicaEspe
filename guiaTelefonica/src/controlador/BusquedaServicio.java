package controlador;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Busqueda;
import modelo.VistaBusqueda;
import conexion.ConexionLocal;

public class BusquedaServicio implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Metodo asignar filtros de busqueda.
	 * */
	public List<Busqueda> obtenerCriterios(){
		List<Busqueda> criterios = new ArrayList<Busqueda>();
		
//		criterios.add(new Busqueda("1","Todos"));
		criterios.add(new Busqueda("2","Por Nombre y/o Apellido"));
		criterios.add(new Busqueda("3","Por Telefono"));
		criterios.add(new Busqueda("4","Por Extensión"));
		criterios.add(new Busqueda("5","Por Sede"));
		
		return criterios;
	} 
	
	/*
	 * Metodo para recuperar datos de la BDD en relacion a los criterios de busqueda.
	 * @param valorCriterio Codigo del criterio de busqueda.
	 * @param valorTexto Dato sobre el cual se filtrara.
	 * */
	public List<VistaBusqueda> buscarExtensiones(String valorCriterio, String valorTexto, String codeSede, String codeUnidad){
		ConexionLocal cn = new ConexionLocal();
		ResultSet res = null;
		
		if(valorCriterio.equals("2")){
			res = cn.consultaPorNombre(valorTexto);
			
		}
		else if(valorCriterio.equals("3")){
			 res = cn.consultaPorTelefono(valorTexto);
			
		}
		else if(valorCriterio.equals("4")){
			 res = cn.consultaPorExtension(valorTexto);
			
		}	
		else if(valorCriterio.equals("5")){
			
			if(codeUnidad.equals("0"))
				res = cn.consultaPorSedeTodos(codeSede, valorTexto);
			else
				res = cn.consultaPorSedeUnidad(codeSede, codeUnidad, valorTexto);
			
		}
		
		List<VistaBusqueda> vistaBusqueda = new ArrayList<VistaBusqueda>();
		if(res == null){
			 System.out.println("Error No Hay Datos");
		}else{
			try{
				while(res.next()){
					vistaBusqueda.add(new VistaBusqueda(res.getString("PK_UZGTPERSON_ID"),res.getString("UZGTPERSON_UNIDAD"),
							res.getString("UZGTPERSON_SEDE"),res.getString("UZGTPERSON_PUEST"),
							res.getString("UZGTPERSON_NOMBRE"),res.getString("UZGTEXTE_NUM_EXTENSION"),
							res.getString("UZGTTELE_NUM_TELEFONO"),res.getString("UZGTPERSON_CORR"),res.getLong("PK_UZGTEXTE_ID"))
					);
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
			
		}
		return vistaBusqueda;
	} 
	
	/*
	 * Metodo para buscar las Sedes con extensiones Telefonicas.
	 * */
	public List<Busqueda> obtenerSedes(){
		
		ConexionLocal cn = new ConexionLocal();
		ResultSet res = cn.consultaSedes();
		List<Busqueda> sedes = new ArrayList<Busqueda>();
		
		if(res == null){
			 System.out.println("Error No Hay Datos");
		}else{
			try{
				while(res.next()){
					sedes.add(new Busqueda(res.getString("CODIGO"),
							res.getString("UZGTPERSON_SEDE")));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
			
		}	
	

		return sedes;
	} 
	
	/*
	 * Metodo para buscar las Sedes con extensiones Telefonicas.
	 * */
	public List<Busqueda> obtenerUnidades(String sedeCode){
		
		ConexionLocal cn = new ConexionLocal();
		ResultSet res = cn.consultaUnidades(sedeCode);
		List<Busqueda> unidades = new ArrayList<Busqueda>();
		
		if(res == null){
			 System.out.println("Error No Hay Datos");
		}else{
			try{
				while(res.next()){
					unidades.add(new Busqueda(res.getString("UNIDAD"),
							res.getString("UNIDAD")));
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
			
		}	
	

		return unidades;
	} 
}

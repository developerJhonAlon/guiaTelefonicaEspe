package beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import modelo.Busqueda;
import modelo.Personal;
import controlador.AsignarAdministradorServicio;

@ManagedBean
@ViewScoped
public class AsignarAdministrador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AsignarAdministradorServicio asignarServicio = new AsignarAdministradorServicio();
	private Personal administrador;
	private List<Busqueda> listaSedes;
	private String[] selectSedes;

	public AsignarAdministrador() {
	}


	public String[] getSelectSedes() {
		return selectSedes;
	}



	public void setSelectSedes(String[] selectSedes) {
		this.selectSedes = selectSedes;
	}




	public Personal getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Personal administrador) {
		this.administrador = administrador;
	}

	public List<Busqueda> getListaSedes() {
		return listaSedes;
	}

	public void setListaSedes(List<Busqueda> listaSedes) {
		this.listaSedes = listaSedes;
	}

	public AsignarAdministradorServicio getAsignarServicio() {
		return asignarServicio;
	}

	public void setAsignarServicio(AsignarAdministradorServicio asignarServicio) {
		this.asignarServicio = asignarServicio;
	}

	@PostConstruct
	public void inicializar() {
		this.listaSedes = this.asignarServicio.obtenerSedes();
		System.out.println("LLENADO DE COMBO DE SEDES");

	}

	public void botonBuscar() {
		// addMessage("Buscando Información !!");
		System.out.println("BUSQUEDA DE PERSONAL BANNER --->>");

		// this.personal =
		// this.asignarServicio.buscarPersonal(this.datoBuscado);

	}

	public void botonAsignar() {
		// addMessage("Buscando Información !!");
		 System.out.println("ASIGNAR ADMINISTRADOR --->>"
		 + selectSedes[1].toString());

//		System.out.println("ASIGNAR ADMINISTRADOR --->>"
//				+ selectSedes.get(1).getDescripcion());

		// System.out.println("ASIGNAR ADMINISTRADOR --->>");
		// this.selectSedes;
		 this.asignarServicio.guardarAdministrador(this.administrador,this.selectSedes);
		

	}

}

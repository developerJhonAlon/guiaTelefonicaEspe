package beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Busqueda;
import modelo.Personal;
import controlador.AsignarAdministradorServicio;

@ManagedBean
@ViewScoped
public class AsignarAdministrador implements Serializable {

	/**
	 * Esta clase sirva para la logica de negocio de la gestion de un Administrador.
	 */
	private static final long serialVersionUID = 1L;

	private AsignarAdministradorServicio asignarServicio = new AsignarAdministradorServicio();
	private Personal administrador;
	private String textoBusqueda; 
	private List<Personal> personal;
	private List<Busqueda> listaSedes;
	private String[] selectSedes;

	public AsignarAdministrador() {
	}

	

	
	public List<Personal> getPersonal() {
		return personal;
	}




	public void setPersonal(List<Personal> personal) {
		this.personal = personal;
	}




	public String getTextoBusqueda() {
		return textoBusqueda;
	}



	public void setTextoBusqueda(String textoBusqueda) {
		this.textoBusqueda = textoBusqueda;
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

		 this.personal = this.asignarServicio.buscarPersonal(this.textoBusqueda);

	}

	public void botonAsignar() {
		// addMessage("Buscando Información !!");
		 System.out.println("ASIGNAR ADMINISTRADOR --->>"
		 + selectSedes[0].toString());

		 this.asignarServicio.guardarAdministrador(this.administrador,this.selectSedes);
		 addMessage("Guardando Información !!");
		 this.listaSedes.clear();
		 this.listaSedes = this.asignarServicio.obtenerSedes();

	}
	
	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}

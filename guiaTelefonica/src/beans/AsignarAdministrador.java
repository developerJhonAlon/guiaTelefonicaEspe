package beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Busqueda;
import modelo.Personal;
import modelo.VistaAdministradores;
import controlador.AsignarAdministradorServicio;
import controlador.ModificarAdmin;

@ManagedBean
@ViewScoped
public class AsignarAdministrador implements Serializable {

	/**
	 * Esta clase sirva para la logica de negocio de la gestion de un
	 * Administrador.
	 */
	private static final long serialVersionUID = 1L;

	private AsignarAdministradorServicio asignarServicio = new AsignarAdministradorServicio();
	private Personal administrador;
	private String textoBusqueda;
	private List<Personal> personal;
	private List<Busqueda> listaSedes;
	private String[] selectSedes;
	private List<VistaAdministradores> vistaAdmin;
	
	@ManagedProperty(value ="#{adiministradorBean}")
	private AdministradorBean modificarAdmin;


	public AsignarAdministrador() {
	}

	
	public AdministradorBean getModificarAdmin() {
		return modificarAdmin;
	}


	public void setModificarAdmin(AdministradorBean modificarAdmin) {
		this.modificarAdmin = modificarAdmin;
	}


	public List<VistaAdministradores> getVistaAdmin() {
		return vistaAdmin;
	}


	public void setVistaAdmin(List<VistaAdministradores> vistaAdmin) {
		this.vistaAdmin = vistaAdmin;
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
		addMessage("Buscando Información !!");
		System.out.println("BUSQUEDA DE PERSONAL BANNER --->>");

		this.personal = this.asignarServicio.buscarPersonal(this.textoBusqueda);

	}

	public void botonAsignar() {
		addMessage("Guardando Información !!");
		System.out.println("ASIGNAR ADMINISTRADOR --->>"
				+ selectSedes[0].toString());

		this.asignarServicio.guardarAdministrador(this.administrador,
				this.selectSedes);
		
		this.listaSedes.clear();
		this.listaSedes = this.asignarServicio.obtenerSedes();
		
//		modificarAdmin.consultar();
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}

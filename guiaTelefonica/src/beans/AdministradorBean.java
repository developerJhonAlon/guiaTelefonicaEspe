package beans;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Busqueda;
import modelo.Personal;
import modelo.VistaAdministradores;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import controlador.AsignarAdministradorServicio;
import controlador.ModificarAdmin;

@ManagedBean
@ViewScoped
public class AdministradorBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<VistaAdministradores> vistaAdmin;
	private List<Busqueda> vistaUnAdmin;
	private ModificarAdmin modificarAdmin = new ModificarAdmin();
	private AsignarAdministradorServicio asignarServicio = new AsignarAdministradorServicio();
	
	private String valor;
	private boolean desplegarInf = false;
	private VistaAdministradores selectedPersona;
	private List<Busqueda> listaSedes;
	private List<Busqueda> todasSedes;
	private String textoBusqueda;
	private List<Personal> personal;
	
	private Personal administrador;
	
	private String[] selectSedes;

	List<String> todas = new ArrayList<String>();

	public AdministradorBean() {
	}

	@PostConstruct
	public void inicializar() {
		this.listaSedes = this.asignarServicio.obtenerSedes();
		System.out.println("LLENADO DE COMBO DE SEDES");
		System.out.println("OBTENER ADMINISTRADORES --->>");
		this.vistaAdmin = modificarAdmin.obtenerAdmin();

	}

	
	public String getTextoBusqueda() {
		return textoBusqueda;
	}

	public void setTextoBusqueda(String textoBusqueda) {
		this.textoBusqueda = textoBusqueda;
	}

	public List<Personal> getPersonal() {
		return personal;
	}

	public void setPersonal(List<Personal> personal) {
		this.personal = personal;
	}

	public Personal getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Personal administrador) {
		this.administrador = administrador;
	}

	public String[] getSelectSedes() {
		return selectSedes;
	}

	public void setSelectSedes(String[] selectSedes) {
		this.selectSedes = selectSedes;
	}

	public List<String> getTodas() {
		return todas;
	}

	public void setTodas(List<String> todas) {
		this.todas = todas;
	}

	public ModificarAdmin getModificarAdmin() {
		return modificarAdmin;
	}

	public void setModificarAdmin(ModificarAdmin modificarAdmin) {
		this.modificarAdmin = modificarAdmin;
	}

	public AsignarAdministradorServicio getAsignarServicio() {
		return asignarServicio;
	}

	public void setAsignarServicio(AsignarAdministradorServicio asignarServicio) {
		this.asignarServicio = asignarServicio;
	}

	public List<Busqueda> getTodasSedes() {
		return todasSedes;
	}

	public void setTodasSedes(List<Busqueda> todasSedes) {
		this.todasSedes = todasSedes;
	}

	public List<Busqueda> getListaSedes() {
		return listaSedes;
	}

	public void setListaSedes(List<Busqueda> listaSedes) {
		this.listaSedes = listaSedes;
	}

	public VistaAdministradores getSelectedPersona() {
		return selectedPersona;
	}

	public void setSelectedPersona(VistaAdministradores selectedPersona) {
		this.selectedPersona = selectedPersona;
		
	}

	public boolean isDesplegarInf() {
		return desplegarInf;
	}

	public void setDesplegarInf(boolean desplegarInf) {
		this.desplegarInf = desplegarInf;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}


	public void consultarUno() {
		System.out.println("OBTENER UN ADMINISTRADOR --->>");
		this.vistaUnAdmin = modificarAdmin.obtenerUnAdmin(this.valor);
	}

	public List<VistaAdministradores> getVistaAdmin() {
		return vistaAdmin;
	}

	public void setVistaAdmin(List<VistaAdministradores> vistaAdmin) {
		this.vistaAdmin = vistaAdmin;
	}

	public List<Busqueda> getVistaUnAdmin() {
		return vistaUnAdmin;
	}

	public void setVistaUnAdmin(List<Busqueda> vistaUnAdmin) {
		this.vistaUnAdmin = vistaUnAdmin;
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void onRowSelect(SelectEvent event) {

		System.out.println("Seleccion Row ");

		this.desplegarInf = true;
		this.todas.clear();

		this.todasSedes = this.asignarServicio.obtenerSedes();

		this.vistaUnAdmin = this.modificarAdmin
				.obtenerUnAdmin(this.selectedPersona.getId_persona());

		for (Busqueda busqueda : vistaUnAdmin) {
			this.todas.add(busqueda.getValor());
		}
	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Seleccion Row ");
		this.desplegarInf = false;
		this.todas.clear();
	}

	public void Modificar() {
		modificarAdmin.modificar(this.selectedPersona, todas);
		addMessage("Información Modificada...");
	}

	public void Eliminar() {
		modificarAdmin.eliminar(this.selectedPersona);
		addMessage("Información Eliminada...");
		this.vistaAdmin.clear();
		this.vistaAdmin = modificarAdmin.obtenerAdmin();
	}
	
	public void botonAsignar() {
		System.out.println("ASIGNAR ADMINISTRADOR --->>");

		if(this.asignarServicio.guardarAdministrador(this.administrador,
				this.selectSedes)==true)
		{
			if(this.selectSedes.length!=0)
			{
				addMessage("Información Guardada !!");
				this.listaSedes.clear();
				this.listaSedes = this.asignarServicio.obtenerSedes();
				this.vistaAdmin = modificarAdmin.obtenerAdmin();
				
				
			}else addMessage("Debe seleccionar al menos una Sede !!");
			
			
		}else if (this.asignarServicio.guardarAdministrador(this.administrador,
				this.selectSedes)==false)
		{
			addMessage("Ya existe administrador !!");
		}

	}
	
	public void botonBuscar() {
		addMessage("Buscando Información !!");
		System.out.println("BUSQUEDA DE PERSONAL BANNER --->>");

		this.personal = this.asignarServicio.buscarPersonal(this.textoBusqueda);

	}
	
}
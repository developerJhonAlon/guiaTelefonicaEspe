package beans;

import java.io.Serializable;
import java.util.ArrayList;
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

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import controlador.AsignarAdministradorServicio;
import controlador.ModificarAdmin;
import controlador.LoginServicio;

@ManagedBean
@ViewScoped
public class AdministradorBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<VistaAdministradores> vistaAdmin;
	private List<Busqueda> vistaUnAdmin;
	private ModificarAdmin modificarAdmin = new ModificarAdmin();
	private AsignarAdministradorServicio asignarServicio = new AsignarAdministradorServicio();
	private String nombreAdministrador = "";
	private String valor;
	private boolean desplegarInf = false;
	private VistaAdministradores selectedPersona;
	private VistaAdministradores adminModal;
	private List<Busqueda> listaSedes;
	private List<Busqueda> todasSedes;
	private String textoBusqueda;
	private List<Personal> personal;
	private String administrado;
	private LoginServicio loginServicio = new LoginServicio();
	private String master;

	private Personal administrador;
	private Personal administradorModal;

	private String[] selectSedes;
	List<String> todas = new ArrayList<String>();

	public AdministradorBean() {
	}

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	public String getAdministrado() {
		return administrado;
	}

	public void setAdministrado(String administrado) {
		this.administrado = administrado;
	}

	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	public VistaAdministradores getAdminModal() {
		return adminModal;
	}

	public void setAdminModal(VistaAdministradores adminModal) {
		this.adminModal = adminModal;
	}

	public Personal getAdministradorModal() {
		return administradorModal;
	}

	public void setAdministradorModal(Personal administradorModal) {
		this.administradorModal = administradorModal;
	}

	public LoginServicio getLoginServicio() {
		return loginServicio;
	}

	public void setLoginServicio(LoginServicio loginServicio) {
		this.loginServicio = loginServicio;
	}

	public String getNombreAdministrador() {
		return nombreAdministrador;
	}

	public void setNombreAdministrador(String nombreAdministrador) {
		this.nombreAdministrador = nombreAdministrador;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
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

	public void onRowSelect2(SelectEvent event) {
		this.administradorModal = this.administrador;
		this.todas.clear();
	}

	public void onRowUnselect2(UnselectEvent event) {
		this.administradorModal = null;
		this.todas.clear();
	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("Seleccion Row ");
		this.adminModal = this.selectedPersona;
		this.todas.clear();
		this.vistaUnAdmin = this.modificarAdmin
				.obtenerUnAdmin(this.selectedPersona.getId_persona());
		for (Busqueda busqueda : vistaUnAdmin) {
			this.todas.add(busqueda.getValor());
		}
	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Seleccion Row ");
		this.todas.clear();
	}

	public void Modificar() {
		modificarAdmin.modificar(this.selectedPersona, todas);
		RequestContext context = RequestContext.getCurrentInstance();
		addMessage("Registro Modificado...");

		context.execute("PF('dlg3').hide()");

	}

	public void Eliminar() {
		modificarAdmin.eliminar(this.selectedPersona);
		addMessage("Registro Eliminado...");
		this.vistaAdmin = modificarAdmin.obtenerAdmin();
	}

	public void botonAsignar() {

		System.out.println("ASIGNAR ADMINISTRADOR --->>");
		if (this.asignarServicio.guardarAdministrador(this.administrador,
				this.todas) == true) {
			RequestContext context = RequestContext.getCurrentInstance();
			addMessage("Registro Guardado !!");
			// Actualizar la Lista de Administradores.
			this.vistaAdmin = modificarAdmin.obtenerAdmin();
			context.execute("PF('dlg2').hide()");
			this.todas.clear();

		} else if (this.asignarServicio.guardarAdministrador(
				this.administrador, this.todas) == false) {
			addMessage("Registro ya existente !!");
		}
	}

	public void cerrar() {
		System.out.println("Seleccion Row Not");

	}

	public void botonAgregar() {
		System.out.println("Seleccion Row Not");
		this.todas.clear();
	}

	public void botonEditar() {
		System.out.println("Seleccion Row Not");
		this.todas.clear();
		this.vistaUnAdmin = this.modificarAdmin
				.obtenerUnAdmin(this.selectedPersona.getId_persona());
		for (Busqueda busqueda : vistaUnAdmin) {
			this.todas.add(busqueda.getValor());
		}
	}

	public void botonBuscar() {
		addMessage("Buscando Información !!");
		System.out.println("BUSQUEDA DE PERSONAL BANNER --->>");
		this.personal = this.asignarServicio.buscarPersonal(this.textoBusqueda);
	}

	@PostConstruct
	public void inicializar() {

		if (this.loginBean.getIdentificadorMaster().equals("")) {
			System.out.println("ERROR DE LOGIN");
		} else {
			// this.administrado =
			// loginServicio.obtenerAdminMaster(this.loginBean.getIdentificadorMaster());
			// System.out.println("MASTER:"+administrado);
			this.listaSedes = this.asignarServicio.obtenerSedes();
			System.out.println("LLENADO DE COMBO DE SEDES");
			System.out.println("OBTENER ADMINISTRADORES --->>");
			this.vistaAdmin = modificarAdmin.obtenerAdmin();
			this.master = loginServicio.nombreMaster(loginBean
					.getIdentificadorMaster());
		}
	}

}
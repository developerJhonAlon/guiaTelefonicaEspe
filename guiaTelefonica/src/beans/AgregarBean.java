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

import modelo.Administrador;
import modelo.Busqueda;
import modelo.Personal;
import modelo.VistaBusqueda;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import controlador.AgregarServicio;
import controlador.AsignarAdministradorServicio;
import controlador.BusquedaServicio;
import controlador.LoginServicio;

@ManagedBean
@ViewScoped
public class AgregarBean implements Serializable {

	/**
	 * Esta clase sirve para agregar nuevas extensiones
	 */
	private static final long serialVersionUID = 1L;

	private AgregarServicio admiSedeServicio = new AgregarServicio();
	private LoginServicio loginServicio = new LoginServicio();
	private BusquedaServicio busquedaServicio = new BusquedaServicio();
	private AsignarAdministradorServicio asigAdminServicio = new AsignarAdministradorServicio();
	private List<VistaBusqueda> listaUnidadExtension;
	private VistaBusqueda extensionSelectModal;
	private VistaBusqueda unidadExtensionSelect;
	private List<Administrador> administrado;
	private String nombreAdministrador = "";
	private List<Busqueda> listaUnidades;
	private List<Busqueda> listaUnidadesGuadar;
	private List<Busqueda> listaUnidadesGuadar2;
	private List<Busqueda> listaSedeExtension2;
	private String unidadSeleccionada;
	private String unidadSeleccionadaGuardar;
	private String sedeSeleccionada;
	private String sedeSeleccionadaGuardar;

	private List<Personal> personal;
	private Personal selectPersonal;
	private String textoBuscado;
	private String telefono;
	private String extension;

	public AgregarBean() {
	}

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	
	
	
	public List<Busqueda> getListaSedeExtension2() {
		return listaSedeExtension2;
	}

	public void setListaSedeExtension2(List<Busqueda> listaSedeExtension2) {
		this.listaSedeExtension2 = listaSedeExtension2;
	}

	public VistaBusqueda getExtensionSelectModal() {
		return extensionSelectModal;
	}

	public void setExtensionSelectModal(VistaBusqueda extensionSelectModal) {
		this.extensionSelectModal = extensionSelectModal;
	}

	public AsignarAdministradorServicio getAsigAdminServicio() {
		return asigAdminServicio;
	}

	public void setAsigAdminServicio(
			AsignarAdministradorServicio asigAdminServicio) {
		this.asigAdminServicio = asigAdminServicio;
	}

	public List<Busqueda> getListaUnidadesGuadar2() {
		return listaUnidadesGuadar2;
	}

	public void setListaUnidadesGuadar2(List<Busqueda> listaUnidadesGuadar2) {
		this.listaUnidadesGuadar2 = listaUnidadesGuadar2;
	}

	public VistaBusqueda getUnidadExtensionSelect() {
		return unidadExtensionSelect;
	}

	public String getUnidadSeleccionadaGuardar() {
		return unidadSeleccionadaGuardar;
	}

	public void setUnidadSeleccionadaGuardar(String unidadSeleccionadaGuardar) {
		this.unidadSeleccionadaGuardar = unidadSeleccionadaGuardar;
	}

	public List<Busqueda> getListaUnidadesGuadar() {
		return listaUnidadesGuadar;
	}

	public void setListaUnidadesGuadar(List<Busqueda> listaUnidadesGuadar) {
		this.listaUnidadesGuadar = listaUnidadesGuadar;
	}

	public void setUnidadExtensionSelect(VistaBusqueda unidadExtensionSelect) {
		this.unidadExtensionSelect = unidadExtensionSelect;
	}

	public String getSedeSeleccionadaGuardar() {
		return sedeSeleccionadaGuardar;
	}

	public void setSedeSeleccionadaGuardar(String sedeSeleccionadaGuardar) {
		this.sedeSeleccionadaGuardar = sedeSeleccionadaGuardar;
	}

	public List<VistaBusqueda> getListaUnidadExtension() {
		return listaUnidadExtension;
	}

	public void setListaUnidadExtension(List<VistaBusqueda> listaUnidadExtension) {
		this.listaUnidadExtension = listaUnidadExtension;
	}

	public String getUnidadSeleccionada() {
		return unidadSeleccionada;
	}

	public void setUnidadSeleccionada(String unidadSeleccionada) {
		this.unidadSeleccionada = unidadSeleccionada;
	}

	public String getSedeSeleccionada() {
		return sedeSeleccionada;
	}

	public void setSedeSeleccionada(String sedeSeleccionada) {
		this.sedeSeleccionada = sedeSeleccionada;
	}

	public List<Busqueda> getListaUnidades() {
		return listaUnidades;
	}

	public void setListaUnidades(List<Busqueda> listaUnidades) {
		this.listaUnidades = listaUnidades;
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

	public List<Administrador> getAdministrado() {
		return administrado;
	}

	public void setAdministrado(List<Administrador> administrado) {
		this.administrado = administrado;
	}

	public BusquedaServicio getBusquedaServicio() {
		return busquedaServicio;
	}

	public void setBusquedaServicio(BusquedaServicio busquedaServicio) {
		this.busquedaServicio = busquedaServicio;
	}

	public AgregarServicio getAdmiSedeServicio() {
		return admiSedeServicio;
	}

	public void setAdmiSedeServicio(AgregarServicio admiSedeServicio) {
		this.admiSedeServicio = admiSedeServicio;
	}

	public List<Personal> getPersonal() {
		return personal;
	}

	public void setPersonal(List<Personal> personal) {
		this.personal = personal;
	}

	public Personal getSelectPersonal() {
		return selectPersonal;
	}

	public void setSelectPersonal(Personal selectPersonal) {
		this.selectPersonal = selectPersonal;
	}

	public String getTextoBuscado() {
		return textoBuscado;
	}

	public void setTextoBuscado(String textoBuscado) {
		this.textoBuscado = textoBuscado;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	/* *
	 * Obtener el ID del login y obtener todos los datos del admin, para la
	 * interfaz y las consultas.
	 */
	@PostConstruct
	public void inicializar() {
		System.out.println("ID ADMINISTRADOR: " + loginBean.getIdentificador());
		if (this.loginBean.getIdentificador().equals("")) {
			System.out.println("ERROR DE LOGIN");
		} else {
			this.administrado = loginServicio
					.obtenerAdminConSedes(this.loginBean.getIdentificador());
			this.nombreAdministrador = this.administrado.get(0).getNombAdmin();

		}

	}

	public void botonBusqueda() {
		addMessage("Busqueda Realizada !!");
		System.out.println("BUSQUEDA DE PERSONAL BANNER --->>");

		// List<String> sedesCodigos = new ArrayList<String>();
		// for (Administrador sedecodigo : administrado) {
		// sedesCodigos.add(sedecodigo.getCodigoSede());
		// }
		//
		// this.personal =
		// this.admiSedeServicio.buscarPersonal(this.textoBuscado,
		// sedesCodigos);

		this.personal = this.asigAdminServicio
				.buscarPersonal(this.textoBuscado);

	}

	public void botonGuardar() {
		System.out.println("GUADAR EXTENSION --->>");
		String nombreSede = "";

		for (Administrador administrador : administrado) {
			if (administrador.getCodigoSede().equals(
					this.selectPersonal.getSedeCode())) {
				nombreSede = administrador.getNombSede();
				break;
			}

		}

		boolean confirmado = this.admiSedeServicio.guardarInformacion(
				this.selectPersonal, this.selectPersonal.getSedeCode(),
				nombreSede, this.selectPersonal.getUnidad(), this.telefono,
				this.extension);

		// Actualizacion de Lista en de combo de las Unidades, en la vista
		// presente.
		this.listaUnidades = this.busquedaServicio
				.obtenerUnidades(this.sedeSeleccionada);
		// Actualizacion de Tabla de las Personas con Extensiones, en la vista
		// presente.
		this.listaUnidadExtension = admiSedeServicio.obtenerUnidadConExtension(
				this.sedeSeleccionada, this.unidadSeleccionada);

		String mensaje = (confirmado) ? "Informacion Guardada !!"
				: "Este Registro ya Existe !!";

		addMessage(mensaje);

	}

	public void botonEditar() {
		System.out.println("GUADAR EXTENSION --->>");
		String nombreSede = "";
		List<VistaBusqueda> auxiliar = null;
		String mensaje="";
		
		for (Administrador administrador : administrado) {
			if (administrador.getCodigoSede().equals(
					this.unidadExtensionSelect.getSedeCodigo())) {
				nombreSede = administrador.getNombSede();
				break;
			}

		}

		
		 auxiliar = this.admiSedeServicio.editarInformacion(
				this.unidadExtensionSelect,
				this.unidadExtensionSelect.getSedeCodigo(),nombreSede,
				this.unidadExtensionSelect.getUnidadNomb(),this.unidadExtensionSelect.getTelefonoNomb(),this.unidadExtensionSelect.getExtensionNomb());

		 
		
		if(auxiliar.size() < 0){
			mensaje = "Este Registro ya Existe !!";
		}else{
			this.listaUnidadExtension = auxiliar;
			mensaje = "Información Modificada !!";
		}
		
		addMessage(mensaje);

	}

	public void actualizarUnidades() {
		this.listaUnidades = this.busquedaServicio
				.obtenerUnidades(this.sedeSeleccionada);
	}

	public void actualizarUnidadesGuardar() {
		this.listaUnidadesGuadar = this.admiSedeServicio
				.obtenerUnidadesPorSede(this.selectPersonal.getSedeCode());
	}

	public void actualizarUnidadesGuardar2() {
		this.listaUnidadesGuadar2 = this.admiSedeServicio
				.obtenerUnidadesPorSedeEditar(this.extensionSelectModal
						.getSedeCodigo());
	}

	public void actualizarExtensiones() {
		this.listaUnidadExtension = admiSedeServicio.obtenerUnidadConExtension(
				this.sedeSeleccionada, this.unidadSeleccionada);
	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("Seleccion Row ");
		this.listaUnidadesGuadar = this.admiSedeServicio
				.obtenerUnidadesPorSede(this.selectPersonal.getSedeCode());

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Seleccion Row ");

	}

	public void onRowSelect2(SelectEvent event) {
		System.out.println("Seleccion Row ");
		
	 
		this.extensionSelectModal = this.unidadExtensionSelect;
			
		List<Busqueda> auxiliar = new ArrayList<Busqueda>();
		for (Administrador administrador : this.administrado) {
			auxiliar.add(new Busqueda(administrador.getCodigoSede(),administrador.getNombSede()));
		}
		
			
		this.listaSedeExtension2 = auxiliar;
			
		
		this.listaUnidadesGuadar2 = this.admiSedeServicio
				.obtenerUnidadesPorSedeEditar(this.extensionSelectModal
						.getSedeCodigo());
	
		

	}

	public void onRowUnselect2(UnselectEvent event) {
		System.out.println("Seleccion Row ");
		this.listaUnidadesGuadar2 = this.admiSedeServicio
				.obtenerUnidadesPorSedeEditar(this.unidadExtensionSelect
						.getSedeCodigo());

	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}

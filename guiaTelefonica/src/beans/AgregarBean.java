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
import controlador.AgregarServicio;
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
	private List<VistaBusqueda> listaUnidadExtension;
	private VistaBusqueda unidadExtensionSelect;
	private List<Administrador> administrado;
	private String nombreAdministrador = "";
	private List<Busqueda> listaUnidades;
	private String unidadSeleccionada;
	private String sedeSeleccionada;
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

	public VistaBusqueda getUnidadExtensionSelect() {
		return unidadExtensionSelect;
	}

	public void setUnidadExtensionSelect(VistaBusqueda unidadExtensionSelect) {
		this.unidadExtensionSelect = unidadExtensionSelect;
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

	public void botonAction() {
		addMessage("Busqueda Realizada !!");
		System.out.println("BUSQUEDA DE PERSONAL BANNER --->>");
		List<String> sedesCodigos = new ArrayList<String>();
		for (Administrador sedecodigo : administrado) {
			sedesCodigos.add(sedecodigo.getCodigoSede());
		}

		this.personal = this.admiSedeServicio.buscarPersonal(this.textoBuscado,
				sedesCodigos);

	}

	public void botonGuardar() {
		System.out.println("GUADAR EXTENSION --->>");
		this.admiSedeServicio.guardarInformacion(this.selectPersonal,
				this.telefono, this.extension);
		this.listaUnidades = this.busquedaServicio.obtenerUnidades(this.sedeSeleccionada);
		this.listaUnidadExtension = admiSedeServicio.obtenerUnidadConExtension(this.sedeSeleccionada, this.unidadSeleccionada);
		
		addMessage("Información Guardada !!");
		

	}

	public void actualizarUnidades() {
		this.listaUnidades = this.busquedaServicio
				.obtenerUnidades(this.sedeSeleccionada);
	}

	public void actualizarExtensiones(){
		this.listaUnidadExtension = admiSedeServicio.obtenerUnidadConExtension(this.sedeSeleccionada, this.unidadSeleccionada);
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}

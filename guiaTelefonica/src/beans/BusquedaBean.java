package beans;


import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Busqueda;
import modelo.VistaBusqueda;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import controlador.BusquedaServicio;

@ManagedBean
@ViewScoped
public class BusquedaBean implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* *
	 * En esta variable invoca a logica de negocio.
	 */
	private BusquedaServicio busquedaServicio = new BusquedaServicio();

	/* *
	 * En esta variable se enlista los criterios de busqueda.
	 */
	private List<Busqueda> busqueda;

	/* *
	 * En esta variable se obtiene la lista de la busqueda realizada.
	 */
	private List<VistaBusqueda> vistaBusqueda;

	/* *
	 * En esta variable se almacena el codigo del criterio de busqueda.
	 */
	private String valorBusqueda;

	/* *
	 * En esta variable se almacena la informacion capturada para la busqueda.
	 */
	private String textoBuscado;

	/* *
	 * En esta variable se almacena el Objecto de la fila seleccionada.
	 */
	private VistaBusqueda selectedPersona;
	
	/* *
	 * En esta variable se verifica la visualizacion de las Sedes.
	 */
	private Boolean desplegarSedes = false;
	
	/* *
	 * En esta variable visualiza departamentos.
	 */
	private Boolean desplegarDepart = false;
	
	/* *
	 * En esta variable permite visualizar la información seleccionada.
	 */
	private Boolean desplegarInf= false;
	
	private List<Busqueda> listaSedes;
	
	private List<Busqueda> listaUnida;
	
	private String sedeSelecciona = "";
	
	private String unidadSelecciona = "";
	
	private Boolean verNombres=false;
	private Boolean verTelefono= false;
	private Boolean verExtension=false;
	private String verMensaj="";
	
	public BusquedaBean(){}
		
	
	public Boolean getVerNombres() {
		return verNombres;
	}


	public void setVerNombres(Boolean verNombres) {
		this.verNombres = verNombres;
	}


	public Boolean getVerTelefono() {
		return verTelefono;
	}


	public void setVerTelefono(Boolean verTelefono) {
		this.verTelefono = verTelefono;
	}


	public Boolean getVerExtension() {
		return verExtension;
	}


	public void setVerExtension(Boolean verExtension) {
		this.verExtension = verExtension;
	}


	public String getVerMensaj() {
		return verMensaj;
	}


	public void setVerMensaj(String verMensaj) {
		this.verMensaj = verMensaj;
	}


	public List<Busqueda> getListaUnida() {
		return listaUnida;
	}




	public Boolean getDesplegarInf() {
		return desplegarInf;
	}


	public void setDesplegarInf(Boolean desplegarInf) {
		this.desplegarInf = desplegarInf;
	}


	public void setListaUnida(List<Busqueda> listaUnida) {
		this.listaUnida = listaUnida;
	}




	public String getUnidadSelecciona() {
		return unidadSelecciona;
	}




	public void setUnidadSelecciona(String unidadSelecciona) {
		this.unidadSelecciona = unidadSelecciona;
	}




	public List<Busqueda> getListaSedes() {
		return listaSedes;
	}


	

	public void setListaSedes(List<Busqueda> listaSedes) {
		this.listaSedes = listaSedes;
	}


	public String getSedeSelecciona() {
		return sedeSelecciona;
	}


	public void setSedeSelecciona(String sedeSelecciona) {
		this.sedeSelecciona = sedeSelecciona;
	}


	public Boolean getDesplegarSedes() {
		return desplegarSedes;
	}


	public void setDesplegarSedes(Boolean desplegarSedes) {
		this.desplegarSedes = desplegarSedes;
	}


	public BusquedaServicio getBusquedaServicio() {
		return busquedaServicio;
	}

	public void setBusquedaServicio(BusquedaServicio busquedaServicio) {
		this.busquedaServicio = busquedaServicio;
	}

	public List<Busqueda> getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(List<Busqueda> busqueda) {
		this.busqueda = busqueda;
	}

	public String getValorBusqueda() {
		return valorBusqueda;
	}

	public void setValorBusqueda(String valorBusqueda) {
		this.valorBusqueda = valorBusqueda;
	}

	public String getTextoBuscado() {
		return textoBuscado;
	}

	public void setTextoBuscado(String textoBuscado) {
		this.textoBuscado = textoBuscado;
	}

	public List<VistaBusqueda> getVistaBusqueda() {
		return vistaBusqueda;
	}

	public void setVistaBusqueda(List<VistaBusqueda> vistaBusqueda) {
		this.vistaBusqueda = vistaBusqueda;
	}

	
	
	public VistaBusqueda getSelectedPersona() {
		return selectedPersona;
	}


	public void setSelectedPersona(VistaBusqueda selectedPersona) {
		this.selectedPersona = selectedPersona;
	}	
	

	public Boolean getDesplegarDepart() {
		return desplegarDepart;
	}



	public void setDesplegarDepart(Boolean desplegarDepart) {
		this.desplegarDepart = desplegarDepart;
	}


	@PostConstruct
	public void inicializar() {
		this.busqueda = this.busquedaServicio.obtenerCriterios();
		System.out.println("LLENADO DE COMBO Y TABLAS");

	}
	
	
	
	/* *
	 * Metodo para realizar la actualizacion y la busqueda.
	 */
	public void buttonAction() {
		addMessage("Busqueda Realizada !!");
		System.out.println("OBTENER EXTENSIONES --->>");
		this.vistaBusqueda = busquedaServicio.buscarExtensiones(this.valorBusqueda, this.textoBuscado, this.sedeSelecciona, this.unidadSelecciona);

	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	
	public void onRowSelect(SelectEvent event) {
		System.out.println("Seleccion Row ");
		this.desplegarInf = true;

        FacesMessage msg = new FacesMessage("Información Selecionada", ((VistaBusqueda) event.getObject()).getIdPersonal());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public void onRowUnselect(UnselectEvent event) {
    	System.out.println("Seleccion Row ");
    	this.desplegarInf = false;
        FacesMessage msg = new FacesMessage("Información Selecionada", ((VistaBusqueda) event.getObject()).getIdPersonal());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
    
    public void verSedes(){
    	System.out.println("Visualizar Sedes");	
    	if(this.valorBusqueda.equals("2"))
    	{
    		verNombres=true;
    		verTelefono= false;
    		verExtension= false;
    		this.verMensaj="Campo incorrecto";
    		this.vistaBusqueda=null;
    		desplegarInf= false;
    		this.textoBuscado="";
    	}
    	if(this.valorBusqueda.equals("3"))
    	{
    		verNombres=false;
    		verTelefono= true;
    		verExtension= false;
    		this.verMensaj="Número incorrecto ..... Ejm: 02-2348741"; 
    		this.vistaBusqueda=null;
    		desplegarInf= false;
    		this.textoBuscado="";
       	}
    	if(this.valorBusqueda.equals("4"))
    	{
    		verNombres=false;
    		verTelefono= false;
    		verExtension= true;
    		this.verMensaj= "Número incorrecto ..... Ejm: 1234";	
    		this.vistaBusqueda=null;
    		desplegarInf= false;
    		this.textoBuscado="";
    	}
    	
    	if(this.valorBusqueda.equals("5")){
    		this.listaSedes = this.busquedaServicio.obtenerSedes();
    		this.desplegarSedes = true;   		
    		verNombres=true;
    		verTelefono= false;
    		verExtension= false;
    		this.verMensaj="Ingrese solo letras";
    		this.vistaBusqueda=null;
    		desplegarInf= false;
    		this.textoBuscado="";
    	}else{
    		this.desplegarSedes = false;
    		this.vistaBusqueda=null;
    		desplegarInf= false;
    		this.textoBuscado="";
    	}
    
    }
    
    /* *
	 * Metodo para realizar la busqueda de departamentos en relacion a la Sede.
	 */
    public void verDepartamento(){
        this.listaUnida = this.busquedaServicio.obtenerUnidades(this.sedeSelecciona);
    	System.out.println("Visualizar Departamento");
    	
    	
    }
}

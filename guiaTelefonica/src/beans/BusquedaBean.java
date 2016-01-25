package beans;


import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.RegexValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

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
	
//	private String campoValidador = "";
	private Boolean desplegarEntrada = false;
	private String verMensaj = "";
	private String msgInformativo  = "";
	private int campoValidador;
	private Pattern pattern;
	private RegexValidator regexValidator;
	
	public BusquedaBean(){}
	
	



	public int getCampoValidador() {
		return campoValidador;
	}





	public void setCampoValidador(int campoValidador) {
		this.campoValidador = campoValidador;
	}





	public RegexValidator getRegexValidator() {
		return regexValidator;
	}





	public void setRegexValidator(RegexValidator regexValidator) {
		this.regexValidator = regexValidator;
	}





	public Pattern getPattern() {
		return pattern;
	}





	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}





	public String getMsgInformativo() {
		return msgInformativo;
	}


	public void setMsgInformativo(String msgInformativo) {
		this.msgInformativo = msgInformativo;
	}



	public Boolean getDesplegarEntrada() {
		return desplegarEntrada;
	}


	public void setDesplegarEntrada(Boolean desplegarEntrada) {
		this.desplegarEntrada = desplegarEntrada;
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
		
		Pattern pattern2;
		Matcher  matcher;
		if(this.campoValidador == 2){
			pattern2 = Pattern.compile("[^0-9]*");
			matcher = pattern2.matcher(this.textoBuscado);
	    	
	    	if(matcher.matches()){
	    		obtenerDatos();

	     	
	    	}else{
	       		addMessage("Error: Ingrese solo letras");
	       		
	    		
	    	}
		}else if(this.campoValidador == 3){
			pattern2 = Pattern.compile("[0-9]{2}+-[0-9]{7}");
	    	matcher = pattern2.matcher(this.textoBuscado);
	    	
	    	if(matcher.matches()){
	    		obtenerDatos();

	     	
	    	}else{
	       		addMessage("Error: Ejm: 02-2348741");
	       		
	    		
	    	}
		}else if(this.campoValidador == 4){
			pattern2 = Pattern.compile("[0-9]{3,4}");
	    	matcher = pattern2.matcher(this.textoBuscado);
	    	
	    	if(matcher.matches()){
	    		obtenerDatos();

	     	
	    	}else{
	       		addMessage("Error: Ejm: 1234");
	       		
	    		
	    	}
		}
		


	}



	private void obtenerDatos() {
		addMessage("Busqueda Realizada !!");
		System.out.println("OBTENER EXTENSIONES --->>");
		this.vistaBusqueda = busquedaServicio.buscarExtensiones(this.valorBusqueda, this.textoBuscado, this.sedeSelecciona, this.unidadSelecciona);
		this.desplegarInf = false;
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
    		this.textoBuscado = "";
    		this.msgInformativo = "Ingrese el nombre";
    		this.desplegarEntrada = true;
    		this.desplegarSedes = false;
    		this.desplegarDepart = false;
    		this.campoValidador = 2;
    		this.vistaBusqueda = null;
    		this.desplegarInf = false;
    		
    	}
    	else if(this.valorBusqueda.equals("3"))
    	{
    		this.textoBuscado = "";
    		this.msgInformativo = "Ingrese el telefono";
    		this.campoValidador = 3;
    		this.desplegarEntrada = true;
    		this.desplegarSedes = false;
    		this.desplegarDepart = false;
    		this.vistaBusqueda = null;
    		this.desplegarInf = false;
    		
       	}
    	else if(this.valorBusqueda.equals("4"))
    	{
    		this.textoBuscado = "";
    		this.msgInformativo = "Ingrese la extensión";
    		this.campoValidador = 4;
    		this.desplegarEntrada = true;
    		this.desplegarSedes = false;
    		this.desplegarDepart = false;
    		this.vistaBusqueda = null;
    		this.desplegarInf = false;
    		
    	}
    	
    	else if(this.valorBusqueda.equals("5")){
    		
    		this.desplegarEntrada = false;
    		this.campoValidador = 2;
    		this.listaSedes = this.busquedaServicio.obtenerSedes();
    		this.desplegarSedes = true;   		
    		this.msgInformativo = "Ingrese el nombre";
    		this.vistaBusqueda = null;
    		this.desplegarInf = false;
    		this.desplegarDepart = false;
    		this.textoBuscado = "";
    		
    	}else{
    		
    		this.desplegarEntrada = false;
    		this.desplegarSedes = false;
    		this.vistaBusqueda = null;
    		this.desplegarInf = false;
    		this.desplegarDepart = false;
   
  
    	}
    
    }
    
//    public void validar(FacesContext context, UIComponent component, Object value) {
//    	context = FacesContext.getCurrentInstance();
//    	String texto = (String)value;
//    
//    	Pattern pattern = Pattern.compile("[^0-9]*");
//    	Matcher  matcher = pattern.matcher((String)value);
//    	
//    	if(!matcher.matches()){
//    		addMessage("Error Validador !!");
//    	}
////    	RegexValidator regexValidator = new RegexValidator();
////   		regexValidator.setPattern(this.campoValidador);
//// 		regexValidator.validate(context, component, value);
//    	
//    }
	
    
    /* *
	 * Metodo para realizar la busqueda de departamentos en relacion a la Sede.
	 */
    public void verDepartamento(){
    	this.desplegarDepart = true;
    	this.desplegarEntrada = false;
        this.listaUnida = this.busquedaServicio.obtenerUnidades(this.sedeSelecciona);
    	System.out.println("Visualizar Departamento");
    	
    	
    }
    
    public void verUnidadSede(){
    	this.desplegarEntrada = true;
    	
    	
    }
}

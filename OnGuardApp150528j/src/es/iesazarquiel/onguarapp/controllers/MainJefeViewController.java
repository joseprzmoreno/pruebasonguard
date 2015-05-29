package es.iesazarquiel.onguarapp.controllers;


import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import es.iesazarquiel.onguarapp.models.dao.DaoDocente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;

public class MainJefeViewController {
	
	//dice al boton OK lo que tiene que hacer
	private boolean activarVerHorarios = false;
	private boolean activarComunicarFaltas = false;
	private boolean activarVerFaltas = false;
	private boolean activarVerGuardias = false;
	private boolean activarAsignarGuardias = false;
	private boolean activarFirmarGuardia = false;
    private boolean activarGenerarInformeGuardias = false;
    private boolean activarGenerarInformeFaltas = false;
    

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Accordion menu;

    @FXML
    private TitledPane horarios;

    @FXML
    private Button verHorarios;

    @FXML
    private TitledPane faltas;

    @FXML
    private Button comunicarFalta;

    @FXML
    private Button verFaltas;

    @FXML
    private TitledPane guardias;

    @FXML
    private Button firmarGuardia;

    @FXML
    private Button verGuardias;

    @FXML
    private Button asignarGuardias;

    @FXML
    private TitledPane informes;

    @FXML
    private Button informeGuadias;

    @FXML
    private Button informeFaltas;

    @FXML
    private DatePicker fecha1;

    @FXML
    private DatePicker fecha2;

    @FXML
    private Button botonok;

    @FXML
    private ComboBox<?> listaFaltas;

    @FXML
    private ComboBox<?> listaHoras;

    @FXML
    private ComboBox<?> listaProfes;

    @FXML
    private Pane ContenedorSuperior;

    @FXML
    void initialize() throws SQLException {
        assert menu != null : "fx:id=\"menu\" was not injected: check your FXML file 'MainJefeView.fxml'.";
        assert horarios != null : "fx:id=\"horarios\" was not injected: check your FXML file 'MainJefeView.fxml'.";
        assert verHorarios != null : "fx:id=\"verHorarios\" was not injected: check your FXML file 'MainJefeView.fxml'.";
        assert faltas != null : "fx:id=\"faltas\" was not injected: check your FXML file 'MainJefeView.fxml'.";
        assert comunicarFalta != null : "fx:id=\"comunicarFalta\" was not injected: check your FXML file 'MainJefeView.fxml'.";
        assert verFaltas != null : "fx:id=\"verFaltas\" was not injected: check your FXML file 'MainJefeView.fxml'.";
        assert guardias != null : "fx:id=\"guardias\" was not injected: check your FXML file 'MainJefeView.fxml'.";
        assert firmarGuardia != null : "fx:id=\"firmarGuardia\" was not injected: check your FXML file 'MainJefeView.fxml'.";
        assert verGuardias != null : "fx:id=\"verGuardias\" was not injected: check your FXML file 'MainJefeView.fxml'.";
        assert asignarGuardias != null : "fx:id=\"asignarGuardias\" was not injected: check your FXML file 'MainJefeView.fxml'.";
        assert informes != null : "fx:id=\"informes\" was not injected: check your FXML file 'MainJefeView.fxml'.";
        assert informeGuadias != null : "fx:id=\"informeGuadias\" was not injected: check your FXML file 'MainJefeView.fxml'.";
        assert informeFaltas != null : "fx:id=\"informeFaltas\" was not injected: check your FXML file 'MainJefeView.fxml'.";
        assert fecha1 != null : "fx:id=\"fecha1\" was not injected: check your FXML file 'MainJefeView.fxml'.";
        assert fecha2 != null : "fx:id=\"fecha2\" was not injected: check your FXML file 'MainJefeView.fxml'.";
        assert botonok != null : "fx:id=\"botonok\" was not injected: check your FXML file 'MainJefeView.fxml'.";
        assert listaFaltas != null : "fx:id=\"listaFaltas\" was not injected: check your FXML file 'MainJefeView.fxml'.";
        assert listaHoras != null : "fx:id=\"listaHoras\" was not injected: check your FXML file 'MainJefeView.fxml'.";
        assert listaProfes != null : "fx:id=\"listaProfes\" was not injected: check your FXML file 'MainJefeView.fxml'.";
        assert ContenedorSuperior != null : "fx:id=\"ContenedorSuperior\" was not injected: check your FXML file 'MainJefeView.fxml'.";
        
        /*Añadir los nombres de los profesores a la combobox*/
        DaoDocente daodocente = new DaoDocente();
        List<String> losProfesores = new ArrayList<String>();
        losProfesores = daodocente.getNombresProfesores();
        ObservableList losProfesoresOL = FXCollections.observableList(losProfesores);
        listaProfes.getItems().clear();
        listaProfes.setItems(losProfesoresOL);
        
        /*Añadir las horas a la combobox*/
        List<String> lasHoras = new ArrayList<String>();
        lasHoras.add("hora1");
        lasHoras.add("hora2");
        lasHoras.add("hora3");
        lasHoras.add("hora4");
        lasHoras.add("hora5");
        lasHoras.add("hora6");
        ObservableList lasHorasOL = FXCollections.observableList(lasHoras);
        listaHoras.getItems().clear();
        listaHoras.setItems(lasHorasOL);
        
        /*Añadir las faltas a la combobox*/
        
    }
    
 
    @FXML
    void enviarTodoAlFondo() {
    	botonok.toBack();
    	listaProfes.toBack();
    	listaHoras.toBack();
    	listaFaltas.toBack();
    	fecha1.toBack();
    	fecha2.toBack();
    }
    
    @FXML 
    void botonVerHorarios() {
    	
    	enviarTodoAlFondo();
    	
    	botonok.toFront();
    	listaProfes.toFront();
    	activarVerHorarios = true;
    }
    
    @FXML 
    void botonComunicarFalta() {
    	
    	enviarTodoAlFondo();
    	
    	botonok.toFront();
    	listaProfes.toFront();
    	listaHoras.toFront();
    	fecha1.toFront();
    	activarComunicarFaltas = true;
    }
    
    @FXML
    void botonVerFaltas() {
    	enviarTodoAlFondo();
    	
    	botonok.toFront();
    	listaProfes.toFront();
    	fecha1.toFront();
    	fecha2.toFront();
    	
    	activarVerFaltas = true;
    }
    
    @FXML
    void botonVerGuardias() {
    	enviarTodoAlFondo();
    	
    	botonok.toFront();
    	listaProfes.toFront();
    	fecha1.toFront();
    	fecha2.toFront();
    	
    	activarVerGuardias = true;
    }
    
    @FXML
    void botonAsignarGuardia() {
    	enviarTodoAlFondo();
    	
        botonok.toFront();
        listaFaltas.toFront();
        listaProfes.toFront();
    	
    	activarAsignarGuardias = true;
    }
    
    @FXML
    void botonFirmarGuardia() {
    	enviarTodoAlFondo();
    	
        botonok.toFront();
        fecha1.toFront();
        listaHoras.toFront();
        listaProfes.toFront();
    	
    	activarFirmarGuardia = true;
    }
    
    @FXML
    void botonGenerarInformeGuardias() {
    	enviarTodoAlFondo();
    	
    	botonok.toFront();
    	fecha1.toFront();
    	
    	activarGenerarInformeGuardias = true;
    }
    
    @FXML
    void botonGenerarInformeFaltas() {
    	enviarTodoAlFondo();
    	
    	botonok.toFront();
    	fecha1.toFront();
    	
    	activarGenerarInformeFaltas = true;
    }
    
    
    @FXML
    void mostrarResultados() {
    	
    	if (activarVerHorarios) {
    		
    		/* Método de Alejandro*/
    		
    		activarVerHorarios = false;
    	}
    	
    	else if (activarComunicarFaltas) {
    		
    		/*Método de Cristina*/
    		
    		activarComunicarFaltas = false;
    	}
    	
    	else if (activarVerFaltas) {
    		
    		/*Método de Carlos*/
    		
    		activarVerFaltas = false;
    		
    	}
    	
    	else if (activarVerGuardias) {
    		
    		/*Método de Carlos*/
    		
    		activarVerGuardias = false;
    	}
    	
    	else if (activarAsignarGuardias) {
    		
    		/*Método de Cristina*/
    		
    		activarAsignarGuardias = false;
    		
    	}
    	
    	else if (activarFirmarGuardia) {
    		
    		/*Método de Ana*/
    		
    		activarFirmarGuardia = false;
    	}
    	
    	else if (activarGenerarInformeGuardias) {
    		
    		/*Método de Jose*/
    		
    		activarGenerarInformeGuardias = false;
    	}
    	
    	else if (activarGenerarInformeFaltas) {
    		
    		/*Método de Jose*/
    		
    		activarGenerarInformeFaltas = false;
    		
    	}
    

    	
    }
    
    
}
package es.iesazarquiel.onguarapp.controllers;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;

public class MainJefeViewController {

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
    private Label mostrarsaludo;

    @FXML
    void saludar() {
    	mostrarsaludo.setText("hola");

    }

    @FXML
    void initialize() {
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
        assert mostrarsaludo != null : "fx:id=\"mostrarsaludo\" was not injected: check your FXML file 'MainJefeView.fxml'.";

    }
}

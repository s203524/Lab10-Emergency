package it.polito.tdp.emergency;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.emergency.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EmergencyController {

	private Model model;
	
    public void setModel(Model model) {
		this.model = model;
	}

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtTurno;

    @FXML
    private Button btnAggiungi;

    @FXML
    private Button btnSimulazione;

    @FXML
    private TextArea txtOutput;

    @FXML
    void doAggiungi(ActionEvent event) {
    	String nome = txtNome.getText();
    	String turno = txtTurno.getText();
    	try{
    		long sfasamento = (Long.parseLong(turno.substring(0, 2))*60)+Long.parseLong(turno.substring(2, 4));
    		model.getSimulatore().aggiungiDottore(nome, sfasamento);
    	}catch(NumberFormatException e){
    		txtOutput.setText("Errore formato ora. Es valido:3:45 = 0345");
    		e.printStackTrace();
    	}
    }

    @FXML
    void doSimulazione(ActionEvent event) {
    	model.stub();

    }

    @FXML
    void initialize() {
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Emergency.fxml'.";
        assert txtTurno != null : "fx:id=\"txtTurno\" was not injected: check your FXML file 'Emergency.fxml'.";
        assert btnAggiungi != null : "fx:id=\"btnAggiungi\" was not injected: check your FXML file 'Emergency.fxml'.";
        assert btnSimulazione != null : "fx:id=\"btnSimulazione\" was not injected: check your FXML file 'Emergency.fxml'.";
        assert txtOutput != null : "fx:id=\"txtOutput\" was not injected: check your FXML file 'Emergency.fxml'.";

    }
}

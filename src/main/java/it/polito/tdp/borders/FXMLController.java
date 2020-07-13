
package it.polito.tdp.borders;

import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    @FXML
    private ComboBox<Country> cmStato;

    @FXML
    private Button tnVicini;
    
    @FXML
    void doVicini(ActionEvent event) {
    	txtResult.clear();
    	Country c= cmStato.getValue();
    	List<Country> vicini= new ArrayList<>(model.vicini(c));
    	txtResult.setText(vicini.toString());
    }


    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	txtResult.clear();
    	int year = 0;
    	List<Country> nazioni;
    	try {
			year= Integer.parseInt(txtAnno.getText());
		} catch (NumberFormatException e) { 
			txtResult.appendText("Scrivi un numero!");
		}
    	model.creaGrafo(year);
    	nazioni= new ArrayList<>(model.getListanazioni());
    	for(Country c: nazioni) {
    		txtResult.appendText( c+ " connesso a "+ model.numeroArchi(year, c)+" Stati\n");
    	}
    	txtResult.appendText("\nnumero Stati presenti: " +model.numeroVertici());
    	
    	cmStato.getItems().addAll(nazioni);
    	
    	txtAnno.clear();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmStato != null : "fx:id=\"cmStato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert tnVicini != null : "fx:id=\"tnVicini\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}

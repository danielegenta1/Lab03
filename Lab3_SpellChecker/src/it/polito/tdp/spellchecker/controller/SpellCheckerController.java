package it.polito.tdp.spellchecker.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.polito.tdp.spellchecker.model.RichWord;
import it.polito.tdp.spellchecker.model.SpellCheckerModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class SpellCheckerController {

	private SpellCheckerModel model;
	
	
    @FXML
    private ComboBox<String> cmbLanguage;

    @FXML
    private TextArea txtInputWords;

    @FXML
    private Button btnSpellCheck;

    @FXML
    private TextArea txtWrongWords;

    @FXML
    private Label lblErrorsNumber;

    @FXML
    private Button btnClearText;

    @FXML
    private Label lblCompletionTime;

    @FXML
    void initialize()
    {
    	ObservableList<String> languages = FXCollections.observableArrayList("English", "Italian");
     	cmbLanguage.setItems(languages);
     	cmbLanguage.getSelectionModel().selectFirst();
    }
    
    
    @FXML
    void clearText(ActionEvent event) 
    {
    	txtInputWords.clear();
    	txtWrongWords.clear();
    }

    @FXML
    void spellCheck(ActionEvent event)
    {
    	long startTime = model.avviaTempo();
    	
    	model.loadDictionary(cmbLanguage.getSelectionModel().getSelectedItem());
    
    	List<RichWord> partial = model.spellCheckText(new ArrayList<String>(Arrays.asList(txtInputWords.getText().split(" "))));
    	String result = model.preparePrint(partial);
    	int num = model.calculateErrorNumbers(partial);
    	long tempo = model.registraTempo(startTime);
    	
    	//output
    	txtWrongWords.setText(result);
    	lblErrorsNumber.setText(String.format("The text contains %s errors", num));
    	lblCompletionTime.setText(String.format("Spell checked completed in %d nanoseconds", tempo));
    }
    
    public void setModel(SpellCheckerModel model) 
	{
		this.model = model;
	}

}


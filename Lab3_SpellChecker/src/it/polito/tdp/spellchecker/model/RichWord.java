package it.polito.tdp.spellchecker.model;

public class RichWord 
{
	private String word;
	private boolean corretta;
	
	public RichWord() {}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public boolean isCorretta() {
		return corretta;
	}

	public void setCorretta(boolean corretta) {
		this.corretta = corretta;
	}

}

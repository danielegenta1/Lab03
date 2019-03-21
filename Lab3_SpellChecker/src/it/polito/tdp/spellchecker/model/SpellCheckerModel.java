package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class SpellCheckerModel 
{
	Set<String>dizionario = new HashSet<String>();
	
	public void loadDictionary(String language)
	{
		//dictionary
		//utilizzo hashset per l'immediatezza della funzione contains su un ampio insieme di parole
		try 
		{
			FileReader fr = new FileReader("rsc/" + language + ".txt");
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine()) != null)
			{
				dizionario.add(word);
			}
			br.close();
		}
		catch (IOException e)
		{
			System.out.println("Errore nella lettura del file");
		}
		
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList)
	{
		List<RichWord> parole = new LinkedList<RichWord>();
		
		for (String s : inputTextList)
		{
			s = s.toLowerCase().replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
			RichWord rw = new RichWord();
			rw.setWord(s);
			rw.setCorretta(dizionario.contains(s));
			parole.add(rw);
		}
		return parole;
	}

	public String preparePrint(List<RichWord> partial)
	{
		String result = "";
		for (RichWord rw : partial)
		{
			if (!rw.isCorretta())
				result += rw.getWord() + "\n";
		}
		return result;
	}

	public int calculateErrorNumbers(List<RichWord> partial)
	{
		int numero = 0;
		for (RichWord rw : partial)
		{
			if (!rw.isCorretta())
				numero++;
		}
		return numero;
	}
	
	 public long avviaTempo()
    {
    	return System.nanoTime();
    }
    
    public long registraTempo(long startTime)
    {
    	return System.nanoTime() - startTime;
    }
	
	

}

package com.jfeather.Player;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import com.jfeatehr.Utils.Contains;
import com.jfeather.Main.TerminalApplication;

public class Character {

	private static final String DEFAULT_NAME = "Jack";
	private static final String[] invalidChars = {"!", "@", "#", "$", "%", "^", "&", "*", "(", ")", ",", "<", ".", ">", "/", "?", ";", ":",
			"\"", "'", "[", "{", "]", "}", "\\", "|", "-", "+", "="}; // Pretty much only letters, numbers, and underscores are allowed in names
	
	// Attributes of a character
	private String name = DEFAULT_NAME;
	
	// Reference variables and values
	private File saveFile;
	
	public Character(String newName) {
		if (Contains.arrElementsInString(invalidChars, newName) == -1)
			name = newName;
		saveFile = new File(TerminalApplication.RESOURCES_PATH + name);
	}
	
	public void setName(String newName) {
		// Make sure that the name is valid, and that it won't be null regardless of the entered name's validity
		if (Contains.arrElementsInString(invalidChars, newName) == -1)
			name = newName;
		else if (name == null)
			name = DEFAULT_NAME;
	}
	
	public String getName() {
		return name;
	}

	public File getSaveFile() {
		return saveFile;
	}
	
	public String getSaveFilePath() {
		return saveFile.getPath();
	}
	
	public void saveCharacter() {
		try {
			FileWriter fr = new FileWriter(saveFile);
			BufferedWriter br = new BufferedWriter(fr);
			
			// TODO: Whatever needs to be saved should be written here
			// ----
			
			// ----
			
			br.flush();
		} catch (Exception ex) {
			
		}
	}
}

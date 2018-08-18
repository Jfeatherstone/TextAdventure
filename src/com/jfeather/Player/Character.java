package com.jfeather.Player;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.jfeather.Main.TerminalApplication;
import com.jfeather.Utils.Contains;

public class Character {

	private static final String DEFAULT_NAME = "Jack";
	private static final String[] invalidChars = {"!", "@", "#", "$", "%", "^", "&", "*", "(", ")", ",", "<", ".", ">", "/", "?", ";", ":",
			"\"", "'", "[", "{", "]", "}", "\\", "|", "-", "+", "=", " "}; // Pretty much only letters, numbers, and underscores are allowed in names
	
	// Attributes of a character
	private String name = DEFAULT_NAME;
	
	// Reference variables and values
	private File saveFile;
	
	public Character(String newName) {
		if (Contains.arrElementsInString(invalidChars, newName) == -1)
			name = newName;
		saveFile = new File(TerminalApplication.RESOURCES_PATH + "Saves/" + name.toLowerCase());
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
			br.write("name=" + name);
			br.newLine();
			// ----
			
			br.flush();
		} catch (Exception ex) {
			System.err.println("Saving process interrupted!");
		}
	}
	
	public static Character[] parseCharacters() {
		// Will parse every file in the Data/ folder
		File folder = new File(TerminalApplication.RESOURCES_PATH + "Saves/");
		File[] files = folder.listFiles();

		// Every file in the folder *should* be a save file, but we don't really know, so we start with an arraylist and convert it later
		ArrayList<Character> list = new ArrayList<>();
		Character[] arr;
		
		for (int j = 0; j < files.length; j++) {
			/*
			 * Just a heads up, this code is copied from another project, so it may not all be utilized
			 * This first part makes sure that the file doesn't start with a period
			 * Again, this may not be necessary, but it can be important based on the operating system this is being run on
			 * For example, I use linux -- Arch btw ecks dee -- and folders are initialized with a file called ".directory"
			 * It is important to ignore this type of file, other we'll get an error because most of this stuff won't be found 
			 * Another important note is that although this does recognize comments using "#",
			 * it is not recommended to ever actually alter save files, as it could corrupt your data
			 */

			if (!files[j].getName().substring(0, 1).equals(".")) {
				try {
					FileReader fr = new FileReader(files[j].getPath());
					BufferedReader br = new BufferedReader(fr);
					String line;
					
					// Temporary attributes for each read in character
					// TODO: add the rest of the attributes when I think of them
					String name = null;
					
					while ((line = br.readLine()) != null) {
						// Iterate through every line
						
						// Find the first non-space character
						int index = 0;
						for (int i = 0; i < line.length(); i++) {
							if (!line.substring(i, i+1).equals(" ")) {
								index = i;
								break;
							}
	
						}
						// Ignore the line if its a comment (in this case indicated by a # as the first character
						// Although, as can be seen below, the code will ignore anything after a # as well, so you can do in line comments # like this
						if (line.length() >= 1) {
							if (!line.substring(index, index + 1).equals("#")) {
								
								// Grab the word before the = sign
								// The only words that will be interpreted are "title", "genre", and "link"
								String word = "";
								for (int i = 0; i < line.length(); i++) {
									if (line.substring(i, i+1).equals("=")) {
										word = line.substring(index, i).trim();
										index = i;
										break;
									}
								}
								
								// This will most of the time just set index2 to the full length of the string, but just in case there is
								// a line comment, it will catch it
								int index2 = line.length();
								for (int i = index + 1; i < line.length(); i++) {
									if (line.substring(i, i+1).equals("#")) {
										index2 = i;
										break;
									}
								}
								
								// Grab the rest of the line (i.e. whatever is after the equals sign)
								String value = line.substring(index + 1, index2).trim();
								
								switch (word) {
								// Any of the fields and attributes that a character has
								// TODO: Fill all of these in when the attributes are decided on
								case "name":
									name = value;
									break;
								}
							}
						}
					}
					
					// Make sure we're not going to get a null pointer
					
				} catch (IOException e) {
					System.err.println("Error reading in save files!");
					return null;
				}
			}
		}
		
		// Now convert the arraylist to a regular array
		arr = new Character[list.size()];
		for (int i = 0; i < arr.length; i++)
			arr[i] = list.get(i);
		
		return arr;
	}
}

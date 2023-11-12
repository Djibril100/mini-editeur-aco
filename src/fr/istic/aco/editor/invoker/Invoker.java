package fr.istic.aco.editor.invoker;

import fr.istic.aco.editor.command.Command;
import fr.istic.aco.editor.exceptions.EditorException;


public interface Invoker {
	
	/**
	 * add cmd to a HashMap table
	 * 
	 * @param cmdRef : must not be null or empty
	 * @param cmd
	 * @throws KeyOrCmdNotFoundException if key or cmd is null or empty then Exception
	 */
	
	void addCommand(String cmdRef, Command cmd) throws EditorException;
	
	/**
	 * execute command related to cmdRef
	 * @param cmdRef
	 * @throws CommandNotFoundException
	 */
	void playCommand(String cmdRef) throws EditorException;
	
	String getTextToInsert();
	void setTextToInsert(String text);
	
	void setIndex(SelectionIndex index);
	SelectionIndex getIndex();

}

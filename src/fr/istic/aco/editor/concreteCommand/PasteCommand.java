package fr.istic.aco.editor.concreteCommand;

import fr.istic.aco.editor.command.Command;
import fr.istic.aco.editor.enums.ErrorType;
import fr.istic.aco.editor.exceptions.EditorException;
import fr.istic.aco.editor.receiver.Engine;

public class PasteCommand implements Command {
	
	private Engine receiver;
	
	public PasteCommand(Engine receiver) throws EditorException {
		
		if(receiver == null)
			throw new EditorException(ErrorType.RECEIVER_NOT_FOUND, "Receiver ne doit pas etre null");
		
		this.receiver = receiver;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		this.receiver.pasteClipboard();	
	}

}

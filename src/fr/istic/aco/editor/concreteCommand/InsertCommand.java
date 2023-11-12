package fr.istic.aco.editor.concreteCommand;

import fr.istic.aco.editor.command.Command;
import fr.istic.aco.editor.enums.ErrorType;
import fr.istic.aco.editor.exceptions.EditorException;
import fr.istic.aco.editor.invoker.Invoker;
import fr.istic.aco.editor.receiver.Engine;

public class InsertCommand implements Command {
	
	private Invoker invoker;
	private Engine receiver;
	private String insertText;
	
	/**
	 * @param invoker  not null
	 * @param receiver not null
	 */
	public InsertCommand(Invoker invoker, Engine receiver) throws EditorException {
		if(invoker==null || receiver==null)
			throw new EditorException(ErrorType.INVOKER_OR_RECEIVER_NOT_FOUND, "Invoker ou Receiver ne doit pas etre null");
		
		this.invoker = invoker;
		this.receiver = receiver;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		this.insertText = this.invoker.getTextToInsert();
		this.receiver.insert(this.insertText);	
	}

}

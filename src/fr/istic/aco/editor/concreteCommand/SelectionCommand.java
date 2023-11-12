package fr.istic.aco.editor.concreteCommand;

import fr.istic.aco.editor.command.Command;
import fr.istic.aco.editor.enums.ErrorType;
import fr.istic.aco.editor.exceptions.EditorException;
import fr.istic.aco.editor.invoker.Invoker;
import fr.istic.aco.editor.invoker.SelectionIndex;
import fr.istic.aco.editor.receiver.Selection;

public class SelectionCommand implements Command {
	
	private Invoker invoker;
	private Selection receiver;
	
	public SelectionCommand(Invoker invoker, Selection receiver) throws EditorException {
		if(invoker==null || receiver==null)
			throw new EditorException(ErrorType.INVOKER_OR_RECEIVER_NOT_FOUND, "Invoker ou Receiver ne doit pas etre null");
		
		this.invoker = invoker;
		this.receiver = receiver;
		
		
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		SelectionIndex index = this.invoker.getIndex();
		
		this.receiver.setBeginIndex(index.getBegin());
		this.receiver.setEndIndex(index.getEnd());
	}

}

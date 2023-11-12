package fr.istic.aco.editor.invoker;

import java.util.HashMap;
import java.util.Map;
import fr.istic.aco.editor.command.Command;
import fr.istic.aco.editor.enums.ErrorType;
import fr.istic.aco.editor.exceptions.EditorException;

public class InvokerImpl implements Invoker {
	
	private Map<String, Command> tableCommand;
	private SelectionIndex index;
	private String textToInsert;
	private Command cmd;
	
	public InvokerImpl() {
		this.tableCommand = new HashMap<>();
		this.index = new SelectionIndex();
		this.textToInsert = new String();
	}

	@Override
	public void addCommand(String cmdRef, Command cmd) throws EditorException{
		// TODO Auto-generated method stub
		
		if(cmdRef==null || cmdRef.isEmpty() || cmd==null) 
				throw new EditorException(ErrorType.KEY_OR_CMD_NOT_FOUND, "Cle ou Commande non trouves !");
		
		tableCommand.put(cmdRef, cmd);
		
	}

	@Override
	public void playCommand(String cmdRef) throws EditorException {
		// TODO Auto-generated method stub
		//Dans les playCommand on appel les action cut, copy, paste ...
		if(cmdRef==null || cmdRef.isEmpty()) 
				throw new EditorException(ErrorType.KEY_NOT_FOUND, "Cle de la commande non trouvee !");
		
		if(tableCommand.containsKey(cmdRef)) {
			//cmd.execute();
			tableCommand.get(cmdRef).execute();
		}else {
			throw new EditorException(ErrorType.COMMAND_NOT_FOUND, "Aucune commande n'est associée avec cette cle !");
		}
		
	}

	@Override
	public String getTextToInsert() {
		// TODO Auto-generated method stub
		return this.textToInsert;
	}

	@Override
	public void setTextToInsert(String text) {
		// TODO Auto-generated method stub
		this.textToInsert = text;
	}

	@Override
	public void setIndex(SelectionIndex index) {
		// TODO Auto-generated method stub
		this.index = index;
	}

	@Override
	public SelectionIndex getIndex() {
		// TODO Auto-generated method stub
		return this.index;
	}
	
	
	
	

}

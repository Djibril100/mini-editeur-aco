package fr.istic.aco.editor.ihm;

import java.util.Scanner;

import fr.istic.aco.editor.concreteCommand.ChangeSelection;
import fr.istic.aco.editor.concreteCommand.CopyCommand;
import fr.istic.aco.editor.concreteCommand.CutCommand;
import fr.istic.aco.editor.concreteCommand.DeleteCommand;
import fr.istic.aco.editor.concreteCommand.InsertCommand;
import fr.istic.aco.editor.concreteCommand.PasteCommand;
import fr.istic.aco.editor.exceptions.EditorException;
import fr.istic.aco.editor.invoker.Invoker;
import fr.istic.aco.editor.invoker.InvokerImpl;
import fr.istic.aco.editor.invoker.SelectionIndex;
import fr.istic.aco.editor.receiver.Engine;
import fr.istic.aco.editor.receiver.EngineImpl;

public class IHM {

	private static final Scanner scanner = new Scanner(System.in);

	public static void myIHM() throws EditorException {

		Engine receiver = new EngineImpl();
		Invoker invoker = new InvokerImpl();

		System.out.println("*****************************************");
		System.out.println("*******Design Pattern Command************");
		System.out.println("*****************************************");
		
		invoker.addCommand("insert", new InsertCommand(invoker, receiver));
		invoker.addCommand("copy", new CopyCommand(receiver));
		invoker.addCommand("cut", new CutCommand(receiver));
		invoker.addCommand("delete", new DeleteCommand(receiver));
		invoker.addCommand("paste", new PasteCommand(receiver));
		invoker.addCommand("select", new ChangeSelection(invoker, receiver.getSelection()));


		while (true) {
            System.out.print("Enter the command (1: Insert, 2: Copy, 3: Cut, 4: Paste, 5: Delete, 6: Select): ");
            int cmd = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (cmd) {
                case 1: // Insert
                    System.out.print("Enter text to insert: ");
                    String text = scanner.nextLine();
                    invoker.setTextToInsert(text);
                    invoker.playCommand("insert");
                    System.out.println("Buffer length: " + receiver.getBufferContents().length());

                    break;

                case 2: // Copy
                case 3: // Cut
                case 4: // Paste
                case 5: // Delete
                case 6: // Change Selection
                    SelectionIndex indexes = getIndexes(receiver);
                    if (indexes != null) {
                        invoker.setIndex(indexes);
                        invoker.playCommand("select");
                        if (cmd != 6) { 
                            String commandName = (cmd == 2) ? "copy" : (cmd == 3) ? "cut" : (cmd == 4) ? "paste" : "delete";
                            invoker.playCommand(commandName);
                        }
                    }
                    break;

                default:
                    System.out.println("Invalid command.");
                    break;
            }

            System.out.println("Buffer: " + receiver.getBufferContents());
            System.out.println("Clipboard:" + receiver.getClipboardContents() + " \n");
            
            int beginIndex = receiver.getSelection().getBeginIndex();
            int endIndex = receiver.getSelection().getEndIndex();
            System.out.println("Current Selection : ");
            System.out.println("Begin Index = " + beginIndex + " et End Index = " + endIndex);
        }

	}

	private static SelectionIndex getIndexes(Engine receiver) {
	    SelectionIndex indexes = new SelectionIndex();
	    System.out.print("Enter begin index: ");
	    int begin = scanner.nextInt();
	    System.out.print("Enter end index: ");
	    int end = scanner.nextInt();
	    scanner.nextLine(); // Consume the newline character

	    // Check if the indices are within the valid range
	    if (begin >= 0 && end <= receiver.getBufferContents().length() && begin <= end) {
	        indexes.setBegin(begin);
	        indexes.setEnd(end);
	        return indexes;
	    } else {
	        System.out.println("Invalid index range. Begin index should be less than or equal to end index, and within buffer length.");
	        return null;
	    }
	}


}

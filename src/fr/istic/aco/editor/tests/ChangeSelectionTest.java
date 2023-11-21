package fr.istic.aco.editor.tests;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.istic.aco.editor.command.Command;
import fr.istic.aco.editor.concreteCommand.ChangeSelection;
import fr.istic.aco.editor.exceptions.EditorException;
import fr.istic.aco.editor.invoker.Invoker;
import fr.istic.aco.editor.invoker.SelectionIndex;
import fr.istic.aco.editor.receiver.Selection;

class ChangeSelectionTest {
	 
	private Command command;
	private Selection receiver;
	private Invoker invoker;
	
	@BeforeEach
	void setUp() throws EditorException {
		this.receiver = mock(Selection.class);
		this.invoker = mock(Invoker.class);
		this.command = new ChangeSelection(invoker, receiver);
	}
	
	@Test
	void selectionCommandTestWhenInvokerIsNull() {
		assertThrows(EditorException.class, ()-> new ChangeSelection(null, receiver));
	}
	@Test
	void selectionCommandTestWhenReceiverIsNull() {
		assertThrows(EditorException.class, ()-> new ChangeSelection(invoker, null));
	}
	@Test
	void selectionCommandTestWithThrowsException() {
		assertThrows(EditorException.class, ()-> new ChangeSelection(null, null));
	}
	@Test
	void selectionCommandTestWithNotThrowsException() {
		assertDoesNotThrow(()-> new ChangeSelection(invoker, receiver));
	}
	
	@Test
	void executeShouldSetSelectionIndexVersion1() {
		SelectionIndex index = new SelectionIndex();
		index.setBegin(5);
		index.setEnd(10);

		when(invoker.getIndex()).thenReturn(index);
		
		assertDoesNotThrow(() -> command.execute());
		
		verify(invoker).getIndex();
		verify(receiver).setBeginIndex(5);
		verify(receiver).setEndIndex(10);
	}
	
	@Test
	void executeShouldSetSelectionIndexVersion2() {
		SelectionIndex index = new SelectionIndex();
		index.setBegin(10);
		index.setEnd(5);

		when(invoker.getIndex()).thenReturn(index);
		
		assertDoesNotThrow(() -> command.execute());
		
		verify(invoker).getIndex();
		verify(receiver).setBeginIndex(10);
		verify(receiver).setEndIndex(5);
	}
		
	

}

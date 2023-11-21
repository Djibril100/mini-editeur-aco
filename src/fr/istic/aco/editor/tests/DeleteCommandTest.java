package fr.istic.aco.editor.tests;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.istic.aco.editor.command.Command;
import fr.istic.aco.editor.concreteCommand.DeleteCommand;
import fr.istic.aco.editor.exceptions.EditorException;
import fr.istic.aco.editor.receiver.Engine;

 class DeleteCommandTest {
	
	private Engine receiver;
	private Command command;
	
	@BeforeEach
	void setUp() throws EditorException {
		this.receiver = mock(Engine.class);
		this.command = new DeleteCommand(receiver);
	}
	
	@Test
    void deleteCommandTestWithThrowsException() {
        assertThrows(EditorException.class, ()->new DeleteCommand(null));
    }
	
	@Test
	void deleteCommandTestWithNotThrowsException() {
		assertDoesNotThrow(()->new DeleteCommand(receiver));
	}
	
	@Test
	void executeTestWithNotThrowsException() {
		assertDoesNotThrow(()->command.execute());
	}
	
	@Test
	void executeShouldInvokeDelete() {
		command.execute();
		verify(receiver).delete();
		
	}

}

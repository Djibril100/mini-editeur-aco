package fr.istic.aco.editor.tests;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.istic.aco.editor.command.Command;
import fr.istic.aco.editor.concreteCommand.CopyCommand;
import fr.istic.aco.editor.exceptions.EditorException;
import fr.istic.aco.editor.receiver.Engine;

class CopyCommandTest {
	
	private Engine receiver;
	private Command command;
	
	@BeforeEach
	void setUp() throws EditorException {
		this.receiver = mock(Engine.class);
		this.command = new CopyCommand(receiver);
	}
	
	@Test
    void copyCommandTestWithThrowsException() {
        assertThrows(EditorException.class, ()->new CopyCommand(null));
    }
	
	@Test
	void copyCommandTestWithNotThrowsException() {
		assertDoesNotThrow(()->new CopyCommand(receiver));
	}
	
	@Test
	void executeTestWithNotThrowsException() {
		assertDoesNotThrow(()->command.execute());
	}
	
	@Test
	void executeShouldInvokeCopySelectedText() {
		command.execute();
		verify(receiver).copySelectedText();
		
	}
	

}

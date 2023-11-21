package fr.istic.aco.editor.tests;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.istic.aco.editor.command.Command;
import fr.istic.aco.editor.concreteCommand.PasteCommand;
import fr.istic.aco.editor.exceptions.EditorException;
import fr.istic.aco.editor.receiver.Engine;

 class PasteCommandTest {
	
	private Engine receiver;
	private Command command;
	
	@BeforeEach
	void setUp() throws EditorException {
		this.receiver = mock(Engine.class);
		this.command = new PasteCommand(receiver);
	}
	
	@Test
    void pasteCommandTestWithThrowsException() {
        assertThrows(EditorException.class, ()->new PasteCommand(null));
    }
	
	@Test
	void pasteCommandTestWithNotThrowsException() {
		assertDoesNotThrow(()->new PasteCommand(receiver));
	}
	
	@Test
	void executeTestWithNotThrowsException() {
		assertDoesNotThrow(()->command.execute());
	}
	
	@Test
	void executeShouldInvokePasteClipboard() {
		command.execute();
		verify(receiver).pasteClipboard();
		
	}

}

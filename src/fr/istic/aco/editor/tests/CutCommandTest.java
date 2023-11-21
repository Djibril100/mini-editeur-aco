package fr.istic.aco.editor.tests;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import fr.istic.aco.editor.command.Command;
import fr.istic.aco.editor.concreteCommand.CutCommand;
import fr.istic.aco.editor.exceptions.EditorException;
import fr.istic.aco.editor.receiver.Engine;

class CutCommandTest {
	
	private Engine receiver;
	private Command command;
	
	@BeforeEach
	void setUp() throws EditorException {
		this.receiver = mock(Engine.class);
		this.command = new CutCommand(receiver);
	}
	
	@Test
	void cutCommandTestWithThrowsException() {
		assertThrows(EditorException.class, ()->new CutCommand(null));
	}
	@Test
	void cutCommandTestWithNotThrowsException() {
		assertDoesNotThrow(()-> new CutCommand(receiver));
	}
	
	@Test
	void executeWithNotThrowsException() {
		assertDoesNotThrow(()-> command.execute());
	}
	
	@Test
	void executeShouldInvokeCutSelectedText() {
		this.command.execute();
		verify(receiver, times(1)).cutSelectedText();
	}

}

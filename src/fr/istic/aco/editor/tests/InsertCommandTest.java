package fr.istic.aco.editor.tests;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import fr.istic.aco.editor.command.Command;
import fr.istic.aco.editor.concreteCommand.InsertCommand;
import fr.istic.aco.editor.exceptions.EditorException;
import fr.istic.aco.editor.invoker.Invoker;
import fr.istic.aco.editor.receiver.Engine;


class InsertCommandTest {
	
	private Command command;
	private Engine receiver;
	private Invoker invoker;
	
	@BeforeEach
	void setUp() throws EditorException {
		this.receiver = mock(Engine.class);
		this.invoker = mock(Invoker.class);
		this.command = new InsertCommand(invoker, receiver);
	}
	
	@Test
	void insertCommandTestWhenInvokerIsNull() {
		assertThrows(EditorException.class, ()-> new InsertCommand(null, receiver));
	}
	@Test
	void insertCommandTestWhenReceiverIsNull() {
		assertThrows(EditorException.class, ()-> new InsertCommand(invoker, null));
	}
	@Test
	void insertCommandTestWithThrowsException() {
		assertThrows(EditorException.class, ()-> new InsertCommand(null, null));
	}
	@Test
	void insertCommandTestWithNotThrowsException() {
		assertDoesNotThrow(()-> new InsertCommand(invoker, receiver));
	}
	
	@Test
	void executeInsertTextVersion1() {
		String text = "My best text";
		
		when(invoker.getTextToInsert()).thenReturn(text);
		this.command.execute();
		
		verify(invoker).getTextToInsert();
		verify(receiver).insert(text);
	}
	
	@ParameterizedTest
	@CsvSource({ "My best text", "je veux ...", "blabla" })
	void executeInsertTextVersion2(String text) {
		when(invoker.getTextToInsert()).thenReturn(text);
		this.command.execute();
		
		verify(invoker).getTextToInsert();
		verify(receiver).insert(text);
	}
	
	

}

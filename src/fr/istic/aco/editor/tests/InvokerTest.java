package fr.istic.aco.editor.tests;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.istic.aco.editor.concreteCommand.ChangeSelection;
import fr.istic.aco.editor.concreteCommand.CopyCommand;
import fr.istic.aco.editor.concreteCommand.CutCommand;
import fr.istic.aco.editor.concreteCommand.DeleteCommand;
import fr.istic.aco.editor.concreteCommand.InsertCommand;
import fr.istic.aco.editor.concreteCommand.PasteCommand;
import fr.istic.aco.editor.exceptions.EditorException;
import fr.istic.aco.editor.invoker.Invoker;
import fr.istic.aco.editor.invoker.InvokerImpl;
import fr.istic.aco.editor.receiver.Engine;
import fr.istic.aco.editor.receiver.Selection;



public class InvokerTest {
	
	private Invoker invoker;
	private Engine receiver;
	private Selection selection;

	@BeforeEach
	void setUp() throws EditorException {
		
		this.receiver = mock(Engine.class);
		this.selection = mock(Selection.class);

		// initialization invoker command
		invoker = new InvokerImpl();
		invoker.addCommand("insert", new InsertCommand(invoker, receiver));
		invoker.addCommand("paste", new PasteCommand(receiver));
		invoker.addCommand("copy", new CopyCommand(receiver));
		invoker.addCommand("cut", new CutCommand(receiver));
		invoker.addCommand("delete", new DeleteCommand(receiver));
		invoker.addCommand("select", new ChangeSelection(invoker, selection));
	}

	@DisplayName("Test for valid command without Exception")
	@Test
	void addCommandTestWithoutThrowsException() {
		assertDoesNotThrow(() -> invoker.addCommand("insert", new InsertCommand(invoker, receiver)));
	}

	@DisplayName("Test when key is null")
	@Test
	void addCommandTestControl1() {
		assertThrows(EditorException.class,
				() -> invoker.addCommand(null, new InsertCommand(invoker, receiver)));
	}
	
	@DisplayName("Test when key is empty")
	@Test
	void addCommandTestControl2() {
		assertThrows(EditorException.class,
				() -> invoker.addCommand("", new InsertCommand(invoker, receiver)));
	}

	@DisplayName("Test when key is null and receiver is null as well")
	@Test
	void addCommandTestControl3() {
		assertThrows(EditorException.class, () -> invoker.addCommand(null, new InsertCommand(invoker, null)));
	}
	
	@DisplayName("Test when key is null and invoker is null as well")
	@Test
	void addCommandTestControl4() {
		assertThrows(EditorException.class, () -> invoker.addCommand(null, new InsertCommand(null, receiver)));
	}

	@DisplayName("Test when key is empty and command is null")
	@Test
	void addCommandTestControl5() {
		assertThrows(EditorException.class, () -> invoker.addCommand("", null));
	}
	
	@DisplayName("Test when key and command are null")
	@Test
	void addCommandTestControl6() {
		assertThrows(EditorException.class, () -> invoker.addCommand(null, null));
	}

	@DisplayName("Test for invalid command key")
	@Test
	void addCommandTestControl7() {
		assertThrows(EditorException.class,
				() -> invoker.addCommand("BadRef_insert", new InsertCommand(null, null)));

		assertThrows(EditorException.class,
				() -> invoker.addCommand("BadRef_insert", new InsertCommand(invoker, null)));

		assertThrows(EditorException.class,
				() -> invoker.addCommand("BadRef_insert", new InsertCommand(null, receiver)));

		assertThrows(EditorException.class, () -> invoker.addCommand("BadRef_paste", new PasteCommand(null)));

		assertThrows(EditorException.class, () -> invoker.addCommand("BadRef_copy", new CopyCommand(null)));

		assertThrows(EditorException.class, () -> invoker.addCommand("BadRef_cut", new CutCommand(null)));
		
		assertThrows(EditorException.class, () -> invoker.addCommand("BadRef_delete", new DeleteCommand(null)));

		assertThrows(EditorException.class,
				() -> invoker.addCommand("BadRef_select", new ChangeSelection(null, null)));

		assertThrows(EditorException.class,
				() -> invoker.addCommand("BadRef_select", new ChangeSelection(null, selection)));

		assertThrows(EditorException.class,
				() -> invoker.addCommand("BadRef_select", new ChangeSelection(invoker, null)));
	}

	@DisplayName("Test for valid command instanciation")
	@Test
	void addCommandTestControl8() {
		assertDoesNotThrow(() -> invoker.addCommand("insert", new InsertCommand(invoker, receiver)));
		assertDoesNotThrow(() -> invoker.addCommand("paste", new PasteCommand(receiver)));
		assertDoesNotThrow(() -> invoker.addCommand("copy", new CopyCommand(receiver)));
		assertDoesNotThrow(() -> invoker.addCommand("cut", new CutCommand(receiver)));
		assertDoesNotThrow(() -> invoker.addCommand("delete", new DeleteCommand(receiver)));
		assertDoesNotThrow(() -> invoker.addCommand("select", new ChangeSelection(invoker, selection)));
	}

	@DisplayName("Test for playing existing command")
	@Test
	void playCommandTestControl1() {
		assertDoesNotThrow(() -> invoker.playCommand("insert"));
		assertDoesNotThrow(() -> invoker.playCommand("paste"));
		assertDoesNotThrow(() -> invoker.playCommand("copy"));
		assertDoesNotThrow(() -> invoker.playCommand("cut"));
		assertDoesNotThrow(() -> invoker.playCommand("delete"));
		assertDoesNotThrow(() -> invoker.playCommand("select"));
	}

	@DisplayName("Test for playing not existing command")
	@Test
	void playCommandTestControl2() {
		// null command key
		assertThrows(EditorException.class, () -> invoker.playCommand(null));
		// empty command key
		assertThrows(EditorException.class, () -> invoker.playCommand(""));
		// black command key
		assertThrows(EditorException.class, () -> invoker.playCommand(" "));
		// wrong_command
		assertThrows(EditorException.class, () -> invoker.playCommand("invalid_key"));
		
	}


}

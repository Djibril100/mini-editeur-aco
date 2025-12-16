package fr.istic.aco.editor.tests;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.istic.aco.editor.exceptions.EditorException;
import fr.istic.aco.editor.receiver.Engine;
import fr.istic.aco.editor.receiver.Selection;
import fr.istic.aco.editor.receiver.SelectionImpl;

public class SelectionTest {

	private Selection selection;
	private Engine receiver;
	private StringBuilder buffer;

	@BeforeEach
	void setUp() throws EditorException {
		this.receiver = mock(Engine.class);
		this.buffer = new StringBuilder("My best text");
		this.selection = new SelectionImpl(buffer);
	}

	@DisplayName("failed instanciation")
	@Test
	void testWhenBufferIsNull() {
		//this.buffer = new StringBuilder();
		assertThrows(EditorException.class, () -> new SelectionImpl(null));
	}

	@Test
	void testWhenInitial() {
		// initial buffer index
		assertEquals(0, selection.getBeginIndex(), "Initial begin index should be 0");
		assertEquals(0, selection.getEndIndex(), "Initial end index should be 0");
	}

	@Test
	public void testValidBeginIndex() {
		selection.setBeginIndex(5);
		assertEquals(5, selection.getBeginIndex(), "Begin index should be updated to 5");
	}

	@Test
	public void testValidEndIndex() {
		selection.setEndIndex(10);
		assertEquals(10, selection.getEndIndex(), "End index should be updated to 10");
	}

	@Test
	public void testBeginIndexGreaterThanEndIndex() {
		assertThrows(IndexOutOfBoundsException.class, () -> selection.setBeginIndex(15),
				"begin index greater than end index should throw IndexOutOfBoundsException");
	}

	@Test
	void testWhenBeginIndexNegative() {
		// set begin index to -1
		assertThrows(IndexOutOfBoundsException.class, () -> selection.setBeginIndex(-1),
				"negative begin index should throw IndexOutOfBoundsException");
	}

	@Test
	void testWhenEndIndexLessThanBeginIndex() {
		selection.setEndIndex(5);
		selection.setBeginIndex(5);
		assertThrows(IndexOutOfBoundsException.class, () -> selection.setEndIndex(4),
				"end index less than begin index should throw IndexOutOfBoundsException");
	}

	@Test
	public void testWhenEndIndexGreaterThanBufferLength() {
		assertThrows(IndexOutOfBoundsException.class, () -> selection.setEndIndex(buffer.length() + 1));
	}


	
	@Test
    public void testBufferBeginAndEndIndex() {
        assertEquals(0, selection.getBufferBeginIndex(), "Buffer begin index should be 0");
        assertEquals(buffer.length(), selection.getBufferEndIndex(), "Buffer end index should match buffer length");
    }
	

}

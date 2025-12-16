package fr.istic.aco.editor.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.istic.aco.editor.exceptions.EditorException;
import fr.istic.aco.editor.receiver.Engine;
import fr.istic.aco.editor.receiver.EngineImpl;
import fr.istic.aco.editor.receiver.Selection;

import static org.junit.jupiter.api.Assertions.*;

class EngineTest {

    private Engine engine;

    @org.junit.jupiter.api.BeforeEach
    void setUp() throws EditorException {
        engine = new EngineImpl();
        engine.insert("My best text insert");
    }
    
    @Test
	void testEngineImpl() {
		assertNotNull(engine, "L'instance n'est pas créee");
	}
	
	@Test
	void testGetSelection() {
		assertNotNull(engine.getSelection(), "La selection n'est pas recuperee");
	}

	

    private void todo() {
        fail("Unimplemented test");
    }
    @Test
    @DisplayName("Buffer must be empty after initialisation")
    void getSelection() throws EditorException {
    	engine = new EngineImpl();
    	
        Selection selection = engine.getSelection();
        assertEquals(selection.getBufferBeginIndex(),selection.getBeginIndex());
        assertEquals("",engine.getBufferContents());
    }

    @Test
    void getBufferContents() {
        assertEquals("My best text insert", engine.getBufferContents(), "Les deux chaines ne sont pas identiques");
    }

    @Test
    void getClipboardContents() {
    	 engine.getSelection().setBeginIndex(3);
    	 engine.getSelection().setEndIndex(7);
    	 engine.copySelectedText();
    	 assertEquals("best", engine.getClipboardContents());
    }

    @Test
    void cutSelectedText() {
        engine.getSelection().setBeginIndex(8);
        engine.getSelection().setEndIndex(12);
        engine.cutSelectedText();
        assertEquals("My best  insert", engine.getBufferContents());
        assertEquals("text", engine.getClipboardContents());
    }

    @Test
    void copySelectedText() {
        engine.getSelection().setEndIndex(19);
        engine.getSelection().setBeginIndex(13);
        engine.copySelectedText();
        assertEquals("My best text insert", engine.getBufferContents());
        assertEquals("insert", engine.getClipboardContents());
    }

    @Test
    void pasteClipboard1() {
        engine.getSelection().setEndIndex(19);
        engine.getSelection().setBeginIndex(13);
        engine.copySelectedText();
        
        engine.getSelection().setBeginIndex(19);
        engine.pasteClipboard();
        assertEquals("My best text insertinsert", engine.getBufferContents());
    }
    @Test
    void pasteClipboard2() {
        engine.getSelection().setEndIndex(19);
        engine.getSelection().setBeginIndex(13);
        engine.cutSelectedText();
        

        engine.pasteClipboard();
        assertEquals("My best text insert", engine.getBufferContents());
    }
    
    @Test
	void testInsertEnd() {
		engine.insert(" est");
		assertEquals("My best text insert est", engine.getBufferContents(), "Les chaines ne sont pas identiques");
	}
	

	@Test
	void testInsertMiddle() {
		engine.getSelection().setBeginIndex(3);
		engine.getSelection().setEndIndex(3);
		engine.insert("good ");
		assertEquals("My good best text insert", engine.getBufferContents(), "Les chaines ne sont pas identiques");
	}
	
	@Test
	void testInsertBefore() {
		engine.getSelection().setBeginIndex(0);
		engine.getSelection().setEndIndex(2);
		engine.insert("Mon");
		assertEquals("Mon best text insert", engine.getBufferContents(), "Les chaines ne sont pas identiques");
	}
	
	@Test
	void testInsertNothing() {
		engine.insert("");
		assertEquals("My best text insert", engine.getBufferContents(), "Les chaines ne sont pas identiques");
	}
}

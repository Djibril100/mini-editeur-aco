package fr.istic.aco.editor.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.istic.aco.editor.receiver.Engine;
import fr.istic.aco.editor.receiver.EngineImpl;
import fr.istic.aco.editor.receiver.Selection;

import static org.junit.jupiter.api.Assertions.*;

class EngineTest {

    private Engine engine;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        engine = new EngineImpl();
        engine.insert("My best text insert");
    }

    private void todo() {
        fail("Unimplemented test");
    }
    @Test
    @DisplayName("Buffer must be empty after initialisation")
    void getSelection() {
    	engine = new EngineImpl();
        //engine.insert("My best text insert");
        
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
    	engine.getSelection().setEndIndex(7);
    	 engine.getSelection().setBeginIndex(3);
    	 
    	 engine.copySelectedText();
    	 assertEquals("best", engine.getClipboardContents());
    }

    @Test
    void cutSelectedText() {
        engine.getSelection().setEndIndex(12);
        engine.getSelection().setBeginIndex(8);
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
    void pasteClipboard() {
        engine.getSelection().setEndIndex(19);
        engine.getSelection().setBeginIndex(13);
        engine.copySelectedText();
        
        engine.getSelection().setBeginIndex(19);
        engine.pasteClipboard();
        assertEquals("My best text insertinsert", engine.getBufferContents());
    }
}

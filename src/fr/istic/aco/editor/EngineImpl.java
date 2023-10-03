package fr.istic.aco.editor;

public class EngineImpl implements Engine {
	
	private StringBuilder buffer;
	private String clipboard;
	private Selection selection;
	
	public EngineImpl() {
		this.buffer = new StringBuilder();
		this.selection = new SelectionImpl(buffer);
		this.clipboard = this.getClipboardContents();
	}
    /**
     * Provides access to the selection control object
     *
     * @return the selection object
     */
    @Override
    public Selection getSelection() {
        // TODO
        return this.selection;
    }

    /**
     * Provides the whole contents of the buffer, as a string
     *
     * @return a copy of the buffer's contents
     */
    @Override
    public String getBufferContents() {
        // TODO
        return this.buffer.toString();
    }

    /**
     * Provides the clipboard contents
     *
     * @return a copy of the clipboard's contents
     */
    @Override
    public String getClipboardContents() {
        // TODO
        return this.clipboard;
    }

    /**
     * Removes the text within the interval
     * specified by the selection control object,
     * from the buffer.
     */
    @Override
    public void cutSelectedText() {
        // TODO
    	int debut = selection.getBeginIndex();
    	int fin = selection.getEndIndex();
    	
    	if(debut >= 0 && fin <= buffer.length()) {
    		this.clipboard = this.buffer.substring(debut, fin);
    		this.buffer.delete(debut, fin);
    		selection.clear();	
    	}
    }

    /**
     * Copies the text within the interval
     * specified by the selection control object
     * into the clipboard.
     */
    @Override
    public void copySelectedText() {
        // TODO
    	int debut = selection.getBeginIndex();
    	int fin = selection.getEndIndex();
    	
    	if(debut >= 0 && fin <= buffer.length()) {
    		this.clipboard = this.buffer.substring(debut, fin);
    		selection.clear();	
    	}
    }

    /**
     * Replaces the text within the interval specified by the selection object with
     * the contents of the clipboard.
     */
    @Override
    public void pasteClipboard() {
        // TODO
    	if(!this.clipboard.isEmpty()) {
    		int debut = selection.getBeginIndex();
    		if(debut>=0 && debut <= buffer.length()) {
    			this.buffer.insert(debut, clipboard);
    		}
    	}
    }

    /**
     * Inserts a string in the buffer, which replaces the contents of the selection
     *
     * @param s the text to insert
     */
    @Override
    public void insert(String s) {
    	int debut = selection.getBeginIndex();
    	int fin = selection.getEndIndex();
    	if(debut>=0 && fin <= buffer.length()) {
    		this.buffer.append(s, debut, fin);
    		this.selection.clear();
    	}
    }
    
	/*
	 * @Override public void insert(String s) { int begin =
	 * selection.getBeginIndex(); int end = selection.getEndIndex(); if (begin >= 0
	 * && end <= buffer.length()) { buffer.replace(begin, end, s);
	 * selection.setBeginIndex(begin + s.length()); selection.setEndIndex(begin +
	 * s.length()); selection.clear(); } }
	 */

    /**
     * Removes the contents of the selection in the buffer
     */
    @Override
    public void delete() {
    	
    	int debut = selection.getBeginIndex();
    	int fin = selection.getEndIndex();
    	
    	if(debut >= 0 && fin <= buffer.length()) {
    		this.buffer.delete(debut, fin);
    		selection.clear();
    	}
    }
}

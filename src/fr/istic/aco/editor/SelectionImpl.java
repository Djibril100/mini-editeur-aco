package fr.istic.aco.editor;

public class SelectionImpl implements Selection {

	private final StringBuilder buffer;
	private static final int BUFFER_BEGIN_INDEX = 0;
	private Integer beginIndex;
	private Integer endIndex;
	
	public SelectionImpl(StringBuilder buffer) {
		this.buffer = buffer;
		this.beginIndex = BUFFER_BEGIN_INDEX;
		this.endIndex = BUFFER_BEGIN_INDEX;
	}
	
	@Override
	public int getBeginIndex() {
		// TODO Auto-generated method stub
		return this.beginIndex;
	}

	@Override
	public int getEndIndex() {
		// TODO Auto-generated method stub
		return this.endIndex;
	}

	@Override
	public int getBufferBeginIndex() {
		// TODO Auto-generated method stub
		return this.BUFFER_BEGIN_INDEX;
	}

	@Override
	public int getBufferEndIndex() {
		// TODO Auto-generated method stub
		return this.buffer.length();
	}

	@Override
	public void setBeginIndex(int beginIndex) {
		// TODO Auto-generated method stub
		if(beginIndex >= BUFFER_BEGIN_INDEX && beginIndex <= this.getBufferEndIndex()) {
			this.beginIndex = beginIndex;
		}else {
			throw new IndexOutOfBoundsException("Begin index not valid !");
		}
		
	}

	@Override
	public void setEndIndex(int endIndex) {
		// TODO Auto-generated method stub
		if(endIndex >= BUFFER_BEGIN_INDEX && endIndex <= this.getBufferEndIndex()) {
			this.endIndex = endIndex;
		}else {
			throw new IndexOutOfBoundsException("End index not valid !");
		}
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		this.beginIndex = BUFFER_BEGIN_INDEX;
		this.endIndex = BUFFER_BEGIN_INDEX;
	}

}

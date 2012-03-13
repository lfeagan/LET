package net.vectorcomputing.ui;


public class ColumnSpecification {

	private static final int DEFAULT_WIDTH = 100;
	private static final int DEFAULT_WIDTH_MINIMUM = 0;
	private static final int DEFAULT_WIDTH_MAXIMUM = 300;
	private static final boolean DEFAULT_HIDDEN = false;
	private static final boolean DEFAULT_RESIZABLE = false;
	private static final boolean DEFAULT_MOVABLE = false;
	
	private String name;
	private int width = DEFAULT_WIDTH;
	private int width_minimum = DEFAULT_WIDTH_MINIMUM;
	private int width_maximum = DEFAULT_WIDTH_MAXIMUM;
	private boolean hidden = DEFAULT_HIDDEN;
	private boolean resizable = DEFAULT_RESIZABLE;
	private boolean movable = DEFAULT_MOVABLE;
	
	public ColumnSpecification(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public ColumnSpecification setName(String name) {
		this.name = name;
		return this;
	}
	
	public int getWidth() {
		return width;
	}
	
	public ColumnSpecification setWidth(int width) {
		if (width < width_minimum) {
			throw new IllegalArgumentException("width is less than minimum");
		}
		if (width > width_maximum) {
			throw new IllegalArgumentException("width is greater than maximum");
		}
		this.width = width;
		return this;
	}
	
	public boolean isHidden() {
		return hidden;
	}
	
	public ColumnSpecification setHidden(boolean hidden) {
		this.hidden = hidden;
		return this;
	}
	
	public boolean isResizable() {
		return resizable;
	}
	
	public ColumnSpecification setResizable(boolean resizable) {
		this.resizable = resizable;
		return this;
	}
	
	public boolean isMovable() {
		return movable;
	}
	
	public ColumnSpecification setMovable(boolean movable) {
		this.movable = movable;
		return this;
	}

}

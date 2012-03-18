package net.vectorcomputing.ui;

public abstract class AbstractColumnSpecification<C extends AbstractColumnSpecification<?>> implements IColumnSpecification<C> {

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
	
	public AbstractColumnSpecification(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public C setName(String name) {
		this.name = name;
		return (C) this;
	}
	
	@Override
	public int getWidth() {
		return width;
	}
	
	@Override
	public C setWidth(int width) {
		if (width < width_minimum) {
			throw new IllegalArgumentException("width is less than minimum");
		}
		if (width > width_maximum) {
			throw new IllegalArgumentException("width is greater than maximum");
		}
		this.width = width;
		return (C) this;
	}
	
	@Override
	public boolean isHidden() {
		return hidden;
	}
	
	@Override
	public C setHidden(boolean hidden) {
		this.hidden = hidden;
		return (C) this;
	}
	
	@Override
	public boolean isResizable() {
		return resizable;
	}
	
	@Override
	public C setResizable(boolean resizable) {
		this.resizable = resizable;
		return (C) this;
	}
	
	@Override
	public boolean isMovable() {
		return movable;
	}
	
	@Override
	public C setMovable(boolean movable) {
		this.movable = movable;
		return (C) this;
	}

}

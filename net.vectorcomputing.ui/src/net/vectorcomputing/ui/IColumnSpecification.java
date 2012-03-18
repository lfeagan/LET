package net.vectorcomputing.ui;

public interface IColumnSpecification<C extends IColumnSpecification<?>> {

	public abstract String getName();

	public abstract C setName(String name);
	
	public abstract int getWidth();
	
	public abstract C setWidth(int width);
	
	public abstract boolean isHidden();
	
	public abstract C setHidden(boolean hidden);

	public abstract boolean isResizable();
	
	public abstract C setResizable(boolean resizable);

	public abstract boolean isMovable();
	
	public abstract C setMovable(boolean movable);

}
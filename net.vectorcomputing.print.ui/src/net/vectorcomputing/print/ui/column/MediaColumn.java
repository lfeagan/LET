package net.vectorcomputing.print.ui.column;

import java.util.Comparator;

import net.vectorcomputing.print.accounting.Media;
import net.vectorcomputing.print.accounting.Price;
import net.vectorcomputing.print.accounting.Size;
import net.vectorcomputing.ui.ColumnSpecification;
import net.vectorcomputing.ui.viewers.AbstractColumnValueIO;
import net.vectorcomputing.ui.viewers.ColumnViewerColumn;
import net.vectorcomputing.ui.viewers.ColumnViewerColumnFactory;
import net.vectorcomputing.ui.viewers.ElementChangedEvent;
import net.vectorcomputing.ui.viewers.IColumnViewerColumn;

import org.eclipse.jface.viewers.TextCellEditor;

public enum MediaColumn {
	UUID("UUID", new UUIDColumnFactory(), null),
	Maker("Maker", new MakerColumnFactory(), null),
	Model("Model", new ModelColumnFactory(), null),
	Type("Type", new TypeColumnFactory(), null),
	Finish("Finish", new FinishColumnFactory(), null),
	Width("Width", new WidthColumnFactory(), null),
	Length("Length", new LengthColumnFactory(), null),
	Quantity("Quantity", new QuantityColumnFactory(), null);
	
	private final String property;
	private final ColumnViewerColumnFactory factory;
	private final Comparator<Price> comparator;
	private MediaColumn(String property, ColumnViewerColumnFactory factory, Comparator<Price> comparator) {
		this.property = property;
		this.factory = factory;
		this.comparator = comparator;
	}
	
	public String getProperty() {
		return property;
	}
	
	public IColumnViewerColumn newColumnViewerColumn(int index) {
		return factory.build(index);
	}

	private static class UUIDColumnFactory implements ColumnViewerColumnFactory {
		@Override
		public IColumnViewerColumn build(int index) {
			return new ColumnViewerColumn(index, new ColumnSpecification(MediaColumn.UUID.getProperty()).setHidden(true).setWidth(250), null, null);
		}
	}

	private static class MakerColumnFactory implements ColumnViewerColumnFactory {
		@Override
		public IColumnViewerColumn build(int index) {
			return new ColumnViewerColumn(index,
					new ColumnSpecification(MediaColumn.Maker.getProperty()),
					new TextCellEditor(),
					new AbstractColumnValueIO() {
				@Override
				public Object getValue(Object element) {
					Media media = (Media) element;
					return media.getMaker();
				}
				@Override
				public void modify(Object element, Object value) {
					Media media = (Media) element;
					String input = (String) value;
					media.setMaker(input);
					elementChanged(new ElementChangedEvent(media, MediaColumn.Maker.getProperty(), input));
				}
			});
		}
	}
	
	private static class ModelColumnFactory implements ColumnViewerColumnFactory {
		@Override
		public IColumnViewerColumn build(int index) {
			return new ColumnViewerColumn(index,
					new ColumnSpecification(MediaColumn.Model.getProperty()),
					new TextCellEditor(),
					new AbstractColumnValueIO() {
						@Override
						public Object getValue(Object element) {
							Media media = (Media) element;
							return media.getModel();
						}

						@Override
						public void modify(Object element, Object value) {
							Media media = (Media) element;
							String input = (String) value;
							media.setModel(input);
							elementChanged(new ElementChangedEvent(media, MediaColumn.Model.getProperty(), input));
						}
					});
		}
	}

	private static class TypeColumnFactory implements ColumnViewerColumnFactory {
		@Override
		public IColumnViewerColumn build(int index) {
			return new ColumnViewerColumn(index, new ColumnSpecification(MediaColumn.Type.getProperty()), null, null);
		}
	}

	private static class FinishColumnFactory implements ColumnViewerColumnFactory {
		@Override
		public IColumnViewerColumn build(int index) {
			return new ColumnViewerColumn(index, new ColumnSpecification(MediaColumn.Finish.getProperty()), null, null);
		}
	}

	private static class WidthColumnFactory implements ColumnViewerColumnFactory {
		@Override
		public IColumnViewerColumn build(int index) {
			return new ColumnViewerColumn(index,
					new ColumnSpecification(MediaColumn.Width.getProperty()).setWidth(150),
					new TextCellEditor(),
					new AbstractColumnValueIO() {
						@Override
						public Object getValue(Object element) {
							Media media = (Media) element;
							return Double.toString(media.getSize().getWidth());
						}
						@Override
						public void modify(Object element, Object value) {
							Media media = (Media) element;
							String input = (String) value;
							Double newWidth = new Double(input);
							Size oldSize = media.getSize();
							media.setSize(new Size(newWidth, oldSize.getLength()));
							elementChanged(new ElementChangedEvent(media, MediaColumn.Width.getProperty(), oldSize.getWidth(), newWidth));
						}
					});
		}
	}
	
	private static class LengthColumnFactory implements ColumnViewerColumnFactory {
		@Override
		public IColumnViewerColumn build(int index) {
			return new ColumnViewerColumn(index,
					new ColumnSpecification(MediaColumn.Length.getProperty()).setWidth(150),
					new TextCellEditor(),
					new AbstractColumnValueIO() {
						@Override
						public Object getValue(Object element) {
							Media media = (Media) element;
							return Double.toString(media.getSize().getLength());
						}
						@Override
						public void modify(Object element, Object value) {
							Media media = (Media) element;
							String input = (String) value;
							Double newLength = new Double(input);
							Size oldSize = media.getSize();
							media.setSize(new Size(oldSize.getWidth(), newLength));
							elementChanged(new ElementChangedEvent(media, MediaColumn.Length.getProperty(), oldSize.getLength(), newLength));
						}
					});
		}
	}

	private static class QuantityColumnFactory implements ColumnViewerColumnFactory {
		@Override
		public IColumnViewerColumn build(int index) {
			return new ColumnViewerColumn(index,
					new ColumnSpecification(MediaColumn.Quantity.getProperty()).setWidth(150),
					new TextCellEditor(),
					new AbstractColumnValueIO() {
						@Override
						public Object getValue(Object element) {
							Media media = (Media) element;
							return Integer.toString(media.getQuantity());
						}
						@Override
						public void modify(Object element, Object value) {
							Media media = (Media) element;
							String input = (String) value;
							Integer newQuantity = new Integer(input);
							media.setQuantity(newQuantity);
							elementChanged(new ElementChangedEvent(media, MediaColumn.Quantity.getProperty(), newQuantity));
						}
					});
		}
	}
	
	public Comparator<Price> getComparator() {
		return comparator;
	}

}

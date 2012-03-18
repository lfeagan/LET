package net.vectorcomputing.print.ui.column;

import java.util.Comparator;

import net.vectorcomputing.print.accounting.CartridgeSpecification;
import net.vectorcomputing.print.accounting.Price;
import net.vectorcomputing.ui.ColumnSpecification;
import net.vectorcomputing.ui.viewers.AbstractColumnValueIO;
import net.vectorcomputing.ui.viewers.ColumnViewerColumn;
import net.vectorcomputing.ui.viewers.ColumnViewerColumnFactory;
import net.vectorcomputing.ui.viewers.ElementChangedEvent;
import net.vectorcomputing.ui.viewers.IColumnViewerColumn;

import org.eclipse.jface.viewers.TextCellEditor;

public enum CartridgeSpecificationColumn {
	UUID("UUID", new UUIDColumnFactory(), null),
	Maker("Maker", new MakerColumnFactory(), null),
	Model("Model", new ModelColumnFactory(), null),
	Abbreviation("Abbreviation", new AbbreviationColumnFactory(), null),
	FillVolume("Fill Volume (mL)", new FillVolumeColumnFactory(), null);
	
	private final String property;
	private final ColumnViewerColumnFactory factory;
	private final Comparator<Price> comparator;
	private CartridgeSpecificationColumn(String property, ColumnViewerColumnFactory factory, Comparator<Price> comparator) {
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
			return new ColumnViewerColumn(index, new ColumnSpecification(CartridgeSpecificationColumn.UUID.getProperty()).setHidden(true).setWidth(250), null, null);
		}
	}

	private static class MakerColumnFactory implements ColumnViewerColumnFactory {
		@Override
		public IColumnViewerColumn build(int index) {
			return new ColumnViewerColumn(index, new ColumnSpecification(CartridgeSpecificationColumn.Maker.getProperty()), null, null);
		}
	}
	
	private static class ModelColumnFactory implements ColumnViewerColumnFactory {
		@Override
		public IColumnViewerColumn build(int index) {
			return new ColumnViewerColumn(index, new ColumnSpecification(CartridgeSpecificationColumn.Model.getProperty()), null, null);
		}
	}

	private static class AbbreviationColumnFactory implements ColumnViewerColumnFactory {
		@Override
		public IColumnViewerColumn build(int index) {
			return new ColumnViewerColumn(index, new ColumnSpecification(CartridgeSpecificationColumn.Abbreviation.getProperty()), null, null);
		}
	}

	private static class FillVolumeColumnFactory implements ColumnViewerColumnFactory {
		@Override
		public IColumnViewerColumn build(int index) {
			return new ColumnViewerColumn(index,
					new ColumnSpecification(CartridgeSpecificationColumn.FillVolume.getProperty()).setWidth(150),
					new TextCellEditor(),
					new AbstractColumnValueIO() {
						@Override
						public Object getValue(Object element) {
							CartridgeSpecification spec = (CartridgeSpecification) element;
							return Double.toString(spec.getFillVolume());
						}
						@Override
						public void modify(Object element, Object value) {
							CartridgeSpecification spec = (CartridgeSpecification) element;
							String input = (String) value;
							Double newVolume = new Double(input);
							spec.setFillVolume(newVolume);
							elementChanged(new ElementChangedEvent(spec, CartridgeSpecificationColumn.FillVolume.getProperty(), newVolume));
						}
					});
		}
	}

	public Comparator<Price> getComparator() {
		return comparator;
	}

}

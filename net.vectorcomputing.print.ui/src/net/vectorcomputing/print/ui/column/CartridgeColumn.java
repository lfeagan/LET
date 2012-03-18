package net.vectorcomputing.print.ui.column;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import net.vectorcomputing.print.accounting.Cartridge;
import net.vectorcomputing.print.preferences.PrintPreferences;
import net.vectorcomputing.ui.ColumnSpecification;
import net.vectorcomputing.ui.viewers.AbstractColumnValueIO;
import net.vectorcomputing.ui.viewers.CalendarCellEditor;
import net.vectorcomputing.ui.viewers.ColumnViewerColumn;
import net.vectorcomputing.ui.viewers.ColumnViewerColumnFactory;
import net.vectorcomputing.ui.viewers.ElementChangedEvent;
import net.vectorcomputing.ui.viewers.IColumnViewerColumn;

import org.eclipse.jface.viewers.TextCellEditor;

public enum CartridgeColumn {
	UUID("UUID", new UUIDColumnFactory()),
	Maker("Maker", new MakerColumnFactory()),
	Model("Model", new ModelColumnFactory()),
	Abbreviation("Abbreviation", new AbbreviationColumnFactory()),
	Name("Name", new NameColumnFactory()),
	FillVolume("Fill Volume (mL)", new FillVolumeColumnFactory()),
	RemainingVolume("Remaining Volume (mL)", new RemainingVolumeColumnFactory()),
	InstallDate("Install Date", new InstallDateColumnFactory()),
	DisposalDate("Disposal Date", new DisposalDateColumnFactory()),
	Price("Price", new PriceColumnFactory());
	
	private final String property;
	private final ColumnViewerColumnFactory factory;
	private CartridgeColumn(String property, ColumnViewerColumnFactory factory) {
		this.property = property;
		this.factory = factory;
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
			return new ColumnViewerColumn(index, new ColumnSpecification(CartridgeColumn.UUID.getProperty()).setHidden(true).setWidth(250), null, null);
		}
	}

	private static class MakerColumnFactory implements ColumnViewerColumnFactory {
		@Override
		public IColumnViewerColumn build(int index) {
			return new ColumnViewerColumn(index, new ColumnSpecification(CartridgeColumn.Maker.getProperty()), null, null);
		}
	}
	
	private static class ModelColumnFactory implements ColumnViewerColumnFactory {
		@Override
		public IColumnViewerColumn build(int index) {
			return new ColumnViewerColumn(index, new ColumnSpecification(CartridgeColumn.Model.getProperty()), null, null);
		}
	}

	private static class AbbreviationColumnFactory implements ColumnViewerColumnFactory {
		@Override
		public IColumnViewerColumn build(int index) {
			return new ColumnViewerColumn(index, new ColumnSpecification(CartridgeColumn.Abbreviation.getProperty()), null, null);
		}
	}
	
	private static class NameColumnFactory implements ColumnViewerColumnFactory {
		@Override
		public IColumnViewerColumn build(int index) {
			return new ColumnViewerColumn(index, new ColumnSpecification(CartridgeColumn.Name.getProperty()), null, null);
		}
	}
	
	private static class FillVolumeColumnFactory implements ColumnViewerColumnFactory {
		@Override
		public IColumnViewerColumn build(int index) {
			return new ColumnViewerColumn(index, new ColumnSpecification(CartridgeColumn.FillVolume.getProperty()), null, null);
		}
	}

	private static class RemainingVolumeColumnFactory implements ColumnViewerColumnFactory {
		@Override
		public IColumnViewerColumn build(int index) {
			return new ColumnViewerColumn(index,
					new ColumnSpecification(CartridgeColumn.RemainingVolume.getProperty()),
					new TextCellEditor(),
					new AbstractColumnValueIO() {
						@Override
						public Object getValue(Object element) {
							Cartridge cartridge = (Cartridge) element;
							return Double.toString(cartridge.getRemainingVolume());
						}
						@Override
						public void modify(Object element, Object value) {
							Cartridge cartridge = (Cartridge) element;
							String input = (String) value;
							Double newVolume = new Double(input);
							cartridge.setRemainingVolume(newVolume);
							elementChanged(new ElementChangedEvent(cartridge, CartridgeColumn.RemainingVolume.getProperty(), newVolume));
						}
					});
		}
	}

	private static class InstallDateColumnFactory implements ColumnViewerColumnFactory {
		@Override
		public IColumnViewerColumn build(int index) {
			return new ColumnViewerColumn(index,
					new ColumnSpecification(CartridgeColumn.InstallDate.getProperty()),
					new CalendarCellEditor(new SimpleDateFormat("yyyy-MM-dd")),
					new AbstractColumnValueIO() {
						@Override
						public Object getValue(Object element) {
							Cartridge cartridge = (Cartridge) element;
							Calendar disposal = cartridge.getDisposeDate();
							if (disposal != null) {
								try {
									String formattedDate = PrintPreferences.getDateFormat().format(disposal.getTime());
									return formattedDate;
								} catch (Exception e) {
									e.printStackTrace();
									return "";
								}
							} else {
								return ""; //$NON-NLS-1$
							}
						}

						@Override
						public void modify(Object element, Object value) {
							Cartridge cartridge = (Cartridge) element;
							try {
								String input = (String) value;
								Date date = PrintPreferences.getDateFormat().parse(input);
								GregorianCalendar install = new GregorianCalendar();
								install.setTime(date);
								cartridge.setInstallDate(install);
								elementChanged(new ElementChangedEvent(cartridge, CartridgeColumn.InstallDate.getProperty(), install));
							} catch (ParseException e) {
								// do nothing
							}
						}
					});
		}
	}

	private static class DisposalDateColumnFactory implements ColumnViewerColumnFactory {
		@Override
		public IColumnViewerColumn build(int index) {
			return new ColumnViewerColumn(index,
					new ColumnSpecification(CartridgeColumn.DisposalDate.getProperty()),
					new CalendarCellEditor(new SimpleDateFormat("yyyy-MM-dd")),
					new AbstractColumnValueIO() {
						@Override
						public Object getValue(Object element) {
							Cartridge cartridge = (Cartridge) element;
							Calendar disposal = cartridge.getDisposeDate();
							if (disposal != null) {
								try {
									String formattedDate = PrintPreferences.getDateFormat().format(disposal.getTime());
									return formattedDate;
								} catch (Exception e) {
									e.printStackTrace();
									return "";
								}
							} else {
								return ""; //$NON-NLS-1$
							}
						}

						@Override
						public void modify(Object element, Object value) {
							Cartridge cartridge = (Cartridge) element;

							try {
								String input = (String) value;
								Date date = PrintPreferences.getDateFormat().parse(input);
								GregorianCalendar disposal = new GregorianCalendar();
								disposal.setTime(date);
								cartridge.setDisposeDate(disposal);
								elementChanged(new ElementChangedEvent(cartridge, CartridgeColumn.DisposalDate.getProperty(), disposal));
							} catch (ParseException e) {
								// do nothing
							}
						}
					});
		}
	}
	
	private static class PriceColumnFactory implements ColumnViewerColumnFactory {
		@Override
		public IColumnViewerColumn build(int index) {
			return new ColumnViewerColumn(index, new ColumnSpecification(CartridgeColumn.Price.getProperty()), null, null);
		}
	}

}

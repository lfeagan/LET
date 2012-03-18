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
import net.vectorcomputing.ui.viewers.ElementChangedEvent;
import net.vectorcomputing.ui.viewers.IColumnValueIO;
import net.vectorcomputing.ui.viewers.IColumnViewerColumn;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.TextCellEditor;

public class CartridgeColumns extends AbstractColumnViewerColumns {
	
//	private final IColumnViewerColumn uuidColumn = new UUIDColumn(0);
//	private final IColumnViewerColumn makerColumn = new MakerColumn(1);
//	private final IColumnViewerColumn modelColumn = new ModelColumn(2);
//	private final IColumnViewerColumn abbreviationColumn = new AbbreviationColumn(3);
//	private final IColumnViewerColumn nameColumn = new NameColumn(4);
//	private final IColumnViewerColumn fillVolumeColumn = new FillVolumeColumn(5);
//	private final IColumnViewerColumn remainingVolumeColumn = new RemainingVolumeColumn(6);
//	private final IColumnViewerColumn installDateColumn = new InstallDateColumn(7);
//	private final IColumnViewerColumn disposalDateColumn = new DisposalDateColumn(8);
//	private final IColumnViewerColumn priceColumn = new PriceColumn(9);
			
	public CartridgeColumns(ColumnViewer viewer) {
		super(viewer);
	}
		
	protected final void initializeValues() {
		CartridgeColumn[] columns = CartridgeColumn.values();
		for (int i=0; i < columns.length; ++i) {
			CartridgeColumn column = columns[i];
			values.add(column.newColumnViewerColumn(i));
		}
//		values.add(uuidColumn);
//		values.add(makerColumn);
//		values.add(modelColumn);
//		values.add(abbreviationColumn);
//		values.add(nameColumn);
//		values.add(fillVolumeColumn);
//		values.add(remainingVolumeColumn);
//		values.add(installDateColumn);
//		values.add(disposalDateColumn);
//		values.add(priceColumn);
	}
	
//	public IColumnViewerColumn uuid() { return uuidColumn; }
//	public IColumnViewerColumn maker() { return makerColumn; }
//	public IColumnViewerColumn model() { return modelColumn; }
//	public IColumnViewerColumn abbreviation() { return abbreviationColumn; }
//	public IColumnViewerColumn name() { return nameColumn; }
//	public IColumnViewerColumn fillVolume() { return fillVolumeColumn; }
//	public IColumnViewerColumn remainingVolume() { return remainingVolumeColumn; }
//	public IColumnViewerColumn installDate() { return installDateColumn; }
//	public IColumnViewerColumn disposalDate() { return disposalDateColumn; }
//	public IColumnViewerColumn price() { return priceColumn; }
			
	private static class UUIDColumn extends ColumnViewerColumn implements IColumnViewerColumn {
		public UUIDColumn(int index) {
			super(index, new ColumnSpecification(CartridgeColumn.UUID.getProperty()).setHidden(true).setWidth(250), null, null);
		}
	}

	private static class MakerColumn extends ColumnViewerColumn implements IColumnViewerColumn {
		public MakerColumn(int index) {
			super(index, columnSpecification, cellEditor, valueIO);
		}
		private static final ColumnSpecification columnSpecification = new ColumnSpecification(CartridgeColumn.Maker.getProperty());
		private static final CellEditor cellEditor = null;
		private static final IColumnValueIO valueIO = null;
	}
	
	private static class ModelColumn extends ColumnViewerColumn implements IColumnViewerColumn {
		public ModelColumn(int index) {
			super(index, columnSpecification, cellEditor, valueIO);
		}
		private static final ColumnSpecification columnSpecification = new ColumnSpecification(CartridgeColumn.Model.getProperty());
		private static final CellEditor cellEditor = null;
		private static final IColumnValueIO valueIO = null;
	}

	private static class AbbreviationColumn extends ColumnViewerColumn implements IColumnViewerColumn {
		public AbbreviationColumn(int index) {
			super(index, columnSpecification, cellEditor, valueIO);
		}
		private static final ColumnSpecification columnSpecification = new ColumnSpecification(CartridgeColumn.Abbreviation.getProperty());
		private static final CellEditor cellEditor = null;
		private static final IColumnValueIO valueIO = null;
	}

	private static class NameColumn extends ColumnViewerColumn implements IColumnViewerColumn {
		public NameColumn(int index) {
			super(index, columnSpecification, cellEditor, valueIO);
		}
		private static final ColumnSpecification columnSpecification = new ColumnSpecification(CartridgeColumn.Name.getProperty());
		private static final CellEditor cellEditor = null;
		private static final IColumnValueIO valueIO = null;
	}

	private static class FillVolumeColumn extends ColumnViewerColumn implements IColumnViewerColumn {
		public FillVolumeColumn(int index) {
			super(index, columnSpecification, cellEditor, valueIO);
		}
		private static final ColumnSpecification columnSpecification = new ColumnSpecification(CartridgeColumn.FillVolume.getProperty());
		private static final CellEditor cellEditor = null;
		private static final IColumnValueIO valueIO = null;
	}

	private class RemainingVolumeColumn extends ColumnViewerColumn implements IColumnViewerColumn {
		public RemainingVolumeColumn(int index) {
			super(index,
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

	private class InstallDateColumn extends ColumnViewerColumn implements IColumnViewerColumn {
		public InstallDateColumn(int index) {
			super(index,
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

	private class DisposalDateColumn extends ColumnViewerColumn implements IColumnViewerColumn {
		public DisposalDateColumn(int index) {
			super(index,
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

	private class PriceColumn extends ColumnViewerColumn implements IColumnViewerColumn {
		public PriceColumn(int index) {
			super(index, new ColumnSpecification(CartridgeColumn.Price.getProperty()), null, null);
		}
	}

}

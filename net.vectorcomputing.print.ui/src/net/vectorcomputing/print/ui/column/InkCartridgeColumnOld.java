package net.vectorcomputing.print.ui.column;

//import java.text.SimpleDateFormat;
//import java.util.TreeMap;
//
//import net.vectorcomputing.print.accounting.Cartridge;
//import net.vectorcomputing.ui.ColumnHeaders;
//import net.vectorcomputing.ui.ColumnSpecification;
//import net.vectorcomputing.ui.IColumnSpecification;
//import net.vectorcomputing.ui.viewers.AbstractCellModifier;
//import net.vectorcomputing.ui.viewers.AbstractColumnViewerColumn;
//import net.vectorcomputing.ui.viewers.CalendarCellEditor;
//import net.vectorcomputing.ui.viewers.ColumnValueIO;
//import net.vectorcomputing.ui.viewers.ColumnViewerColumn;
//import net.vectorcomputing.ui.viewers.ColumnViewerColumnUtils;
//
//import org.eclipse.jface.viewers.CellEditor;
//import org.eclipse.jface.viewers.ColumnViewer;
//import org.eclipse.jface.viewers.ICellModifier;
//import org.eclipse.jface.viewers.TableViewer;
//import org.eclipse.jface.viewers.TextCellEditor;
//import org.eclipse.jface.viewers.TreeViewer;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.Table;
//import org.eclipse.swt.widgets.Tree;
//
//@SuppressWarnings("rawtypes")
//enum CartridgeColumn implements ColumnViewerColumn {
//	UUID(new UUIDColumn(0)),
//	Maker(new MakerColumn(1)),
//	Model(new ModelColumn(2)),
//	Abbreviation(new AbbreviationColumn(3)),
//	Name(new NameColumn(4)),
//	FillVolume(new FillVolumeColumn(5)),
//	RemainingVolume(new RemainingVolumeColumn(6)),
//	InstallDate(new InstallDateColumn(7)),
//	DisposalDate(new DisposalDateColumn(8)),
//	Price(new PriceColumn(9));
//	
//	/**
//	 * Stores a sorted map, using a TreeMap, of the columns.
//	 */
//	private static final TreeMap<Integer,CartridgeColumn> map = new TreeMap<Integer,CartridgeColumn>();
//	private static final ColumnHeaders<IColumnSpecification> columnHeaders = new ColumnHeaders<IColumnSpecification>();
//	static {
//		for (CartridgeColumn column : values()) {
//			map.put(column.getIndex(), column);
//			columnHeaders.add(column.getColumnSpecification());
//		}
//	}
//
//	private final int index;
//	private final IColumnSpecification columnSpecification;
//	private final CellEditor cellEditor;
//	final ColumnValueIO valueIO;
//		
//	private CartridgeColumn(ColumnViewerColumn column) {
//		this.index = column.getIndex();
//		this.columnSpecification = column.getColumnSpecification();
//		this.cellEditor = column.getCellEditor();
//		this.valueIO = column.getValueIO();
//	}
//	
//	@Override
//	public int getIndex() { return index; }
//	@Override
//	public String getProperty() { return columnSpecification.getName(); }
//	@Override
//	public IColumnSpecification getColumnSpecification() { return columnSpecification; }
//	@Override
//	public CellEditor getCellEditor() { return cellEditor; }
//	@Override
//	public ColumnValueIO getValueIO() { return valueIO; }
//	
//	public static CartridgeColumn getProperty(String property) {
//		return ColumnViewerColumnUtils.getProperty(values(), property);
//	}
//
//	public static CellEditor[] getCellEditors(Composite parent) {
//		return ColumnViewerColumnUtils.getCellEditors(map, parent);
//	}
//	
//	public static ColumnHeaders<IColumnSpecification> getColumnHeaders() {
//		return columnHeaders;
//	}
//	
//	public static void configureViewer(ColumnViewer viewer) {
//		viewer.setColumnProperties(getColumnProperties());
//		viewer.setCellModifier(new CartridgeColumn.CellModifier(viewer));
//		if (viewer instanceof TableViewer) {
//			TableViewer tableViewer = (TableViewer) viewer;
//			Table table = tableViewer.getTable();
//			getColumnHeaders().configureTableColumns(tableViewer);
//			viewer.setCellEditors(getCellEditors(table));
//		} else if (viewer instanceof TreeViewer) {
//			TreeViewer treeViewer = (TreeViewer) viewer;
//			Tree tree = treeViewer.getTree();
//			getColumnHeaders().configureTreeColumns(treeViewer);
//			viewer.setCellEditors(getCellEditors(tree));
//		} else {
//			throw new RuntimeException("Specified viewer is not a table or a tree viewer");
//		}
//	}
//	
//	private static class CellModifier extends AbstractCellModifier<CartridgeColumn> implements ICellModifier {
//		public CellModifier(ColumnViewer viewer) {
//			super(viewer);
//		}
//		
//		@Override
//		public CartridgeColumn getColumn(String property) {
//			return CartridgeColumn.getProperty(property);
//		}
//	}
//
//	public static String[] getColumnProperties() {
//		return ColumnViewerColumnUtils.getColumnProperties(values());
//	}
//	
//	private static class UUIDColumn extends AbstractColumnViewerColumn implements ColumnViewerColumn {
//		public UUIDColumn(int index) {
//			super(index, columnSpecification, cellEditor, valueIO);
//		}
//		private static final String name = "UUID";
//		private static final ColumnSpecification columnSpecification = new ColumnSpecification(name).setHidden(true).setWidth(250);
//		private static final CellEditor cellEditor = null;
//		private static final ColumnValueIO valueIO = new ColumnValueIO() {
//			@Override
//			public Object getValue(Object element) {
//				Cartridge ink = (Cartridge) element;
//				return ink.getUuid().toString();
//			}
//			@Override
//			public void modify(Object element, Object value) {
////				UUID price = (UUID) element;			
////				String input = (String) value;
////				BigDecimal oldPrice = price.getPrice();
////				BigDecimal newPrice = new BigDecimal(input);
////				price.setPrice(newPrice);
////				elementChanged(new ElementChangedEvent(price, PriceHistoryColumn.Price.getProperty(), oldPrice, newPrice));
//			}
//		};
//	}
//
//	private static class MakerColumn extends AbstractColumnViewerColumn implements ColumnViewerColumn {
//		public MakerColumn(int index) {
//			super(index, columnSpecification, cellEditor, valueIO);
//		}
//		private static final String name = "Maker";
//		private static final ColumnSpecification columnSpecification = new ColumnSpecification(name);
//		private static final CellEditor cellEditor = null;
//		private static final ColumnValueIO valueIO = null;
//	}
//	
//	private static class ModelColumn extends AbstractColumnViewerColumn implements ColumnViewerColumn {
//		public ModelColumn(int index) {
//			super(index, columnSpecification, cellEditor, valueIO);
//		}
//		private static final String name = "Model";
//		private static final ColumnSpecification columnSpecification = new ColumnSpecification(name);
//		private static final CellEditor cellEditor = null;
//		private static final ColumnValueIO valueIO = null;
//	}
//
//	private static class AbbreviationColumn extends AbstractColumnViewerColumn implements ColumnViewerColumn {
//		public AbbreviationColumn(int index) {
//			super(index, columnSpecification, cellEditor, valueIO);
//		}
//		private static final String name = "Abbreviation";
//		private static final ColumnSpecification columnSpecification = new ColumnSpecification(name);
//		private static final CellEditor cellEditor = null;
//		private static final ColumnValueIO valueIO = null;
//	}
//
//	private static class NameColumn extends AbstractColumnViewerColumn implements ColumnViewerColumn {
//		public NameColumn(int index) {
//			super(index, columnSpecification, cellEditor, valueIO);
//		}
//		private static final String name = "Name";
//		private static final ColumnSpecification columnSpecification = new ColumnSpecification(name);
//		private static final CellEditor cellEditor = null;
//		private static final ColumnValueIO valueIO = null;
//	}
//
//	private static class FillVolumeColumn extends AbstractColumnViewerColumn implements ColumnViewerColumn {
//		public FillVolumeColumn(int index) {
//			super(index, columnSpecification, cellEditor, valueIO);
//		}
//		private static final String name = "Fill Volume (mL)";
//		private static final ColumnSpecification columnSpecification = new ColumnSpecification(name);
//		private static final CellEditor cellEditor = new TextCellEditor();
//		private static final ColumnValueIO valueIO = null;
//	}
//
//	private static class RemainingVolumeColumn extends AbstractColumnViewerColumn implements ColumnViewerColumn {
//		public RemainingVolumeColumn(int index) {
//			super(index, columnSpecification, cellEditor, valueIO);
//		}
//		private static final String name = "Remaining Volume (mL)";
//		private static final ColumnSpecification columnSpecification = new ColumnSpecification(name);
//		private static final CellEditor cellEditor = new TextCellEditor();
//		private static final ColumnValueIO valueIO = new ColumnValueIO() {
//			@Override
//			public Object getValue(Object element) {
//				Cartridge cartridge = (Cartridge) element;
//				return Double.toString(cartridge.getRemainingVolume());
//			}
//			@Override
//			public void modify(Object element, Object value) {
//				Cartridge cartridge = (Cartridge) element;
//				String input = (String) value;
//				cartridge.setRemainingVolume(Double.parseDouble(input));
//			}
//		};
//	}
//
//	private static class InstallDateColumn extends AbstractColumnViewerColumn implements ColumnViewerColumn {
//		public InstallDateColumn(int index) {
//			super(index, columnSpecification, cellEditor, valueIO);
//		}
//		private static final String name = "Install Date";
//		private static final ColumnSpecification columnSpecification = new ColumnSpecification(name);
//		private static final CellEditor cellEditor = new CalendarCellEditor(new SimpleDateFormat("yyyy-MM-dd"));
//		private static final ColumnValueIO valueIO = null;
//	}
//
//	private static class DisposalDateColumn extends AbstractColumnViewerColumn implements ColumnViewerColumn {
//		public DisposalDateColumn(int index) {
//			super(index, columnSpecification, cellEditor, valueIO);
//		}
//		private static final String name = "Disposal Date";
//		private static final ColumnSpecification columnSpecification = new ColumnSpecification(name);
//		private static final CellEditor cellEditor = new CalendarCellEditor(new SimpleDateFormat("yyyy-MM-dd"));
//		private static final ColumnValueIO valueIO = null;
//	}
//
//	private static class PriceColumn extends AbstractColumnViewerColumn implements ColumnViewerColumn {
//		public PriceColumn(int index) {
//			super(index, columnSpecification, cellEditor, valueIO);
//		}
//		private static final String name = "Price";
//		private static final ColumnSpecification columnSpecification = new ColumnSpecification(name);
//		private static final CellEditor cellEditor = new TextCellEditor();
//		private static final ColumnValueIO valueIO = null;
//	}
//
//}
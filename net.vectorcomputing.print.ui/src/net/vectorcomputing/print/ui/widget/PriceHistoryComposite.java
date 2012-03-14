package net.vectorcomputing.print.ui.widget;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import net.vectorcomputing.print.accounting.Price;
import net.vectorcomputing.print.accounting.PriceHistory;
import net.vectorcomputing.print.ui.provider.PricesContentProvider;
import net.vectorcomputing.print.ui.provider.PricesTableLabelProvider;
import net.vectorcomputing.ui.ColumnHeaders;
import net.vectorcomputing.ui.ColumnSpecification;
import net.vectorcomputing.ui.viewers.BigDecimalCellEditor;
import net.vectorcomputing.ui.viewers.CalendarCellEditor;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class PriceHistoryComposite extends Composite {
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	private static final ColumnHeaders columnHeaders = new ColumnHeaders();
	
	static {
//		columnHeaders.add(new ColumnSpecification("UUID").setHidden(true).setWidth(250));
		columnHeaders.add(new ColumnSpecification("Date"));
		columnHeaders.add(new ColumnSpecification("Price"));
	}

	private final TableViewer viewer;
	private final Table table;
	private final PriceHistory priceHistory;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public PriceHistoryComposite(Composite parent, int style, final UUID uuid) {
		super(parent, style);
		setLayout(new GridLayout(3, false));
		
		this.priceHistory = PriceHistory.getPriceHistory(uuid);
		
		viewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
		table = viewer.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL,true,true));

		columnHeaders.configureTableColumns(viewer);
		new Label(this, SWT.NONE);
		
		viewer.setContentProvider(new PricesContentProvider());
		viewer.setLabelProvider(new PricesTableLabelProvider(dateFormat));
		viewer.setInput(priceHistory);

		viewer.setColumnProperties(columnHeaders.getNames().toArray(new String[2]));

		viewer.setCellModifier(new ICellModifier() {
			
			@Override
			public void modify(Object element, String property, Object value) {
				TableItem item = (TableItem)element;
				Price price = (Price) item.getData();

				if (property.equals("Date")) {				
					try {
						String input = (String) value;
						Date date = dateFormat.parse(input);
						GregorianCalendar cal = new GregorianCalendar();
						cal.setTime(date);
						price.setDate(cal);
					} catch (ParseException e) {
						// do nothing
					}					
				} else if (property.equals("Price")) {
					String input = (String) value;
					price.setPrice(new BigDecimal(input));
				} 
				
				viewer.update(item.getData(), null);
//				viewer.refresh(); // as an alternative to viewer.update
			}
			
			@Override
			public Object getValue(Object element, String property) {
				Price price = (Price) element;
				if (property.equals("Date")) {
					Calendar date = price.getDate();
					if (date != null) {
						try {
							String formattedDate = dateFormat.format(price.getDate().getTime());
							return formattedDate;
						} catch (Exception e ) {
							e.printStackTrace();
							return "";
						}
					} else {
						return ""; //$NON-NLS-1$
					}
				} else if(property.equals("Price")) {
					return price.getPrice().toString();
				} else {
					return null;
				}
			}
			
			@Override
			public boolean canModify(Object element, String property) {
				return true;
			}
		});

		CellEditor[] editors = new CellEditor[2];
		editors[0] = new CalendarCellEditor(table, dateFormat);
		editors[1] = new BigDecimalCellEditor(table);
		viewer.setCellEditors(editors);
		
		Composite actionComposite = new Composite(this, SWT.NONE);
		actionComposite.setLayout(new GridLayout(1, true));
		actionComposite.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, false, false));
		Button button = new Button(actionComposite, SWT.PUSH);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				priceHistory.addCost(new BigDecimal("0.00"));
				viewer.refresh();
			}
		});
		button.setText("Add");
		
		Button btnNewButton = new Button(actionComposite, SWT.NONE);
		btnNewButton.setText("Delete");
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void refresh() {
		viewer.refresh();
	}
	
}

/*******************************************************************************
 * Copyright 2011 Lance Feagan
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package net.vectorcomputing.photo.ui.widgets;

import net.vectorcomputing.photo.PhotoPlugin;
import net.vectorcomputing.photo.catalog.IPhotoCatalog;
import net.vectorcomputing.photo.factory.IPhotoFactoryRegistry;

import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;

public class PhotoFactoriesList extends Composite {

	private TreeViewer viewer;
	private Tree tree;

	private final IPhotoFactoryRegistry factories = PhotoPlugin.getFactoryRegistry();
	
	public PhotoFactoriesList(Composite parent, int style) {
		super(parent, style | SWT.READ_ONLY);
		setLayout(new FillLayout());
		createComponents();
		viewer.setInput(factories);
//		factories.addListener(this);
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents sub-classing of SWT components
	}
	
	private void createComponents(){		
		viewer = new TreeViewer(this, SWT.SINGLE | SWT.V_SCROLL | SWT.H_SCROLL);
		viewer.setContentProvider(new PhotoFactoriesContentProvider());
		tree = viewer.getTree();
		createHeaders();
	}
	
	private void createHeaders(){
		String[] headers = {"ID", "Name", "Enabled"};
		int[] columnWidths = {100, 100, 100};
		for (int i = 0; i < headers.length; i++){
			TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.NONE);
			column.getColumn().setText(headers[i]);
			column.getColumn().setResizable(true);
			//column.getColumn().setMoveable(true);
			column.getColumn().setWidth(columnWidths[i]);
		}

		viewer.setLabelProvider(new PhotoFactoriesCellLabelProvider());

		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);
	}
	
	public IPhotoCatalog getSelectedCatalog() {
		ITreeSelection selection = (ITreeSelection) viewer.getSelection();
		return (IPhotoCatalog) selection.getFirstElement();
	}

	public ISelectionProvider getViewer() {
		return viewer;
	}
	
	@Override
	public void dispose() {
//		factories.removeListener(this);
		super.dispose();
	}

//	@Override
//	public void handle(IPhotoCatalogEvent event) {
//		this.getDisplay().asyncExec(new CatalogEventHandler(event));
//	}
//	
//	private final class CatalogEventHandler implements Runnable {
//
//		private final IPhotoCatalogEvent event;
//		
//		public CatalogEventHandler(IPhotoCatalogEvent event) {
//			this.event = event;
//		}
//		
//		@Override
//		public void run() {
//			viewer.refresh();		
//		}
//		
//	}
	
}

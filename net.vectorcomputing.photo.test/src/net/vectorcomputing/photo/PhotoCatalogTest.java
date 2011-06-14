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
package net.vectorcomputing.photo;

import net.vectorcomputing.photo.catalog.IPhotoCatalog;
import net.vectorcomputing.photo.catalog.IPhotoCatalogs;
import net.vectorcomputing.photo.library.IPhoto;
import net.vectorcomputing.photo.library.IPhotoLibrary;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.junit.Test;

public class PhotoCatalogTest {

//	@Test
//	public void library() {
//		IPhotoLibrary root = PhotoPlugin.getLibrary();
//		System.out.println("root.name = " + root.getName());
//		
//	}
	
	@Test
	public void foo() {
//		IPath smallPath = new Path("/tmp/catalog");
//		IPath largePath = new Path("/Volumes/photo/Soccer/Stoll Park/2011-04-29_vs_Waldo_United");
//		IPath altpath = new Path("/Volumes/photo/TakeYourChildToWork");
		IPath allPhotos = new Path("/Volumes/photo/Soccer");
		
		try {
			IPhotoCatalogs catalogs = PhotoPlugin.getCatalogs();
			
			IPhotoCatalog pc = catalogs.create(allPhotos);
			
//			System.out.println(pc.isAvailable());
//			for (IPhoto photo : pc.getPhotos()) {
//				System.out.println(photo.getFileStore().toURI().toString());
//			}
		} catch (Exception e) {
			e.printStackTrace();
		};
	}
	
}

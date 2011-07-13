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
package net.vectorcomputing.photo.internal.catalog;

import java.util.Calendar;

import net.vectorcomputing.photo.catalog.IPhotoCatalogUpdateStatistics;

public class PhotoCatalogUpdateStatistics implements IPhotoCatalogUpdateStatistics {
	
	private final long beginTimeInMillis = System.currentTimeMillis();
	private Calendar beginTime = null;
	
	private long endTimeInMillis = Long.MIN_VALUE;
	private Calendar endTime = null;
	
	private int filesRead;
	private int photosRead;
	private int photosAdded;
	private int photosPurged;
	
	public PhotoCatalogUpdateStatistics() {
	}
	
	@Override
	public Calendar getBeginTime() {
		if (beginTime == null) {
			beginTime = Calendar.getInstance();
			beginTime.setTimeInMillis(beginTimeInMillis);
		}
		return beginTime;
	}

	@Override
	public Calendar getEndTime() {
		if (endTimeInMillis != Long.MIN_VALUE && endTime == null) {
			endTime = Calendar.getInstance();
			endTime.setTimeInMillis(endTimeInMillis);
		}
		return endTime;
	}

	@Override
	public float getElapsedTime() {
		if (endTimeInMillis != Long.MIN_VALUE) {
			float elapsed = endTimeInMillis - beginTimeInMillis;
			return elapsed / 1000.0f;
		} else {
			return Float.NaN;
		}
	}

	public void setStopTime() {
		endTimeInMillis = System.currentTimeMillis();
	}
	
	public float getRate() {
//		double rate = ((double)pc.getPhotos().size()) / (((double) elapsedTime) / 1000.0);
		float rate = ((float) filesRead) / getElapsedTime();
		return rate;
	}
	
	public void incrementFilesReadCounter() {
		++filesRead;
	}
	
	public void incrementPhotosRead() {
		++photosRead;
	}
	
	public int getNumberOfPhotosRead() {
		return photosRead;
	}
	
	public void incrementPhotosAdded() {
		++photosAdded;
	}

	public int getNumberOfPhotosAdded() {
		return photosAdded;
	}

	public void incrementPhotosPurged() {
		++photosPurged;
	}
	
	public int getNumberOfPhotosPurged() {
		return photosPurged;
	}
	

	
	
}

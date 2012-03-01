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
package net.vectorcomputing.build;

import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.IJobManager;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

public class ImportProjectsApplication implements IApplication {

	private final IWorkspace workspace;
	private final IPath workspacePath;

	public ImportProjectsApplication() {
		workspace = ResourcesPlugin.getWorkspace();
		workspacePath = workspace.getRoot().getLocation();		
	}
	
	@Override
	public Object start(IApplicationContext context) throws Exception {
		
		IJobManager jobManager = Job.getJobManager();
		jobManager.addJobChangeListener(new MyJobChangeListener());
		
		int returnCode = 0;
		try {
			importExistingProjects();
		} catch (Exception e) {
			returnCode = 1;
		}
		
		waitForNoNewBuilds();
		
		ResourcesPlugin.getWorkspace().save(true, new NullProgressMonitor());
		
		waitOnJobCompletion();
		
		return returnCode;
	}

	@Override
	public void stop() {

	}
	
	private void importExistingProjects() {
		List<IPath> folders = findPotentialProjectFolders();
		for (IPath folder : folders) {
			try {
				importProject(folder);
			} catch (Exception e) {
				String message = MessageFormat.format("Unable to import folder: {0}\nException: {1}", folder.toOSString(), e.getMessage());
				System.err.println(message);
			}
		}
	}
	
	private List<IPath> findPotentialProjectFolders() {
		List<IPath> folders = new ArrayList<IPath>();
		File[] files = workspacePath.toFile().listFiles();
		for (File file : files) {
			if (isPotentialProject(file)) {
				folders.add(new Path(file.getPath()));
			}
		}
		return folders;
	}

	/**
	 * A file is likely to be a project if:
	 * <ol>
	 * <li>the file is a directory</li>
	 * <li>the directory name does not start with the period (dot) character</li>
	 * <li>the directory contains a .project file</li>
	 * </ol>
	 */
	private static final boolean isPotentialProject(File file) {
		// a project must be a directory
		if (!file.isDirectory()) {
			return false;
		}
		// the directory name must not start with "."
		if (file.getName().startsWith(".")) { //$NON-NLS-1$
			return false;
		}
		// the directory must contain a project description file
		for (File child : file.listFiles()) {
			if (child.getName().equals(IProjectDescription.DESCRIPTION_FILE_NAME)) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Imports the given path into the workspace as a project. Returns true if
	 * the operation succeeded, false if it failed to import due to an overlap.
	 * 
	 * @param projectPath
	 *            the path to the project to be imported
	 * @return <code>true</code> if the project was successfully imported
	 * @throws CoreException
	 *             if unable to perform a key operation required to import the
	 *             project
	 */
	private boolean importProject(IPath projectPath) throws CoreException {
		final IPath descriptionPath = projectPath.addTrailingSeparator().append(IProjectDescription.DESCRIPTION_FILE_NAME);
		final IProjectDescription description = workspace.loadProjectDescription(descriptionPath);
		final IProject project = workspace.getRoot().getProject(description.getName());

		if (project.exists()) {
			String message = MessageFormat.format("Skipping project {0}", project.getName());
			BuildPlugin.getPlugin().getLog().log(new Status(IStatus.INFO, BuildPlugin.PLUGIN_ID, message));
			return false;
		} else {
			String message = MessageFormat.format("Importing project {0}", project.getName());
			BuildPlugin.getPlugin().getLog().log(new Status(IStatus.INFO, BuildPlugin.PLUGIN_ID, message));
			importProject(description, project);
			return true;
		}
	}

	private void importProject(final IProjectDescription description,
			final IProject project) throws CoreException {
		IWorkspaceRunnable runnable = new IWorkspaceRunnable() {
			public void run(IProgressMonitor monitor) throws CoreException {
				project.create(description, monitor);
				project.open(IResource.NONE, monitor);
			}
		};
		workspace.run(runnable,
				workspace.getRuleFactory().modifyRule(workspace.getRoot()),
				IResource.NONE, null);
	}
	
	private void waitOnJobCompletion() {
		System.out.println("Waiting...");
		IJobManager jobManager = Job.getJobManager();
		try {
			jobManager.join(ResourcesPlugin.FAMILY_AUTO_REFRESH, new NullProgressMonitor());
			jobManager.join(ResourcesPlugin.FAMILY_AUTO_BUILD, new NullProgressMonitor());
			Thread.currentThread().sleep(5000);
			jobManager.join(ResourcesPlugin.FAMILY_AUTO_BUILD, new NullProgressMonitor());
		} catch (OperationCanceledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("Done Waiting");
		}
	}
	
	private void waitForNoNewBuilds() {
		IJobManager jobManager = Job.getJobManager();
		int count = 0;
		while (count <= 5) {
			try {
				Thread.sleep(5000);
				Job[] autoBuildJobs = jobManager.find(ResourcesPlugin.FAMILY_AUTO_BUILD);
				Job[] manualBuildJobs = jobManager.find(ResourcesPlugin.FAMILY_MANUAL_BUILD);
				System.out.println("build job counts, auto=" + autoBuildJobs.length +" manual=" + manualBuildJobs.length);
				if (autoBuildJobs.length == 0 && manualBuildJobs.length == 0) {
					System.out.println("incrementing build job count");
					++count;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static class MyJobChangeListener implements IJobChangeListener {
		IJobManager jobManager = Job.getJobManager();
		
		@Override
		public void aboutToRun(IJobChangeEvent event) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void awake(IJobChangeEvent event) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void done(IJobChangeEvent event) {
			System.out.println("Job Done: " + event.getJob());
			//printJobs();
		}
		
		private void printJobs() {
			Job[] jobs = jobManager.find(null);
			for (Job job : jobs) {
				System.out.println(job + " " + job.getState());
			}
		}

		@Override
		public void running(IJobChangeEvent event) {
			System.out.println("Job Running: " + event.getJob().getName());
		}

		@Override
		public void scheduled(IJobChangeEvent event) {
			System.out.println("Job Scheduled: " + event.getJob().getName());
		}

		@Override
		public void sleeping(IJobChangeEvent event) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}

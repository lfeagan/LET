package net.vectorcomputing.ui;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.IStructuredSelection;

public class ProjectUtils {
	
	public static IProject getContainingProject(IStructuredSelection selection) {
		if (selection != null && !selection.isEmpty()) {
			Object selectedElement = selection.getFirstElement();
			if (selectedElement instanceof IAdaptable) {
				IAdaptable adaptable = (IAdaptable) selectedElement;
				IResource resource = (IResource) adaptable.getAdapter(IResource.class);
				return getContainingProject(resource);
			}
		}
		return null;
	}

	public static IProject getContainingProject(IResource resource) {
		if (resource != null && resource.getType() != IResource.ROOT) {
			while (resource.getType() != IResource.PROJECT) {
				resource = resource.getParent();
			}
			
			if (resource != null) {
				return (IProject) resource;
			}
		}
		return null;
	}
	
}

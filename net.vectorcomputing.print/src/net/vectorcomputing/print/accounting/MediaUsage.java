package net.vectorcomputing.print.accounting;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Describes how much media was consumed; typically used as an embedded element
 * in a print job.
 * 
 * @author lfeagan
 * @since 1.0
 * @noextend This class is not intended to be subclassed by clients.
 */
@Embeddable
public class MediaUsage {

	public MediaUsage() {
		
	}
	
	@Column(name="mediaSpecification", nullable=false)
	private Media media;
	public Media getMediaSpecification() {
		return media;
	}
	void setMediaSpecification(Media media) {
		this.media = media;
	}
	
	@Column(name="size", nullable=false)
	private Size size;
	public Size getSize() {
		return size;
	}
	void setSize(Size size) {
		this.size = size;
	}
	
}

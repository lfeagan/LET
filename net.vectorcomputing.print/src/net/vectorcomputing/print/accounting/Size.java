package net.vectorcomputing.print.accounting;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Size {
	
	public Size(double width, double length) {
		this.width = width;
		this.length = length;
	}

	@Column(name="width", nullable=false)
	private double width;
	public double getWidth() { return width; }
	@SuppressWarnings("unused")
	private void setWidth(double width) { this.width = width; }

	@Column(name="length", nullable=false)
	private double length;
	public double getLength() { return length; }
	@SuppressWarnings("unused")
	private void setLength(double length) { this.length = length; }
	
	public Size() {
		this.width = 0;
		this.length = 0;
	}

	public double getArea() {
		return width * length;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(length);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(width);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Size)) {
			return false;
		}
		Size other = (Size) obj;
		if (Double.doubleToLongBits(length) != Double
				.doubleToLongBits(other.length)) {
			return false;
		}
		if (Double.doubleToLongBits(width) != Double
				.doubleToLongBits(other.width)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Size [width=");
		builder.append(width);
		builder.append(", length=");
		builder.append(length);
		builder.append("]");
		return builder.toString();
	}
	
}
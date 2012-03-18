package net.vectorcomputing.print.accounting;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CartridgeUsage {

	public CartridgeUsage() {
		
	}
	
	@Column(name="Cartridge", nullable=false)
	private Cartridge cartridge;
	public Cartridge getCartridge() {
		return cartridge;
	}
	void setCartridge(Cartridge cartridge) {
		this.cartridge = cartridge;
	}
	
	@Column(name="volume", nullable=false)
	private double volume;
	public double getVolume() {
		return volume;
	}
	void setVolume(double volume) {
		this.volume = volume;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cartridge == null) ? 0 : cartridge.hashCode());
		long temp;
		temp = Double.doubleToLongBits(volume);
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
		if (!(obj instanceof CartridgeUsage)) {
			return false;
		}
		CartridgeUsage other = (CartridgeUsage) obj;
		if (cartridge == null) {
			if (other.cartridge != null) {
				return false;
			}
		} else if (!cartridge.equals(other.cartridge)) {
			return false;
		}
		if (Double.doubleToLongBits(volume) != Double.doubleToLongBits(other.volume)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InkUsage [Cartridge=");
		builder.append(cartridge);
		builder.append(", volume=");
		builder.append(volume);
		builder.append("]");
		return builder.toString();
	}
	
}

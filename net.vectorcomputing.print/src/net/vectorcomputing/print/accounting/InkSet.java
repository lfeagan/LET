package net.vectorcomputing.print.accounting;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class InkSet {
	
	private final Map<InkCartridge, Double> inks = new HashMap<InkCartridge, Double>();

	public void addCartridges(Collection<? extends InkCartridge> cartridges) {
		for (InkCartridge cartridge : cartridges) {
			inks.put(cartridge, 0.0d);
		}
	}
	
	public Set<InkCartridge> getInks() {
		return inks.keySet();
	}
	
	public void useInk(InkCartridge ink, double volume) {
		
	}
	
	public Double getUsage(InkCartridge ink) {
		Double usage = inks.get(ink);
		if (usage == null) {
			throw new IllegalArgumentException("ink not found");
		}
		
		return usage;
	}

}

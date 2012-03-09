package net.vectorcomputing.print.accounting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Printer {

	private static final List<Printer> printers = new ArrayList<Printer>();
	
	private final UUID uuid;
	private final String name;
	private final Set<InkCartridgeSpecification> cartridgeSpecifications = new HashSet<InkCartridgeSpecification>();
	private final InkSet loadedCartridges = new InkSet();
	
	public Printer getPrinter(UUID uuid) {
		for (Printer printer : printers) {
			if (printer.getUUID().equals(uuid)) {
				return printer;
			}
		}
		return null;
	}
	
	public Printer(UUID uuid, String name, Collection<? extends InkCartridgeSpecification> cartridgeSpecifications, Collection<? extends InkCartridge> loadedCartridges) {
		this.uuid = uuid;
		this.name = name;
		this.cartridgeSpecifications.addAll(cartridgeSpecifications);
//		this.loadedCartridges.addAll(loadedCartridges);
	}
	
	public UUID getUUID() {
		return uuid;
	}
	
	public String getName() {
		return this.name;
	}

	public Set<InkCartridgeSpecification> getCartridgeSpecifications() {
		return Collections.unmodifiableSet(cartridgeSpecifications);
	}
	
//	public Set<InkCartridge> getLoadedCartridges() {
//		return Collections.unmodifiableSet(loadedCartridges);
//	}
		
}

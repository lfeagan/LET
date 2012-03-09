package net.vectorcomputing.print.accounting;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Printers {

	private static final Set<Printer> printers = new HashSet<Printer>();
	
	public static final Printer get(UUID uuid) {
		for (Printer printer : printers) {
			if (printer.getUUID().equals(uuid)) {
				return printer;
			}
		}
		return null;
	}
	
	public static final Set<Printer> getAll() {
		return Collections.unmodifiableSet(printers);
	}
	
	public static final Printer add(String name, Collection<? extends InkCartridgeSpecification> cartridgeSpecifications, Collection<? extends InkCartridge> loadedCartridges) {
		return add(newUUID(), name, cartridgeSpecifications, loadedCartridges);
	}

	private static UUID newUUID() {
		return UUID.randomUUID();
	}
	
	public static final Printer add(UUID uuid, String name, Collection<? extends InkCartridgeSpecification> cartridgeSpecifications, Collection<? extends InkCartridge> loadedCartridges) {
		Printer printer = new Printer(uuid, name, cartridgeSpecifications, loadedCartridges);
		printers.add(printer);
		return printer;
	}
	
}

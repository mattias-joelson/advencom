package org.joelson.mattias.advencom.events;

import org.joelson.mattias.advencom.model.Event;

import java.util.function.Supplier;

public enum EventType {

    MOTHERLAND("Motherland", Motherland::getMotherlandEvent);

    private final String name;
    private final Supplier<Event> eventSupplier;

    EventType(String name, Supplier<Event> eventSupplier) {
        this.name = name;
        this.eventSupplier = eventSupplier;
    }

    public String getName() {
        return name;
    }

    public Event getEvent() {
        return eventSupplier.get();
    }
}

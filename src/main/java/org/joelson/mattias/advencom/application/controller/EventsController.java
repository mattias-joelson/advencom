package org.joelson.mattias.advencom.application.controller;

import org.joelson.mattias.advencom.application.view.ActionBuilder;
import org.joelson.mattias.advencom.events.EventType;

import javax.swing.Action;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public class EventsController {

    private final ApplicationController applicationController;
    private final Map<String, EventController> eventControllers;

    public EventsController(ApplicationController applicationController) {
        this.applicationController = applicationController;
        eventControllers = new HashMap<>();
    }

    public EventController getOrCreateEventController(EventType eventType) {
        EventController eventController = eventControllers.get(eventType.getName());
        if (eventController == null) {
            eventController = new EventController(applicationController, eventType.getEvent());
            eventControllers.put(eventType.getName(), eventController);
        }
        return eventController;
    }

    public Action getShowEventViewAction(EventType eventType) {
        return new ActionBuilder(actionEvent -> showEventView(actionEvent, eventType))
                .withName("Show " + eventType.getName() + " event")
                .build();
    }

    public void showEventView(ActionEvent actionEvent, EventType eventType) {
        getOrCreateEventController(eventType).showView(actionEvent);
    }
}

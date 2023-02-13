package org.joelson.mattias.advencom.application.controller;

import org.joelson.mattias.advencom.application.model.EventModel;
import org.joelson.mattias.advencom.application.view.EventView;
import org.joelson.mattias.advencom.application.view.IndustryView;
import org.joelson.mattias.advencom.model.Event;

import java.awt.event.ActionEvent;

public class EventController {

    private final ApplicationController applicationController;
    private final EventModel eventModel;
    private final EventView eventView;

    public EventController(ApplicationController applicationController, Event event) {
        this.applicationController = applicationController;
        eventModel = createEventModel(event);
        eventView = createEventView(event, this);
    }

    private static EventModel createEventModel(Event event) {
        return new EventModel(event);
    }

    private static EventView createEventView(Event event, EventController eventController) {
        return new EventView(event, eventController);
    }

    public void showView(ActionEvent actionEvent) {
        applicationController.getApplicationView().setPane(eventView.getContentPane());
    }

    public void calculateProduction(IndustryView industryView) {
        System.out.println("Calculate " + industryView);
    }
}

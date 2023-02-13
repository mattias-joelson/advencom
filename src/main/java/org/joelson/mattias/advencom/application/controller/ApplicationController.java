package org.joelson.mattias.advencom.application.controller;

import org.joelson.mattias.advencom.application.view.ApplicationView;

public class ApplicationController {

    private ApplicationView applicationView;
    private final EventsController eventsController;

    public ApplicationController() {
        eventsController = new EventsController(this);
    }

    public void setApplicationView(ApplicationView applicationView) {
        this.applicationView = applicationView;
    }

    public ApplicationView getApplicationView() {
        return applicationView;
    }

    public void closeApplication() {
        //applicationData.closeDatabase();
        applicationView.dispose();
        System.exit(0);
    }

    public EventsController getEventsController() {
        return eventsController;
    }
}

package org.joelson.mattias.advencom.application.controller;

import org.joelson.mattias.advencom.application.view.ApplicationView;

public class ApplicationController {

    private ApplicationView applicationView;

    public void setApplicationUI(ApplicationView applicationView) {
        this.applicationView = applicationView;
    }

    public void closeApplication() {
        //applicationData.closeDatabase();
        applicationView.dispose();
        System.exit(0);
    }
}

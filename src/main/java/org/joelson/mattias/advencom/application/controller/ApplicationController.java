package org.joelson.mattias.advencom.application.controller;

import org.joelson.mattias.advencom.application.view.ApplicationUI;

public class ApplicationController {

    private ApplicationUI applicationUI;

    public void setApplicationUI(ApplicationUI applicationUI) {
        this.applicationUI = applicationUI;
    }

    public void closeApplication() {
        //applicationData.closeDatabase();
        applicationUI.dispose();
        System.exit(0);
    }
}

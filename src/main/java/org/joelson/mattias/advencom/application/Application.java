package org.joelson.mattias.advencom.application;

import org.joelson.mattias.advencom.application.controller.ApplicationActions;
import org.joelson.mattias.advencom.application.view.ApplicationUI;

public class Application {

    private final ApplicationUI applicationUI;

    public Application() {
        ApplicationActions applicationActions = new ApplicationActions();
        applicationUI = new ApplicationUI(applicationActions);
        applicationActions.setApplicationUI(applicationUI);
    }

    public void show() {
        applicationUI.show();
    }
}

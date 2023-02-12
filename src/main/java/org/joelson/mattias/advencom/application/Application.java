package org.joelson.mattias.advencom.application;

import org.joelson.mattias.advencom.application.controller.ApplicationController;
import org.joelson.mattias.advencom.application.view.ApplicationUI;

public class Application {

    private final ApplicationUI applicationUI;

    public Application() {
        ApplicationController applicationController = new ApplicationController();
        applicationUI = new ApplicationUI(applicationController);
        applicationController.setApplicationUI(applicationUI);
    }

    public void show() {
        applicationUI.show();
    }
}

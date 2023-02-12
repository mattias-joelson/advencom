package org.joelson.mattias.advencom.application;

import org.joelson.mattias.advencom.application.controller.ApplicationController;
import org.joelson.mattias.advencom.application.view.ApplicationView;

public class Application {

    private final ApplicationView applicationView;

    public Application() {
        ApplicationController applicationController = new ApplicationController();
        applicationView = new ApplicationView(applicationController);
        applicationController.setApplicationView(applicationView);
    }

    public void show() {
        applicationView.show();
    }
}

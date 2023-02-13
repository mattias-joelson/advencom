package org.joelson.mattias.advencom.application.view;

import org.joelson.mattias.advencom.application.controller.ApplicationController;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.desktop.QuitStrategy;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ApplicationView {

    private static final String APPLICATION_TITLE = "AdVenture Communist Calculator";

    private final ApplicationController applicationController;

    private final JFrame applicationFrame;
    private final JLabel statusLabel;
    private Container currentContent;

    public ApplicationView(ApplicationController applicationController) {
        this.applicationController = applicationController;
        applicationFrame = createApplicationFrame();
        statusLabel = createContent(applicationFrame.getContentPane());
    }

    private JFrame createApplicationFrame() {
        JFrame frame = new JFrame(APPLICATION_TITLE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width / 2, screenSize.height /2);
        frame.setLocation(screenSize.width / 4, screenSize.height / 4);
        frame.setExtendedState(frame.getExtendedState() | Frame.MAXIMIZED_BOTH);
        frame.setJMenuBar(MenuBuilder.createApplicationMenu(applicationController));
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(createFrameWindowListener());
        Desktop.getDesktop().disableSuddenTermination();
        try {
            Desktop.getDesktop().setQuitStrategy(QuitStrategy.CLOSE_ALL_WINDOWS);
        } catch (UnsupportedOperationException e) {
            // ignore, not available on all platforms
        }
        return frame;
    }

    private WindowListener createFrameWindowListener() {
        return new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                applicationController.closeApplication();
            }
        };
    }

    private JLabel createContent(Container contentPane) {
        contentPane.setLayout(new BorderLayout());
        Container emptyContainer = new Container();
        contentPane.add(emptyContainer, BorderLayout.CENTER);
        currentContent = emptyContainer;
        JLabel statusLabel = new JLabel("<no user>"); //NON-NLS
        contentPane.add(statusLabel, BorderLayout.PAGE_END);
        return statusLabel;
    }
    public void show() {
        applicationFrame.setVisible(true);
    }

    public void dispose() {
        applicationFrame.dispose();
    }

    private void clearPane() {
        setPane(new Container());
    }

    public void setPane(Container container) {
        applicationFrame.getContentPane().remove(currentContent);
        JScrollPane scrollPane = new JScrollPane(container, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        applicationFrame.getContentPane().add(currentContent = scrollPane, BorderLayout.CENTER);
        applicationFrame.getContentPane().revalidate();
        applicationFrame.getContentPane().repaint();
    }
}

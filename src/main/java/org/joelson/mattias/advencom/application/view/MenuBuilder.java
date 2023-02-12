package org.joelson.mattias.advencom.application.view;

import org.joelson.mattias.advencom.application.controller.ApplicationController;

import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.Toolkit;
import java.awt.event.InputEvent;

public class MenuBuilder {

    private MenuBuilder() throws IllegalAccessException {
        throw new IllegalAccessException("Should not be instantiated!");
    }
    static JMenuBar createApplicationMenu(ApplicationController applicationController, ApplicationView applicationView) {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = addMenu(menuBar,"File");
        addMenuItem(fileMenu, "Quit", createMenuShortcutAccelerator('Q'), applicationController::closeApplication);

        JMenu motherlandMenu = addMenu(menuBar, "Motherland");
        addMenuItem(motherlandMenu, applicationView.motherlandViewAction());

        return menuBar;
    }

    private static JMenu addMenu(JMenuBar menuBar, String caption) {
        JMenu menu = new JMenu(caption);
        menuBar.add(menu);
        return menu;
    }

    private static JMenuItem addMenuItem(JMenu menu, Action action) {
        JMenuItem menuItem = new JMenuItem(action);
        menu.add(menuItem);
        return menuItem;
    }

    private static JMenuItem addMenuItem(JMenu menu, String caption) {
        return addMenuItem(menu, caption, null, null);
    }

    private static JMenuItem addMenuItem(JMenu menu, String caption, KeyStroke accelerator, Runnable action) {
        JMenuItem menuItem = new JMenuItem(caption);
        menu.add(menuItem);
        if (accelerator != null) {
            menuItem.setAccelerator(accelerator);
        }
        if (action != null) {
            menuItem.addActionListener(e -> action.run());
        }
        return menuItem;
    }

    private static KeyStroke createMenuShortcutAccelerator(char ch) {
        return KeyStroke.getKeyStroke(ch, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx());
    }

    private static KeyStroke createMenuShiftedShortcutAccelerator(char ch) {
        return KeyStroke.getKeyStroke(ch, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx() | InputEvent.SHIFT_DOWN_MASK);
    }
}

package controller;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import modell.LightOnModell;
import view.LightOnGUI;

public class LightOnController {

    private LightOnModell modell;
    private LightOnGUI view;

    public LightOnController(LightOnModell modell, LightOnGUI view) {
        this.modell = modell;
        this.view = view;

        
        view.setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitConfirm();
            }
        });

        modell.newGame(); 
        initListeners();       
        initMenuListeners();
        updateView();
    }

    
    private void initListeners() {
        var buttons = view.getButtons();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int x = i;
                int y = j;

                buttons[i][j].addActionListener(e -> {
                    modell.toggleAround(x, y);
                    updateView();
                });
            }
        }
    }

   
    private void initMenuListeners() {
        
        view.getMnuNewgame().addActionListener(e -> {
            modell.newGame();
            updateView();
        });

        
        view.getMnuExit().addActionListener(e -> exitConfirm());
    }

    
    private void updateView() {
        var buttons = view.getButtons();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setBackground(
                    modell.isOn(i, j) ? Color.YELLOW : new Color(0,153,0)
                );
            }
        }

        view.getClickField().setText(String.valueOf(modell.getClickCount()));

        
        if (modell.isAllOn()) {
            javax.swing.JOptionPane.showMessageDialog(
                view, 
                "Congratulation! You win! \n Final number of clicks: " + modell.getClickCount()
            );
        }
    }

    
    private void exitConfirm() {
        int answer = javax.swing.JOptionPane.showConfirmDialog(
                view,
                "Do you want to exit?",
                "Confirm exit",
                javax.swing.JOptionPane.YES_NO_OPTION
        );

        if (answer == javax.swing.JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}

package controller;

import java.awt.Color;
import modell.LightOnModell;
import view.LightOnGUI;

public class LightOnController {

    private LightOnModell modell;
    private LightOnGUI view;

    public LightOnController(LightOnModell modell, LightOnGUI view) {
        this.modell = modell;
        this.view = view;

        modell.newGame();
        initListeners();
        updateView();
        initMenuListeners();
        
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
            javax.swing.JOptionPane.showMessageDialog(view, "Congratulations! You Win!\n Final number of clicks:"+modell.getClickCount());
        }

    }
    
    private void initMenuListeners() {
    view.getMnuNewgame().addActionListener(e -> {
        modell.newGame();
        updateView();
    });
}

}

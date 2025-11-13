
package modell;

public class LightOnModell {
    private boolean[][] grid = new boolean[3][3];
    private int clickCount = 0;

    public boolean isOn(int i, int j) {
        return grid[i][j];
    }

    public int getClickCount() {
        return clickCount;
    }

    public void toggle(int i, int j) {
        grid[i][j] = !grid[i][j];
    }

    public void toggleAround(int i, int j) {
        clickCount++;

        toggle(i, j);
        if (i > 0) toggle(i - 1, j);
        if (i < 2) toggle(i + 1, j);
        if (j > 0) toggle(i, j - 1);
        if (j < 2) toggle(i, j + 1);
    }
    
    public boolean isAllOn() {
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            if (!grid[i][j]) {
                return false;
            }
        }
    }
    return true;
}
    public void newGame() {
    java.util.Random rnd = new java.util.Random();
    clickCount = 0;

    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            
            grid[i][j] = rnd.nextBoolean();
        }
    }
}

}


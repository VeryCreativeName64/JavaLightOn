package test;
import modell.LightOnModell;
import java.io.File;

public class LightOnTest {




    public static void main(String[] args) throws Exception {
        testToggleFlipsSingleCell();
        testToggleAroundFlipsFiveCells();
        testAllOnDetection();
        testClickCountIncrements();
        testSaveAndLoadKeepsState();

        System.out.println("\n✔ MINDEN TESZT SIKERESEN ÁTMENT!");
    }

    
    private static void testToggleFlipsSingleCell() {
        LightOnModell model = new LightOnModell();
        model.newGame();

        boolean before = model.isOn(1, 1);
        model.toggle(1, 1);
        boolean after = model.isOn(1, 1);

        assert before != after : "❌ toggle() nem váltotta át a cellát!";
        System.out.println("✔ testToggleFlipsSingleCell: sikeres");
    }

  
    private static void testToggleAroundFlipsFiveCells() {
        LightOnModell model = new LightOnModell();

        // minden cella legyen OFF (false)
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (model.isOn(i,j)) model.toggle(i,j);

        model.toggleAround(1, 1);

        assert model.isOn(1, 1) : "❌ középső cella nincs bekapcsolva!";
        assert model.isOn(0, 1) : "❌ felső szomszéd nincs bekapcsolva!";
        assert model.isOn(2, 1) : "❌ alsó szomszéd nincs bekapcsolva!";
        assert model.isOn(1, 0) : "❌ bal szomszéd nincs bekapcsolva!";
        assert model.isOn(1, 2) : "❌ jobb szomszéd nincs bekapcsolva!";

        System.out.println("✔ testToggleAroundFlipsFiveCells: sikeres");
    }

   
    private static void testAllOnDetection() {
        LightOnModell model = new LightOnModell();

        // minden cellát ON-ra váltunk
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (!model.isOn(i,j)) model.toggle(i,j);

        assert model.isAllOn() : "❌ isAllOn() nem ismerte fel, hogy minden cella be van kapcsolva!";
        System.out.println("✔ testAllOnDetection: sikeres");
    }

   
    private static void testClickCountIncrements() {
        LightOnModell model = new LightOnModell();

        model.toggleAround(1,1);
        assert model.getClickCount() == 1 : "❌ ClickCount nem 1 kattintás után!";

        model.toggleAround(0,0);
        assert model.getClickCount() == 2 : "❌ ClickCount nem 2 kattintás után!";

        System.out.println("✔ testClickCountIncrements: sikeres");
    }

    
    private static void testSaveAndLoadKeepsState() throws Exception {
        LightOnModell m1 = new LightOnModell();

        m1.toggle(0,0);
        m1.toggle(1,2);
        m1.toggleAround(1,1);   

        File f = File.createTempFile("lighton_test", ".txt");
        m1.saveGame(f);

        LightOnModell m2 = new LightOnModell();
        m2.loadGame(f);

        
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                assert m1.isOn(i,j) == m2.isOn(i,j)
                        : "❌ cella eltér betöltés után: ("+i+","+j+")";

        assert m1.getClickCount() == m2.getClickCount()
                : "❌ kattintásszám eltér betöltés után!";

        System.out.println("✔ testSaveAndLoadKeepsState: sikeres");
    }
}



package test.java.heat;

import main.java.heat.HeatMap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HeatMapTest {
    @Test
    void zero() {
        double[][] heat = new double[10][10];
        HeatMap heatMap = new HeatMap(heat);
        HeatMap diffusedMap = heatMap.diffusion();

        for (double[] row : diffusedMap.heat())
            for (double h : row)
                assertEquals(0, h, 1e-6);
    }

    @Test
    void spread() {
        double[][] heat = new double[10][10];
        heat[1][1] = 100000;
        HeatMap heatMap = new HeatMap(heat);
        HeatMap diffusedMap = heatMap.diffusion();

        assertNotEquals(0, diffusedMap.heat()[1][0], 1e-6);
        assertNotEquals(0, diffusedMap.heat()[1][2], 1e-6);
        assertNotEquals(0, diffusedMap.heat()[0][1], 1e-6);
        assertNotEquals(0, diffusedMap.heat()[2][1], 1e-6);
    }

    @Test
    void conservation() {
        double addedHeat = 100000;
        double[][] heat = new double[10][10];
        heat[1][1] = addedHeat;
        HeatMap heatMap = new HeatMap(heat);

        for (int i = 0; i < 10000; i++)
            heatMap = heatMap.diffusion();

        double resultingHeat = 0;
        for (double[] row : heatMap.heat())
            for (double h : row)
                resultingHeat += h;

        assertEquals(addedHeat, resultingHeat, 1e-6);
    }
}
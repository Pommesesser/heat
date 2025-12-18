package main.java.heat;

public record HeatMap(double[][] heat) implements Printable {
    public HeatMap diffusion() {
        double[][] next_heat = new double[heat.length][heat[0].length];
        final double k = 0.1f;
        assert(k >= 0.0f && k <= 0.25f);

        for (int i = 0; i < heat.length; i++) {
            for (int j = 0; j < heat[i].length; j++) {
                double heat_up = i == 0 ? heat[i][j] : heat[i-1][j];
                double heat_down = i == heat.length - 1 ? heat[i][j] : heat[i+1][j];
                double heat_left = j == 0 ? heat[i][j] : heat[i][j-1];
                double heat_right = j == heat[i].length - 1 ? heat[i][j] : heat[i][j+1];
                next_heat[i][j] = heat[i][j] + k * (heat_up + heat_down + heat_left + heat_right - 4 * heat[i][j]);
            }
        }

        return new HeatMap(next_heat);
    }

    public Color[][] colors() {
        Color[][] colors = new Color[heat.length][heat[0].length];

        for (int i = 0; i < heat.length; i++) {
            for (int j = 0; j < heat[i].length; j++) {
                if (heat[i][j] >= 40)
                    colors[i][j] = new Color(255, 0, 0);
                else if (heat[i][j] >= 30)
                    colors[i][j] = new Color(255, 165, 0);
                else if (heat[i][j] >= 20)
                    colors[i][j] = new Color(0, 255, 0);
                else if (heat[i][j] >= 10)
                    colors[i][j] = new Color(0, 255, 255);
                else
                 colors[i][j] = new Color(0, 0, 255);
            }
        }

        return colors;
    }
}
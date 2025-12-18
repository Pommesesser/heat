package main.java.heat;

import org.jspecify.annotations.NonNull;

import java.util.Arrays;

public record MaterialMap(Material[][] materials) implements Printable{
    public static MaterialMap of(@NonNull Material material, int dim) {
        Material[][] materials = new Material[dim][dim];
        for (Material[] row : materials)
            Arrays.fill(row, material);

        return new MaterialMap(materials);
    }

    public Color[][] colors() {
        Color[][] colors = new Color[materials.length][materials[0].length];

        for (int i = 0; i < materials.length; i++) {
            for (int j = 0; j < materials[i].length; j++) {
                colors[i][j] = switch (materials[i][j]) {
                    case AIR -> new Color(0, 0, 0);
                    case WALL -> new Color(255, 255, 255);
                };
            }
        }

        return colors;
    }
}
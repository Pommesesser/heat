package main.java.heat;

import org.jspecify.annotations.NonNull;

public record Grid(@NonNull HeatMap heatMap, @NonNull MaterialMap materialMap) {
    public Grid simulate() {
        return new Grid(heatMap.diffusion(), materialMap);
    }
}

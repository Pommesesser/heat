import main.java.heat.*;

Printer printer = new Printer();

volatile int printMode = 1;

void main() {
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        System.out.print("\u001b[2J");
        System.out.print("\u001b[0m");
        System.out.print("\u001b[H");
    }));

    int dim = 40;
    double[][] heat = new double[dim][dim];
    heat[20][20] = 5000;
    HeatMap heatMap = new HeatMap(heat);
    Material[][] materials = new Material[dim][dim];
    for (Material[] row : materials)
        Arrays.fill(row, Material.WALL);
    MaterialMap materialMap = new MaterialMap(materials);
    Grid grid = new Grid(heatMap, materialMap);

    Thread inputThread = new Thread(this::handleInput);
    inputThread.setDaemon(true);
    inputThread.start();

    while (true) {
        switch (printMode) {
            case 1 -> printer.print(grid.heatMap());
            case 2 -> printer.print(grid.materialMap());
        }
        grid = grid.simulate();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

void handleInput() {
    while (true) {
        try {
            int x = System.in.read();
            switch (x) {
                case '1' -> printMode = 1;
                case '2' -> printMode = 2;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
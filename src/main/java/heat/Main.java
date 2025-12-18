import main.java.heat.HeatMap;
import main.java.heat.Printer;

Printer printer = new Printer();

void main() {
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        System.out.print("\u001b[2J");
        System.out.print("\u001b[0m");
        System.out.print("\u001b[H");
    }));

    double[][] heat = new double[40][40];
    heat[2][0] = 500;
    heat[2][1] = 500;
    heat[4][8] = 1000;
    heat[30][30] = 10000;
    HeatMap heatMap = new HeatMap(heat);
    printer.print(heatMap);

    while (true) {
        heatMap = heatMap.diffusion();
        printer.print(heatMap);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
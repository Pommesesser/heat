package main.java.heat;

public record Printer() {
    public void print(Printable printable) {
        final Color[][] colors = printable.colors();
        StringBuilder sb = new StringBuilder();
        sb.append("\u001b[H");

        for (Color[] row : colors) {
            for (Color c : row) {
                sb.append("\u001b[48;2;" + c.r() + ";" + c.g() + ";" + c.b() + "m");
                sb.append("  ");
                sb.append("\u001b[0m");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
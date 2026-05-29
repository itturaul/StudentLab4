interface ExportHandler {
    void exportData();
}

class StudentsExport implements ExportHandler {
    @Override
    public void exportData() {
        System.out.println("[Export] Se generează fișierul cu studenți...");
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("[Export] Exportul s-a finalizat cu succes!");
    }
}

class ExecutionTimeExportDecorator implements ExportHandler {
    private final ExportHandler decoratedExport;

    public ExecutionTimeExportDecorator(ExportHandler decoratedExport) {
        this.decoratedExport = decoratedExport;
    }

    @Override
    public void exportData() {
        long startTime = System.currentTimeMillis();

        decoratedExport.exportData();

        long endTime = System.currentTimeMillis();

        long executionTime = endTime - startTime;
        System.out.println("[Decorator] Timp de execuție export: " + executionTime + " ms");
    }
}

public class Main {
    public static void main(String[] args) {
        ExportHandler exportSimplu = new StudentsExport();

        ExportHandler exportCuCronometru = new ExecutionTimeExportDecorator(exportSimplu);

        System.out.println("--- Rulare Export Decorat ---");
        exportCuCronometru.exportData();
    }
}
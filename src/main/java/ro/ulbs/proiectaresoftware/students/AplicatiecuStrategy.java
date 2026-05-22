package ro.ulbs.proiectaresoftware.students;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface ExportStrategy {
    void exporta(List<Student> studenti, String destinatie);
}

interface ImportStrategy {
    List<Student> importa(String sursa);
}

class StudentiInConsola implements ExportStrategy {
    @Override
    public void exporta(List<Student> studenti, String destinatie) {
        System.out.println("\n--- [Strategy] Afisare Studenti in Consola ---");
        studenti.forEach(System.out::println);
    }
}

class StudentiInFisierText implements ExportStrategy {
    @Override
    public void exporta(List<Student> studenti, String destinatie) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(destinatie))) {
            for (Student s : studenti) {
                writer.println(s.getNrMatricol() + "," + s.getPrenume() + "," + s.getNume() + "," + s.getFormatieDeStudiu() + "," + s.getNota());
            }
            System.out.println("Export TXT reusit: " + destinatie);
        } catch (IOException e) {
            System.err.println("Eroare export TXT: " + e.getMessage());
        }
    }
}

class StudentiInFisierXlsx implements ExportStrategy {
    @Override
    public void exporta(List<Student> studenti, String destinatie) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Studenti");
            Row headerRow = sheet.createRow(0);
            String[] coloane = {"Nr. Matricol", "Prenume", "Nume", "Formatie", "Nota"};
            for (int i = 0; i < coloane.length; i++) {
                headerRow.createCell(i).setCellValue(coloane[i]);
            }

            int rowIdx = 1;
            for (Student s : studenti) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(s.getNrMatricol());
                row.createCell(1).setCellValue(s.getPrenume());
                row.createCell(2).setCellValue(s.getNume());
                row.createCell(3).setCellValue(s.getFormatieDeStudiu());
                row.createCell(4).setCellValue(s.getNota());
            }

            try (FileOutputStream fileOut = new FileOutputStream(destinatie)) {
                workbook.write(fileOut);
                System.out.println("Export XLSX reusit: " + destinatie);
            }
        } catch (Exception e) {
            System.err.println("Eroare export XLSX: " + e.getMessage());
        }
    }
}

class StudentiDinFisierText implements ImportStrategy {
    @Override
    public List<Student> importa(String sursa) {
        List<Student> studenti = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(sursa))) {
            String linie;
            while ((linie = reader.readLine()) != null) {
                String[] parti = linie.split(",");
                if (parti.length == 5) {
                    int nrMatricol = Integer.parseInt(parti[0].trim());
                    String prenume = parti[1].trim();
                    String nume = parti[2].trim();
                    String formatie = parti[3].trim();
                    double nota = Double.parseDouble(parti[4].trim());
                    studenti.add(new Student(nrMatricol, prenume, nume, formatie, nota));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Eroare import TXT: " + e.getMessage());
        }
        return studenti;
    }
}

class StudentiDinFisierXlsx implements ImportStrategy {
    @Override
    public List<Student> importa(String sursa) {
        List<Student> studenti = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(new File(sursa));
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;
                int matricol = (int) row.getCell(0).getNumericCellValue();
                String prenume = row.getCell(1).getStringCellValue();
                String nume = row.getCell(2).getStringCellValue();
                String formatie = row.getCell(3).getStringCellValue();
                double nota = row.getCell(4).getNumericCellValue();
                studenti.add(new Student(matricol, prenume, nume, formatie, nota));
            }
        } catch (Exception e) {
            System.err.println("Eroare import XLSX: " + e.getMessage());
        }
        return studenti;
    }
}


class ManagerStudenti {
    private ExportStrategy exportStrategy;
    private ImportStrategy importStrategy;

    public void setExportStrategy(ExportStrategy exportStrategy) {
        this.exportStrategy = exportStrategy;
    }

    public void setImportStrategy(ImportStrategy importStrategy) {
        this.importStrategy = importStrategy;
    }

    public void executaExport(List<Student> studenti, String destinatie) {
        if (exportStrategy != null) exportStrategy.exporta(studenti, destinatie);
    }

    public List<Student> executaImport(String sursa) {
        return importStrategy != null ? importStrategy.importa(sursa) : new ArrayList<>();
    }
}

public class AplicatieCuStrategy {
    public static void main(String[] args) {
        List<Student> studenti = Arrays.asList(
                new Student(1025, "Andrei", "Popa", "ISM141/2", 8.70),
                new Student(1024, "Ioan", "Mihalcea", "ISM141/1", 10),
                new Student(1026, "Anamaria", "Prodan", "TI131/1", 8.90),
                new Student(1029, "Bianca", "Popescu", "TI131/1", 10),
                new Student(1029, "Maria", "Pana", "TI131/,", 4.10),
                new Student(1029, "Gabriela", "Mohanu", "TI131/2", 7.33),
                new Student(1029, "Marius", "Nasta", "TI131/2", 3.20),
                new Student(1029, "Marius", "Nasta", "TI131/1", 5.12),
                new Student(1029, "Andrei", "Dobrescu", "TI131/2", 2.22)
        );

        ManagerStudenti manager = new ManagerStudenti();

        manager.setExportStrategy(new StudentiInConsola());
        manager.executaExport(studenti, null);

        manager.setExportStrategy(new StudentiInFisierText());
        manager.executaExport(studenti, "studenti_laborator.txt");

        manager.setExportStrategy(new StudentiInFisierXlsx());
        manager.executaExport(studenti, "studenti_laborator.xlsx");

        manager.setImportStrategy(new StudentiDinFisierText());
        List<ro.ulbs.proiectaresoftware.students.Student> dinTxt = manager.executaImport("studenti_laborator.txt");
        System.out.println("Cititi din TXT: " + dinTxt.size());

        manager.setImportStrategy(new StudentiDinFisierXlsx());
        List<ro.ulbs.proiectaresoftware.students.Student> dinXlsx = manager.executaImport("studenti_laborator.xlsx");
        System.out.println("Cititi din XLSX: " + dinXlsx.size());
    }
}
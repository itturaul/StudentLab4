package ro.ulbs.proiectaresoftware.students;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class StudentExcelManager {

    public static void main(String[] args) {
        String fileName = "laborator8_students.xlsx";

        List<Student> listaInitiala = new ArrayList<>();
        listaInitiala.add(new Student(101, "Ion", "Popescu", "311AC"));
        listaInitiala.add(new Student(102, "Maria", "Ionescu", "312AC"));
        listaInitiala.add(new Student(103, "Andrei", "Vasile", "311AC"));

        exportStudentsToExcel(listaInitiala, fileName);

        System.out.println("\nCitim studenții din fișierul generat:");
        List<Student> listaCitita = importStudentsFromExcel(fileName);

        listaCitita.forEach(System.out::println);
    }
    -
    public static void exportStudentsToExcel(List<Student> studenti, String filePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Studenti");

            Row headerRow = sheet.createRow(0);
            String[] coloane = {"Nr. Matricol", "Prenume", "Nume", "Formatie"};
            for (int i = 0; i < coloane.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(coloane[i]);
            }

            int rowIdx = 1;
            for (Student s : studenti) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(s.getNrMatricol());
                row.createCell(1).setCellValue(s.getPrenume());
                row.createCell(2).setCellValue(s.getNume());
                row.createCell(3).setCellValue(s.getFormatieDeStudiu());
            }

            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
                System.out.println("Fișierul " + filePath + " a fost generat cu succes!");
            }
        } catch (Exception e) {
            System.err.println("Eroare la export: " + e.getMessage());
        }
    }

    public static List<Student> importStudentsFromExcel(String filePath) {
        List<Student> listaStudenti = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                int matricol = (int) row.getCell(0).getNumericCellValue();
                String prenume = row.getCell(1).getStringCellValue();
                String nume = row.getCell(2).getStringCellValue();
                String formatie = row.getCell(3).getStringCellValue();

                listaStudenti.add(new Student(matricol, prenume, nume, formatie));
            }

        } catch (Exception e) {
            System.err.println("Eroare la import: " + e.getMessage());
        }

        return listaStudenti;
    }
}


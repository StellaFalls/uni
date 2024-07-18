package code.core;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public abstract class ViewImpl<T> implements View<T> {
    protected Scanner scanner;

    @Override
    public void show(List<T> data) {
        for (T elements : data) {
            System.out.println("----------------------------------------------");
            System.out.println(elements);
            System.out.println("----------------------------------------------");
        }
    }

    public static LocalDate formatDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(date, formatter);
    }

    public static String generateNumber(int number, String format) {
        int size = String.valueOf(number).length();
        return format + "0".repeat((4 - size) < 0 ? 0 : 4 - size) + number;
    }

}
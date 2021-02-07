package com.project.dp;

import com.project.dp.Filter.Filter;

import java.util.Scanner;

public class App {

    public static void start(){

        System.out.println("Podaj zapytanie");
        Scanner scanner = new Scanner(System.in);
        String written_from_input = scanner.nextLine();
        Filter.filter(written_from_input);
    }
}

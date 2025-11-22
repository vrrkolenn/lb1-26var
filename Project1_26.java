package com.mycompany.project1_26;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Project1_26 {

    public static void main(String[] args) {
        System.out.println("Part 1: Array input and processing");
        List<Integer> arrayA = readArrayFromConsole();
        List<Integer> arrayB = createArrayB(arrayA);
        System.out.println("Array B: " + arrayB);

    }

    private static List<Integer> readArrayFromConsole() {
        Scanner scanner = new Scanner(System.in);
        List<Integer> array = new ArrayList<>();

        System.out.println("Input N elements of array A delemeted by spaces:");
        String inputLine = scanner.nextLine();
        String[] elements = inputLine.split(" ");


        for (String element : elements) {
            try {
                array.add(Integer.parseInt(element));
            } catch (NumberFormatException e) {
                System.out.println("Error: Wrong input. Please, input integer value.");
                return readArrayFromConsole(); // Рекурсивный вызов для повторного ввода
            }
        }

        return array;
    }


    private static List<Integer> createArrayB(List<Integer> arrayA) {
        List<Integer> arrayB = new ArrayList<>();
        arrayB.add (0);
        int k = 1;

        while (k < arrayA.size()) {
            arrayB.add( arrayA.get( k-1 ) + arrayB.get( arrayB.size() - 1 ) );
            k ++;
        }

        return arrayB;
    }
}


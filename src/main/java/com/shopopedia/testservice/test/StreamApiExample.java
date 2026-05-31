package com.shopopedia.testservice.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamApiExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);

        List<Integer> evenList = new ArrayList<>();

        for (int i=1;i<=numbers.size();i++){
            if (i %2 == 0) {
                //evenList.add(i);
            }
        }
        for (Integer i : numbers){
            if (i%2 ==0){
                //evenList.add(i);
            }
        }
    ///Take n and print it
       //numbers.stream().forEach(n-> System.out.println(n));
        numbers.stream()
                .filter(n-> n % 2 == 0)
                        .forEach(n-> System.out.println(n));
        ///true → keep
        ///false → remove

        List<String> names = Arrays.asList("John","Alex","Mike");

        names.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);

        names.stream()
                .map(String::toLowerCase)
                .forEach(System.out ::println);


        List<Integer > resultN = numbers.stream()
                .filter(i-> i%2==0)
                .collect(Collectors.toList());

        List<String> resultS = names.stream()
                .sorted()
                .collect(Collectors.toList());

        resultS.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList())
                .forEach(System.out::println);
        resultS.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList())
                .forEach(System.out::println);

        resultN.stream().filter(i->i%2==0).count();

        System.out.println(resultN);



        //numbers.stream().forEach(System.out :: println);

    }
}

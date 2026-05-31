package com.shopopedia.testservice.test;

import java.util.*;
import java.util.stream.Collectors;

public class test {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Amit", "Rahul", "Ankit", "Suresh");


        List<String> result = names.stream()
                .filter(name->name.startsWith("A"))
                .collect(Collectors.toList());

        System.out.println(result);

//        filter()   // condition
//        map()      // transform data
//        sorted()   // sort data
//        collect()  // convert stream back to list/set
//        forEach()  // loop
//        count()    // count record
    }
}
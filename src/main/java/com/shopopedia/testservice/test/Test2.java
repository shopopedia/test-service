package com.shopopedia.testservice.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Test2 {
    public static void main(String[] args) {

        Map<EmployeeKey, String> map = new HashMap<>();

        EmployeeKey e1 = new EmployeeKey(101);

        map.put(e1, "Parishkar");

        EmployeeKey e2 = new EmployeeKey(101);

        //map.put(e2, "Parishkar");

        System.out.println(map.get(e2));

    }
}


class EmployeeKey {

    int id;

    EmployeeKey(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;

        if (!(o instanceof EmployeeKey))
            return false;

        EmployeeKey e = (EmployeeKey) o;

        return id == e.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

package com.example.dz1java;


import java.util.ArrayList;
import java.util.List;

class NumDataSource {
    List<Integer> numArray = new ArrayList();

    public void generateData(int upperBound) {
        numArray.clear();
        for (int i = 1; i <= upperBound; i++) {
            numArray.add(i);
        }
    }

    public void addNumber() {
        Integer last = numArray.get(numArray.size()-1);
        last++;
        numArray.add(last);
    }
}

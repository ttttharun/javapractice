package com.javapractice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class NumberPlayList {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 5; i++) list.add(i);

        // Method 1: Iterator
        Iterator<Integer> it = list.iterator();
        while (it.hasNext())
            System.out.println("Iterator: " + it.next());

        // Method 2: Consumer interface
        class MyConsumer implements Consumer<Integer> {
            @Override
            public void accept(Integer integer) {
                System.out.println("Consumer: " + integer);
            }
        }
        MyConsumer action = new MyConsumer();
        list.forEach(action);

        // Method 3: anonymous consumer
        list.forEach(
                new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) {
                        System.out.println("Anonymous consumer: " + integer);
                    }
                }
        );

        // Method 4: lambda
        list.forEach(n -> System.out.println("lambda: " + n));

        // Method 5: convert integer to double using Function interface
        Function<Integer, Double> toDouble = Integer::doubleValue;
        list.forEach(n -> System.out.println("To double: " + toDouble.apply(n)));
    }
}

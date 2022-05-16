package com.rxjava.descriptor;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class DescriptorConsumer {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 6, 10, 30, 65, 70, 102);
        forEachPrint(numbers, n -> System.out.println(n));
    }

    public static <T> void forEachPrint(List<T> numbers, Consumer<T> consumer) {
        for (T number : numbers) {
            consumer.accept(number);
        }
    }
}

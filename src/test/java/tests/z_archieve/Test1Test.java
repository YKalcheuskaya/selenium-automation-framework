package tests.z_archieve;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test1Test {

    @Test
    public void streamFilter() {

        List<String> names = new ArrayList<>();
        names.add("Abba");
        names.add("Aaron");
        names.add("Genna");
        names.add("Rim");
        names.add("Bam");

        long c = names.stream().filter(s -> s.startsWith("A")).count();
        System.out.println(c);  // Ожидаемый вывод: 2 ("Abba", "Aaron")


        long d = Stream.of("Abba", "Aaron", "Genna", "Rim", "Bam")
                .filter(s -> s.startsWith("A"))
                .count();
        System.out.println(d);

        List<String> words = List.of("Apple", "Car", "Tree", "Go", "Pencil");
        words.stream()
                .filter(s -> s.length() > 3)
                .forEach(System.out::println);

        List<String> words1 = List.of("Apple", "Car", "Tree", "Go", "Pencil");
        words1.stream()
                .filter(s -> s.endsWith("o"))
                .forEach(System.out::println);

        List<String> words2 = List.of("Apple", "Car", "Tree", "Go", "Pencil");
        words2.stream()
                .filter(s -> s.endsWith("o"))
                .sorted()
                .map(s->s.toLowerCase())
                .forEach(System.out::println);

        Stream<String> newStream1 = Stream.concat(words1.stream(), words2.stream());
        newStream1.sorted().forEach(System.out::println);

        Stream<String> newStream2 = Stream.concat(words1.stream(), words2.stream());
        boolean anyMatch = newStream2.anyMatch(s -> s.equalsIgnoreCase("Apple"));
        Assert.assertTrue(anyMatch);

    }

    @Test

    public void streamCollect() {

        List<String> ls = Stream.of("Abba", "Aaron", "Genna", "Rim", "Bam")
                .filter(s -> s.endsWith("a"))
                .map(s-> s.toUpperCase())
                .collect(Collectors.toList());
        System.out.println(ls.get(0));


        List<Integer> values = Arrays.asList(3,3,2,3,1,1,4);
        // print unique number from this array
        // sort the array
        values.stream()
                .distinct()
                .sorted()
                .forEach(s->System.out.println(s));


    }
}

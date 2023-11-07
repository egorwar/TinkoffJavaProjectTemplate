package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings({"MultipleStringLiterals", "MagicNumber"})
public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    public static List<Animal> task1(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .collect(Collectors.toList());
    }

    public static List<Animal> task2(List<Animal> animals, int k) {

        if (k < 1 || k > animals.size()) {
            throw new IllegalArgumentException("k should belong to [1, animals.size()]");
        }

        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(k)
            .collect(Collectors.toList());
    }

    public static Map<Animal.Type, Integer> task3(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(e -> 1)));
    }

    public static Optional<Animal> task4(List<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparingInt(a -> a.name().length()));
    }

    public static Animal.Sex task5(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::sex, Collectors.counting()))
            .entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse(null);
    }

    public static Map<Animal.Type, Animal> task6(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.maxBy(Comparator.comparingInt(Animal::weight))))
            .entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().orElse(null)));
    }

    public static Animal task7(List<Animal> animals, int k) {

        if (k < 0 || k >= animals.size()) {
            throw new IllegalArgumentException("k should belong to [1, animals.size()]");
        }

        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::age).reversed())
            .skip(k - 1)
            .findFirst()
            .orElse(null);
    }

    public static Optional<Animal> task8(List<Animal> animals, int k) {

        if (k <= 0) {
            throw new IllegalArgumentException("k should be > 0");
        }

        return animals.stream()
            .filter(a -> a.height() < k)
            .max(Comparator.comparingInt(Animal::weight));
    }

    public static Integer task9(List<Animal> animals) {
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    public static List<Animal> task10(List<Animal> animals) {
        return animals.stream()
            .filter(a -> a.age() != a.paws())
            .collect(Collectors.toList());
    }

    public static List<Animal> task11(List<Animal> animals) {
        return animals.stream()
            .filter(a -> a.bites() && a.height() > 100)
            .collect(Collectors.toList());
    }

    public static Integer task12(List<Animal> animals) {
        return (int) animals.stream()
            .filter(a -> a.weight() > a.height())
            .count();
    }

    public static List<Animal> task13(List<Animal> animals) {
        return animals.stream()
            .filter(a -> a.name().split("\\s+").length > 2)
            .collect(Collectors.toList());
    }

    public static Boolean task14(List<Animal> animals, int k) {

        if (k <= 0) {
            throw new IllegalArgumentException("k should be > 0");
        }

        return animals.stream()
            .filter(a -> a.type() == Animal.Type.DOG)
            .anyMatch(a -> a.height() > k);
    }

    public static Map<Animal.Type, Integer> task15(List<Animal> animals, int k, int l) {

        if (k <= 0) {
            throw new IllegalArgumentException("k should be > 0");
        }

        if (l <= 0) {
            throw new IllegalArgumentException("l should be > 0");
        }

        if (k > l) {
            throw new IllegalArgumentException("k should be <= l");
        }

        return animals.stream()
            .filter(a -> a.age() >= k && a.age() <= l)
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
    }

    public static List<Animal> task16(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .collect(Collectors.toList());
    }

    public static Boolean task17(List<Animal> animals) {
        if (animals.stream().noneMatch(a -> a.type() == Animal.Type.SPIDER)) {
            return false;
        } else if (animals.stream().noneMatch(a -> a.type() == Animal.Type.DOG)) {
            return false;
        } else {
            return animals.stream()
                .filter(a -> a.type() == Animal.Type.SPIDER || a.type() == Animal.Type.DOG)
                .collect(Collectors.groupingBy(Animal::type, Collectors.counting()))
                .getOrDefault(Animal.Type.SPIDER, 0L) > animals.stream()
                .filter(a -> a.type() == Animal.Type.SPIDER || a.type() == Animal.Type.DOG)
                .collect(Collectors.groupingBy(Animal::type, Collectors.counting()))
                .getOrDefault(Animal.Type.DOG, 0L);
        }
    }

    public static Animal task18(List<List<Animal>> animals) {
        return animals.stream()
            .flatMap(List::stream)
            .filter(a -> a.type() == Animal.Type.FISH)
            .max(Comparator.comparingInt(Animal::weight))
            .orElse(null);
    }

    public static Map<String, Set<ValidationError>> task19(List<Animal> animals) {
        return animals.stream()
            .filter(Animal::hasErrors)
            .collect(Collectors.toMap(Animal::name, Animal::getErrors));
    }

    public static Map<String, String> task20(Map<String, Set<ValidationError>> animalErrors) {
        return animalErrors.entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, entry -> {
                Set<ValidationError> errors = entry.getValue();
                return errors.stream()
                    .map(ValidationError::getFullDescription)
                    .collect(Collectors.joining(""));
            }));
    }

    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) {
        // goto tests
    }
}

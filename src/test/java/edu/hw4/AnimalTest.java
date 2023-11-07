package edu.hw4;

import net.bytebuddy.dynamic.DynamicType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AnimalTest {

    @Test
    @DisplayName("проверка задачи 1")
    void task1() {
        // given
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Barsik", Animal.Type.CAT, Animal.Sex.M, 5, 40, 6000, false));
        animals.add(new Animal("Aqua", Animal.Type.FISH, Animal.Sex.F, 1, 10, 100, false));
        animals.add(new Animal("Ku", Animal.Type.DOG, Animal.Sex.M, 8, 80, 10000, true));
        animals.add(new Animal("Chuppy", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 9, false));

        List<Animal> sortedAnimals = new ArrayList<>();
        sortedAnimals.add(new Animal("Chuppy", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 9, false));
        sortedAnimals.add(new Animal("Aqua", Animal.Type.FISH, Animal.Sex.F, 1, 10, 100, false));
        sortedAnimals.add(new Animal("Barsik", Animal.Type.CAT, Animal.Sex.M, 5, 40, 6000, false));
        sortedAnimals.add(new Animal("Ku", Animal.Type.DOG, Animal.Sex.M, 8, 80, 10000, true));

        // when
        var sorted = Main.task1(animals);

        // then
        assertThat(sorted).isEqualTo(sortedAnimals);
    }

    @Test
    @DisplayName("проверка задачи 2")
    void task2() {
        // given
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Barsik", Animal.Type.CAT, Animal.Sex.M, 5, 40, 6000, false));
        animals.add(new Animal("Aqua", Animal.Type.FISH, Animal.Sex.F, 1, 10, 100, false));
        animals.add(new Animal("Ku", Animal.Type.DOG, Animal.Sex.M, 8, 80, 5000, true));
        animals.add(new Animal("Chuppy", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 9, false));

        List<Animal> sortedAnimals = new ArrayList<>();
        sortedAnimals.add(new Animal("Barsik", Animal.Type.CAT, Animal.Sex.M, 5, 40, 6000, false));
        sortedAnimals.add(new Animal("Ku", Animal.Type.DOG, Animal.Sex.M, 8, 80, 5000, true));
//        sortedAnimals.add(new Animal("Aqua", Animal.Type.FISH, Animal.Sex.F, 1, 10, 100, false));
//        sortedAnimals.add(new Animal("Chuppy", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 9, false));

        // when
        var sorted = Main.task2(animals, 2);

        // then
        assertThat(sorted).isEqualTo(sortedAnimals);
    }

    @Test
    @DisplayName("проверка задачи 2 с неверно заданным k")
    void task2invalidK() {
        // given
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Barsik", Animal.Type.CAT, Animal.Sex.M, 5, 40, 6000, false));
        animals.add(new Animal("Aqua", Animal.Type.FISH, Animal.Sex.F, 1, 10, 100, false));
        animals.add(new Animal("Ku", Animal.Type.DOG, Animal.Sex.M, 8, 80, 5000, true));
        animals.add(new Animal("Chuppy", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 9, false));

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> Main.task2(animals, 0),
            "Expected Main.task2 to throw, but it didn't"
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo("k should belong to [1, animals.size()]");
    }

    @Test
    @DisplayName("проверка задачи 3")
    void task3() {
        // given
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Prushka", Animal.Type.CAT, Animal.Sex.F, 8, 30, 5000, false));
        animals.add(new Animal("Barsik", Animal.Type.CAT, Animal.Sex.M, 5, 40, 6000, false));
        animals.add(new Animal("Aqua", Animal.Type.FISH, Animal.Sex.F, 1, 10, 100, false));
        animals.add(new Animal("Ku", Animal.Type.DOG, Animal.Sex.M, 8, 80, 5000, true));
//        animals.add(new Animal("Chuppy", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 9, false));

        Map<Animal.Type, Integer> expectedFreqs = new HashMap<>();
        expectedFreqs.put(Animal.Type.CAT, 2);
        expectedFreqs.put(Animal.Type.DOG, 1);
        expectedFreqs.put(Animal.Type.FISH, 1);

        // when
        var trueFreqs = Main.task3(animals);

        // then
        assertThat(trueFreqs).isEqualTo(expectedFreqs);
    }

    @Test
    @DisplayName("проверка задачи 4")
    void task4() {
        // given
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Prushka", Animal.Type.CAT, Animal.Sex.F, 8, 30, 5000, false));
        animals.add(new Animal("Barsik", Animal.Type.CAT, Animal.Sex.M, 5, 40, 6000, false));
        animals.add(new Animal("Aqua", Animal.Type.FISH, Animal.Sex.F, 1, 10, 100, false));
        animals.add(new Animal("Ku", Animal.Type.DOG, Animal.Sex.M, 8, 80, 5000, true));
        animals.add(new Animal("Chuppy", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 9, false));

        var expectedLongestName = Optional.of(
            new Animal("Prushka", Animal.Type.CAT, Animal.Sex.F, 8, 30, 5000, false)
        );

        // when
        var trueLongestName = Main.task4(animals);

        // then
        assertThat(trueLongestName).isEqualTo(expectedLongestName);
    }

    @Test
    @DisplayName("проверка задачи 5")
    void task5() {
        // given
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Prushka", Animal.Type.CAT, Animal.Sex.F, 8, 30, 5000, false));
        animals.add(new Animal("Barsik", Animal.Type.CAT, Animal.Sex.M, 5, 40, 6000, false));
        animals.add(new Animal("Aqua", Animal.Type.FISH, Animal.Sex.F, 1, 10, 100, false));
        animals.add(new Animal("Ku", Animal.Type.DOG, Animal.Sex.M, 8, 80, 5000, true));
        animals.add(new Animal("Chuppy", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 9, false));

        var expectedMostFreqGender = Animal.Sex.M;

        // when
        var trueMostFreqGender = Main.task5(animals);

        // then
        assertThat(trueMostFreqGender).isEqualTo(expectedMostFreqGender);
    }

    @Test
    @DisplayName("проверка задачи 6")
    void task6() {
        // given
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Prushka", Animal.Type.CAT, Animal.Sex.F, 8, 30, 5000, false));
        animals.add(new Animal("Barsik", Animal.Type.CAT, Animal.Sex.M, 5, 40, 6000, false));
        animals.add(new Animal("Aqua", Animal.Type.FISH, Animal.Sex.F, 1, 10, 100, false));
        animals.add(new Animal("Ku", Animal.Type.DOG, Animal.Sex.M, 8, 80, 5000, true));
        animals.add(new Animal("Chuppy", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 9, false));
        animals.add(new Animal("Selifon", Animal.Type.BIRD, Animal.Sex.F, 4, 11, 130, true));
        animals.add(new Animal("Pickle Pee", Animal.Type.BIRD, Animal.Sex.F, 9000, 7, 30, false));

        Map<Animal.Type, Animal> expectedTopWeights = new HashMap<>();
        expectedTopWeights.put(
            Animal.Type.CAT,
            new Animal("Barsik", Animal.Type.CAT, Animal.Sex.M, 5, 40, 6000, false)
        );
        expectedTopWeights.put(Animal.Type.DOG, new Animal("Ku", Animal.Type.DOG, Animal.Sex.M, 8, 80, 5000, true));
        expectedTopWeights.put(Animal.Type.FISH, new Animal("Aqua", Animal.Type.FISH, Animal.Sex.F, 1, 10, 100, false));
        expectedTopWeights.put(
            Animal.Type.SPIDER,
            new Animal("Chuppy", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 9, false)
        );
        expectedTopWeights.put(
            Animal.Type.BIRD,
            new Animal("Selifon", Animal.Type.BIRD, Animal.Sex.F, 4, 11, 130, true)
        );

        // when
        var trueTopWeights = Main.task6(animals);

        // then
        assertThat(trueTopWeights).isEqualTo(expectedTopWeights);
    }

    @Test
    @DisplayName("проверка задачи 7")
    void task7() {
        // given
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Prushka", Animal.Type.CAT, Animal.Sex.F, 8, 30, 5000, false));
        animals.add(new Animal("Barsik", Animal.Type.CAT, Animal.Sex.M, 5, 40, 6000, false));
        animals.add(new Animal("Aqua", Animal.Type.FISH, Animal.Sex.F, 1, 10, 100, false));
        animals.add(new Animal("Ku", Animal.Type.DOG, Animal.Sex.M, 8, 80, 5000, true));
        animals.add(new Animal("Chuppy", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 9, false));
        animals.add(new Animal("Selifon", Animal.Type.BIRD, Animal.Sex.F, 4, 11, 130, true));
        animals.add(new Animal("Pickle Pee", Animal.Type.BIRD, Animal.Sex.F, 9000, 7, 30, false));

        Animal expectedKthEldest = new Animal("Barsik", Animal.Type.CAT, Animal.Sex.M, 5, 40, 6000, false);

        // when
        Animal trueKthEldest = Main.task7(animals, 4);

        // then
        assertThat(trueKthEldest).isEqualTo(expectedKthEldest);
    }

    @Test
    @DisplayName("проверка задачи 7 с неверно заданным k")
    void task7invalidK() {
        // given
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Prushka", Animal.Type.CAT, Animal.Sex.F, 8, 30, 5000, false));
        animals.add(new Animal("Barsik", Animal.Type.CAT, Animal.Sex.M, 5, 40, 6000, false));
        animals.add(new Animal("Aqua", Animal.Type.FISH, Animal.Sex.F, 1, 10, 100, false));
        animals.add(new Animal("Ku", Animal.Type.DOG, Animal.Sex.M, 8, 80, 5000, true));
        animals.add(new Animal("Chuppy", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 9, false));
        animals.add(new Animal("Selifon", Animal.Type.BIRD, Animal.Sex.F, 4, 11, 130, true));
        animals.add(new Animal("Pickle Pee", Animal.Type.BIRD, Animal.Sex.F, 9000, 7, 30, false));

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> Main.task7(animals, 8),
            "Expected Main.task7 to throw, but it didn't"
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo("k should belong to [1, animals.size()]");
    }

    @Test
    @DisplayName("проверка задачи 8")
    void task8() {
        // given
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Prushka", Animal.Type.CAT, Animal.Sex.F, 8, 30, 5000, false));
        animals.add(new Animal("Barsik", Animal.Type.CAT, Animal.Sex.M, 5, 40, 6000, false));
        animals.add(new Animal("Aqua", Animal.Type.FISH, Animal.Sex.F, 1, 10, 100, false));
        animals.add(new Animal("Ku", Animal.Type.DOG, Animal.Sex.M, 8, 80, 5000, true));
        animals.add(new Animal("Chuppy", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 9, false));
        animals.add(new Animal("Selifon", Animal.Type.BIRD, Animal.Sex.F, 4, 11, 130, true));
        animals.add(new Animal("Pickle Pee", Animal.Type.BIRD, Animal.Sex.F, 9000, 7, 30, false));

        var expectedHeaviestSmallerThanK = Optional.of(
            new Animal("Prushka", Animal.Type.CAT, Animal.Sex.F, 8, 30, 5000, false)
        );

        // when
        Optional<Animal> trueHeaviestSmallerThanK = Main.task8(animals, 31);

        // then
        assertThat(trueHeaviestSmallerThanK).isEqualTo(expectedHeaviestSmallerThanK);
    }

    @Test
    @DisplayName("проверка задачи 8 с некорректным k")
    void task8invalidK() {
        // given
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Prushka", Animal.Type.CAT, Animal.Sex.F, 8, 30, 5000, false));
        animals.add(new Animal("Barsik", Animal.Type.CAT, Animal.Sex.M, 5, 40, 6000, false));
        animals.add(new Animal("Aqua", Animal.Type.FISH, Animal.Sex.F, 1, 10, 100, false));
        animals.add(new Animal("Ku", Animal.Type.DOG, Animal.Sex.M, 8, 80, 5000, true));
        animals.add(new Animal("Chuppy", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 9, false));
        animals.add(new Animal("Selifon", Animal.Type.BIRD, Animal.Sex.F, 4, 11, 130, true));
        animals.add(new Animal("Pickle Pee", Animal.Type.BIRD, Animal.Sex.F, 9000, 7, 30, false));

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> Main.task8(animals, -1),
            "Expected Main.task8 to throw, but it didn't"
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo("k should be > 0");
    }

    @Test
    @DisplayName("проверка задачи 9")
    void task9() {
        // given
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Prushka", Animal.Type.CAT, Animal.Sex.F, 8, 30, 5000, false));
        animals.add(new Animal("Barsik", Animal.Type.CAT, Animal.Sex.M, 5, 40, 6000, false));
        animals.add(new Animal("Aqua", Animal.Type.FISH, Animal.Sex.F, 1, 10, 100, false));
        animals.add(new Animal("Ku", Animal.Type.DOG, Animal.Sex.M, 8, 80, 5000, true));
        animals.add(new Animal("Chuppy", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 9, false));
        animals.add(new Animal("Selifon", Animal.Type.BIRD, Animal.Sex.F, 4, 11, 130, true));
        animals.add(new Animal("Pickle Pee", Animal.Type.BIRD, Animal.Sex.F, 9000, 7, 30, false));

        int expectedSumLimbs = 24;

        // when
        var trueSumLimbs = Main.task9(animals);

        // then
        assertThat(trueSumLimbs).isEqualTo(expectedSumLimbs);
    }

    @Test
    @DisplayName("проверка задачи 10")
    void task10() {
        // given
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Prushka", Animal.Type.CAT, Animal.Sex.F, 8, 30, 5000, false));
        animals.add(new Animal("Barsik", Animal.Type.CAT, Animal.Sex.M, 4, 40, 6000, false));
        animals.add(new Animal("Aqua", Animal.Type.FISH, Animal.Sex.F, 1, 10, 100, false));
        animals.add(new Animal("Ku", Animal.Type.DOG, Animal.Sex.M, 8, 80, 5000, true));
        animals.add(new Animal("Chuppy", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 9, false));
        animals.add(new Animal("Selifon", Animal.Type.BIRD, Animal.Sex.F, 4, 11, 130, true));
        animals.add(new Animal("Pickle Pee", Animal.Type.BIRD, Animal.Sex.F, 2, 7, 30, false));

        var expectedAgePawsDiffer = new ArrayList<Animal>();
        expectedAgePawsDiffer.add(new Animal("Prushka", Animal.Type.CAT, Animal.Sex.F, 8, 30, 5000, false));
        expectedAgePawsDiffer.add(new Animal("Aqua", Animal.Type.FISH, Animal.Sex.F, 1, 10, 100, false));
        expectedAgePawsDiffer.add(new Animal("Ku", Animal.Type.DOG, Animal.Sex.M, 8, 80, 5000, true));
        expectedAgePawsDiffer.add(new Animal("Chuppy", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 9, false));
        expectedAgePawsDiffer.add(new Animal("Selifon", Animal.Type.BIRD, Animal.Sex.F, 4, 11, 130, true));

        // when
        var trueAgePawsDiffer = Main.task10(animals);

        // then
        assertThat(trueAgePawsDiffer).isEqualTo(expectedAgePawsDiffer);
    }

    @Test
    @DisplayName("проверка задачи 11")
    void task11() {
        // given
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Prushka", Animal.Type.CAT, Animal.Sex.F, 8, 30, 5000, false));
        animals.add(new Animal("Barsik", Animal.Type.CAT, Animal.Sex.M, 4, 40, 6000, false));
        animals.add(new Animal("Aqua", Animal.Type.FISH, Animal.Sex.F, 1, 10, 100, false));
        animals.add(new Animal("Ku", Animal.Type.DOG, Animal.Sex.M, 8, 120, 5000, true));
        animals.add(new Animal("Chuppy", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 9, false));
        animals.add(new Animal("Selifon", Animal.Type.BIRD, Animal.Sex.F, 4, 11, 130, true));
        animals.add(new Animal("Pickle Pee", Animal.Type.BIRD, Animal.Sex.F, 2, 7, 30, false));

        var expectedBiteAnd100plusHeight = new ArrayList<Animal>();
        expectedBiteAnd100plusHeight.add(new Animal("Ku", Animal.Type.DOG, Animal.Sex.M, 8, 120, 5000, true));

        // when
        var trueBiteAnd100plusHeight = Main.task11(animals);

        // then
        assertThat(trueBiteAnd100plusHeight).isEqualTo(expectedBiteAnd100plusHeight);
    }

    @Test
    @DisplayName("проверка задачи 12")
    void task12() {
        // given
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Prushka", Animal.Type.CAT, Animal.Sex.F, 8, 30, 5000, false));
        animals.add(new Animal("Barsik", Animal.Type.CAT, Animal.Sex.M, 4, 40, 6000, false));
        animals.add(new Animal("Aqua", Animal.Type.FISH, Animal.Sex.F, 1, 10, 100, false));
        animals.add(new Animal("Ku", Animal.Type.DOG, Animal.Sex.M, 8, 120, 5000, true));
        animals.add(new Animal("Chuppy", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 9, false));
        animals.add(new Animal("Selifon", Animal.Type.BIRD, Animal.Sex.F, 4, 11, 130, true));
        animals.add(new Animal("Pickle Pee", Animal.Type.BIRD, Animal.Sex.F, 2, 7, 4, false));

        var expectedWeightGreaterThanHeight = 6;

        // when
        var trueWeightGreaterThanHeight = Main.task12(animals);

        // then
        assertThat(trueWeightGreaterThanHeight).isEqualTo(expectedWeightGreaterThanHeight);
    }

    @Test
    @DisplayName("проверка задачи 13")
    void task13() {
        // given
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Prushka", Animal.Type.CAT, Animal.Sex.F, 8, 30, 5000, false));
        animals.add(new Animal("Barsik", Animal.Type.CAT, Animal.Sex.M, 4, 40, 6000, false));
        animals.add(new Animal("Aqua", Animal.Type.FISH, Animal.Sex.F, 1, 10, 100, false));
        animals.add(new Animal("Ku", Animal.Type.DOG, Animal.Sex.M, 8, 120, 5000, true));
        animals.add(new Animal("Chuppy", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 9, false));
        animals.add(new Animal("Selifon", Animal.Type.BIRD, Animal.Sex.F, 4, 11, 130, true));
        animals.add(new Animal("Pump a Rum", Animal.Type.BIRD, Animal.Sex.F, 2, 7, 4, false));

        var expectedTwoPlusWordNames = new ArrayList<Animal>();
        expectedTwoPlusWordNames.add(new Animal("Pump a Rum", Animal.Type.BIRD, Animal.Sex.F, 2, 7, 4, false));

        // when
        var trueTwoPlusWordNames = Main.task13(animals);

        // then
        assertThat(trueTwoPlusWordNames).isEqualTo(expectedTwoPlusWordNames);
    }

    @Test
    @DisplayName("проверка задачи 14")
    void task14() {
        // given
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Prushka", Animal.Type.CAT, Animal.Sex.F, 8, 30, 5000, false));
        animals.add(new Animal("Barsik", Animal.Type.CAT, Animal.Sex.M, 4, 40, 6000, false));
        animals.add(new Animal("Aqua", Animal.Type.FISH, Animal.Sex.F, 1, 10, 100, false));
        animals.add(new Animal("Ku", Animal.Type.DOG, Animal.Sex.M, 8, 120, 5000, true));
        animals.add(new Animal("Chuppy", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 9, false));
        animals.add(new Animal("Selifon", Animal.Type.BIRD, Animal.Sex.F, 4, 11, 130, true));
        animals.add(new Animal("Pump a Rum", Animal.Type.BIRD, Animal.Sex.F, 2, 7, 4, false));

        var expectedDogHigherThanK = true;

        // when
        var trueDogHigherThanK = Main.task14(animals, 110);

        // then
        assertThat(trueDogHigherThanK).isEqualTo(expectedDogHigherThanK);
    }

    @Test
    @DisplayName("проверка задачи 14 с неверным k")
    void task14invalidK() {
        // given
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Prushka", Animal.Type.CAT, Animal.Sex.F, 8, 30, 5000, false));
        animals.add(new Animal("Barsik", Animal.Type.CAT, Animal.Sex.M, 4, 40, 6000, false));
        animals.add(new Animal("Aqua", Animal.Type.FISH, Animal.Sex.F, 1, 10, 100, false));
        animals.add(new Animal("Ku", Animal.Type.DOG, Animal.Sex.M, 8, 120, 5000, true));
        animals.add(new Animal("Chuppy", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 9, false));
        animals.add(new Animal("Selifon", Animal.Type.BIRD, Animal.Sex.F, 4, 11, 130, true));
        animals.add(new Animal("Pump a Rum", Animal.Type.BIRD, Animal.Sex.F, 2, 7, 4, false));

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> Main.task14(animals, 0),
            "Expected Main.task14 to throw, but it didn't"
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo("k should be > 0");
    }

    @Test
    @DisplayName("проверка задачи 15")
    void task15() {
        // given
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Prushka", Animal.Type.CAT, Animal.Sex.F, 8, 30, 5000, false));
        animals.add(new Animal("Barsik", Animal.Type.CAT, Animal.Sex.M, 4, 40, 6000, false));
        animals.add(new Animal("Aqua", Animal.Type.FISH, Animal.Sex.F, 1, 10, 100, false));
        animals.add(new Animal("Ku", Animal.Type.DOG, Animal.Sex.M, 8, 120, 5000, true));
        animals.add(new Animal("Chuppy", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 9, false));
        animals.add(new Animal("Selifon", Animal.Type.BIRD, Animal.Sex.F, 4, 11, 130, true));
        animals.add(new Animal("Pump a Rum", Animal.Type.BIRD, Animal.Sex.F, 2, 7, 4, false));

        var expectedHeightsBetweenKAndL = new HashMap<Animal.Type, Integer>();
        expectedHeightsBetweenKAndL.put(Animal.Type.CAT, 6000);
        expectedHeightsBetweenKAndL.put(Animal.Type.SPIDER, 9);
        expectedHeightsBetweenKAndL.put(Animal.Type.BIRD, 134);

        // when
        var trueHeightsBetweenKAndL = Main.task15(animals, 2, 4);

        // then
        assertThat(trueHeightsBetweenKAndL).isEqualTo(expectedHeightsBetweenKAndL);
    }

    @Test
    @DisplayName("проверка задачи 15 с неверными k и l")
    void task15invalidKAndL() {
        // given
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Prushka", Animal.Type.CAT, Animal.Sex.F, 8, 30, 5000, false));
        animals.add(new Animal("Barsik", Animal.Type.CAT, Animal.Sex.M, 4, 40, 6000, false));
        animals.add(new Animal("Aqua", Animal.Type.FISH, Animal.Sex.F, 1, 10, 100, false));
        animals.add(new Animal("Ku", Animal.Type.DOG, Animal.Sex.M, 8, 120, 5000, true));
        animals.add(new Animal("Chuppy", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 9, false));
        animals.add(new Animal("Selifon", Animal.Type.BIRD, Animal.Sex.F, 4, 11, 130, true));
        animals.add(new Animal("Pump a Rum", Animal.Type.BIRD, Animal.Sex.F, 2, 7, 4, false));

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> Main.task15(animals, 4, 3),
            "Expected Main.task15 to throw, but it didn't"
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo("k should be <= l");
    }

    @Test
    @DisplayName("проверка задачи 16")
    void task16() {
        // given
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Prushka", Animal.Type.CAT, Animal.Sex.F, 8, 30, 5000, false));
        animals.add(new Animal("Barsik", Animal.Type.CAT, Animal.Sex.M, 4, 40, 6000, false));
        animals.add(new Animal("Aqua", Animal.Type.FISH, Animal.Sex.F, 1, 10, 100, false));
        animals.add(new Animal("Ku", Animal.Type.DOG, Animal.Sex.M, 8, 120, 5000, true));
        animals.add(new Animal("Chuppy", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 9, false));
        animals.add(new Animal("Selifon", Animal.Type.BIRD, Animal.Sex.F, 4, 11, 130, true));
        animals.add(new Animal("Pump a Rum", Animal.Type.BIRD, Animal.Sex.F, 2, 7, 4, false));

        var expectedSorted = new ArrayList<Animal>();
        expectedSorted.add(new Animal("Barsik", Animal.Type.CAT, Animal.Sex.M, 4, 40, 6000, false));
        expectedSorted.add(new Animal("Prushka", Animal.Type.CAT, Animal.Sex.F, 8, 30, 5000, false));
        expectedSorted.add(new Animal("Ku", Animal.Type.DOG, Animal.Sex.M, 8, 120, 5000, true));
        expectedSorted.add(new Animal("Pump a Rum", Animal.Type.BIRD, Animal.Sex.F, 2, 7, 4, false));
        expectedSorted.add(new Animal("Selifon", Animal.Type.BIRD, Animal.Sex.F, 4, 11, 130, true));
        expectedSorted.add(new Animal("Aqua", Animal.Type.FISH, Animal.Sex.F, 1, 10, 100, false));
        expectedSorted.add(new Animal("Chuppy", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 9, false));

        // when
        var trueSorted = Main.task16(animals);

        // then
        assertThat(trueSorted).isEqualTo(expectedSorted);
    }

    @Test
    @DisplayName("проверка задачи 17")
    void task17() {
        // given
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Prushka", Animal.Type.CAT, Animal.Sex.F, 8, 30, 5000, false));
        animals.add(new Animal("Barsik", Animal.Type.CAT, Animal.Sex.M, 4, 40, 6000, false));
        animals.add(new Animal("Aqua", Animal.Type.FISH, Animal.Sex.F, 1, 10, 100, false));
        animals.add(new Animal("Ku", Animal.Type.DOG, Animal.Sex.M, 8, 120, 5000, true));
        animals.add(new Animal("Chuppy", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 9, true));
        animals.add(new Animal("Muppy", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 9, true));
        animals.add(new Animal("Selifon", Animal.Type.BIRD, Animal.Sex.F, 4, 11, 130, true));
        animals.add(new Animal("Pump a Rum", Animal.Type.BIRD, Animal.Sex.F, 2, 7, 4, false));

        var expectedDoSpidersBiteMoreFreqThanDogs = true;

        // when
        var trueDoSpidersBiteMoreFreqThanDogs = Main.task17(animals);

        // then
        assertThat(trueDoSpidersBiteMoreFreqThanDogs).isEqualTo(expectedDoSpidersBiteMoreFreqThanDogs);
    }

    @Test
    @DisplayName("проверка задачи 17 если недостаточно данных")
    void task17problematic() {
        // given
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Prushka", Animal.Type.CAT, Animal.Sex.F, 8, 30, 5000, false));
        animals.add(new Animal("Barsik", Animal.Type.CAT, Animal.Sex.M, 4, 40, 6000, false));
        animals.add(new Animal("Aqua", Animal.Type.FISH, Animal.Sex.F, 1, 10, 100, false));
        animals.add(new Animal("Chuppy", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 9, true));
        animals.add(new Animal("Muppy", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 9, true));
        animals.add(new Animal("Selifon", Animal.Type.BIRD, Animal.Sex.F, 4, 11, 130, true));
        animals.add(new Animal("Pump a Rum", Animal.Type.BIRD, Animal.Sex.F, 2, 7, 4, false));

        var expectedDoSpidersBiteMoreFreqThanDogs = false;

        // when
        var trueDoSpidersBiteMoreFreqThanDogs = Main.task17(animals);

        // then
        assertThat(trueDoSpidersBiteMoreFreqThanDogs).isEqualTo(expectedDoSpidersBiteMoreFreqThanDogs);
    }

    @Test
    @DisplayName("проверка задачи 18")
    void task18() {
        // given
        List<Animal> animals1 = new ArrayList<>();
        animals1.add(new Animal("Aqua", Animal.Type.FISH, Animal.Sex.F, 1, 10, 100, false));
        animals1.add(new Animal("Chuppy", Animal.Type.FISH, Animal.Sex.M, 3, 13, 91, true));

        List<Animal> animals2 = new ArrayList<>();
        animals1.add(new Animal("Thore", Animal.Type.BIRD, Animal.Sex.F, 2, 12, 100, false));
        animals1.add(new Animal("Oktavia", Animal.Type.FISH, Animal.Sex.F, 4, 13, 120, true));

        List<Animal> animals3 = new ArrayList<>();
        animals1.add(new Animal("Lol", Animal.Type.FISH, Animal.Sex.F, 1, 10, 94, false));
        animals1.add(new Animal("Kek", Animal.Type.SPIDER, Animal.Sex.M, 3, 3, 3, true));

        List<List<Animal>> animals = new ArrayList<>();
        animals.add(animals1);
        animals.add(animals2);
        animals.add(animals3);

        var expectedHeaviestFish = new Animal("Oktavia", Animal.Type.FISH, Animal.Sex.F, 4, 13, 120, true);

        // when
        var trueHeaviestFish = Main.task18(animals);

        // then
        assertThat(trueHeaviestFish).isEqualTo(expectedHeaviestFish);
    }

    // Задачу 19 тестировать проблематично, а корректная работа задачи 20 гарантирует корректную работу задачи 19
    @Test
    @DisplayName("проверка задачи 20")
    void task20() {
        // given
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Prushka", Animal.Type.CAT, Animal.Sex.F, 8, 30, 5000, false));
        animals.add(new Animal("", Animal.Type.CAT, Animal.Sex.M, 52, 900000, 6000, false));
        animals.add(new Animal("Aqua", Animal.Type.FISH, Animal.Sex.F, 1, 10, 100, false));
        animals.add(new Animal("Chuppy", Animal.Type.SPIDER, Animal.Sex.M, 3, 1, 9, true));
        animals.add(new Animal("Muppy", Animal.Type.SPIDER, Animal.Sex.M, -5, -1, 99999999, true));
        animals.add(new Animal("Selifon", Animal.Type.BIRD, Animal.Sex.F, 4, 11, 130, true));
        animals.add(new Animal("Namy", Animal.Type.BIRD, Animal.Sex.F, 2, 7, 4, false));

        var expectedErrors = new HashMap<String, String>();
        String str11 = """
            Type: Invalid age, should be a positive number <= 50
            Value: 52

            """;
        String str12 = """
            Type: Name should not be empty
            Value:\s

            """;

        String str13 = """
            Type: Invalid height, should be a positive number <= 2000
            Value: 900000

            """;

        String str21 = """
            Type: Invalid height, should be a positive number <= 2000
            Value: -1

            """;
        String str22 = """
            Type: Invalid age, should be a positive number <= 50
            Value: -5

            """;

        String str23 = """
            Type: Invalid weight, should be a positive number <= 80000
            Value: 99999999

            """;
        expectedErrors.put("", str11 + str12 + str13);
        expectedErrors.put("Muppy", str21 + str22 + str23);

        // when
        var trueErrors = Main.task20(Main.task19(animals));

        // then
        assertThat(trueErrors.get("").contains(str11)
            && trueErrors.get("").contains(str12)
            && trueErrors.get("").contains(str13)).isTrue();
        assertThat(trueErrors.get("Muppy").contains(str21)
            && trueErrors.get("Muppy").contains(str22)
            && trueErrors.get("Muppy").contains(str23)).isTrue();
    }

}

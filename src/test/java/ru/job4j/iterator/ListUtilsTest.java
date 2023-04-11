package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIf3() {
        ListUtils.addAfter(input, 0, 2);
        ListUtils.removeIf(input, integer -> integer < 3);
        assertThat(input).containsSequence(3).hasSize(1);
    }

    @Test
    void whenAddToEnd() {
        ListUtils.addAfter(input, 1, 4);
        ListUtils.addAfter(input, 0, 2);
        ListUtils.addAfter(input, 3, 5);
        assertThat(input).hasSize(5).containsSequence(1, 2, 3, 4, 5);
    }

    @Test
    void whenReplaceEven() {
        ListUtils.addAfter(input, 1, 4);
        ListUtils.addAfter(input, 0, 2);
        ListUtils.addAfter(input, 3, 5);
        ListUtils.replaceIf(input, i -> i % 2 == 0, 100);
        assertThat(input).containsSequence(1, 100, 3, 100, 5);
    }

    @Test
    void whenRemoveAll() {
        List<Integer> toRemove = new ArrayList<>();
        toRemove.add(3);
        toRemove.add(1);
        ListUtils.removeAll(input, toRemove);
        assertThat(input).hasSize(0);
    }
}
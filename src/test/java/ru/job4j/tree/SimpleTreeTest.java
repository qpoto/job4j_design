package ru.job4j.tree;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class SimpleTreeTest {
    @Test
    void when6ElFindLastThen6() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.findBy(6)).isPresent();
    }
    @Test
    void whenElFindNotExistThenOptionEmpty() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(tree.findBy(7)).isEmpty();
    }
    @Test
    void whenChildExistOnLeafThenNotAdd() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.add(2, 6)).isFalse();
    }
    @Test
    public void testIsBinaryWhenTreeIsBinary() {
        SimpleTree<Integer> tree = new SimpleTree<>(1);
        assertTrue(tree.add(1, 2));
        assertTrue(tree.add(1, 3));
        assertTrue(tree.add(2, 4));
        assertTrue(tree.add(2, 5));
        assertTrue(tree.add(3, 6));
        assertTrue(tree.add(3, 7));
        assertTrue(tree.isBinary());
    }
    @Test
    public void testIsBinaryWhenTreeIsNotBinary() {
        SimpleTree<Integer> tree = new SimpleTree<>(1);
        assertTrue(tree.add(1, 2));
        assertTrue(tree.add(1, 3));
        assertTrue(tree.add(2, 4));
        assertTrue(tree.add(2, 5));
        assertTrue(tree.add(3, 6));
        assertTrue(tree.add(3, 7));
        assertTrue(tree.add(3, 8));
        assertFalse(tree.isBinary());
    }
}
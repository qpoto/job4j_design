package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisNotSphere() {
        Box box = new Box(1, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void numberOfVertices4() {
        Box box = new Box(4, 10);
        int vert = box.getNumberOfVertices();
        assertThat(vert).isEqualTo(4);
    }

    @Test
    void numberOfVertices0() {
        Box box = new Box(0, 10);
        int vert = box.getNumberOfVertices();
        assertThat(vert).isEqualTo(0);
    }

    @Test
    void isNotExist() {
        Box box = new Box(-1, 10);
        boolean exist = box.isExist();
        assertThat(exist).isEqualTo(false);
    }

    @Test
    void isExist() {
        Box box = new Box(0, 10);
        boolean exist = box.isExist();
        assertThat(exist).isEqualTo(true);
    }

    @Test
    void AreaNull() {
        Box box = new Box(1, 1);
        double x = box.getArea();
        assertThat(x).isEqualTo(0);
    }

    @Test
    void AreaSix() {
        Box box = new Box(8, 1);
        double x = box.getArea();
        assertThat(x).isEqualTo(6);
    }
}
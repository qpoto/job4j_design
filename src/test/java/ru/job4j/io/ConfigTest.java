package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairWithoutValue() {
        String path = "./data/error.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Line must have value");
    }

    @Test
    void whenPairWithoutKey() {
        String path = "./data/error2.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Line must have key");
    }

    @Test
    void key3ContainValue4() {
        String path = "./data/error3.properties";
        Config config = new Config(path);
        config.load();
        String expected = "4";
        String rsl = config.value("3");
        assertThat(rsl).isEqualTo(expected);
    }
}
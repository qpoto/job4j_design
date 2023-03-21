package ru.job4j.generic;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RolStoreTest {

    @Test
    void whenAddAndFindThenRollnameIsProger() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Proger"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Proger");
    }

    @Test
    void whenAddAndFindThenRollIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Proger"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRollnameIsProger() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Proger"));
        store.add(new Role("1", "Junior"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Proger");
    }

    @Test
    void whenReplaceThenRollnameIsJunior() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Proger"));
        store.replace("1", new Role("1", "Junior"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Junior");
    }

    @Test
    void whenNoReplaceUserThenNoChangeRollname() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Proger"));
        store.replace("10", new Role("10", "Maxim"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Proger");
    }

/*    @Test
    void whenDeleteUserThenUserIsNull() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        store.delete("1");
        User result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteUserThenUsernameIsPetr() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        store.delete("10");
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Petr");
    }

    @Test
    void whenReplaceOkThenTrue() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        boolean rsl = store.replace("1", new User("1", "Maxim"));
        assertThat(rsl).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        boolean rsl = store.replace("10", new User("10", "Maxim"));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        boolean rsl = store.delete("1");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        boolean rsl = store.delete("2");
        assertThat(rsl).isFalse();
    }*/
}
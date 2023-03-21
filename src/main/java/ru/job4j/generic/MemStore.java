package ru.job4j.generic;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        storage.put(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean replOk = false;
        T repl = storage.replace(id, model);
        if (repl != null) {
            replOk = true;
        }
        return replOk;
    }

    @Override
    public boolean delete(String id) {
        boolean delOk = false;
        T del = storage.remove(id);
        if (del == null) {
            delOk = true;
        }
        return delOk;
    }

    @Override
    public T findById(String id) {
        return storage.get(id);
    }
}
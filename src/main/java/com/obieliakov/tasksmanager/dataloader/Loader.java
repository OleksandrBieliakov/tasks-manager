package com.obieliakov.tasksmanager.dataloader;

import java.util.List;

public interface Loader<T> {

    String DELIMITER = "_";

    List<T> generate(String tag, int number, int from);

    void load(List<T> entities);

    default String format(String tag, String columnName, int number) {
        return tag + DELIMITER + columnName + DELIMITER + number;
    }
}

package com.example.myfitness.database;

import android.provider.BaseColumns;

public final class exercise_contract {
    private exercise_contract() {}

    public static class ExerciseEntry implements BaseColumns {
        public static final String TABLE_NAME = "exercise";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_CATEGORY = "category";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
    }
}

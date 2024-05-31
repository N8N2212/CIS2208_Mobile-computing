package com.example.myfitness.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;

import com.example.myfitness.exercise;

import java.util.ArrayList;
import java.util.List;
import android.text.TextUtils;


public class db_helper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "MyFitnessDB.db";

    private final String name = exercise_contract.ExerciseEntry.COLUMN_NAME_NAME;
    private final String category = exercise_contract.ExerciseEntry.COLUMN_NAME_CATEGORY;
    private final String description = exercise_contract.ExerciseEntry.COLUMN_NAME_DESCRIPTION;


    public db_helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTables());
        db.execSQL(createWorkoutPresetTable());  // new workout presets table
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {  // Assuming '2' is the new version with the description column
            db.execSQL("ALTER TABLE " + exercise_contract.ExerciseEntry.TABLE_NAME +
                    " ADD COLUMN " + exercise_contract.ExerciseEntry.COLUMN_NAME_DESCRIPTION + " TEXT");
            db.execSQL(createWorkoutPresetTable());
        }
    }


    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private String createTables() {
        return "CREATE TABLE " + exercise_contract.ExerciseEntry.TABLE_NAME + " (" +
                exercise_contract.ExerciseEntry._ID + " INTEGER PRIMARY KEY," +
                exercise_contract.ExerciseEntry.COLUMN_NAME_NAME + " TEXT," +
                exercise_contract.ExerciseEntry.COLUMN_NAME_CATEGORY + " TEXT," +
                exercise_contract.ExerciseEntry.COLUMN_NAME_DESCRIPTION + " TEXT)";
    }


    private String dropTables() {
        return "DROP TABLE IF EXISTS " + exercise_contract.ExerciseEntry.TABLE_NAME;
    }

    public long insertExercise(exercise exercise) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(name, exercise.getName());
        values.put(category, exercise.getCategory());
        values.put(description, exercise.getDescription());

        return db.insert(exercise_contract.ExerciseEntry.TABLE_NAME, null, values);
    }

    public ArrayList<exercise> getExercises() {
        ArrayList<exercise> exercises = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                exercise_contract.ExerciseEntry._ID,
                name,
                category,
                description
        };

        String sortOrder = name + " ASC";

        Cursor cursor = db.query(
                exercise_contract.ExerciseEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(exercise_contract.ExerciseEntry._ID));
            String exerciseName = cursor.getString(cursor.getColumnIndexOrThrow(name));
            String exerciseCategory = cursor.getString(cursor.getColumnIndexOrThrow(category));
            String exerciseDescription = cursor.getString(cursor.getColumnIndexOrThrow(description));
            exercise exercise = new exercise(id, exerciseName, exerciseCategory, exerciseDescription);
            exercises.add(exercise);
        }
        cursor.close();

        return exercises;
    }

    public exercise getExerciseById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                exercise_contract.ExerciseEntry._ID,
                name,
                category,
                description
        };

        String selection = exercise_contract.ExerciseEntry._ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };

        Cursor cursor = db.query(
                exercise_contract.ExerciseEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        exercise exercise = null;

        if (cursor.moveToNext()) {
            String exerciseName = cursor.getString(cursor.getColumnIndexOrThrow(name));
            String exerciseCategory = cursor.getString(cursor.getColumnIndexOrThrow(category));
            String exerciseDescription = cursor.getString(cursor.getColumnIndexOrThrow(description));
            exercise = new exercise(id, exerciseName, exerciseCategory, exerciseDescription);
        }
        cursor.close();

        return exercise;
    }

    public int updateExercise(exercise exercise) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(name, exercise.getName());
        values.put(category, exercise.getCategory());

        String selection = exercise_contract.ExerciseEntry._ID + " = ?";
        String[] selectionArgs = { String.valueOf(exercise.getId()) };

        return db.update(exercise_contract.ExerciseEntry.TABLE_NAME, values, selection, selectionArgs);
    }

    public int deleteExercise(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = exercise_contract.ExerciseEntry._ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };

        return db.delete(exercise_contract.ExerciseEntry.TABLE_NAME, selection, selectionArgs);
    }

    public ArrayList<exercise> getRandomExercisesByCategory(String category, int limit) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<exercise> exercises = new ArrayList<>();

        String[] projection = {
                exercise_contract.ExerciseEntry._ID,
                name,
                this.category,
                description
        };

        // SQL query equivalent: SELECT * FROM exercises WHERE category = ? ORDER BY RANDOM() LIMIT ?
        Cursor cursor = db.query(
                exercise_contract.ExerciseEntry.TABLE_NAME, // The table to query
                projection,                                 // The array of columns to return (pass null to get all)
                this.category + " = ?",                     // The columns for the WHERE clause
                new String[]{category},                     // The values for the WHERE clause
                null,                                       // Don't group the rows
                null,                                       // Don't filter by row groups
                "RANDOM()",                                 // Random order
                String.valueOf(limit)                       // 'LIMIT' clause
        );

        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(exercise_contract.ExerciseEntry._ID));
            String exerciseName = cursor.getString(cursor.getColumnIndexOrThrow(name));
            String exerciseCategory = cursor.getString(cursor.getColumnIndexOrThrow(this.category));
            String exerciseDescription = cursor.getString(cursor.getColumnIndexOrThrow(description));
            exercise exercise = new exercise(id, exerciseName, exerciseCategory, exerciseDescription);
            exercises.add(exercise);
        }
        cursor.close();
        db.close();

        return exercises;
    }

    public long insertWorkoutPreset(String presetName, List<Long> exerciseIds) {
        if (getWorkoutPresets().size() >= 3) {
            return -1; // Indicates failure due to too many presets
        }
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("preset_name", presetName);
        values.put("exercise_ids", TextUtils.join(",", exerciseIds));
        return db.insert("WorkoutPresets", null, values);
    }


    public List<String> getWorkoutPresets() {
        List<String> presets = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Fetch all presets
        Cursor cursor = db.query("WorkoutPresets", new String[]{"preset_id", "preset_name", "exercise_ids"}, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String presetName = cursor.getString(cursor.getColumnIndexOrThrow("preset_name"));
                String[] exerciseIds = cursor.getString(cursor.getColumnIndexOrThrow("exercise_ids")).split(",");

                // Fetch exercise names
                List<String> exerciseNames = new ArrayList<>();
                for (String id : exerciseIds) {
                    Cursor exCursor = db.query(exercise_contract.ExerciseEntry.TABLE_NAME,
                            new String[]{exercise_contract.ExerciseEntry.COLUMN_NAME_NAME},
                            exercise_contract.ExerciseEntry._ID + " = ?",
                            new String[]{id}, null, null, null);

                    if (exCursor != null && exCursor.moveToFirst()) {
                        String exerciseName = exCursor.getString(exCursor.getColumnIndexOrThrow(exercise_contract.ExerciseEntry.COLUMN_NAME_NAME));
                        exerciseNames.add(exerciseName);
                        exCursor.close();
                    }
                }

                // Concatenate exercise names for display
                presets.add(presetName + " - " + TextUtils.join(", ", exerciseNames));
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return presets;
    }

    public boolean deleteWorkoutPreset(int presetId) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Assuming "preset_id" is the primary key column name for your presets
        return db.delete("WorkoutPresets", "preset_id = ?", new String[]{String.valueOf(presetId)}) > 0;
    }



    private String createWorkoutPresetTable() {
        return "CREATE TABLE WorkoutPresets (" +
                "preset_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "preset_name TEXT," +
                "exercise_ids TEXT)";
    }
}

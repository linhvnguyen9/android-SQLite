package com.example.android_2604_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.android_2604_sqlite.model.Student;

import java.util.ArrayList;
import java.util.List;

public class SQLiteStudentHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "StudentDB.db";
    private static final int DB_VERSION = 1;

    public SQLiteStudentHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE student(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "gender BOOLEAN," +
                "mark REAL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    /**
     * @Description add student
     * @param student
     */
    public void addStudent(Student student){
        String sql = "INSERT INTO student(name,gender,mark) VALUES(?,?,?)";
        String[] args = {student.getName(), Boolean.toString(student.isGender()), Double.toString(student.getMark())};
        SQLiteDatabase statement = getWritableDatabase();
        statement.execSQL(sql, args);
    }

    /**
     * @Description get all student
     * @return
     */
    public List<Student> getAllStudent(){
        List<Student> students = new ArrayList<>();
        SQLiteDatabase statement = getReadableDatabase();
        Cursor resultSet = statement.query("student", null, null, null, null, null, null);
        while (resultSet != null && resultSet.moveToNext()){
            int id = resultSet.getInt(0);
            String name = resultSet.getString(1);
            boolean gender = resultSet.getString(2).equals("true");
            double mark = resultSet.getDouble(3);
            students.add(new Student(id, name, gender, mark));
        }
        return students;
    }

    /**
     * @Description get by id
     */
    public Student getStudentById(int id){
        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(id)};
        SQLiteDatabase statement = getReadableDatabase();
        Cursor rs = statement.query("student", null, whereClause, whereArgs, null, null, null);
        if (rs.moveToNext()){
            String name = rs.getString(1);
            boolean gender = rs.getString(2).equals("true");
            double mark = rs.getDouble(3);

            return new Student(id, name, gender, mark);
        }
        return null;
    }

    /**
     * @Description Update student by id
     */
    public int update(Student student){
        ContentValues values = new ContentValues();
        values.put("name", student.getName());
        values.put("gender", student.isGender());
        values.put("mark", student.getMark());
        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(student.getId())};
        SQLiteDatabase statement = getWritableDatabase();
        return statement.update("student", values, whereClause, whereArgs);
    }



    /**
     * @Description delete by id
     */
    public int deleteById(int id){
        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(id)};
        SQLiteDatabase statement = getWritableDatabase();
        return statement.delete("student", whereClause, whereArgs);
    }
}

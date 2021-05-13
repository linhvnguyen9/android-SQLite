package com.example.android_2604_sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.android_2604_sqlite.model.Student;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btAdd, btAll, btGet, btUpdate, btDelete;
    private EditText txtId, txtName, txtMark;
    private RadioButton male, female;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private SQLiteStudentHelper sqLiteStudentHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btDelete.setEnabled(false);
        btUpdate.setEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(recyclerViewAdapter);
        sqLiteStudentHelper = new SQLiteStudentHelper(this);

        /**Add student to list**/
        btAdd.setOnClickListener(v -> {
            String name = txtName.getText().toString();
            boolean gender = false;
            if (male.isChecked()){
                gender = true;
            }
            try {
                double mark = Double.parseDouble(txtMark.getText().toString());
                Student student = new Student(name, gender, mark);
                sqLiteStudentHelper.addStudent(student);
            }catch (NumberFormatException e){
                System.out.println(e + "");
            }
        });

        /**Get student**/
        btAll.setOnClickListener(v -> {
            List<Student> students = sqLiteStudentHelper.getAllStudent();
            recyclerViewAdapter.setStudents(students);
            recyclerView.setAdapter(recyclerViewAdapter);
        });

        /**Get student**/
        btGet.setOnClickListener(v -> {
            try {
                int id = Integer.parseInt(txtId.getText().toString());
                Student student = sqLiteStudentHelper.getStudentById(id);
                if (student == null){
                    Toast.makeText(getApplicationContext(), "Khong co", Toast.LENGTH_SHORT);
                }else {
                    txtName.setText(student.getName());
                    txtMark.setText(student.getMark() + "");
                    if (student.isGender()){
                        male.setChecked(true);
                    }else {
                        female.setChecked(true);
                    }
                }

                btDelete.setEnabled(true);
                btUpdate.setEnabled(true);
                btAdd.setEnabled(false);
                txtId.setEnabled(false);
            }catch (NumberFormatException e){
                System.out.println(e + "");
            }
        });

        /**Update student**/
        btUpdate.setOnClickListener(v -> {
            try {
                int id = Integer.parseInt(txtId.getText().toString());
                String name = txtName.getText().toString();
                boolean gender = false;
                if (male.isChecked()){
                    gender = true;
                }else if (female.isChecked()){
                    gender = false;
                }
                double mark = Double.parseDouble(txtMark.getText().toString());
                Student student = new Student(id, name, gender, mark);
                sqLiteStudentHelper.update(student);

                btDelete.setEnabled(false);
                btUpdate.setEnabled(false);
                btAdd.setEnabled(true);
                txtId.setEnabled(true);
            }catch (NumberFormatException e){
                System.out.println(e + "");
            }
        });

        /**Delete student**/
        btDelete.setOnClickListener(v -> {
            try {
                int id = Integer.parseInt(txtId.getText().toString());
                sqLiteStudentHelper.deleteById(id);

                btDelete.setEnabled(false);
                btUpdate.setEnabled(false);
                btAdd.setEnabled(true);
                txtId.setEnabled(true);
            }catch (NumberFormatException e){
                System.out.println(e + "");
            }
        });
    }
    
    public void init(){
        btAdd = findViewById(R.id.btAdd);
        btAll = findViewById(R.id.btAll);
        btGet = findViewById(R.id.btGet);
        btUpdate = findViewById(R.id.btUpdate);
        btDelete = findViewById(R.id.btDelete);

        txtId = findViewById(R.id.stID);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        txtName = findViewById(R.id.stName);
        txtMark = findViewById(R.id.stMark);

        recyclerView = findViewById(R.id.recyclerView);
    }
}
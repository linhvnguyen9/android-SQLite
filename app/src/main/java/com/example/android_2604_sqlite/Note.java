package com.example.android_2604_sqlite;

public class Note {
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_search,menu);
//        MenuItem item=menu.findItem(R.id.mSearch);
//        SearchView searchView=(SearchView) MenuItemCompat.getActionView(item);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                List<Student> list=sqLiteHelper.getStudentByName(newText);
//                adapter.setStudents(list);
//                recyclerView.setAdapter(adapter);
//                return true;
//            }
//        });
//        return super.onCreateOptionsMenu(menu);
//    }

//    // SELECT * FROM student WHERE name LIKE ?
//    public List<Student> getStudentByName(String name) {
//        List<Student> list=new ArrayList<>();
//        String whereClause = "name LIKE ?";
//        String[] whereArgs = {"%" + name + "%"};
//        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
//        Cursor cursor = sqLiteDatabase.query("student", null, whereClause, whereArgs, null, null, null, null);
//        while (cursor != null && cursor.moveToNext()) {
//            int studentId = cursor.getInt(cursor.getColumnIndex("id"));
//            String studentName = cursor.getString(cursor.getColumnIndex("name"));
//            boolean studentGender = cursor.getInt(cursor.getColumnIndex("gender"))==1;
//            double studentMark=cursor.getDouble(cursor.getColumnIndex("mark"));
//            list.add(new Student(studentId, studentName, studentGender,studentMark));
//        }
//        cursor.close();
//        return list;
//    }
}

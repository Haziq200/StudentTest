package sg.edu.rp.dmsd.studenttest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "student.db";

    private static final String TABLE_STUDENT = "student";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_GPA = "gpa";

    public DBHelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_STUDENT + "(" + COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + "TEXT," + COLUMN_GPA + " TEXT )";
        db.execSQL(createTableSql);
        Log.i("info","created tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        onCreate(db);
    }


    public void insertStudent(String name,String gpa){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        // Store the column name as key and the description as value
        values.put(COLUMN_NAME, name);

        // Store the column name as key and the date as value
        values.put(COLUMN_GPA, gpa);

        // Insert the row into the TABLE_TASK
        db.insert(TABLE_STUDENT, null, values);

        // Close the database connection
        db.close();
    }


    public ArrayList<String> getStudentContent(){
        // Create an ArrayList that holds String objects
        ArrayList<String> student = new ArrayList<String>();

        // Select all the tasks' description
        String selectQuery = "SELECT " + COLUMN_ID + "," + COLUMN_NAME + ","
                + COLUMN_GPA + " FROM " + TABLE_STUDENT;



        // Get the instance of database to read
        SQLiteDatabase db = this.getReadableDatabase();

        // Run the SQL query and get back the Cursor object
        Cursor cursor = db.rawQuery(selectQuery, null);

        // moveToFirst() moves to first row
        if (cursor.moveToFirst()) {

            do {

                student.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // Close connection
        cursor.close();
        db.close();

        return student;
    }

    public  ArrayList<Student> getTasks(){
        ArrayList<Student> tasks = new ArrayList<Student>();
        String query = "SELECT " + COLUMN_ID + ", "
                + COLUMN_NAME + ", "
                + COLUMN_GPA
                + " FROM " + TABLE_STUDENT;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String gpa = cursor.getString(2);
                Student student = new Student(id, name, gpa);
                tasks.add(student);
            }while(cursor.moveToFirst());
        }cursor.close();
        db.close();
        return  tasks;
    }

}

package sg.edu.rp.dmsd.studenttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etGPA;
    Button btnInsert;
    Button btnRetrieve;
    TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        etName = findViewById(R.id.etName);
        etGPA = findViewById(R.id.etGPA);
        btnInsert = findViewById(R.id.btnInsert);
        btnRetrieve = findViewById(R.id.btnRetrieve);
        tvShow = findViewById(R.id.Result);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(MainActivity.this);

                String strGPA = etGPA.getText().toString();


                db.insertStudent(etName.getText().toString(), strGPA);
                db.close();
            }
        });


        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHelper db = new DBHelper(MainActivity.this);

                ArrayList<String> data = db.getStudentContent();
                db.close();

                String txt = "";
                for(int i = 0;i < data.size();i++){
                    Log.d("Database Content",i + ". " + data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
                tvShow.setText(txt);
            }
        });

    }
}

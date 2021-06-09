package sg.edu.rp.c346.id20011280.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText newTodo;
    Spinner tdItems;
    ListView lvItems;
    ArrayList<String> alTodo;
    ArrayAdapter<String> aaTodo;
    Button buttonAdd;
    Button buttonDelete;
    Button buttonClear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newTodo = findViewById(R.id.Todo);
        buttonAdd = findViewById(R.id.btnAdd);
        buttonDelete = findViewById(R.id.btnDelete);
        buttonClear = findViewById(R.id.btnClear);
        lvItems= findViewById(R.id.lv);
        tdItems = findViewById(R.id.Todoitems);

        alTodo = new ArrayList<>();

        aaTodo = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,alTodo);
        lvItems.setAdapter(aaTodo);


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String EditTodo = newTodo.getText().toString();
                alTodo.add(EditTodo);
                aaTodo.notifyDataSetChanged();
                newTodo.setText(null);
                Toast.makeText(MainActivity.this, "A Task has been added", Toast.LENGTH_LONG).show();
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(alTodo.size() == 0)
                {
                    Toast.makeText(MainActivity.this, "Theres nothing to remove", Toast.LENGTH_LONG).show();
                    return;
                }
                int Position = Integer.parseInt(newTodo.getText().toString());

                if(Position > alTodo.size() - 1)
                {
                    Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {
                    alTodo.remove(Position);
                    aaTodo.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "A Task has been removed", Toast.LENGTH_LONG).show();
                    return;

                }


            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                alTodo.clear();
                aaTodo.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "All Tasks has been cleared", Toast.LENGTH_LONG).show();

            }
        });
        tdItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                switch (position){
                    case 0:
                        newTodo.setHint("Type in your new task here");
                        buttonDelete.setEnabled(false);
                        buttonAdd.setEnabled(true);
                        break;
                    case 1:
                        newTodo.setHint("Type in index of the task you want to remove starting from 0");
                        buttonDelete.setEnabled(true);
                        buttonAdd.setEnabled(false);
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });





    }
}
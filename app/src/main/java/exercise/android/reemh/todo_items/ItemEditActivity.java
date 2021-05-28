package exercise.android.reemh.todo_items;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ItemEditActivity extends AppCompatActivity {

    public TodoItemsHolderImpl holder = null;
    TextView creationTime;
    TextView modifiedTime;
    EditText description;
    CheckBox myCheckBox;
    TodoItem item;
    String oldDescription;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        if (holder == null){
            holder = MyApplication.getInstance().getItemsStorage();
        }
        Intent intent = getIntent();
        if (!intent.hasExtra("edit_item")){ //todo
            finish();
            return;
        }
        item = (TodoItem) intent.getSerializableExtra("clicked_item");
        if (item == null){
            finish();
            return;
        }
        description = findViewById(R.id.description);
        myCheckBox = findViewById(R.id.checkBox2);
        creationTime = findViewById(R.id.CreationDate);
        modifiedTime = findViewById(R.id.modifiedDate);
        oldDescription = item.getDescription();

        description.setText(item.getDescription());
//        myCheckBox.setChecked(item.isDone());
        creationTime.setText(item.getCreationTime().toString());
        if (item.getFinishedTime() != null){
            modifiedTime.setText(item.getFinishedTime().toString());
        }
        else
        {
            String modified = "not finish yet";
            modifiedTime.setText(modified);
        }
        String statusProgress = "";
        if (item.isDone())
        {
            myCheckBox.setChecked(true);
            statusProgress = "Done";
        }
        else {
            myCheckBox.setChecked(false);
            statusProgress = "In progress";
        }

        description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!description.getText().toString().equals("")){
                    item.setDescription(s.toString());
                }
                else {
                  //  Toast.makeText(ItemEditActivity.class, "This is my Toast message!", Toast.LENGTH_LONG).show();
                }
                // updateTime
            }
        });

        myCheckBox.setOnCheckedChangeListener((buttonView, isChecked) ->{
            if (isChecked){
                holder.markItemDone(item);
            }
            else{
                holder.markItemInProgress(item);
            }
            // change the date todo
        });





    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("description_before_edit", description.getText().toString());
        outState.putString("last_edit_date", modifiedTime.toString());
        outState.putString("creation_date", creationTime.toString());
        outState.putBoolean("status", myCheckBox.isChecked());
        outState.putString("old_description", oldDescription);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        description.setText(savedInstanceState.getString("description_before_edit"));
        modifiedTime.setText(savedInstanceState.getString("last_edit_date"));
        creationTime.setText(savedInstanceState.getString("creation_date"));
        myCheckBox.setChecked(savedInstanceState.getBoolean("status"));
        oldDescription = savedInstanceState.getString("old_description");
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        if (description.getText().toString().equals("")){
            item.setDescription(oldDescription);
        }
    }
}

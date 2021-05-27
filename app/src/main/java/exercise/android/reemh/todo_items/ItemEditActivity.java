package exercise.android.reemh.todo_items;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ItemEditActivity extends AppCompatActivity {

    public TodoItemsHolderImpl holder = null;
    TextView description;
    CheckBox myCheckBox;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        Intent intent = getIntent();

        if (holder == null){
            holder = MyApplication.getInstance().getItemsStorage();
        }
        if (!intent.hasExtra("edit_item")){
            finish();
            return;
        }


    }

    @Override //todo
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override //todo
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onBackPressed(){ //todo
        super.onBackPressed();
//        String descript =
    }
}

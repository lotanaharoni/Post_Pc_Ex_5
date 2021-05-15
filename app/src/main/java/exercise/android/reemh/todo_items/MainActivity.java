package exercise.android.reemh.todo_items;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

  public TodoItemsHolder holder = null;
  EditText editText;
  FloatingActionButton buttonCreateTodoItem;
  RecyclerView recyclerView;
  MyAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (holder == null) {
      holder = new TodoItemsHolderImpl();
    }

    editText = findViewById(R.id.editTextInsertTask);
    buttonCreateTodoItem = findViewById(R.id.buttonCreateTodoItem);
    recyclerView = findViewById(R.id.recyclerTodoItemsList);
    recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    adapter = new MyAdapter(this.holder, this);
    recyclerView.setAdapter(adapter);

    editText.setText("");

    buttonCreateTodoItem.setOnClickListener(v -> {
      if (!editText.getText().toString().equals("")){
        holder.addNewInProgressItem(editText.getText().toString());
        editText.setText("");
        this.adapter.notifyDataSetChanged();
      }
    });

  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putSerializable("TodoItemsHolder", holder);
    outState.putString("text", editText.getText().toString());
  }

  @Override
  protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    this.holder.loadInstanceState(((TodoItemsHolder)savedInstanceState.getSerializable("TodoItemsHolder")).getCurrentItems());
    editText.setText(savedInstanceState.getString("text"));
  }
}

/*

SPECS:

- the "TodoItems" list is shown in the screen
  * DONE items should show the checkbox as checked, and the description with a strike-through text
  * IN-PROGRESS items should show the checkbox as not checked, and the description text normal
- when a screen rotation happens (user flips the screen):
  * the UI should still show the same list of TodoItems
  * the edit-text should store the same user-input (don't erase input upon screen change)
*/

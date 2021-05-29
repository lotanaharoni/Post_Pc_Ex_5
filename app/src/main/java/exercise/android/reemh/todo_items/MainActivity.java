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
  MyApplication application;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (holder == null) {
      holder = new TodoItemsHolderImpl(this);
    }

    application = new MyApplication();
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
  protected void onStop(){
    super.onStop();
    //application.setItems(this.holder.getCurrentItems());
    // todo
  }

  @Override
  protected void onResume(){
    super.onResume();
    // todo
  }

  @Override
  protected void onDestroy(){
    super.onDestroy();
    // todo
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

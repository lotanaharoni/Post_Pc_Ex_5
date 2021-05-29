package exercise.android.reemh.todo_items;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

  public TodoItemsHolderImpl holder = null;
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
      holder = MyApplication.getInstance().getItemsStorage();
    }
    adapter = new MyAdapter(this.holder, this);

    holder.todoItemsLiveData.observe(this, todoItems -> adapter.setData(holder.getCurrentItems()));

    editText = findViewById(R.id.editTextInsertTask);
    buttonCreateTodoItem = findViewById(R.id.buttonCreateTodoItem);
    recyclerView = findViewById(R.id.recyclerTodoItemsList);
    recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    recyclerView.setAdapter(adapter);

    editText.setText("");

    buttonCreateTodoItem.setOnClickListener(v -> {
      if (!editText.getText().toString().equals("")){
        holder.addNewInProgressItem(editText.getText().toString());
        editText.setText("");
        this.adapter.notifyDataSetChanged();
      }
    });

    adapter.onDeleteTask = item -> {
      holder.deleteItem(item);
    };

    adapter.onChangeDescription = item -> {
      Intent intent = new Intent(this, ItemEditActivity.class);
      intent.putExtra("clicked_item", item.getId());
      startActivity(intent);
    };


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
//    outState.putSerializable("TodoItemsHolder", holder);
//    outState.putString("text", editText.getText().toString());
  }

  @Override
  protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
//    this.holder.loadInstanceState(((TodoItemsHolder)savedInstanceState.getSerializable("TodoItemsHolder")).getCurrentItems());
//    editText.setText(savedInstanceState.getString("text"));
  }
}

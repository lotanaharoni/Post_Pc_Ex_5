package exercise.android.reemh.todo_items;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TodoItemsHolderImpl implements TodoItemsHolder {

  private final MutableLiveData<List<TodoItem>> todoItemMutableLiveData;
  public final LiveData<List<TodoItem>> todoItemsLiveData;
  private List<TodoItem> allTodoItems;
  private final SharedPreferences sp;


  public TodoItemsHolderImpl(Context context){
    this.todoItemMutableLiveData = new MutableLiveData<>();
    this.todoItemsLiveData = todoItemMutableLiveData;
    this.allTodoItems = new ArrayList<>();
    this.sp = context.getSharedPreferences("my_db", Context.MODE_PRIVATE);

    for (String key: sp.getAll().keySet()){
      if (sp.getString(key, null) == null){
        return;
      }
      TodoItem addItem = TodoItem.parseKey(sp.getString(key, null));
      if (addItem != null){
        allTodoItems.add(addItem);
      }
    }
    todoItemMutableLiveData.setValue(this.getCurrentItems());
  }

  @Override
  public List<TodoItem> getCurrentItems() {
    return new ArrayList<>(this.allTodoItems);
  }

  @Override
  public void addNewInProgressItem(String description) {
    TodoItem newItem = new TodoItem(description);
    this.allTodoItems.add(0, newItem);

    setSharedPreferences(newItem);
    setLiveData();
  }

  @Override
  public void markItemDone(TodoItem item) {
    for (TodoItem todoItem: this.allTodoItems){
      if (todoItem == item){
        todoItem.setCurrentStatus(true);
      }
    }
    Collections.sort(this.allTodoItems);

    setSharedPreferences(item);
    setLiveData();
  }

  @Override
  public void markItemInProgress(TodoItem item) {
    for (TodoItem todoItem: this.allTodoItems){
      if (todoItem == item){
        todoItem.setCurrentStatus(false);
      }
    }
    Collections.sort(this.allTodoItems);

    setSharedPreferences(item);
    setLiveData();
  }

  @Override
  public void deleteItem(TodoItem item) {
    if (item != null){
      this.allTodoItems.remove(item);

      SharedPreferences.Editor editor = sp.edit();
      editor.remove(item.getId());
      editor.apply();
      setLiveData();
    }
  }

  @Override
  public void setItemDescription(TodoItem oldItem, String newDescription){
    for (TodoItem todoItem: this.allTodoItems){
      if (todoItem == oldItem){
        todoItem.setDescription(newDescription);
      }
    }
    Collections.sort(this.allTodoItems);

    setSharedPreferences(oldItem);
    setLiveData();
  }

  public void loadInstanceState(List<TodoItem> newList){
    this.allTodoItems = new ArrayList<>(newList);
  }

  private void setSharedPreferences(TodoItem item){
    SharedPreferences.Editor editor = sp.edit();
    editor.putString(item.getId(), item.getSerialize());
    editor.apply();
  }

  private void setLiveData(){
    todoItemMutableLiveData.setValue(this.getCurrentItems());
  }

  @Nullable
  public TodoItem getItem(String itemId){
    for (TodoItem item: allTodoItems){
      if (item.getId().equals(itemId)){
        return item;
      }
    }
    return null;
  }
}

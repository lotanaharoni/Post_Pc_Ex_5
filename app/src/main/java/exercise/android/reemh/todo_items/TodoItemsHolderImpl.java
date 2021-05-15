package exercise.android.reemh.todo_items;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TodoItemsHolderImpl implements TodoItemsHolder {

  private List<TodoItem> allTodoItems;

  public TodoItemsHolderImpl(){
    this.allTodoItems = new ArrayList<>();
  }

  @Override
  public List<TodoItem> getCurrentItems() {
    return this.allTodoItems; }

  @Override
  public void addNewInProgressItem(String description) {
    TodoItem newItem = new TodoItem(description);
    this.allTodoItems.add(0, newItem);
  }

  @Override
  public void markItemDone(TodoItem item) {
    for (TodoItem todoItem: this.allTodoItems){
      if (todoItem == item){
        todoItem.setCurrentStatus(true);
      }
    }
    Collections.sort(this.allTodoItems);
  }

  @Override
  public void markItemInProgress(TodoItem item) {
    for (TodoItem todoItem: this.allTodoItems){
      if (todoItem == item){
        todoItem.setCurrentStatus(false);
      }
    }
    Collections.sort(this.allTodoItems);
  }

  @Override
  public void deleteItem(TodoItem item) {
    if (item != null){
      this.allTodoItems.remove(item);
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
  }

  public void loadInstanceState(List<TodoItem> newList){
    this.allTodoItems = new ArrayList<>(newList);
  }
}

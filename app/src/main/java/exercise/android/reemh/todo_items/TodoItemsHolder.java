package exercise.android.reemh.todo_items;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public interface TodoItemsHolder extends Serializable {

  /** Get a copy of the current items list */
  List<TodoItem> getCurrentItems();

  /**
   * Creates a new TodoItem and adds it to the list, with the @param description and status=IN-PROGRESS
   * Subsequent calls to [getCurrentItems()] should have this new TodoItem in the list
   */
  void addNewInProgressItem(String description);

  /** mark the @param item as DONE */
  void markItemDone(TodoItem item);

  /** mark the @param item as IN-PROGRESS */
  void markItemInProgress(TodoItem item);

  /** delete the @param item */
  void deleteItem(TodoItem item);

  /** load the state of the @param item */
  void loadInstanceState(List<TodoItem> newList);

  /** Update description of the @param item */
  void setItemDescription(TodoItem oldItem, String newDescription);

  /** Update modified time of the @param item */
  public void setItemModifiedTime(TodoItem oldItem, Date newDate);
}

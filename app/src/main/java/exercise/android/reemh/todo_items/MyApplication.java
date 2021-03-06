package exercise.android.reemh.todo_items;

import android.app.Application;

public class MyApplication extends Application {

    private static MyApplication instance = null;
    TodoItemsHolderImpl itemsStorage;

    @Override
    public void onCreate(){
        super.onCreate();
        itemsStorage = new TodoItemsHolderImpl(this);
        instance = this;
    }

    public static MyApplication getInstance(){
        return instance;
    }

    public TodoItemsHolderImpl getItemsStorage(){
        return this.itemsStorage;
    }
}

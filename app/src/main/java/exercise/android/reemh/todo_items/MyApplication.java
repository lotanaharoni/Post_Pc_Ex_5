package exercise.android.reemh.todo_items;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.List;

public class MyApplication extends Application {
    Context myContext;
    TodoItemsHolderImpl itemsStorage;
    List<TodoItem> items;
    SharedPreferences sharedPreferences;
    private static MyApplication instance = null;


    public void setItems(List<TodoItem> itemsToSave){
        this.items = itemsToSave;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;
    }

    public static MyApplication getInstance(){
        return instance;
    }

    public TodoItemsHolderImpl getItemsStorage(){
        return this.itemsStorage;
    }
}

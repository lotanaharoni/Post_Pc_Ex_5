package exercise.android.reemh.todo_items;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.List;

public class MyApplication extends Application {
    Context myContext;
    List<TodoItem> items;
    SharedPreferences sharedPreferences;

    public MyApplication(Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.myContext = context;
    }

    public void setItems(List<TodoItem> itemsToSave){
        this.items = itemsToSave;
    }

    @Override
    public void onCreate(){
        super.onCreate();
    }
}

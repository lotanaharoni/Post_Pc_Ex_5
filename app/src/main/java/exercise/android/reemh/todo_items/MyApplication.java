package exercise.android.reemh.todo_items;

import android.app.Application;
import android.content.Context;

import java.util.List;

public class MyApplication extends Application {
    Context myContext;
    List<TodoItem> items;

    public MyApplication(Context context){
        this.myContext = context;
    }

    @Override
    public void onCreate(){
        super.onCreate();
    }
}

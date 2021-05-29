package exercise.android.reemh.todo_items;

import com.google.gson.Gson;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class TodoItem implements Serializable, Comparable<TodoItem>{
    private final String id;
    private boolean isDone;
    private final Date creationTime;
    private String description;
    private Date modifiedTime;

    public TodoItem(String description){
        this.id = UUID.randomUUID().toString();
        this.isDone = false;
        this.creationTime = new Date();
        this.description = description;
        this.modifiedTime = new Date();

        try {
            Thread.sleep(2);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void setFinishedTime(Date newFinishedTimeDate){
        this.modifiedTime = newFinishedTimeDate;
    }

    @Override
    public String toString(){
        return this.description;
    }

    public void flipState(){
        if (this.isDone){
            this.setCurrentStatus(false);
        }
        else {
            this.setCurrentStatus(true);
        }
    }

    public boolean isDone() {
        return isDone;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public String getDescription() {
        return description;
    }

    public void setCurrentStatus(boolean done) {
        this.modifiedTime = new Date();
        isDone = done;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    @Override
    public int compareTo(TodoItem o) {
        if (o != null){
            if (this.isDone && o.isDone){
                return this.modifiedTime.compareTo(o.modifiedTime);
            }
            else if (this.isDone && !o.isDone){
                return 1;
            }
            else if (!this.isDone && o.isDone){
                return -1;
            }
            else{
                return o.creationTime.compareTo(this.creationTime);
            }
        }
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof TodoItem)){
            return false;
        }
        TodoItem otherTodoItem = (TodoItem)obj;
        return this.isDone == otherTodoItem.isDone && this.creationTime == otherTodoItem.creationTime &&
                this.description.equals(otherTodoItem.description) && this.modifiedTime == this.modifiedTime;
    }

    public static TodoItem parseKey(String keySerialize){
        Gson gson = new Gson();
        return gson.fromJson(keySerialize, TodoItem.class);
    }

    public String getId(){
        return this.id;
    }

    public String getSerialize(){
        Gson gson = new Gson();
        return  gson.toJson(this);
    }
}

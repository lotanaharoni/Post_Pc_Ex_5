package exercise.android.reemh.todo_items;

import android.provider.Telephony;

import com.google.gson.Gson;

import java.io.Serializable;
import java.io.StringReader;
import java.util.Date;
import java.util.UUID;

public class TodoItem implements Serializable, Comparable<TodoItem>{
    private final String id;
    private boolean isDone;
    private Date creationTime;
    private String description;
    private Date finishedTime;

    public TodoItem(String description){
        this.id = UUID.randomUUID().toString();
        this.isDone = false;
        this.creationTime = new Date();
        this.description = description;
        this.finishedTime = null;
    }

//    public TodoItem(){
//        this.isDone = false;
//        this.description = "";
//    }

    public void setFinishedTime(Date newFinishedTimeDate){
        this.finishedTime = newFinishedTimeDate;
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
        if (done){
            this.finishedTime = new Date();
        }
        else {
            this.finishedTime = null;
        }
        isDone = done;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getFinishedTime() {
        return finishedTime;
    }

    @Override
    public int compareTo(TodoItem o) { //todo
        if (o != null){
            if (this.isDone && o.isDone){
                return this.finishedTime.compareTo(o.finishedTime);
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
                this.description.equals(otherTodoItem.description) && this.finishedTime == this.finishedTime;
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

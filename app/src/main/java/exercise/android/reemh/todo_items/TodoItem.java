package exercise.android.reemh.todo_items;

import java.io.Serializable;
import java.util.Date;

public class TodoItem implements Serializable {
    private boolean isDone;
    private Date creationTime;
    private String description;
    private Date finishedTime;

    public TodoItem(String description){
        this.isDone = false;
        this.creationTime = new Date();
        this.description = description;
        this.finishedTime = null;
    }

    public void  setFinishedTime(Date newFinishedTimeDate){
        this.finishedTime = newFinishedTimeDate;
    }

    public void flipState(){
        this.isDone = !this.isDone;
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

    public void setDone(boolean done) {
        isDone = done;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

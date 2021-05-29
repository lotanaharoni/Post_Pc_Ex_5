package exercise.android.reemh.todo_items;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{
    TodoItemsHolder myHolder;
    OnTaskClickListener onDeleteTask;
    OnTaskClickListener onChangeDescription;
    Context context;
    private final ArrayList<TodoItem> items = new ArrayList<>();

    public MyAdapter(TodoItemsHolder newHolder, Context newContext){
        super();
        this.context = newContext;
        this.myHolder = newHolder;
    }

    public void setData(List<TodoItem> newItems){
        items.clear();
        items.addAll(newItems);
        Collections.sort(this.items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View newView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_todo_item, parent, false);
        return new MyViewHolder(newView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TodoItem item = items.get(position);

        holder.myCheckBox.setChecked(item.isDone());
        holder.myTextView.setText(item.getDescription());
        if (item.isDone()){
            holder.myTextView.setPaintFlags(holder.myTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        else {
            holder.myTextView.setPaintFlags(0);
        }

        holder.myDeleteButton.setOnClickListener(v -> {
            myHolder.deleteItem(item);
            notifyDataSetChanged();
        });

        holder.myUpdateButton.setOnClickListener(v -> {
            if (this.onChangeDescription != null){
                onChangeDescription.onClick(item);
            }
        });

        holder.myCheckBox.setOnClickListener(v -> {
            if (item.isDone()){
                myHolder.markItemInProgress(item);
            }
            else{
                myHolder.markItemDone(item);
            }
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return this.myHolder.getCurrentItems().size();
    }
}


package exercise.android.reemh.todo_items;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{
    TodoItemsHolder myHolder;
    LayoutInflater inflater;
    Context context;

    public MyAdapter(TodoItemsHolder newHolder, Context newContext){
        this.myHolder = newHolder;
        this.context = newContext;
        this.inflater = LayoutInflater.from(newContext);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View newView = inflater.inflate(R.layout.row_todo_item, parent, false);
        return new MyViewHolder(newView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TodoItem item = myHolder.getCurrentItems().get(position);
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
            Intent intent = new Intent(this.context, ItemEditActivity.class);
            intent.putExtra("clicked_item", item);
            this.context.startActivity(intent);
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

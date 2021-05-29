package exercise.android.reemh.todo_items;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView myTextView;
    CheckBox myCheckBox;
    ImageButton myDeleteButton;
    ImageButton myUpdateButton;

    public MyViewHolder(@NonNull View itemView){
        super(itemView);
        myCheckBox = itemView.findViewById(R.id.checkBox);
        myTextView = itemView.findViewById(R.id.description);
        myDeleteButton = itemView.findViewById(R.id.todoDeleteitem);
        myUpdateButton = itemView.findViewById(R.id.todoUpdateItem);
    }
}

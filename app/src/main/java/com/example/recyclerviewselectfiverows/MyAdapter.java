package com.example.recyclerviewselectfiverows;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ModelClass> arrayList;
    private int selectedRowCount = 0;

    public MyAdapter(MainActivity context, ArrayList<ModelClass> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ModelClass modelClass = arrayList.get(position);
        holder.textView.setText(modelClass.getName());

        if (modelClass.isSelected()) {
            holder.itemView.setBackgroundColor(context.getResources()
                    .getColor(R.color.highlight));
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ConstraintLayout rowItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.textView);
            this.rowItem = itemView.findViewById(R.id.rowitem);

            rowItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    setMultipleSelection(getAdapterPosition());
                }
            });
        }
    }

    private void setMultipleSelection(int adapterPosition) {
        //If row is already selected then unselect it, otherwise select it.
        if (arrayList.get(adapterPosition).isSelected()) {
            arrayList.get(adapterPosition).setSelected(false);
            selectedRowCount--;
        } else {
            if(selectedRowCount < 5){
                arrayList.get(adapterPosition).setSelected(true);
                selectedRowCount++;
            }else {
                Toast.makeText(context,
                        "You can select maximum 5 rows.",
                        Toast.LENGTH_SHORT).show();
            }
        }
        notifyDataSetChanged();
    }

}

package com.example.mypantry;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PantryAdapter extends RecyclerView.Adapter<PantryAdapter.PantryViewHolder> {

    private List<PantryItem> pantryList;
    private Context context;
    private Database db;

    // Constructor
    public PantryAdapter(Context context, List<PantryItem> pantryList) {
        this.context = context;
        this.pantryList = pantryList;

        db = new Database(context, "pantry", null, 1);
    }

    @NonNull
    @Override
    public PantryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_pantry, parent, false);

        return new PantryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PantryViewHolder holder, int position) {

        PantryItem item = pantryList.get(position);

        // Display pantry item information
        holder.textView.setText(
                "Item: " + item.getName() +
                        "\nQuantity: " + item.getQuantity() + " " + item.getUnit() +
                        "\nExpiry Date: " + item.getExpiry()
        );

        // DELETE BUTTON
        holder.btnDelete.setOnClickListener(v -> {

            db.deleteItem(item.getId());

            int currentPosition = holder.getAdapterPosition();

            if (currentPosition != RecyclerView.NO_POSITION) {
                pantryList.remove(currentPosition);
                notifyItemRemoved(currentPosition);
            }

            Toast.makeText(
                    context,
                    "Deleted " + item.getName(),
                    Toast.LENGTH_SHORT
            ).show();
        });

        // EDIT BUTTON
        holder.btnEdit.setOnClickListener(v -> {

            Intent intent = new Intent(context, EditItemActivity.class);

            // Send the ID of the item to the Edit screen
            intent.putExtra("ITEM_ID", item.getId());

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return pantryList.size();
    }

    // ViewHolder
    static class PantryViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        Button btnEdit;
        Button btnDelete;

        public PantryViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textViewPantryItem);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
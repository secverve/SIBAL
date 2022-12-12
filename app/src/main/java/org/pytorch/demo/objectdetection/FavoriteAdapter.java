package org.pytorch.demo.objectdetection;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import androidx.fragment.app.Fragment;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyViewHolder>{
    private ArrayList<Favorite> mDataset;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        //ViewHolder
        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
        }
    }

    public FavoriteAdapter(ArrayList<Favorite> myData){
        this.mDataset = myData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.MyViewHolder holder, int position) {

        holder.name.setText(mDataset.get(position).getName());

            }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
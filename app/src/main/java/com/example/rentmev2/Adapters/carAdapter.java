package com.example.rentmev2.Adapters;

import com.example.rentmev2.Classes.*;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.rentmev2.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class carAdapter extends FirebaseRecyclerAdapter<
        Car, carAdapter.carViewHolder> {
    private final String userEmail;

    public carAdapter(
            @NonNull FirebaseRecyclerOptions<Car> options, String userEmail) {
        super(options);
        this.userEmail = userEmail;
    }

    @Override
    protected void onBindViewHolder(@NonNull carViewHolder holder,
                                    int position, @NonNull Car model) {

        if (model != null) {
            holder.brand.setText(model.getBrand());
            holder.model.setText(model.getModel());
            holder.seats.setText(String.valueOf(model.getSeats()));
            holder.price.setText(String.valueOf(model.getPrice()));
// Access the nested URL
            String imageURL = model.getImageURL();
            if (imageURL != null) {
                Glide.with(holder.itemView.getContext()).load(imageURL).into(holder.imageURL);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

              //      Intent intent = new Intent(holder.itemView.getContext(), CarDescription.class);

        //            intent.putExtra("selectedCar", model); // Assuming Car implements Serializable or Parcelable
       //             intent.putExtra("email",userEmail);
      //              holder.itemView.getContext().startActivity(intent);
                }
            });
        }


    }

    @NonNull
    @Override
    public carViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.caradapter, parent, false);
        return new carViewHolder(view);
    }

    static class carViewHolder extends RecyclerView.ViewHolder {
        TextView model, brand, seats, price;
        ImageView imageURL;

        public carViewHolder(@NonNull View itemView) {
            super(itemView);
            model = itemView.findViewById(R.id.listModel);
            brand = itemView.findViewById(R.id.listBrand);
            seats = itemView.findViewById(R.id.listSeat);
            price = itemView.findViewById(R.id.listPrice);

            imageURL = itemView.findViewById(R.id.imageView3);
        }
    }
}

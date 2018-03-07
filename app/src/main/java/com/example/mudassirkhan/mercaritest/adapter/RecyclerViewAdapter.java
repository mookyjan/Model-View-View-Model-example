package com.example.mudassirkhan.mercaritest.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mudassirkhan.mercaritest.R;
import com.example.mudassirkhan.mercaritest.model.DataItem;

import java.util.List;

/**
 * Created by Mudassir Khan on 2/22/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private List<DataItem> dataItems;


    Context context;

    public RecyclerViewAdapter(List<DataItem> powderModelClassList) {
        this.dataItems = powderModelClassList;
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView;


        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_card_layout, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {

        DataItem allDataModelClass = dataItems.get(position);
        //set the name of item
        holder.txtItemName.setText(allDataModelClass.getId());
        //set the num likes
        holder.txtNumLikes.setText(Integer.toString(allDataModelClass.getNumLikes()));
        //set the comments
        holder.txtNumComments.setText(Integer.toString(allDataModelClass.getNumComments()));
        //set the price
        holder.txtPrice.setText(Integer.toString(allDataModelClass.getPrice()));
        //use the glide library to load image into imageView
        Glide.with(context).load(allDataModelClass.getPhoto()).into(holder.imageView);
        //show the sold Out bage if the status is sold_out
        if(allDataModelClass.getStatus().equalsIgnoreCase("sold_out")){
            //set the sold bage visible
            holder.imgSoldBage.setVisibility(View.VISIBLE);
        }else {
            //set the sold bage invisible
            holder.imgSoldBage.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {

        return dataItems.size();
    }
}



class RecyclerViewHolder extends RecyclerView.ViewHolder {

    public AppCompatTextView  txtItemName,txtNumLikes,txtNumComments,txtPrice;
    ImageView imageView,imgSoldBage;

    public CardView cardView;
    public RecyclerViewHolder(View itemView) {
        super(itemView);
        // cardView=(CardView)itemView.findViewById(R.id.card_view);
        //get the reference of ImageView
        imageView=(ImageView)itemView.findViewById(R.id.imageView);
        //get reference to the txtName
        txtItemName=(AppCompatTextView)itemView.findViewById(R.id.txtName);
        txtNumLikes=(AppCompatTextView)itemView.findViewById(R.id.txtLike);
        txtNumComments=(AppCompatTextView)itemView.findViewById(R.id.txtComment);
        txtPrice=(AppCompatTextView)itemView.findViewById(R.id.txtPrice);
        imgSoldBage=(ImageView)itemView.findViewById(R.id.imgSoldBage);
    }
}

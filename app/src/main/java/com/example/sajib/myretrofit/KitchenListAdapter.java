package com.example.sajib.myretrofit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.sajib.myretrofit.Model.Modeltwo;


import java.util.ArrayList;

/**
 * Created by Jannat Mostafiz on 4/6/2018.
 */

public class KitchenListAdapter extends RecyclerView.Adapter<KitchenListAdapter.ViewHolder> implements Filterable {
    private ArrayList<Modeltwo> mArrayList;
    private Context context;
    //create a variable for the interface
    private OnItemClickListener mListener;
    public static int kid;
    public static String kName;
    private ArrayList<Modeltwo> mFilteredList;
    private static final int EMPTY_VIEW = 10;

    public void setOnItemClickListener(OnItemClickListener listener) {

        mListener = listener;
    }


    public KitchenListAdapter(ArrayList<Modeltwo> arrayList, Context context) {
        this.mArrayList = arrayList;
        this.mFilteredList = arrayList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if(viewType==EMPTY_VIEW){

//            v= LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_view,parent,false);
//            return new ViewHolder(v,mListener);
        }
        //at first we will return the viewholder
        v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list, parent, false);
        return new ViewHolder(v, mListener);
    }



    @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
        if(holder instanceof ViewHolder){
            ViewHolder viewHolder= (ViewHolder) holder;
            String pi = String.valueOf(mFilteredList.get(position));
        }
        Modeltwo listItem = mFilteredList.get(position);
        kid = listItem.getKitchenId();
        kName = String.valueOf(listItem.getKitchenName());
        holder.textViewHead.setText(listItem.getKitchenName());
        holder.textViewDesc.setText(listItem.getCuisine());
        holder.textFoodRating.setText(String.format("%.2f",listItem.getRating()));

        String status = String.valueOf(listItem.getKitchenStatus());

        String mImage = "http://www.fnstatic.co.uk/images/content/recipe/quiz-can-you-guess-which-fast-food-item-has-more-calories-.jpeg";
        if(!listItem.getKitchenImage().isEmpty()){
            mImage = listItem.getKitchenImage();
        }

        Glide.with(context)
                .load(mImage)
                .centerCrop()
                .placeholder(R.drawable.logo)
                .crossFade()
                .into(holder.imageView1);
    }


    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }


    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = mArrayList;
                } else {

                    ArrayList<Modeltwo> filteredList = new ArrayList<>();

                    for (Modeltwo kitchen : mArrayList) {

                        if (kitchen.getAreaName().toLowerCase().contains(charString) || kitchen.getDetAddress().toLowerCase().contains(charString)
                                || kitchen.getCuisine().toLowerCase().contains(charString)|| kitchen.getKitchenName().toLowerCase().contains(charString)
                                || String.valueOf(kitchen.getKitchenId()).toLowerCase().contains(charString)) {

                            filteredList.add(kitchen);
                        }
                    }
                    mFilteredList.clear();
                    mFilteredList.addAll(filteredList);
                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                filterResults.count = mFilteredList.size();
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<Modeltwo>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getItemViewType(int position) {
        if(mFilteredList.size()==0){
            return EMPTY_VIEW;
        }
        return super.getItemViewType(position);
    }

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }




    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewHead;
        public TextView textViewDesc;
        public ImageView imageView1;
        public TextView textFoodRating;

        public ViewHolder(View itemView, final OnItemClickListener listener) {

            super(itemView);
            textViewHead = (TextView) itemView.findViewById(R.id.textFoodName);
            textViewDesc = (TextView) itemView.findViewById(R.id.textFoodCat);
            imageView1 = (ImageView) itemView.findViewById(R.id.imageView);
            textFoodRating = (TextView) itemView.findViewById(R.id.textFoodRating);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.OnItemClick(position);
                        }

                    }


                }
            });

        }
    }


}


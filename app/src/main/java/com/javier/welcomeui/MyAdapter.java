package com.javier.welcomeui;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;
import java.util.Observable;

/**
 * Created by User on 1/24/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
//   private rx.Observable<List<ResultAPI>> myList;
    private List<ResultAPI> myList;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView results;
        public MyViewHolder(View view)
        {
            super(view);
            view.setOnClickListener(this);
            results = (TextView) view.findViewById(R.id.mystuff_tv);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Intent intent = new Intent(v.getContext(), DetailsActivity.class);
            if(position != 0)

                v.getContext().startActivity(intent);
            }

        }





    public MyAdapter(List<ResultAPI> myList){
        this.myList = myList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
               View itemView = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.mystuff_list,parent, false);


        return new MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ResultAPI resultAPI=  myList.get(position);
        holder.results.setText(resultAPI.getName().toString());
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }
}

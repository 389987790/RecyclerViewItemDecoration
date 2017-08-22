package com.example.kongjian.recyclerviewitemdecoration.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

	private ArrayList<String> list;

	public MyRecyclerAdapter(ArrayList<String> list) {
		this.list = list;
	}
	
	class MyViewHolder extends RecyclerView.ViewHolder{
		TextView tv;

		public MyViewHolder(View view) {
			super(view);
			tv = (TextView)view.findViewById(android.R.id.text1);
			
		}
		
	}

	@Override
	public int getItemCount() {
		return list.size();
	}

	@Override
	public void onBindViewHolder(MyViewHolder holder, final int position) {
		holder.tv.setText(list.get(position));
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int arg1) {
		MyViewHolder holder = new MyViewHolder(View.inflate(viewGroup.getContext(), android.R.layout.simple_list_item_1, null));
		return holder;
	}
}

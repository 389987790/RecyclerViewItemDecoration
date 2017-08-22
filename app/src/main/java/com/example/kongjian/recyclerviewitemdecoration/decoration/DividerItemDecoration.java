package com.example.kongjian.recyclerviewitemdecoration.decoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

public class DividerItemDecoration extends ItemDecoration {
	
	private int mOrientation = LinearLayoutManager.VERTICAL;
	private Drawable mDivider;
	private int[] attrs= new int[]{
			android.R.attr.listDivider
	};
	
	public DividerItemDecoration(Context context, int orientation) {
		TypedArray typedArray = context.obtainStyledAttributes(attrs);
		mDivider = typedArray.getDrawable(0);
		typedArray.recycle();
		setOrientation(orientation);
	}

	public void setOrientation(int orientation){
		if(orientation!=LinearLayoutManager.HORIZONTAL&&orientation!=LinearLayoutManager.VERTICAL){
			throw new IllegalArgumentException("not horizontal and vertical");
		}
		this.mOrientation = orientation;
	}
	
	@Override
	public void onDraw(Canvas c, RecyclerView parent, State state) {
		//RecyclerView会调用该方法绘制分割线
		if(mOrientation == LinearLayoutManager.VERTICAL){//竖直
			drawVertical(c,parent);
		}else{//水平
			drawHorizontal(c,parent);
		}
		
		super.onDraw(c, parent, state);
	}
	
	private void drawHorizontal(Canvas c, RecyclerView parent) {// 画水平线
		int top = parent.getPaddingTop();
		int bottom = parent.getHeight() - parent.getPaddingBottom();
		int childCount = parent.getChildCount();
		for (int i = 0; i < childCount ; i++) {
			View child = parent.getChildAt(i);
			
			LayoutParams params = (LayoutParams) child.getLayoutParams();
			int left = child.getRight() + params.rightMargin + Math.round(ViewCompat.getTranslationX(child));
			int right = left + mDivider.getIntrinsicHeight();
			mDivider.setBounds(left, top , right, bottom);
			mDivider.draw(c);
		}
	}

	private void drawVertical(Canvas c, RecyclerView parent) {// 画水竖直
		int left = parent.getPaddingLeft();
		int right = parent.getWidth() - parent.getPaddingRight();
		int childCount = parent.getChildCount();
		for (int i = 0; i < childCount ; i++) {
			View child = parent.getChildAt(i);
			
			LayoutParams params = (LayoutParams) child.getLayoutParams();
			int top = child.getBottom() + params.bottomMargin + Math.round(ViewCompat.getTranslationY(child));
			int bottom = top + mDivider.getIntrinsicHeight();
			mDivider.setBounds(left, top , right, bottom);
			mDivider.draw(c);
		}
	}

	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
			State state) {
		// 调用此方法先获取条目之间的宽度并设置outRect矩形区域
		// 最后一项不需要分割线所以可以设置偏移量都为0
		if (parent.getChildViewHolder(view).getAdapterPosition() == parent.getAdapter().getItemCount() - 1) {
			outRect.set(0, 0, 0, 0);
			return;
		}
		if(mOrientation == LinearLayoutManager.VERTICAL){
			outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
		}else{
			outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0 );
		}
	}
}

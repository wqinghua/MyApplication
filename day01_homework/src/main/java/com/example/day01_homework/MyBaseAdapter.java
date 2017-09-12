package com.example.day01_homework;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by 陌少 on 2017/9/11.
 */

public class MyBaseAdapter extends BaseAdapter {
    private ArrayList<Bean> been;
    private Context context;

    public MyBaseAdapter(ArrayList<Bean> been, Context context) {
        this.been = been;
        this.context = context;
    }

    @Override
    public int getCount() {
        return been.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;
        if (view ==null){
            view = View.inflate(context, R.layout.list_item, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.mTextView_title.setText(been.get(i).getTitle());
        viewHolder.mTextView_context.setText(been.get(i).getContent());
        Glide.with(context).load(been.get(i).getImgs().get(0)).into(viewHolder.mImgage);
        return view;
    }

    public static class ViewHolder {
        public View rootView;
        public ImageButton mImgage;
        public TextView mTextView_title;
        public TextView mTextView_context;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mImgage = (ImageButton) rootView.findViewById(R.id.mImgage);
            this.mTextView_title = (TextView) rootView.findViewById(R.id.mTextView_title);
            this.mTextView_context = (TextView) rootView.findViewById(R.id.mTextView_context);
        }

    }
}

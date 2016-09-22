package com.gta.administrator.cniaoshop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gta.administrator.cniaoshop.R;

import java.util.List;

/**
 * Created by Administrator on 2016/9/21.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHodler> {

    private List<String> datas;

    private Context mContext;

    public MyAdapter(Context context, List<String> datas) {
        this.mContext = context;
        this.datas = datas;
    }

    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHodler viewHolder = new MyViewHodler(LayoutInflater.from(mContext).inflate(R.layout.recycler_view_item, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHodler holder, int position) {
        holder.text.setText(datas.get(position));

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHodler extends RecyclerView.ViewHolder {
        TextView text;

        public MyViewHodler(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
        }
    }

}

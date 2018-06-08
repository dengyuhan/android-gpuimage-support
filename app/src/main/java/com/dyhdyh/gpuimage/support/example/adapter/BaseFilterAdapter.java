package com.dyhdyh.gpuimage.support.example.adapter;

import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dyhdyh.gpuimage.support.example.R;
import com.dyhdyh.gpuimage.support.example.model.FilterInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.netopen.hotbitmapgg.library.view.RingProgressBar;

/**
 * @author dengyuhan
 *         created 2018/6/7 20:03
 */
public class BaseFilterAdapter extends BaseRecyclerAdapter<FilterInfo, BaseFilterAdapter.ItemHolder> {

    public BaseFilterAdapter(List<FilterInfo> data) {
        super(data);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position, FilterInfo item) {
        holder.tv_filter_name.setText(item.getName());
        holder.pb.setProgress((int) (item.getProgress() * 100));
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position, List<Object> payloads) {
        if (payloads == null || payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
        } else {
            final FilterInfo item = getItem(position);
            int progress = (int) (item.getProgress() * 100);
            ObjectAnimator.ofInt(holder.pb, "progress", holder.pb.getProgress(), progress)
                    .setDuration(500)
                    .start();
        }

    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_base_filter, parent, false));
    }


    static class ItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.pb_filter_progress)
        RingProgressBar pb;
        @BindView(R.id.tv_filter_name)
        TextView tv_filter_name;

        public ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
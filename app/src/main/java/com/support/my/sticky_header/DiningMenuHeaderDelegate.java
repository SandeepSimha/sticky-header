package com.support.my.sticky_header;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.support.my.sticky_header.lib.StickyHeaderDelegateAdapter;


/**
 * @author chers026 on 10/16/17.
 */
public class DiningMenuHeaderDelegate implements StickyHeaderDelegateAdapter<DiningMenuHeaderDelegate.ViewHolder, DiningMenuHeader> {

    @Override
    public DiningMenuHeaderDelegate.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(DiningMenuHeaderDelegate.ViewHolder holder, DiningMenuHeader item) {
        holder.bind(item);
    }

    @Override
    public void onBindStickyHeaderViewHolder(ViewHolder holder, DiningMenuHeader item) {
        holder.bind(item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView scheduleTextView;

        public ViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_dining_menu_header, parent, false));

            scheduleTextView = (TextView) itemView.findViewById(R.id.menu_header);
        }

        public void bind(DiningMenuHeader item) {
            scheduleTextView.setText(item.getMenuType());
        }
    }
}

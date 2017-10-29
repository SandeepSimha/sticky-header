package com.support.my.sticky_header;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.support.my.sticky_header.lib.DelegateAdapter;


/**
 * @author chers026 on 10/16/17.
 */
public class DiningMenuElementDelegate implements DelegateAdapter<DiningMenuElementDelegate.ViewHolder, DiningMenuElement> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, DiningMenuElement item) {
        holder.setIsRecyclable(false);
        holder.bind(item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView descriptionTextView;

        public ViewHolder(ViewGroup viewGroup) {
            super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_dining_menu_element, viewGroup, false));

            titleTextView = (TextView) itemView.findViewById(R.id.title);
            descriptionTextView = (TextView) itemView.findViewById(R.id.description);
        }

        public void bind(DiningMenuElement diningMenuElement) {
            titleTextView.setText(diningMenuElement.getTitle());
            descriptionTextView.setText(diningMenuElement.getDescription());
        }
    }
}

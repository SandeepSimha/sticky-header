package com.support.my.sticky_header.lib;

import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;

/**
 * Interface to create and bind {@link RecyclerView.ViewHolder}
 */
public interface DelegateAdapter<VH extends RecyclerView.ViewHolder, T extends RecyclerViewType> {

    VH onCreateViewHolder(ViewGroup parent);

    void onBindViewHolder(VH holder, T item);

}
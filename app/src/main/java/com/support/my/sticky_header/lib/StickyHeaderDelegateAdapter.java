package com.support.my.sticky_header.lib;

import android.support.v7.widget.RecyclerView;

/**
 * Interface to create and bind {@link RecyclerView.ViewHolder} that may be sticky header
 */
public interface StickyHeaderDelegateAdapter<VH extends RecyclerView.ViewHolder, T extends RecyclerViewType> extends DelegateAdapter<VH, T> {

    void onBindStickyHeaderViewHolder(VH holder, T item);
}

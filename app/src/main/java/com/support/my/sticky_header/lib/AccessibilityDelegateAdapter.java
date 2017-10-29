package com.support.my.sticky_header.lib;

import android.support.v7.widget.RecyclerView;

/**
 * Interface to set the accessibility to a {@link RecyclerView.ViewHolder}
 */

public interface AccessibilityDelegateAdapter<VH extends RecyclerView.ViewHolder, T extends RecyclerViewType> {

    /**
     * Method to set the accessibility to a {@link RecyclerView.ViewHolder}
     * It will be called after the data binding process is over.
     *
     * @param holder Object to hold the views to be recycled by the {@link RecyclerView}
     * @param item   Object to be used to set the accessibility data to the views inside the holder
     */
    void onBindViewHolderAccessibility(VH holder, T item);
}

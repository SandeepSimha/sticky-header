package com.support.my.sticky_header.lib;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Interface to provide sticky header's information
 */
public interface StickyHeadersAdapter<VH extends RecyclerView.ViewHolder> {
    int NO_STICKY_HEADER = -1;

    /**
     * @param position of an element that may have a header
     * @return header type > 0 if the item on {@param position} has one, -1 otherwise
     */
    int getHeaderType(int position);

    /**
     * @param position of the element that may be a header
     * @return whether or not the item on {@param position} is a header
     */
    boolean isHeader(int position);

    /**
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param type   The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #onBindStickyHeaderViewHolder(RecyclerView.ViewHolder, int)
     */
    VH onCreateViewHolder(ViewGroup parent, int type);

    /**
     * Bind the information and style to the sticky header
     *
     * @param viewHolder The ViewHolder which should be updated to represent the contents of the
     *                   header with the given type.
     * @param type       The type of the header
     */
    void onBindStickyHeaderViewHolder(VH viewHolder, int type);

    /**
     * Bind the information and style to the non sticky header
     *
     * @param viewHolder The ViewHolder which should be updated to represent the contents of the
     *                   header with the given type.
     * @param type       The type of the header
     */
    void onBindHeaderViewHolder(VH viewHolder, int type);

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    int getItemCount();

    /**
     * When a header is pushing off the screen an other header
     * sometimes we need an offset from the top of the new header
     * and the bottom of the old header.
     * This method returns that offset
     *
     * @return offset to take into account between the bottom of the old header and the top of the new one
     */
    int getNextHeaderOffset();
}

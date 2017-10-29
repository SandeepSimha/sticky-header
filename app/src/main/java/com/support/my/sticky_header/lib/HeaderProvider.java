package com.support.my.sticky_header.lib;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

/**
 * Creates and caches header views
 */
class HeaderProvider {

    private final StickyHeadersAdapter adapter;
    private final SparseArray<WeakReference<RecyclerView.ViewHolder>> headerViews = new SparseArray<>();

    public HeaderProvider(StickyHeadersAdapter adapter) {
        this.adapter = adapter;
    }

    /**
     * @param parent   {@link RecyclerView} that contains all items.
     * @param position of the item that request a header
     * @return a header for the item at {@param position}
     */
    public View getHeader(RecyclerView parent, int position) {
        int headerType = adapter.getHeaderType(position);

        WeakReference<RecyclerView.ViewHolder> holder = headerViews.get(headerType);
        View header = null;
        if (holder != null && holder.get() != null) {
            header = holder.get().itemView;
        } else if (headerType != StickyHeadersAdapter.NO_STICKY_HEADER) {
            RecyclerView.ViewHolder viewHolder = adapter.onCreateViewHolder(parent, headerType);
            adapter.onBindStickyHeaderViewHolder(viewHolder, headerType);
            header = viewHolder.itemView;
            if (header.getLayoutParams() == null) {
                header.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }

            int widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth(), View.MeasureSpec.EXACTLY);
            int heightSpec = View.MeasureSpec.makeMeasureSpec(parent.getHeight(), View.MeasureSpec.UNSPECIFIED);

            int childWidth = ViewGroup.getChildMeasureSpec(widthSpec,
                    parent.getPaddingLeft() + parent.getPaddingRight(), header.getLayoutParams().width);
            int childHeight = ViewGroup.getChildMeasureSpec(heightSpec,
                    parent.getPaddingTop() + parent.getPaddingBottom(), header.getLayoutParams().height);
            header.measure(childWidth, childHeight);
            header.layout(0, 0, header.getMeasuredWidth(), header.getMeasuredHeight());
            headerViews.put(headerType, new WeakReference<>(viewHolder));
        }
        return header;
    }

    /**
     * Clears all saved headers
     */
    public void invalidate() {
        headerViews.clear();
    }

    /**
     * Update the header to the default header style
     * using {@link StickyHeadersAdapter#onBindHeaderViewHolder(RecyclerView.ViewHolder, int)}
     *
     * @param position the position of the item that request a header
     */
    public void updateHeaderStock(int position) {
        int headerType = adapter.getHeaderType(position);

        WeakReference<RecyclerView.ViewHolder> holder = headerViews.get(headerType);
        if (holder != null && holder.get() != null) {
            adapter.onBindHeaderViewHolder(holder.get(), headerType);
        }
    }

    /**
     * Update the header to the sticky header style
     * using {@link StickyHeadersAdapter#onBindStickyHeaderViewHolder(RecyclerView.ViewHolder, int)}
     *
     * @param position the position of the item that request a header
     */
    public void updateHeader(int position) {
        int headerType = adapter.getHeaderType(position);

        WeakReference<RecyclerView.ViewHolder> holder = headerViews.get(headerType);
        if (holder != null && holder.get() != null) {
            adapter.onBindStickyHeaderViewHolder(holder.get(), headerType);
        }
    }
}

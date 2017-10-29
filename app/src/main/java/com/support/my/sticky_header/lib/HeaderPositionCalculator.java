package com.support.my.sticky_header.lib;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Calculates the position and location of header views
 */
class HeaderPositionCalculator {

    private final StickyHeadersAdapter adapter;
    private final HeaderProvider headerProvider;
    private View topView;
    private int topViewOffset;
    private int marginTop;

    public HeaderPositionCalculator(StickyHeadersAdapter adapter, HeaderProvider headerProvider) {
        this(adapter, headerProvider, 0);
    }

    public HeaderPositionCalculator(StickyHeadersAdapter adapter, HeaderProvider headerProvider, int marginTop) {
        this.adapter = adapter;
        this.headerProvider = headerProvider;
        this.marginTop = marginTop;
    }

    /**
     * Determines if a view should have a sticky header.
     * The view has a sticky header if:
     * 1. It is the first element in the recycler view
     * 2. It has a valid ID associated to its position
     *
     * @param parent   {@link RecyclerView} where we will show headers
     * @param itemView given by the RecyclerView
     * @param position of the list item in question
     * @return True if the view should have a sticky header
     */
    public boolean hasStickyHeader(RecyclerView parent, View itemView, int position) {
        Rect margins = DimensionCalculator.getMargins(itemView);
        int top = itemView.getTop() - margins.top;
        int bottom = itemView.getBottom() + margins.bottom + margins.top;
        int listTop = getListTop(parent);

        return bottom >= listTop && adapter.getHeaderType(position) != StickyHeadersAdapter.NO_STICKY_HEADER &&
                ((adapter.isHeader(position) && top <= listTop) ||
                        (!adapter.isHeader(position) && top <= marginTop));
    }

    /**
     * Calculate header bounds
     *
     * @param recyclerView {@link RecyclerView} that contains all items.
     * @param header       the header
     * @param firstView    first view after the header
     * @param position     of the header
     * @return {@link Rect} with header's bounds
     */
    public Rect getHeaderBounds(RecyclerView recyclerView, View header, View firstView, int position) {
        Rect bounds = getDefaultHeaderOffset(recyclerView, header, firstView);

        if (isStickyHeaderBeingPushedOffTheScreen(recyclerView, header)) {
            View newHeader = getFirstViewUnObscuredByHeader(recyclerView, header);
            headerProvider.updateHeaderStock(position);
            bounds = translateHeaderWithNextHeader(recyclerView, bounds,
                    header, newHeader);
        } else {
            headerProvider.updateHeader(position);
        }

        return bounds;
    }

    /**
     * Set the View to be shown in the top.
     *
     * @param topView View
     */
    public void setTopView(View topView) {
        this.topView = topView;
    }

    /**
     * Set offset for the top view.
     *
     * @param topViewOffset Offset
     */
    public void setTopViewOffset(int topViewOffset) {
        this.topViewOffset = topViewOffset;
    }

    /**
     * @param firstView    first view after the header
     * @param header       the header
     * @param recyclerView {@link RecyclerView} that contains all items.
     * @return {@link Rect} with default header's bounds
     */
    private Rect getDefaultHeaderOffset(RecyclerView recyclerView, View header, View firstView) {
        int translationY;
        Rect headerMargins = DimensionCalculator.getMargins(header);

        translationY = Math.max(
                firstView.getTop() - header.getHeight() - headerMargins.bottom,
                getListTop(recyclerView) + headerMargins.top);


        return new Rect(headerMargins.left, translationY, headerMargins.left + header.getWidth(),
                translationY + header.getHeight());
    }

    /**
     * @param recyclerView {@link RecyclerView} that contains all items.
     * @param stickyHeader current header of the {@param recyclerView}
     * @return if a new header is moving the previous rendered header
     */
    private boolean isStickyHeaderBeingPushedOffTheScreen(RecyclerView recyclerView, View stickyHeader) {
        View newHeader = getFirstViewUnObscuredByHeader(recyclerView, stickyHeader);
        int firstViewUnderHeaderPosition = recyclerView.getChildAdapterPosition(newHeader);
        if (newHeader == null || firstViewUnderHeaderPosition == RecyclerView.NO_POSITION || !adapter.isHeader(firstViewUnderHeaderPosition)) {
            return false;
        }

        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) newHeader.getLayoutParams();
        Rect headerMargins = DimensionCalculator.getMargins(stickyHeader);
        int itemTop = newHeader.getTop() - layoutParams.topMargin - getListTop(recyclerView);
        int headerBottom = stickyHeader.getBottom() + headerMargins.bottom + headerMargins.top;
        return (headerBottom - adapter.getNextHeaderOffset()) > itemTop;
    }

    /**
     * @param recyclerView  {@link RecyclerView} that contains all items.
     * @param translation   current {@link Rect} to start from
     * @param currentHeader The header that is now stick to top
     * @param nextHeader    The new header that will replace the existing one
     * @return new {@link Rect} that will contain translation information for the current header
     */
    private Rect translateHeaderWithNextHeader(RecyclerView recyclerView, Rect translation,
                                               View currentHeader, View nextHeader) {
        Rect newTranslation = new Rect(translation);
        Rect stickyHeaderMargins = DimensionCalculator.getMargins(currentHeader);
        newTranslation.top -= (
                stickyHeaderMargins.bottom + currentHeader.getHeight() + getListTop(recyclerView) - nextHeader.getTop() -
                        adapter.getNextHeaderOffset());
        return newTranslation;
    }

    /**
     * Returns the first item currently in the RecyclerView that is not obscured by a header.
     *
     * @param parent {@link RecyclerView} containing all the list items
     * @return first item that is fully beneath a header
     */
    private View getFirstViewUnObscuredByHeader(RecyclerView parent, View firstHeader) {
        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            if (!isItemObscuredByHeader(parent, child, firstHeader)) {
                return child;
            }
        }
        return null;
    }

    /**
     * Determines if an item is obscured by a header
     *
     * @param parent {@link RecyclerView} container of all items
     * @param item   to determine if obscured by header
     * @param header that might be obscuring the item
     * @return true if the item view is obscured by the header view
     */
    private boolean isItemObscuredByHeader(RecyclerView parent, View item, View header) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) item.getLayoutParams();
        Rect headerMargins = DimensionCalculator.getMargins(header);

        int adapterPosition = parent.getChildAdapterPosition(item);
        int itemTop = item.getTop() - layoutParams.topMargin;
        int headerBottom = header.getBottom() + headerMargins.bottom + headerMargins.top;
        return !(adapterPosition == RecyclerView.NO_POSITION || headerProvider.getHeader(parent, adapterPosition) != header) && itemTop <=
                headerBottom;
    }

    /**
     * @param view {@link RecyclerView} to get the top
     * @return the top of the given {@link RecyclerView}
     */
    private int getListTop(RecyclerView view) {
        int top = 0;
        if (topView != null) {
            top = (int) (topView.getHeight() + topView.getY() - topViewOffset);
        }
        if (view.getLayoutManager().getClipToPadding()) {
            top += view.getPaddingTop();
        }
        return Math.max(top, 0);
    }

    public HeaderProvider getHeaderProvider() {
        return headerProvider;
    }
}

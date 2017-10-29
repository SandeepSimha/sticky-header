package com.support.my.sticky_header.lib;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * An {@link RecyclerView.ItemDecoration} to draw sticky headers
 */
public class StickyHeadersItemDecoration extends RecyclerView.ItemDecoration {

    private final StickyHeadersAdapter stickyHeadersAdapter;
    private final HeaderProvider headerProvider;
    private final HeaderPositionCalculator headerPositionCalculator;
    private final HeaderRenderer renderer;

    public StickyHeadersItemDecoration(StickyHeadersAdapter adapter) {
        this(adapter, new HeaderRenderer(), new HeaderProvider(adapter));
    }

    public StickyHeadersItemDecoration(StickyHeadersAdapter adapter, HeaderRenderer headerRenderer, HeaderProvider headerProvider) {
        this(adapter, headerRenderer, headerProvider, new HeaderPositionCalculator(adapter, headerProvider));
    }

    @Deprecated
    public StickyHeadersItemDecoration(StickyHeadersAdapter adapter, HeaderRenderer headerRenderer, HeaderProvider headerProvider,
                                       HeaderPositionCalculator headerPositionCalculator) {
        stickyHeadersAdapter = adapter;
        this.headerProvider = headerProvider;
        renderer = headerRenderer;
        this.headerPositionCalculator = headerPositionCalculator;
    }

    public StickyHeadersItemDecoration(StickyHeadersAdapter adapter, HeaderRenderer headerRenderer,
                                       HeaderPositionCalculator headerPositionCalculator) {
        this.stickyHeadersAdapter = adapter;
        this.headerProvider = headerPositionCalculator.getHeaderProvider();
        this.renderer = headerRenderer;
        this.headerPositionCalculator = headerPositionCalculator;
    }

    /**
     * @param adapter
     * @param marginTop margin for item decorator in px
     */
    public StickyHeadersItemDecoration(StickyHeadersAdapter adapter, int marginTop) {
        this(adapter, new HeaderRenderer(), new HeaderPositionCalculator(adapter, new HeaderProvider(adapter), marginTop));
    }

    /**
     * Set the View to be shown in the top.
     *
     * @param topView View
     */
    public void setTopView(View topView) {
        this.headerPositionCalculator.setTopView(topView);
    }

    /**
     * Set offset for the top view.
     *
     * @param topViewOffset Offset
     */
    public void setTopViewOffset(int topViewOffset) {
        this.headerPositionCalculator.setTopViewOffset(topViewOffset);
    }

    @Override
    public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(canvas, parent, state);

        if (parent.getChildCount() <= 0 || stickyHeadersAdapter.getItemCount() <= 0) {
            return;
        }

        for (int childIndex = 0; childIndex < parent.getChildCount(); childIndex++) {
            View itemView = parent.getChildAt(childIndex);
            int position = parent.getChildAdapterPosition(itemView);
            if (position != RecyclerView.NO_POSITION) {
                boolean hasStickyHeader = headerPositionCalculator.hasStickyHeader(parent, itemView, position);
                if (hasStickyHeader) {
                    View header = headerProvider.getHeader(parent, position);
                    if (header != null) {
                        Rect headerOffset = headerPositionCalculator.getHeaderBounds(parent, header,
                                itemView, position);
                        renderer.drawHeader(parent, canvas, header, headerOffset);
                        /**
                         * only the first one has sticky headers
                         * {@link HeaderPositionCalculator#hasStickyHeader(RecyclerView, View, int)}
                         *
                         */
                        break;
                    }
                }
            }
        }
    }

    /**
     * Clears headers to be recreated when needed
     */
    public void invalidateHeaders() {
        this.headerProvider.invalidate();
    }
}
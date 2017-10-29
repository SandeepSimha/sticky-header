package com.support.my.sticky_header;

import android.content.Context;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;

import com.support.my.sticky_header.lib.BaseRecyclerViewAdapter;
import com.support.my.sticky_header.lib.RecyclerViewType;
import com.support.my.sticky_header.lib.StickyHeaderDelegateAdapter;
import com.support.my.sticky_header.lib.StickyHeadersAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chers026 on 10/16/17.
 */
public class DiningMenuAdapter extends BaseRecyclerViewAdapter implements StickyHeadersAdapter {

    private static final int VIEW_MENU_HEADER_REGION = 900000;

    private DiningMenuHeaderDelegate diningMenuHeaderDelegate;
    private final List<DiningMenuHeader> sections = new ArrayList<>();

    public DiningMenuAdapter(Context context) {
        delegateAdapters = new SparseArrayCompat<>();
        diningMenuHeaderDelegate = new DiningMenuHeaderDelegate();
        delegateAdapters.put(ViewType.DINING_MENU_HEADER.ordinal(), diningMenuHeaderDelegate);
        delegateAdapters.put(ViewType.DINING_MENU_INFO_ELEMENT.ordinal(), new DiningMenuElementDelegate());
    }

    public void setMenu(DiningMenu diningMenu) {
        int baseHeaderViewType = VIEW_MENU_HEADER_REGION;
        sections.clear();

        for (DiningMenuHeader diningMenuHeader : diningMenu.getHeaders()) {
            diningMenuHeader.setViewType(baseHeaderViewType++);
            delegateAdapters.put(diningMenuHeader.getViewType(), diningMenuHeaderDelegate);
            items.add(diningMenuHeader);
            sections.add(diningMenuHeader);
            items.addAll(diningMenuHeader.getDiningMenuElements());
        }
        notifyDataSetChanged();
    }

    @Override
    public int getHeaderType(int position) {
        final RecyclerViewType item = this.items.get(position);
        if (item instanceof DiningMenuElement) {
            DiningMenuHeader section = findSectionContaining(this.sections, item);
            if (section != null) {
                return section.getViewType();
            }
        } else if (item instanceof DiningMenuHeader) {
            return item.getViewType();
        }

        return NO_STICKY_HEADER;
    }

    @Override
    public boolean isHeader(int position) {
        return this.items.get(position) instanceof DiningMenuHeader;
    }

    @Override
    public void onBindStickyHeaderViewHolder(RecyclerView.ViewHolder viewHolder, int type) {
        final RecyclerViewType item = getSectionByViewType(type);
        if (item != null) {
            ((StickyHeaderDelegateAdapter) delegateAdapters.get(item.getViewType())).onBindStickyHeaderViewHolder(viewHolder, item);
        }
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder viewHolder, int type) {
        final RecyclerViewType item = getSectionByViewType(type);
        if (item != null) {
            delegateAdapters.get(item.getViewType()).onBindViewHolder(viewHolder, item);
        }
    }

    @Override
    public int getNextHeaderOffset() {
        return 0;
    }

    private DiningMenuHeader findSectionContaining(final List<DiningMenuHeader> sections, final RecyclerViewType item) {
        for (DiningMenuHeader scheduleMenuHeaderViewModel : sections) {
            if (scheduleMenuHeaderViewModel.getDiningMenuElements().contains(item)) {
                return scheduleMenuHeaderViewModel;
            }
        }
        return null;
    }

    private RecyclerViewType getSectionByViewType(int viewType) {
        for (DiningMenuHeader scheduleMenuHeaderViewModel : sections) {
            if (scheduleMenuHeaderViewModel.getViewType() == viewType) {
                return scheduleMenuHeaderViewModel;
            }
        }
        return null;
    }
}

package com.support.my.sticky_header.lib;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class that simplifies the DelegateAdapter pattern implementation
 */
public class BaseRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected SparseArrayCompat<DelegateAdapter> delegateAdapters;
    protected SparseArrayCompat<AccessibilityDelegateAdapter> accessibilityDelegateAdapters;

    protected final List<RecyclerViewType> items = new ArrayList<>();

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return delegateAdapters.get(viewType).onCreateViewHolder(parent);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, this.items.get(position));

        //We do this check to avoid crashing apps due to the missing init in projects that already use this class.
        if (accessibilityDelegateAdapters != null) {
            AccessibilityDelegateAdapter accessibilityDelegateAdapter = accessibilityDelegateAdapters.get(getItemViewType(position));
            if (accessibilityDelegateAdapter != null) {
                accessibilityDelegateAdapter.onBindViewHolderAccessibility(holder, this.items.get(position));
            }
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getViewType();
    }
}
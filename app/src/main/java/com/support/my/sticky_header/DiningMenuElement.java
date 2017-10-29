package com.support.my.sticky_header;


import com.support.my.sticky_header.lib.RecyclerViewType;

import java.util.List;

/**
 * @author chers026 on 10/16/17.
 */
public class DiningMenuElement implements RecyclerViewType {
    private String title;
    private String description;
    private List<Integer> legendIconList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getLegendIconList() {
        return legendIconList;
    }

    public void setLegendIconList(List<Integer> legendIconList) {
        this.legendIconList = legendIconList;
    }

    @Override
    public int getViewType() {
        return ViewType.DINING_MENU_INFO_ELEMENT.ordinal();
    }
}

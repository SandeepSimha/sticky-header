package com.support.my.sticky_header;


import com.support.my.sticky_header.lib.RecyclerViewType;

import java.util.List;

/**
 * @author chers026 on 10/16/17.
 */
public class DiningMenuHeader implements RecyclerViewType {
    private String menuType;
    private List<DiningMenuElement> diningMenuElements;
    private int viewType;

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public List<DiningMenuElement> getDiningMenuElements() {
        return diningMenuElements;
    }

    public void setDiningMenuElements(List<DiningMenuElement> diningMenuElements) {
        this.diningMenuElements = diningMenuElements;
    }

    @Override
    public int getViewType() {
        return viewType;
    }
}

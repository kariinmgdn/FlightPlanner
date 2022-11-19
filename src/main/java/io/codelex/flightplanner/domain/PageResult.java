package io.codelex.flightplanner.domain;

import java.util.List;
import java.util.Objects;

public class PageResult<T> {

    private final int page;
    private final int totalItems;
    private final List<T> items;

    public PageResult(int page, int totalItems, List<T> items) {
        this.page = page;
        this.totalItems = totalItems;
        this.items = items;
    }

    public int getPage() {
        return page;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public List<T> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageResult<?> that = (PageResult<?>) o;
        return page == that.page && totalItems == that.totalItems && items.equals(that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, totalItems, items);
    }
}

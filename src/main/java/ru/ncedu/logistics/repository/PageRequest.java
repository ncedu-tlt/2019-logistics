package ru.ncedu.logistics.repository;

public class PageRequest {
    private int limit;
    private int offset;

    public PageRequest(int page, int size){
        this.limit = size;
        this.offset = page*size;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }
}

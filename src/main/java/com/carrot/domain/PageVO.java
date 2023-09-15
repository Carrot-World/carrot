package com.carrot.domain;

public class PageVO {
    private int start;
    private int end;
    private int current;
    private int size;
    private int block;
    private int total;

    public PageVO() {
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PageVO{" +
                "start=" + start +
                ", end=" + end +
                ", current=" + current +
                ", size=" + size +
                ", block=" + block +
                ", total=" + total +
                '}';
    }
}

package com.try1.tutorial.capability;

public class ShitIntent {
    private int value = 0;
    private final int max = 20;
    public int get()  { return value; }
    public int getMax() { return max; }
    public void set(int v) { value = Math.max(0, Math.min(v, max)); }
    public void add(int v) { set(value + v); }
    public void sub(int v) { set(value - v); }
    public boolean isFull() { return value >= max; }
}
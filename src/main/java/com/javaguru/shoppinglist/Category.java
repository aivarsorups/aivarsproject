package com.javaguru.shoppinglist;

public enum Category {
    FRUITS(1), VEGETABLES(2);
    private int value;

    public void setValue(int value) {
        this.value = value;
    }


    Category(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;


    }
}
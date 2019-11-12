package com.javaguru.shoppinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public enum Category {

    FRUITS,
    VEGETABLES;
    @Autowired
    Category() {
    }
}
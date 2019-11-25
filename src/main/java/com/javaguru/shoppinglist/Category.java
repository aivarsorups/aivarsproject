package com.javaguru.shoppinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.CannotCreateTransactionException;

import javax.persistence.*;


public enum Category {

    FRUITS,
    VEGETABLES


}
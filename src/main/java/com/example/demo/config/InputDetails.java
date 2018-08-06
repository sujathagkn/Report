package com.example.demo.config;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by sujatha.bandi on 5/8/18.
 */
@Getter
@Setter
public class InputDetails implements Serializable {
    private static final long serialVersionUID = -2514021076150667586L;

    private int index;
    private int size;
}

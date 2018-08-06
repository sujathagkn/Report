package com.example.demo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by sujatha.bandi on 5/8/18.
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "InputConfiguration")
public class InputConfiguration implements Serializable {
    private static final long serialVersionUID = 3937371389218048388L;
    private HashMap<String, InputDetails> dataOptions;
}

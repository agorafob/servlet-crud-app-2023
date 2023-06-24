package com.agorafob.model;

import lombok.Data;

@Data
public class Department {
    private Long id;
    private String name;


    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }
    public Department(Long id, String name) {
        this(name);
        this.id = id;
    }

}

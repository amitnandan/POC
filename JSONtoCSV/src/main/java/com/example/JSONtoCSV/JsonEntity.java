package com.example.JSONtoCSV;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JsonEntity {

    private String item;
    private int quantity;
    private double unitPrice;
}

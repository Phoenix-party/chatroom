package com.example.chat.model;

import lombok.Data;

@Data
public class ChatDetailEntity {
    int id;
    String name;
    String category;
    int calories;
    float protein;
    float saturatedFat;
    float totalCarbohydrates;
    float dietaryFiber;
}

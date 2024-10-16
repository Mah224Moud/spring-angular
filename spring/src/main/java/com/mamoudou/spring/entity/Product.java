package com.mamoudou.spring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Le nom ne peut ne peut pas etre vide")
    private String name;

    @NotEmpty(message = "La description ne peut ne peut pas etre vide")
    @Length(min = 3, max = 100, message = "La description doit avoir entre 3 et 100 caractere")
    private String description;

    @Positive(message = "Le prix ne peut pas etre inférieur ou egal à 0")
    @NotNull(message = "Le prix ne peut ne peut pas etre null")
    private double price;
}

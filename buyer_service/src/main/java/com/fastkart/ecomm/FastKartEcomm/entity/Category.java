package com.fastkart.ecomm.FastKartEcomm.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@Entity(name = "category")
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @JsonBackReference
    @OneToMany(mappedBy="category", fetch = FetchType.EAGER)
    private Set<Product> product;
}

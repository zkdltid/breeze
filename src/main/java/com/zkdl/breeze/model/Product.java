package com.zkdl.breeze.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    //Product
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cant be null")
    @Size(max = 20)
    private String name;

    @NotNull(message = "Price cant be null")
    private Integer price;

//    @Size(max = 120)
//    private String category;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category categoryId;

    @Size(max = 120)
    @Column(name = "`desc`")
    private String desc;

    @Size(max = 120)
    private String image;

    @NotNull(message = "Stock cant be null")
    private Integer stock;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

//    @Column(name = "created_by")
//    private String createdBy;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
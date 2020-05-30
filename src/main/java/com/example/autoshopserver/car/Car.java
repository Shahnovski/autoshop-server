package com.example.autoshopserver.car;

import com.example.autoshopserver.car.brand.Brand;
import com.example.autoshopserver.car.comment.Comment;
import com.example.autoshopserver.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "brand_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User owner;

    @Column(name = "carModel")
    private String carModel;

    @Column(name = "typeTransmission")
    @Enumerated(EnumType.STRING)
    private TypeTransmission typeTransmission;

    @Column(name = "typeEngine")
    @Enumerated(EnumType.STRING)
    private TypeEngine typeEngine;

    @Column(name = "carMileage")
    private Double carMileage;

    @Column(name = "carStatus")
    private String carStatus;

    @Column(name = "carCost")
    private Float carCost;

    @Column(name = "countryLocation")
    private String countryLocation;

    @Column(name = "cityLocation")
    private String cityLocation;

    @Lob
    @Column(name="carImage")
    private byte[] carImage;

    @Column(name = "isDeleted")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "car", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Comment> comments;
}

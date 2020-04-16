package com.example.autoshopserver.car.brand;

import com.example.autoshopserver.car.Car;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "brand")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "brandTitle")
    private String brandTitle;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Car> cars;
}

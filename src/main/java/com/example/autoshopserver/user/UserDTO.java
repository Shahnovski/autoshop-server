package com.example.autoshopserver.user;

import com.example.autoshopserver.car.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    Long id;
    Set<Role> roles = new HashSet<>();
    String username;
    private List<Car> cars;

}

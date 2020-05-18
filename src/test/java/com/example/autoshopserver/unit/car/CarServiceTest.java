package com.example.autoshopserver.unit.car;

import com.example.autoshopserver.auth.info.AuthInfoService;
import com.example.autoshopserver.car.Car;
import com.example.autoshopserver.car.CarMapper;
import com.example.autoshopserver.car.CarRepository;
import com.example.autoshopserver.car.CarService;
import com.example.autoshopserver.car.brand.BrandMapper;
import com.example.autoshopserver.car.brand.BrandService;
import com.example.autoshopserver.car.page.CarPageMapper;
import com.example.autoshopserver.car.page.request.CarPageRequestMapper;
import com.example.autoshopserver.user.Role;
import com.example.autoshopserver.user.User;
import com.example.autoshopserver.user.UserRepository;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CarServiceTest {

    private static final Car CAR = mock(Car.class);
    private static final User USER = mock(User.class);
    private static final Authentication AUTHENTICATION = mock(Authentication.class);

    private final CarRepository carRepository = mock(CarRepository.class);
    private final UserRepository userRepository = mock(UserRepository.class);
    private final BrandService brandService = mock(BrandService.class);
    private final AuthInfoService authInfoService = mock(AuthInfoService.class);
    private final CarPageRequestMapper carPageRequestMapper = mock(CarPageRequestMapper.class);
    private final CarPageMapper carPageMapper = mock(CarPageMapper.class);
    private final CarMapper carMapper = mock(CarMapper.class);
    private final BrandMapper brandMapper = mock(BrandMapper.class);

    private final CarService carService = new CarService(
            carRepository,
            brandService,
            userRepository,
            authInfoService,
            carMapper,
            brandMapper,
            carPageRequestMapper,
            carPageMapper
    );

    @Test
    public void adminShouldAlwaysEditToAnyCar() {
        when(authInfoService.getUserByAuthentication(AUTHENTICATION)).thenReturn(USER);
        when(USER.getRoles()).thenReturn(Collections.singleton(Role.ADMIN));
        boolean editAllowed = carService.allowEditCars(AUTHENTICATION, CAR);
        assertThat(editAllowed).isTrue();
    }

    @Test
    public void userShouldEditToTheirCar() {
        final Long USER_ID = 1L;
        final Long CAR_OWNER_ID = 1L;

        when(authInfoService.getUserByAuthentication(AUTHENTICATION)).thenReturn(USER);
        when(USER.getRoles()).thenReturn(Collections.singleton(Role.USER));
        when(CAR.getOwner().getId()).thenReturn(CAR_OWNER_ID);
        when(USER.getId()).thenReturn(USER_ID);
        boolean accessAllowed = carService.allowEditCars(AUTHENTICATION, CAR);
        assertThat(accessAllowed).isTrue();
    }

    @Test
    public void userShouldNotEditToSomeoneCar() {
        final Long USER_ID = 1L;
        final Long CAR_OWNER_ID = 2L;

        when(authInfoService.getUserByAuthentication(AUTHENTICATION)).thenReturn(USER);
        when(USER.getRoles()).thenReturn(Collections.singleton(Role.USER));
        when(CAR.getOwner().getId()).thenReturn(CAR_OWNER_ID);
        when(USER.getId()).thenReturn(USER_ID);
        boolean accessAllowed = carService.allowEditCars(AUTHENTICATION, CAR);
        assertThat(accessAllowed).isTrue();
    }

}

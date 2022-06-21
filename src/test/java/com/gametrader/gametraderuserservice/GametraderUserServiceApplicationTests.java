package com.gametrader.gametraderuserservice;

import com.gametrader.gametraderuserservice.util.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(properties = {"JWT_ALGORITHM_SECRET=test"})
class GametraderUserServiceApplicationTests {

    @MockBean
    private JwtUtils jwtUtils;
    @Test
    void contextLoads() {
    }

}

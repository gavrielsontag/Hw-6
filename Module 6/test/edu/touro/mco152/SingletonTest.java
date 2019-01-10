package edu.touro.mco152;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

class SingletonTest {

    @Test
    void singletonTest() {
       //assertEquals(Singleton.getInstance(), Singleton.getInstance());
        Singleton.getString();
    }
}
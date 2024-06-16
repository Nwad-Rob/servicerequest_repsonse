package com.movie.movie;

import org.junit.BeforeClass;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import mockit.Mocked;

@Slf4j
public class TestMovie {

    @BeforeClass
    public static void setup(){

    }

    @Mocked EntityManager mockedServiceEm;
    

}

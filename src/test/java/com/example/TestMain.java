package com.example;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
/**
 * This class test is validate charge of managing the files in heroku.
 * @author Miguel Angel Rodríguez Siachoque
 */
public class TestMain 
{
    /**
     * This method Test 1.
     */
    @BeforeClass
    public static void beforeClassTest () 
    {
        System.out.println("before class test");
    }
    /**
     * This method Test 2.
     */
    @Before
    public void beforeTest () 
    {
        System.out.println("before test");
    }
    /**
     *  This method Test 3.
     */
    @Test
    public void Test3 () 
    {
        Main test3 = new Main();
        assertEquals(test3.index(),"index");
    }
    /**
     *  This method Test 4.
     */
    @Test
    public void Test4 () 
    {
        Main test4 = new Main();
        assertEquals(test4.pointsfames(),"pointsfames");
    }
    /**
     *  This method Test 5.
     */
    @After
    public void afterTest () 
    {
        System.out.println("after test");
    }
    /**
     *  This method Test 6.
     */
    @AfterClass
    public static void afterClassTest() {
        System.out.println("after class test");
    }
}
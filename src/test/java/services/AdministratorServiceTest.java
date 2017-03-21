package services;

import domain.Actor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import utilities.AbstractTest;

/**
 * Created by mruwzum on 17/3/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/datasource.xml",
        "classpath:spring/config/packages.xml"})
@Transactional

public class AdministratorServiceTest extends AbstractTest {
    // System under test ------------------------------------------------------

    @Autowired
    AdministratorService administratorService;


    // Tests ------------------------------------------------------------------

    // The following are fictitious test cases that are intended to check that
    // JUnit works well in this project.  Just righ-click this class and run
    // it using JUnit.

    @Test
    public void samplePositiveTest() {
        Assert.isTrue(true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void sampleNegativeTest() {
        Assert.isTrue(false);
    }

    // Ancillary methods ------------------------------------------------------
}
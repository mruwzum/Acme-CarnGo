package services;

import domain.Actor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import utilities.AbstractTest;

/**
 * Created by mruwzum on 17/3/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/datasource.xml",
        "classpath:spring/config/packages.xml"})
@Transactional

public class ActorServiceTest extends AbstractTest {
    @Autowired
    private ActorService actorService;


    @Test
    public void findActorByEmail() throws Exception {
        String perri = "Customer2Email@gmail.com";
        Actor a = actorService.findActorByEmail(perri);

        System.out.println(a);
    }

}
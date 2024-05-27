package com.beereal.beerealbackend.misc;

import com.beereal.beerealbackend.model.*;
import com.beereal.beerealbackend.repository.BarRepository;
import com.beereal.beerealbackend.repository.UserRepository;
import com.beereal.beerealbackend.repository.VisitRepository;
import com.beereal.beerealbackend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Service
public class DBFiller {

    @Autowired
    UserService userService = new UserServiceImplementation();
    @Autowired
    BarService barService = new BarServiceImplementation();
    @Autowired
    VisitService visitService = new VisitServiceImplementation();

    @Autowired
    RankingService rankingService = new RankingServiceImplementation();

    @Autowired
    CommentService commentService = new CommentServiceImplementation();


    RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

    public void fill() {
//        fillBar();
//        fillUser();
//        fillVisit();
//        fillRanking();
//        fillComments();
    }

    public void fillUser() {
        User user1 = new User("user1", "user1@gmail.com", "123123");
        User user2 = new User("user2", "user2@gmail.com", "123123");
        User user3 = new User("user3", "user3@gmail.com", "123123");
        User user4 = new User("user4", "user4@gmail.com", "123123");
        User user5 = new User("user5", "user5@gmail.com", "123123");
        User user6 = new User("user6", "user6@gmail.com", "123123");
        User user7 = new User("user7", "user7@gmail.com", "123123");
        User user8 = new User("user8", "user8@gmail.com", "123123");

        userService.registerUser(user1);
        userService.registerUser(user2);
        userService.registerUser(user3);
        userService.registerUser(user4);
        userService.registerUser(user5);
        userService.registerUser(user6);
        userService.registerUser(user7);
        userService.registerUser(user8);

    }

    public void fillBar() {
        Bar bar1 = new Bar("Bar1", "Wrocławska 12", "https://as2.ftcdn.net/v2/jpg/00/29/82/81/1000_F_29828143_RkHCM5hFK8ZcuT35xrAOYVAsNFIQ6MHN.jpg");
        Bar bar2 = new Bar("Bar2", "Wrocławska 13", "https://as2.ftcdn.net/v2/jpg/00/29/82/81/1000_F_29828143_RkHCM5hFK8ZcuT35xrAOYVAsNFIQ6MHN.jpg");
        Bar bar3 = new Bar("Bar3", "Wrocławska 14", "https://as2.ftcdn.net/v2/jpg/00/29/82/81/1000_F_29828143_RkHCM5hFK8ZcuT35xrAOYVAsNFIQ6MHN.jpg");
        Bar bar4 = new Bar("Bar4", "Wrocławska 15", "https://as2.ftcdn.net/v2/jpg/00/29/82/81/1000_F_29828143_RkHCM5hFK8ZcuT35xrAOYVAsNFIQ6MHN.jpg");
        Bar bar5 = new Bar("Bar5", "Wrocławska 16", "https://as2.ftcdn.net/v2/jpg/00/29/82/81/1000_F_29828143_RkHCM5hFK8ZcuT35xrAOYVAsNFIQ6MHN.jpg");
        Bar bar6 = new Bar("Bar6", "Wrocławska 17", "https://as2.ftcdn.net/v2/jpg/00/29/82/81/1000_F_29828143_RkHCM5hFK8ZcuT35xrAOYVAsNFIQ6MHN.jpg");
        Bar bar7 = new Bar("Bar7", "Wrocławska 18", "https://as2.ftcdn.net/v2/jpg/00/29/82/81/1000_F_29828143_RkHCM5hFK8ZcuT35xrAOYVAsNFIQ6MHN.jpg");
        Bar bar8 = new Bar("Bar8", "Wrocławska 19", "https://as2.ftcdn.net/v2/jpg/00/29/82/81/1000_F_29828143_RkHCM5hFK8ZcuT35xrAOYVAsNFIQ6MHN.jpg");

        barService.addBar(bar1);
        barService.addBar(bar2);
        barService.addBar(bar3);
        barService.addBar(bar4);
        barService.addBar(bar5);
        barService.addBar(bar6);
        barService.addBar(bar7);
        barService.addBar(bar8);
    }

    public void fillVisit() {
        List<User> users = userService.getAll();
        List<Bar> bars = barService.getAllBars();
        for (int i = 0; i < 10; i++) {
            User user = users.get(randomNumberGenerator.getIntBetween(0, users.size()));
            Bar bar = bars.get(randomNumberGenerator.getIntBetween(0, bars.size()));

            Visit visit = new Visit(user, bar, LocalDateTime.now());
            visitService.addVisit(visit);
        }
    }

    public void fillRanking() {
        List<User> users = userService.getAll();

        for (int i = 0; i < 10; i++) {
            User user = users.get(randomNumberGenerator.getIntBetween(0, users.size()));
            int visitedbars = randomNumberGenerator.getIntBetween(0, 20);
            String time = "0"+randomNumberGenerator.getIntBetween(0, 2) +":" + randomNumberGenerator.getIntBetween(10,49) + ":" + randomNumberGenerator.getIntBetween(10, 49);
            LocalTime timeFormatted = LocalTime.parse(time);
            RankingEntry entry = new RankingEntry(user, visitedbars, timeFormatted);
            rankingService.addEntry(entry);
        }
    }

    public void fillComments() {
        List<User> users = userService.getAll();
        List<Bar> bars = barService.getAllBars();
        String[] adj = new String[] {
                "super", "fajnie", "bombowo", "fajna barmanka", "kocham piwo", "siema", "pozdro dla dziadka", "kocham kelnerke", "6739530304 call me ;)"
        };
        for (int i = 0; i < 10; i++) {
            User user = users.get(randomNumberGenerator.getIntBetween(0, users.size()));
            Bar bar = bars.get(randomNumberGenerator.getIntBetween(0, bars.size()));

            Comment comment = new Comment(user, bar, LocalDateTime.now(), adj[randomNumberGenerator.getIntBetween(0, adj.length)]);
            commentService.addComment(comment);
        }
    }
}

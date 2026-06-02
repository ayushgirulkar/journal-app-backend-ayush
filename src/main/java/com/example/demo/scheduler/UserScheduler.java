package com.example.demo.scheduler;

import com.example.demo.entity.JournalEntry;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepositoryImpl;
import com.example.demo.service.EmailService;
import com.example.demo.service.SentimateAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private SentimateAnalysisService sentimateAnalysisService;

    @Scheduled(cron="0 7 * * SUN")
    public void fetchUserAndSendMail()
    {
        List<User> users = userRepository.getUserForSA();

        for (User user :users)
        {
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<String> filteredList = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x->x.getContent()).collect(Collectors.toList());
            String join = String.join(" ", filteredList);
            String sentimate = sentimateAnalysisService.getSentimate(join);
            emailService.sendEmail(user.getEmail(),"sentiment for 7 days ",sentimate);

        }


    }

}

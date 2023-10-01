package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.notification_task;
import pro.sky.telegrambot.repositories.notification_taskRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationTaskService {
    public Integer count = 0;
    private static final Logger logger = LoggerFactory.getLogger(NotificationTaskService.class);

    @Autowired
    private notification_taskRepository notificationTaskRepository;
    @Autowired
    private TelegramBot telegramBot;

    public NotificationTaskService (notification_taskRepository notificationTaskRepository) {
        this.notificationTaskRepository = notificationTaskRepository;
    }

    public notification_task createNotificationTask(notification_task notificationTask) {
        logger.info("Was invoked method for create notificationTask");
        System.out.println(notificationTask);
        return notificationTaskRepository.save(notificationTask);
    }

    @Scheduled(cron = "0 0/1 * * * *")
    public void sendNotifications() {
        logger.info("Was invoked method for sending notificationTask");
        List<notification_task> notificationsToSend = new ArrayList<>();
        LocalDateTime time = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        //System.out.println("time = " + time);
        notificationsToSend = notificationTaskRepository.getNotificationsToSent(time);

        for (notification_task notification: notificationsToSend) {
            System.out.println("НАПОМИНАНИЕ =  " + notification.getText());
            SendMessage message = new SendMessage(notification.getChatID()
                    ,"НАПОМИНАНИЕ =  " + notification.getText());
            telegramBot.execute(message);
        }

    }
}

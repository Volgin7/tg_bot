package pro.sky.telegrambot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.notification_task;
import pro.sky.telegrambot.repositories.notification_taskRepository;

@Service
public class NotificationTaskService {
    public Integer count = 0;
    private static final Logger logger = LoggerFactory.getLogger(NotificationTaskService.class);

    @Autowired
    private notification_taskRepository notificationTaskRepository;

    public NotificationTaskService (notification_taskRepository notificationTaskRepository) {
        this.notificationTaskRepository = notificationTaskRepository;
    }

    public notification_task createNotificationTask(notification_task notificationTask) {
        logger.info("Was invoked method for create notificationTask");
        System.out.println(notificationTask);
        return notificationTaskRepository.save(notificationTask);
    }

}

package pro.sky.telegrambot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.notification_task;

public interface notification_taskRepository extends JpaRepository<notification_task,Long> {



}
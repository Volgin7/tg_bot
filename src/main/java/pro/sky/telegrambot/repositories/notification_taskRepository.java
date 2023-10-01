package pro.sky.telegrambot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pro.sky.telegrambot.model.notification_task;

import java.time.LocalDateTime;
import java.util.List;

public interface notification_taskRepository extends JpaRepository<notification_task,Long> {
@Query(value = "SELECT * FROM notification_task WHERE time = ?1", nativeQuery = true)
//@Query(value = "SELECT * FROM notification_task", nativeQuery = true)
    public List<notification_task> getNotificationsToSent(LocalDateTime time);
}


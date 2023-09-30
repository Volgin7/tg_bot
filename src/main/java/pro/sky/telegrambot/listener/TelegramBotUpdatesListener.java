package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.notification_task;
import java.time.LocalDateTime;

import javax.annotation.PostConstruct;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            // Process your updates here
            if(update.message().text().equalsIgnoreCase("/start")) {
                Long chatId = update.message().chat().id();
                sendMessage(chatId,"Welcome to Event-Notificator Bot !!!");
            } else {
                notification_task  notificationTask = new notification_task();
                notificationTask = ParseMessage(update);
                System.out.println(notificationTask);
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    public void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage(chatId, textToSend);
        telegramBot.execute(message);
    } // end send message

    public notification_task ParseMessage(Update update) {
        notification_task notificationTask = new notification_task();

        notificationTask.setChatID(update.message().chat().id());

        String text = update.message().text();
        System.out.println("text = " + text);

        Pattern pattern = Pattern.compile("([0-9\\.\\:\\s]{16})(\\s)([\\W+]+)");
        Matcher matcher = pattern.matcher(text);
        String date_str = null;
        String task_str = null;
        if (matcher.matches()) {
            date_str = matcher.group(1);
            task_str= matcher.group(3);
        }
        System.out.println("date_str = " + date_str);
        System.out.println("task_str = " + task_str);

        LocalDateTime date = LocalDateTime.parse(date_str, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        notificationTask.setTime(date);
        notificationTask.setText(task_str);

        return notificationTask;
    }

}

package com.bezkoder.spring.jpa.h2.bootstrap;

/*
2. Create a Manual "Wiring" Class
This acts like a poor man’s DI container — you instantiate and connect components yourself.

 */
public class AppConfig {

    public MessageService messageService() {
        return new EmailMessageService();
    }

    public NotificationManager notificationManager() {
        return new NotificationManager(messageService());
    }
}

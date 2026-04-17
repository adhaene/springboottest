package com.bezkoder.spring.jpa.h2.bootstrap;

public class Application {

        public static void main(String[] args) {
            // Manual wiring
            AppConfig config = new AppConfig();
            NotificationManager manager = config.notificationManager();

            // Use the wired object
            manager.sendNotification();
        }
}

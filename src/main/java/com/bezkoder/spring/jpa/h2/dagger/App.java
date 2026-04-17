package com.bezkoder.spring.jpa.h2.dagger;

import javax.inject.Inject;

public class App {

    @Inject
    MessageService messageService; // Dagger will inject this

    public static void main(String[] args) {
        // Build the Dagger component
        AppComponent component = DaggerAppComponent.create();

        // Create app instance and inject dependencies
        App app = new App();
        component.inject(app);

        // Use the injected service
        System.out.println(app.messageService.getMessage());
    }
}

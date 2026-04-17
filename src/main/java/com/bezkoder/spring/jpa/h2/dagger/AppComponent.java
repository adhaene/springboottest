package com.bezkoder.spring.jpa.h2.dagger;

import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = ServiceModule.class)
public interface AppComponent {
    void inject(App app); // Where to inject dependencies
}

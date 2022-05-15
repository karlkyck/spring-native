package com.example.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.context.FunctionRegistration;
import org.springframework.cloud.function.context.FunctionalSpringApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.nativex.hint.TypeHint;

@TypeHint(types = GenericApplicationContext.class,
          typeNames = "org.springframework.context.support.GenericApplicationContext$init")
@SpringBootApplication
public class DemoApplication implements ApplicationContextInitializer<GenericApplicationContext> {

    public static void main(final String[] args) {
        FunctionalSpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void initialize(final GenericApplicationContext context) {
        context.registerBean("nameUppercaser",
                             FunctionRegistration.class,
                             () -> new FunctionRegistration<>(new NameUppercaser()).type(NameUppercaser.class));
    }
}

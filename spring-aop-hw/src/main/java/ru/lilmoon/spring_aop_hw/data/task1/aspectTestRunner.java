package ru.lilmoon.spring_aop_hw.data.task1;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.lilmoon.spring_aop_hw.aspects.TestInterface;

import java.util.List;

@Component
public class aspectTestRunner {

    @Autowired
    private final List<TestInterface> tests;

    public aspectTestRunner(List<TestInterface> tests) {
        this.tests = tests;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady(){
        for (TestInterface test : tests) {
            test.firstTest();
            test.secondTest();
        }
    }
}

package ru.lilmoon.spring_aop_hw.data.task1;

import org.slf4j.event.Level;
import org.springframework.stereotype.Component;
import ru.lilmoon.spring_aop_hw.aspects.Measurable;
import ru.lilmoon.spring_aop_hw.aspects.TestInterface;

import java.util.LinkedList;
import java.util.Random;
@Component
public class Test2 implements TestInterface {
    LinkedList<Integer> integers = new LinkedList<>();
    @Override
    @Measurable(level = Level.INFO)
    public void firstTest() {
        for (int i = 0; i < 1_000_000; i++) {
            integers.add(new Random().nextInt());
        }
    }

    @Override
    @Measurable(level = Level.INFO)
    public void secondTest() {
        for (int i = 0; i < 50_000; i++) {
            integers.removeFirst();
        }
    }
}

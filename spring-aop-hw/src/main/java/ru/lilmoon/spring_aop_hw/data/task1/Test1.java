package ru.lilmoon.spring_aop_hw.data.task1;

import org.slf4j.event.Level;
import org.springframework.stereotype.Component;
import ru.lilmoon.spring_aop_hw.aspects.Measurable;
import ru.lilmoon.spring_aop_hw.aspects.TestInterface;

import java.util.ArrayList;
import java.util.Random;

@Measurable(level = Level.WARN)
@Component
public class Test1 implements TestInterface {
    ArrayList<Integer> integers = new ArrayList<>();

    public void firstTest() {
        integers = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i++) {
            integers.add(new Random().nextInt());
        }
    }

    public void secondTest() {
        for (int i = 0; i < 50_000; i++) {
            integers.removeFirst();
        }
    }


}

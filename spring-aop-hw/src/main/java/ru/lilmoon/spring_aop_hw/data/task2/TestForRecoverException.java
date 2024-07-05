package ru.lilmoon.spring_aop_hw.data.task2;

import org.slf4j.event.Level;
import org.springframework.stereotype.Component;
import ru.lilmoon.spring_aop_hw.aspects.Measurable;
import ru.lilmoon.spring_aop_hw.aspects.RecoverException;
import ru.lilmoon.spring_aop_hw.aspects.TestInterface;

import java.io.FileNotFoundException;

@Component
public class TestForRecoverException implements TestInterface {

    @Override
    @RecoverException(noRecoverFor = RuntimeException.class)
    @Measurable(level = Level.INFO)
    public void firstTest() {
//        throw new IllegalArgumentException("test #1 exception throw");
    }

    @Override
    @RecoverException(noRecoverFor = NumberFormatException.class)
    @Measurable(level = Level.INFO)
    public void secondTest() {
        throw new IllegalArgumentException("test #2 exception throw");
    }
}

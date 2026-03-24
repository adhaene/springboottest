package com.bezkoder.spring.jpa.h2;

import com.bezkoder.spring.jpa.h2.model.Tutorial;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class test {

    static List<Arguments> argsTutorial() {
        Tutorial tutorial = new Tutorial("Tut title", "Tut desc", true);
        Tutorial tutorial2 = new Tutorial("TUTU", "Tut desc", true);
        return Stream.of(tutorial,tutorial2
                        )
                .map(Arguments::of)
                .toList();
    }

    @ParameterizedTest
    @MethodSource("argsTutorial")
    void testTutorial(Tutorial tutorial) {
        Tutorial expected = new Tutorial("TUTU", "Tut desc", true);
        assertThat(expected, equalTo(tutorial));
    }
}

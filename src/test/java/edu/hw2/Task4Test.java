package edu.hw2;

import edu.hw2.task4.CallingInfo;
import edu.hw2.task4.Task4;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    @DisplayName("Проверка работы")
    void test() {
        // given
        String thisClassName = "edu.hw2.Task4Test";
        String thisMethodName = "test";
        var thisInfo = new CallingInfo(thisClassName, thisMethodName);

        // when
        var checkInfo = Task4.callingInfo();

        // then
        assertThat(checkInfo).isEqualTo(thisInfo);
    }
}

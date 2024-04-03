package model;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class Ball {
    private static final int START_NUMBER = 1;
    private static final int END_NUMBER = 45;
    public static List<Ball> lottoNums = IntStream.rangeClosed(START_NUMBER, END_NUMBER).mapToObj(Ball::new).collect(toList());

    private final int ball;

    public Ball(int ball) {
        validateNumberRange(ball);
        this.ball = ball;
    }

    public static Set<Ball> createBallSet(Set<Integer> balls) {
        return balls.stream()
            .map(Ball::new)
            .collect(toSet());
    }


    private void validateNumberRange(int ballNumber) {
        if (ballNumber < START_NUMBER ||
            ballNumber > END_NUMBER) {
            throw new IllegalArgumentException(String.format("%d ~ %d 사이 값을 입력하세요",
                START_NUMBER, END_NUMBER));
        }
    }

    public int getBall() {
        return ball;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ball ball1 = (Ball) o;
        return ball == ball1.ball;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ball);
    }
}

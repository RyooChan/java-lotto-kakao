package model.random;

import java.util.List;
import java.util.Set;

import model.Ball;

import static java.util.Collections.*;
import static java.util.stream.Collectors.*;
import static model.Ball.*;

public interface RandomNumberGenerator {

    default Set<Ball> generateRandomNumbers() {
        List<Ball> balls = getBalls();
        shuffle(balls);

        return balls
            .stream()
            .limit(6)
            .collect(toSet());
    }
}

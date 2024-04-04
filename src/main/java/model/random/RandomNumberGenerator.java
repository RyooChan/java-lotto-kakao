package model.random;

import java.util.Set;

import model.Ball;

import static java.util.Collections.*;
import static java.util.stream.Collectors.*;
import static model.Ball.*;

public interface RandomNumberGenerator {

    default Set<Ball> generateRandomNumbers() {
        shuffle(lottoNums);

        return lottoNums
            .stream()
            .limit(6)
            .collect(toSet());
    }
}

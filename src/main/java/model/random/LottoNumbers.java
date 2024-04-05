package model.random;

import java.util.Set;

import model.Ball;

public class LottoNumbers {
    private final Set<Ball> LottoNumbers;

    public LottoNumbers(Set<Ball> lottoNumbers) {
        LottoNumbers = lottoNumbers;
    }

    public static Set<Ball> generateRandomNumbers(RandomNumberGenerator randomNumberGenerator) {
        return randomNumberGenerator.generateRandomNumbers();
    }

    public Set<Ball> getLottoNumbers() {
        return LottoNumbers;
    }
}

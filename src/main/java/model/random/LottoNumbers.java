package model.random;

import java.util.HashSet;
import java.util.Set;

import model.Ball;

public class LottoNumbers {
    private final Set<Ball> LottoNumbers;

    public LottoNumbers(Set<Ball> lottoNumbers) {
        LottoNumbers = lottoNumbers;
    }

    public static Set<Ball> generateRandNums(RandomNumberGenerator randomNumberGenerator) {
        return randomNumberGenerator.generateRandNums();
    }

    public Set<Ball> getLottoNumbers() {
        return LottoNumbers;
    }
}

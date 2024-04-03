package model.random;

import java.util.HashSet;
import java.util.Set;

import model.Ball;

public class LottoNumbers {
    private Set<Ball> LottoNumbers = new HashSet<>();


    public LottoNumbers(Set<Ball> lottoNumbers) {
        LottoNumbers = lottoNumbers;
    }

    public Set<Ball> getLottoNumbers() {
        return LottoNumbers;
    }

    public static Set<Ball> generateRandNums(RandomNumberGenerator randomNumberGenerator) {
        return randomNumberGenerator.generateRandNums();
    }
}

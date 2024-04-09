package model.random;

import java.util.Set;

import model.Ball;

public class LottoNumbers {
    public static final int  LOTTO_NUMBERS_LENGTH = 6;

    private final Set<Ball> LottoNumbers;

    public LottoNumbers(Set<Ball> lottoNumbers) {
        validate(lottoNumbers);
        LottoNumbers = lottoNumbers;
    }

    private void validate(Set<Ball> lottoNumbers) {
        if (lottoNumbers.size() > LOTTO_NUMBERS_LENGTH) {
            throw new IllegalArgumentException("6개 이상의 로또넘버는 생성 불가합니다.");
        }
    }

    public static Set<Ball> generateRandomNumbers(RandomNumberGenerator randomNumberGenerator) {
        return randomNumberGenerator.generateRandomNumbers();
    }

    public Set<Ball> getLottoNumbers() {
        return LottoNumbers;
    }
}

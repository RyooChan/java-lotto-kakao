package model.winningLottery;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import model.Ball;
import model.random.LottoNumbers;

public class WinningNumbers {
    private static final int NUMBER_COUNT = 6;
    private Set<Ball> winningNumbers = new HashSet<>();

    public WinningNumbers(Set<Ball> winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    public int matchCount(LottoNumbers lottoNumbers) {
        return lottoNumbers.getLottoNumbers()
            .stream()
            .mapToInt(
                this::numberMatch
            ).sum();
    }

    private int numberMatch(Ball lottoNumber) {
        if (winningNumbers.contains(lottoNumber)){
            return 1;
        }

        return 0;
    }

    public static WinningNumbers createWinningNumbers(String winningNumberStr) {

        Set<Ball> winningNumberSet = Arrays.stream(winningNumberStr.split(","))
            .map(Integer::parseInt)
            .map(Ball::new)
            .collect(Collectors.toSet());

        validate(winningNumberSet);

        return new WinningNumbers(winningNumberSet);
    }

    public Set<Ball> getWinningNumbers() {
        return winningNumbers;
    }

    private static void validate(Set<Ball> winningNumberSet) {
        validateUniqueNumberCount(winningNumberSet);
    }

    private static void validateUniqueNumberCount(Set<Ball> winningNumberSet) {
        if (winningNumberSet.size() != NUMBER_COUNT) {
            throw new IllegalArgumentException(String.format("서로 다른 %d개 숫자를 입력해주세요", NUMBER_COUNT));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningNumbers that = (WinningNumbers) o;
        return Objects.equals(winningNumbers, that.winningNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningNumbers);
    }
}

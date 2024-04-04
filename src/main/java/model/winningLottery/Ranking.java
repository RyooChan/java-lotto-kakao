package model.winningLottery;

import static java.util.Arrays.*;

public enum Ranking {
    FIRST(6, false, 2000000000),
    SECOND(5, true, 30000000),
    THIRD(5, false, 1500000),
    FOURTH(4, false, 50000),
    FIFTH(3, false, 5000),
    NONE(0, false, 0),
    ;

    Ranking(int winningMatchCount, boolean isBonusMatch, long reward) {
        this.winningMatchCount = winningMatchCount;
        this.isBonusMatch = isBonusMatch;
        this.reward = reward;
    }

    private final int winningMatchCount;
    private final boolean isBonusMatch;
    private final long reward;

    public static Ranking calculateRanking(int winningMatchCount, boolean isBonusMatch) {
        return stream(values())
            .filter(ranking -> isRanking(ranking, winningMatchCount, isBonusMatch))
            .findFirst()
            .orElse(NONE);
    }

    private static boolean isRanking(Ranking ranking, int winningMatchCount, boolean isBonusMatch) {
        return winningMatchCount >= ranking.winningMatchCount &&
            ((isBonusMatch == ranking.isBonusMatch) || !ranking.isBonusMatch);
    }

    public int getWinningMatchCount() {
        return winningMatchCount;
    }

    public boolean isBonusMatch() {
        return isBonusMatch;
    }

    public long getReward() {
        return reward;
    }
}

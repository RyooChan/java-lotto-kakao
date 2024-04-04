package model.random;

import java.util.ArrayList;
import java.util.List;

import model.Amount;

import static model.Amount.LOTTO_UNIT_PRICE;

public class LottoGenerator {
    private final List<LottoNumbers> lottoNumbers;

    private LottoGenerator(List<LottoNumbers> lottoNumbers) {
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
    }

    public static LottoGenerator generate(Amount amount) {
        int count = amount.getCost() / LOTTO_UNIT_PRICE;

        List<LottoNumbers> lottoNumberList = new ArrayList<>();

        for (int i=0; i<count; i++) {
            LottoNumbers lottoNumbers =
                new LottoNumbers(LottoNumbers.generateRandomNumbers(new RandomNumberGenerator() {}));
            lottoNumberList.add(lottoNumbers);
        }

        return new LottoGenerator(lottoNumberList);
    }

    public int calculateCount() {
        return lottoNumbers.size();
    }

    public List<LottoNumbers> getLottoNumbers() {
        return lottoNumbers;
    }

    public int getWholeLottoSize() {
        return lottoNumbers.size();
    }
}

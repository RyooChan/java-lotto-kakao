package model.random;

import java.util.ArrayList;
import java.util.List;

import model.Amount;

import static model.Amount.LOTTO_UNIT_PRICE;

public class LottoGenerator {
    private final List<LottoNumbers> lottoNumberList;

    private LottoGenerator(List<LottoNumbers> lottoNumberList) {
        this.lottoNumberList = new ArrayList<>(lottoNumberList);
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
        return lottoNumberList.size();
    }

    public List<LottoNumbers> getLottoNumberList() {
        return lottoNumberList;
    }

    public int getWholeLottoSize() {
        return lottoNumberList.size();
    }
}

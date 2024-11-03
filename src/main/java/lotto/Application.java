package lotto;

import static camp.nextstep.edu.missionutils.Randoms.pickUniqueNumbersInRange;
import static camp.nextstep.edu.missionutils.Console.readLine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Arrays;

public class Application {

    public static List<Lotto> lottos = new ArrayList<>();

    public static int stringToNum(String input) {
        try {
            int num = Integer.parseInt(input);
            return num;
        } catch (Exception e) {
            throw new IllegalArgumentException("[ERROR] 숫자만 입력가능합니다.");
        }
    }

    public static int inputAmount() {
        while (true) {
            try {
                System.out.println("구입금액을 입력해 주세요.");
                int lotto_cost = stringToNum(readLine());
                return lotto_cost;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void makeLotto(int lotto_cost) {
        for (int i = lotto_cost; i > 0; i -= 1000) {
            Lotto element = new Lotto(pickUniqueNumbersInRange(1, 45, 6));
            lottos.add(element);
        }
    }

    public static void outputLottoNumbers() {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        for (Lotto lotto : lottos) {
            System.out.println(lotto.printNumbers());
        }
    }

    public static void buyLotto() {
        int lotto_cost = inputAmount();
        makeLotto(lotto_cost);
        outputLottoNumbers();
    }

    public static void checkSetVaild(Set<String> uniqueNumbers) {
        if (uniqueNumbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복없이 6개여야 합니다.");
        }
        List<Integer> numbers = new ArrayList<Integer>();
        for (String e : uniqueNumbers) {
            numbers.add(stringToNum(e));
        }
    }

    public static void makeWinningLotto() {
        while (true) {
            try {
                System.out.println("당첨 번호를 입력해 주세요.");
                String input = readLine();
                Set<String> uniqueNumbers = new HashSet<>(Arrays.asList(input.split(",")));
                checkSetVaild(uniqueNumbers);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void inputWinningLotto() {
        makeWinningLotto();
    }

    public static void main(String[] args) {
        buyLotto();
        inputWinningLotto();
    }
}

import java.util.Random;
import java.util.Scanner;

public class MasterMind extends GameGuessing{
    private StringBuilder colors = new StringBuilder("RGBYOP");
    private int CODESIZE ;
    private int score = 100;
    private String validPool = "RGBYOP";
    public MasterMind(int size){
        CODESIZE = size;
    }

    public void randomPhrase(){
        int  i = 0;
        while (i < CODESIZE){
            Random rand = new Random();
            int r = rand.nextInt(6); // gets 0, 1, or 2
            char ch = colors.charAt(r);
            phrase.append(ch);
        }
    }

    public int checkExact(StringBuilder secretSB, StringBuilder guessSB){
        int exact = 0;
        for (int j= 0; j < CODESIZE; j++){
            if (secretSB.charAt(j) == guessSB.charAt(j)){
                exact ++;
                secretSB.setCharAt(j,'-');
            }
        }
        return exact;
    }

    public int checkPartials(StringBuilder secretSB, StringBuilder guessSB) {
        // compare secret to guess
        int i=0;

        int partials=0;
        while (i<CODESIZE) {
            int j=0;
            while (j<CODESIZE) {
                if (secretSB.charAt(i) == guessSB.charAt(j)) {
                    partials = partials + 1;
                    secretSB.setCharAt(i,'-');
                    guessSB.setCharAt(j,'*');
                }
                j++;
            }
            i++;
        }
        return partials;
    }

    public String allGuess() {
        String answer = "";
        for (int j = 0; j < CODESIZE; j++) {
            char guess = getGuess(validPool);
            answer += guess;
        }
        return answer;
    }

    public GameRecord play(){
        String id = getId();
        score = 100;
        randomPhrase();
        getSecretphrase();
        System.out.println(secret);
        int exact = 0;
        while (exact != CODESIZE){
            String answer = allGuess();
            StringBuilder secretSB = new StringBuilder(secret);
            StringBuilder guessSB = new StringBuilder(answer);
            exact = checkExact(secretSB,guessSB);
            int partial = checkPartials(secretSB,guessSB);
            score --;
            System.out.println(exact+" exactly correct and "+partial+" partially correct.");
        }
        System.out.println("Congradulation! You are win!");
        GameRecord newrecord = new GameRecord(score,id);
        return newrecord;
    }
}

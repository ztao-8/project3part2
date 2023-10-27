import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WheelOfFurtune extends GameGuessing{
    protected List<String> phList;

    protected String filename;
    private String previousGuess = "";
    protected static int score = 26;
    private String validPool = "abcdefghijklmnopqrstuvwxyz";

    public WheelOfFurtune(String filename){
        this.filename = filename;
        readPhrases(filename);
    }

    public void readPhrases(String filename){
        List<String> phraseList= new ArrayList<String>();
        // Get the phrase from a file of phrases
        try {
            phraseList = Files.readAllLines(Paths.get(filename));
        } catch (IOException e) {
            System.out.println(e);
        }
        phList = phraseList;
    }
    public void randomPhrase(){
        Random rand = new Random();
        int r = rand.nextInt(phList.size()); // gets 0, 1, or 2
        phrase = new StringBuilder(phList.get(r));
        phList.remove(r) ;
    }
    public boolean processGuess(char guesschar){
        boolean ans = false;
        for (int i = 0; i < secret.length(); i++){
            char character  = phrase.charAt(i);
            if (Character.toLowerCase(guesschar) == Character.toLowerCase(character)){
                secret.setCharAt(i,character);
                ans = true;
            }
        }
        return ans;
    }

    public char nextGuess() {
        char guess = getGuess(validPool);
        while (this.previousGuess.indexOf( Character.toLowerCase(guess)) != -1){
            System.out.println("You already guessed this character!");
            guess = getGuess(validPool);
        }
        previousGuess += guess;
        return guess;
    }
    public GameRecord play() {
        String id = getId();
        score = 26;
        randomPhrase();
        getSecretphrase();
        System.out.println(secret);
        while (!secret.toString().equals(phrase)){
            char guess = nextGuess();
            if (processGuess(guess)){
                System.out.println(secret);
            }
            else{
                score -= 1;
                System.out.println("There is no " + guess + " in the phrase.");
            }
        }
        GameRecord newrecord = new GameRecord(score,id);
        System.out.println("Congratulation! You are win!");
        return newrecord;
    }

    @Override
    public boolean playNext() {
        if (!phList.isEmpty()){
            return super.playNext();}
        return false;
    }
}

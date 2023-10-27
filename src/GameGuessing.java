import java.util.Scanner;

public abstract class GameGuessing extends Game{
    protected StringBuilder secret;
    protected StringBuilder phrase = new StringBuilder();;

    public void getSecretphrase(){
        secret = new StringBuilder();
        for (int i = 0; i < phrase.length(); i++ ){
            char ch = phrase.charAt(i);
            if (Character.isLetter(ch)){
                secret.append("*");
            }
            else{
                secret.append(ch);
            }
        }
    }
    public String getId(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type your id:");
      String id = scanner.next();

        return id;
    }

    public boolean playNext(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to play it? Yes/No");
        String answer = scanner.next();
////            while (answer.equals("Yes") && answer.equals("No")) {
////                System.out.println("Do you want to play more? Yes/No");
////                answer = scanner.next();
////            }
        if (answer.equals("Yes")) {
            return true;
        }
        System.out.println("Game stops.");
        return false;
    };

    public char getGuess(String validPool) {
        char guess;
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter a Character:");
        guess = scanner.next().charAt(0);
        while (validPool.indexOf(Character.toLowerCase(guess))==-1) {
            System.out.println("Guessing validly:"+ validPool);
            System.out.println("enter a Character:");
            guess = scanner.next().charAt(0);
        }
        return guess;
    }
    public abstract GameRecord play();
    public abstract void randomPhrase();



}

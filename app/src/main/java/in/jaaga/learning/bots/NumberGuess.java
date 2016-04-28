package in.jaaga.learning.bots;

import java.util.Random;

import in.jaaga.learning.api.Bot;
import in.jaaga.learning.api.ChatItem;

/**
 * Created by freeman on 26/4/16.
 */
public class NumberGuess extends Bot {
    public static final int USER_GUESS = 8;
    public static final int COMPUTER_GUESS = 4;

    int mode = USER_GUESS;
    int number;
    int guesses = 0;

    public void onStart() {
        startUserGuess();
    }

    public void onMessageReceived(String text) {
        if (mode == USER_GUESS) {
            onUserGuessMessageReceived(text);
        } else if (mode == COMPUTER_GUESS) {
            onComputerGuessMessageReceived(text);
        }
    }

    void startUserGuess() {
        mode = USER_GUESS;
        guesses = 0;
        number = new Random().nextInt(100);
        sender.send(new ChatItem("I'm thinking of a number between 1 and 100.\n"+
                "What do you think it is ?", ChatItem.NUMBER_RESPONSE));
    }

    public void onUserGuessMessageReceived(String text) {
        ++guesses;
        int guess = Integer.parseInt(text);
        if (guess == number) {
            sender.send(new ChatItem("Fantastic. You got it in just " + guesses +
                    " guesses!! Lets play again. This time you pick a number and I'll guess", ChatItem.NO_RESPONSE));
            startComputerGuess();
        } else if (guess < number) {
            if (number - guess > 10 / guesses) {
                sender.send(new ChatItem("Good try, but it's much higher.", ChatItem.NUMBER_RESPONSE));
            } else {
                sender.send(new ChatItem("Almost there, it's just a bit higher", ChatItem.NUMBER_RESPONSE));
            }

        } else if (guess > number) {
            if (guess - number > 10 / guesses) {
                sender.send(new ChatItem("mmm, good guess, but my number is much less than that.", ChatItem.NUMBER_RESPONSE));
            } else {
                sender.send(new ChatItem("Almost there, it's just a bit lower", ChatItem.NUMBER_RESPONSE));
            }
        }
    }

    int low = 0;
    int high = 100;
    int guess;

    void startComputerGuess() {
        mode = COMPUTER_GUESS;
        low = 0;
        high = 100;
        guess();
    }

    public void onComputerGuessMessageReceived(String text) {
        if ("yes".equalsIgnoreCase(text)) {
            sender.send(new ChatItem("I'm pretty smart :)", ChatItem.NO_RESPONSE));
            sender.send(new ChatItem("OK, now its your turn to guess.", ChatItem.NUMBER_RESPONSE));
            startUserGuess();
            return;
        } else if ("less".equalsIgnoreCase(text)) {
            high = guess;
        } else if ("more".equalsIgnoreCase(text)) {
            low = guess;
        } else {
            sender.send(new ChatItem("err.... I'm really smart, but only understand 3 words - \n"+
                                     "'yes' if I guess your number\n"+
                                     "'less' if your number is less than my guess\n"+
                                     "'more' if your number is greater than my guess.", ChatItem.TEXT_RESPONSE));
        }
        guess();
    }

    void guess() {
        guess = low + (high - low) / 2;
        sender.send(new ChatItem("Is Your number "+guess, ChatItem.TEXT_RESPONSE));
    }
}

package in.jaaga.learning.bots;

import java.util.Random;

import in.jaaga.learning.api.Bot;
import in.jaaga.learning.api.ChatItem;

/**
 * Created by freeman on 26/4/16.
 */
public class NumberGuess extends Bot {
    int number;
    int guesses = 0;

    public void onStart() {
        number = new Random().nextInt(100);

        sender.send(new ChatItem("Hi, I'm thinking of a number between 1 and 100.\n"+
                "What do you think it is ?", ChatItem.NUMBER_RESPONSE));
    }

    public void onMessageReceived(String text) {
        ++guesses;
        int guess = Integer.parseInt(text);
        if (guess == number) {
            sender.send(new ChatItem("Fantastic. You got it in just " + guesses +
                    " guesses!! Lets play again...", ChatItem.NO_RESPONSE));
            guesses = 0;
            onStart();
        } else if (guess < number) {
            if (number-guess > 10/guesses) {
                sender.send(new ChatItem("Good try, but it's much higher.", ChatItem.NUMBER_RESPONSE));
            }
            else {
                sender.send(new ChatItem("Almost there, it's just a bit higher", ChatItem.NUMBER_RESPONSE));
            }

        } else if (guess > number) {
            if (guess-number > 10/guesses) {
                sender.send(new ChatItem("mmm, good guess, but my number is much less than that.", ChatItem.NUMBER_RESPONSE));
            }
            else {
                sender.send(new ChatItem("Almost there, it's just a bit lower", ChatItem.NUMBER_RESPONSE));
            }
        }
    }
}

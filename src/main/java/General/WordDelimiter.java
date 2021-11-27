package General;

import lombok.Data;

@Data
public class WordDelimiter {
    private static final char SPACE = ' ';

    //Encapsulation
    private final String firstWord;
    //Encapsulation
    private final String secondWord;

    public WordDelimiter(String commandName) {
        final int spacePosition = commandName.indexOf(SPACE);
        if (spacePosition > 0){
            this.firstWord = commandName.substring(0, spacePosition).trim();
            this.secondWord = commandName.substring(spacePosition + 1).trim();
        } else {
            this.firstWord = commandName.trim().toLowerCase();
            this.secondWord = "";
        }
    }

}

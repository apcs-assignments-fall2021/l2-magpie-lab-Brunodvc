import java.util.Locale;

/**
 * A program to carry on conversations with a human user.
 * This is the initial version that:  
 * <ul><li>
 *       Uses indexOf to find strings
 * </li><li>
 *          Handles responding to simple words and phrases 
 * </li></ul>
 * This version uses a nested if to handle default responses.
 * @author Laurie White
 * @version April 2012
 */
public class Magpie
{
    /**
     * Get a default greeting   
     * @return a greeting
     */
    public String getGreeting()
    {
        return "Hello, let's talk.";
    }
    
    /**
     * Gives a response to a user statement
     * 
     * @param statement
     *            the user statement
     * @return a response based on the rules given
     */
    public String getResponse(String statement)
    {
        String response = "";
        if (findWord(statement,"I want") >= 0){
            response = transformIWantStatement(statement);
        }
        else if (findWord(statement,"I") >= 0 || findWord(statement,"you") > findWord(statement,"I")){
            response = transformIYouStatement(statement);
        }
        else if (findWord(statement, "I want to") >= 0){
            response = transformIWantToStatement(statement);
        }
        else if (findWord(statement, "you")>= 0 && findWord(statement, "me")> findWord(statement, "you")){
            response = transformYouMeStatement(statement);
        }
        else if (findWord(statement,"I") >= 0 && findWord(statement, "that") > findWord(statement, "I")){
            response = transform_I_That_statement(statement);
        }
         else if (statement.indexOf("no") >= 0)
        {
            response = "Why so negative?";
        }
         else if (statement.length() > 30){
             response = "Bro ion even know what ur sayin cuz its too long";
        }
        else if (statement.indexOf(findWord(statement,"mother")) >= 0
                || statement.indexOf("father") >= 0
                || statement.indexOf("sister") >= 0
                || statement.indexOf("brother") >= 0)
        {
            response = "Tell me more about your family.";
        }
        else if (statement.indexOf("cat") >= 0
                || statement.indexOf("dog") >= 0)
        {
            response = "tell me more about your stupid pets.";
        }
        else if (statement.indexOf("Mr") >= 0
                || statement.indexOf("teacher") >= 0) {
            response = "tell me more about your teachers.";
        }
        else if (statement.trim()=="") {
            response = "say something";
        }
        else if (statement.indexOf("sport") >= 0) {
            response = "tell me more about your sports.";}
        else if (statement.indexOf("no") >= 0) {
            response = "why not";}
        else
        {
            response = getRandomResponse();
        }
        return response;
    }
    
    /**
     * Pick a default response to use if nothing else fits.
     * @return a non-committal string
     */
    public String getRandomResponse()
    {
        final int NUMBER_OF_RESPONSES = 4;
        double r = Math.random();
        int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
        String response = "";
        
        if (whichResponse == 0)
        {
            response = "Interesting, tell me more.";
        }
        else if (whichResponse == 1)
        {
            response = "Hmmm.";
        }
        else if (whichResponse == 2)
        {
            response = "Do you really think so?";
        }
        else if (whichResponse == 3)
        {
            response = "You don't say.";
        }
    
        return response;
    }

    // Checks to see if the String word appears as a whole word
    // in the String str (in this case, a "whole word" means that 
    // word is not just a substring of some larger word in str)

    // This method should work regardless of the capitalization 
    // of str or word

    // The method returns the index of the first character in word
    // if it is found, and returns -1 otherwise.
    //the word is at the start, and there is a space after the last letter, the word is at the end, and there's a space
    // before the first letter, or there is a space before the first letter and after the last letter.
    public int findWord(String str, String word) {
        str = str.toLowerCase();
        word = word.toLowerCase();
        int start_index = str.indexOf(word);
        int end_index = str.indexOf(word)+word.length();
        //the index of the start of the word has to be 0, and the character at the end of the word has to be ' '
        if(str.contains(word)) {
            if (start_index == 0 && str.charAt(str.indexOf(word) + word.length()) == ' ') {
                return start_index;
            }
            //the index of the end of the word has to be str.length()-1, and the character at the start of the word has to
            // be ' '
            else if (str.charAt(str.indexOf(word) - 1) == ' ' && end_index == str.length() ) {
                return start_index;
            }
            //the character at the start of the word has to be ' ' and the character at the end of the word has to be ' '
            else if (str.charAt(str.indexOf(word) - 1) == ' ' && str.charAt(str.indexOf(word) + word.length()) == ' ') {
                return start_index;
            }
            else{
                return -1;
            }
        }
        // none of the above cases were true so just return -1
        else{
            return -1;
        }
    }
    // We will work on the following methods later!

    /**
     * Take a statement with "I want <something>." and transform it into 
     * "Would you really be happy if you had <something>?"
     * @param statement the user statement, assumed to contain "I want"
     * @return the transformed statement
     */
    public String transformIWantStatement(String statement)
    {
        String what_I_want = "";
        //what i want is a substring starting from after "I want" and ending at the next space.
        what_I_want = statement.substring(statement.indexOf("I want")+7, statement.length());
        System.out.println(what_I_want);
            return "Would you really be happy if you had " + what_I_want + '?';
    }

    /**
     * Take a statement with "I <something> you" and transform it into 
     * "Why do you <something> me?"
     * @param statement the user statement, assumed to contain "I" followed by "you"
     * @return the transformed statement
     */
    public String transformIYouStatement(String statement)
    {
        String something = "";
        something = statement.substring(statement.indexOf('I')+2, statement.indexOf("you")-1);
        return "Why do you " + something + " me?";
    }

    /**
     * Take a statement with "I want to <something>." and transform it into 
     * "What would it mean to <something>?"
     * @param statement the user statement, assumed to contain "I want to"
     * @return the transformed statement
     */
    public String transformIWantToStatement(String statement)
    {
        String I_want_to = "";
        //what i want is a substring starting from after "I want to" and ending at the next space.
        I_want_to = statement.substring(statement.indexOf("I want to")+10, statement.length());
        System.out.println(I_want_to);
        return "What would it mean to " + I_want_to + '?';
    }




    /**
     * Take a statement with "you <something> me" and transform it into 
     * "What makes you think that I <something> you?"
     * @param statement the user statement, assumed to contain "you" followed by "me"
     * @return the transformed statement
     */
    public String transformYouMeStatement(String statement)
    {
        String something = "";
        something = statement.substring(statement.indexOf("you")+4, statement.indexOf("me")-1);
        return "What makes you think that I " + something + " you?";
    }
    public String transform_I_That_statement(String statement){
        String something = "";
        something = statement.substring(statement.indexOf("I") + 2, statement.indexOf("that")-2);
        return "why do you " + something + " that?";
    }
}



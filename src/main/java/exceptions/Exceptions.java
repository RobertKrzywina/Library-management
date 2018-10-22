package exceptions;

public class Exceptions extends RuntimeException {

    public static final String NO_CLIENT_IN_DB = "Couldn't find client of this Id in Database!";
    public static final String NO_ENTITY_IN_DB = "Couldn't find entity(client / book) of this Id in Database!";
    public static final String WRONG_ISBN_FORMAT = "ISBN should contains 13 digits!";

    public Exceptions(String message) {
        super(message);
    }
}

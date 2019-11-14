import org.apache.commons.validator.routines.IBANValidator;

import java.io.Console;
import java.io.IOException;

/**
 * Application for IBAN numbers validation from user input and local file
 *
 * @author Edgaras Bajorinas
 */
public class Main {
    public static void main(String[] args) {
        Console console = System.console();
        String menuString = "Choose 1 to check IBAN number\n" +
                "Choose 2 to check IBAN numbers from file\n" +
                "Choose 3 to leave application ";
        String readLine = console.readLine(menuString);
        IBANValidator ibanValidator = new IBANValidator();
        IBANHelper ibanHelper = new IBANHelper();

        while (!readLine.equals("3")) {

            if (readLine.equals("1")) {
                ibanHelper.isIBANValid(console, readLine, ibanValidator);
            } else if (readLine.equals("2")) {
                shouldCreateFileAndValidateIBAN(console, ibanValidator, ibanHelper);
            }
            readLine = console.readLine(menuString);
        }
    }

    /**
     * Helper method to call {@link IBANHelper#createFileAndValidateIBAN}
     *
     * @param console it is used to get responses from cmd
     * @param ibanValidator class which checks if IBAN number is valid
     * @param ibanHelper is used to call {@link IBANHelper#createFileAndValidateIBAN}
     *
     * @author Edgaras Bajorinas
     */
    private static void shouldCreateFileAndValidateIBAN(Console console, IBANValidator ibanValidator, IBANHelper ibanHelper) {
        try {
            String readLine = console.readLine("\nEnter file path: ");
            ibanHelper.createFileAndValidateIBAN(ibanValidator, readLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

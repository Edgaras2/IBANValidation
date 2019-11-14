import org.apache.commons.validator.routines.IBANValidator;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Helper class for {@link Main}
 *
 * @author Edgaras Bajorinas
 */
public class IBANHelper {

    /**
     * Validates IBAN number given by user.
     *
     * @param console it is used to get responses from cmd
     * @param userInput user input from cmd, it is used to determine his choices and
     *                 checks if user IBAN number is valid
     * @param ibanValidator class which checks if IBAN number is valid
     *
     * @author Edgaras Bajorinas
     */
    protected void isIBANValid(Console console, String userInput, IBANValidator ibanValidator) {
        while (userInput.equals("1")) {
            userInput = console.readLine("\nEnter your IBAN number: ");
            userInput = userInput.replaceAll(" ", "");

            boolean valid = ibanValidator.isValid(userInput);
            if (valid) {
                System.out.println(userInput + " IBAN number is correct\n");
            } else {
                System.out.println(userInput + " IBAN number is incorrect\n");
            }
            userInput = console.readLine("Choose 1 to try again\nChoose any other symbol to leave application ");
        }
    }

    /**
     * Retrieves file from file path, validates IBAN numbers existing in file and creates new file with new naming
     *
     * @param ibanValidator class which checks if IBAN number is valid
     * @param path is used to retrieve file
     * @throws IOException if file is not found throws exception
     *
     * @author Edgaras Bajorinas
     */
    protected void createFileAndValidateIBAN(IBANValidator ibanValidator, String path) throws IOException {
        File file = new File(path);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
            path = path.substring(0, path.length() - 4) + "Out.txt";
        } catch (FileNotFoundException e) {
            throw new IOException("Wrong file path\n");
        }

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(path), StandardCharsets.UTF_8))) {
            if (sc != null) {
                while (sc.hasNextLine()) {
                    String iBan = sc.nextLine();
                    boolean valid = ibanValidator.isValid(iBan);
                    writer.write(iBan + ";" + valid + "\n");
                }
            }
        }
        System.out.println("File create successfully it's path is " + path);
    }
}



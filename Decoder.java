import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.math.BigInteger;

public class Decoder extends ParentCoder {

    /* конструктор экземпляра класса Decoder */
    public Decoder(File privateKeyFile, File encodedFile){
        setEncodedMessage(readEncodedFile(encodedFile));
        readKeys(privateKeyFile);
        decrypt();
    }

    /* метод, осуществляющий дешифрование по алгоритму RSA */
    private void decrypt () {
        System.out.println("Дешифрование...");
        StringBuilder stringBuilder = new StringBuilder();
        for (BigInteger bigNumber : getEncodedMessage()) {
            BigInteger bigIntegerCodeSymbol = bigNumber.modPow(getKeyPartI(),getKeyPartII());
            int codeSymbol = bigIntegerCodeSymbol.intValueExact();
            char symbol = (char) codeSymbol;
            stringBuilder.append(symbol);
        }
        setOriginalMessage(stringBuilder.toString());
        System.out.println("Расшифровано.");
    }

    /* метод считывает зашифрованные данные из файла */
    private LinkedList<BigInteger> readEncodedFile (File publicKeyFile) {
        LinkedList<BigInteger> publicKey = new LinkedList<>();
        Scanner scanner;
        try {
            scanner = new Scanner(publicKeyFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                BigInteger value = new BigInteger(line, 16);
                publicKey.add(value);
            }
            System.out.println("Зашифрованные данные прочитаны.");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return publicKey;
    }
}

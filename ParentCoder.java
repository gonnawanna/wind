import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.math.BigInteger;


public class ParentCoder {
    private String originalMessage;
    private LinkedList<BigInteger> encodedMessage;

    private BigInteger keyPartI;
    private BigInteger keyPartII;

    public String getOriginalMessage() {
        return originalMessage;
    }

    public void setOriginalMessage(String originalMessage) {
        this.originalMessage = originalMessage;
    }

    public LinkedList<BigInteger> getEncodedMessage() {
        return encodedMessage;
    }

    public void setEncodedMessage(LinkedList<BigInteger> encodedMessage) {
        this.encodedMessage = encodedMessage;
    }

    public BigInteger getKeyPartI() {
        return keyPartI;
    }

    public BigInteger getKeyPartII() {
        return keyPartII;
    }

    /* метод устанавливает значение ключа, считав его из файла */
    void readKeys (File keyFile) {
        List<BigInteger> publicKey = new ArrayList<>();
        Scanner scanner;
        try {
            int i = 0;
            scanner = new Scanner(keyFile);
            while (i < 2 && scanner.hasNextLine()) {
                String line = scanner.nextLine();
                BigInteger value = new BigInteger(line, 16);
                publicKey.add(value);
                i++;
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.keyPartI = publicKey.get(0);
        this.keyPartII = publicKey.get(1);
        System.out.println("Считан ключ из файла " + keyFile + ".");
    }
}

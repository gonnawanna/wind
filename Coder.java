import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.math.BigInteger;

public class Coder extends ParentCoder {

    /* конструктор экземпляра класса Coder */
    public Coder (File publicKeyFile){
        Scanner in = new Scanner(System.in);
        System.out.println("Введите данные для шифрования:");
        setOriginalMessage(in.nextLine());
        readKeys(publicKeyFile);
        encrypt();
    }

    /* метод, осуществляющий шифрование по алгоритму RSA */
    private void encrypt () {
        System.out.println("Шифрование...");
        char[] symbolsOriginalMessage = getOriginalMessage().toCharArray();
        LinkedList<BigInteger> symbolsResultData = new LinkedList<>();
        int i;
        int n = symbolsOriginalMessage.length;
        for (i = 0; i < n; i++) {
            int code = (int) symbolsOriginalMessage[i];
            BigInteger newSymbol = BigInteger.valueOf(code).modPow(getKeyPartI(),getKeyPartII());
            symbolsResultData.add(newSymbol);
        }
        setEncodedMessage(symbolsResultData);
        System.out.println("Зашифровано (RSA).");
    }

    /* метод сохраняет зашифрованные данные в файл */
    public void saveEncodedMessageToFile (){
        try (FileOutputStream fos = new FileOutputStream("encodedRSA.txt");
             PrintStream printStream = new PrintStream(fos)) {
            for (BigInteger hex : getEncodedMessage()) {
                printStream.println(hex.toString(16));
                //!(radix:16)
            }
            System.out.println("Результат сохранен в файл encodedRSA.txt.");
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

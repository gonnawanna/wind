import java.io.*;
import java.util.Random;
import java.math.BigDecimal;
import java.math.BigInteger;

public class Key {
    private BigInteger encryptingKey;
    private BigInteger decryptingKey;
    private BigInteger n;
    private int bitLength;

    /* конструктор экземпляра класса Key, генерирующий открытый и закрытый ключи */
    public Key(int bitLength){
        this.bitLength = bitLength;
        RandomPrime p = new RandomPrime(bitLength);
        RandomPrime q = new RandomPrime(bitLength);
        BigInteger phi = p.calculateFunctionEuler(q);
        this.n = p.getValue().multiply(q.getValue());
        this.encryptingKey = generateEncryptingKey(phi);
        this.decryptingKey = generateDecryptingKey(phi, encryptingKey);

    }

    /* метод-генератор константы e */
    private BigInteger generateEncryptingKey (BigInteger phi) {
        BigInteger e;
        do {
            Random random = new Random();
            e = new BigInteger(bitLength + bitLength, random);
        }
        while(e.compareTo(BigInteger.ONE)<0 || e.compareTo(phi)>0 || !e.gcd(phi).equals(BigInteger.ONE));
        return e;
    }

    /* метод-генератор константы d */
    private BigInteger generateDecryptingKey (BigInteger phi, BigInteger e) {
        return extendedEuclidean(phi,e);
    }

    /* метод, реализующий расширенный алгоритм Евклида, с целью вычисления секретной экспоненты d */
    private BigInteger extendedEuclidean (BigInteger a, BigInteger b) {

        BigDecimal a1 = new BigDecimal(a);
        BigDecimal a0 = new BigDecimal(a);
        BigDecimal b0 = new BigDecimal(b);
        BigDecimal t0 = BigDecimal.ZERO;
        BigDecimal t = BigDecimal.ONE;
        BigDecimal div = a0.divide(b0, BigDecimal.ROUND_FLOOR);
        BigDecimal r = a0.subtract(div.multiply(b0));

        while(r.compareTo(BigDecimal.ZERO)>0){
            BigDecimal temp = t0.subtract(div.multiply(t)).remainder(a1);
            t0 = t;
            t = temp;
            a0 = b0;
            b0 = r;
            div = a0.divide(b0, BigDecimal.ROUND_FLOOR);
            r = a0.subtract(div.multiply(b0));
        }
        return t.toBigInteger();
    }

    /* метод, создающий файлы, содержащие открытый и закрытый ключи*/
    public void saveKeysToFiles(){
        File publicKeyFile = new File("publicKey.txt");
        File privateKeyFile = new File("privateKey.txt");
        saveKey(publicKeyFile, encryptingKey);
        saveKey(privateKeyFile, decryptingKey);
        System.out.println("Открытый ключ - файл publicKey.txt");
        System.out.println("Закрытый ключ - файл privateKey.txt");
    }

    /* метод, записывающий значение ключа в файл */
    private void saveKey (File file, BigInteger keyPartI) {
        try (FileOutputStream fos = new FileOutputStream(file);
             PrintStream ps = new PrintStream(fos)) {
            ps.println(keyPartI.toString(16));
            ps.println(this.n.toString(16));
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

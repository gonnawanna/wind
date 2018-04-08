import java.io.File;

public class MainCoder {
    /* 2. Точка входа для шифрования */
    /* метод шифрует данные по алгоритму RSA на основе файла, содержащего открытый ключ, и сохраняет результат в файл */
    public static void main (String[] args) {
        File publicKeyFile = new File("publicKey.txt");

        Coder coder = new Coder(publicKeyFile);
        coder.saveEncodedMessageToFile();
    }
}
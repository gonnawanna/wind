import java.io.File;

public class MainDecoder {
    /* 3. Точка входа для дешифрования */
    /* метод дешифрует данные по алгоритму RSA на основе файла, содержащего закрытый ключ, и выводит результат в консоль */
    public static void main (String[] args) {
        File privateKeyFile = new File("privateKey.txt");
        File encodedFile = new File ("encodedRSA.txt");

        Decoder decoder = new Decoder(privateKeyFile, encodedFile);
        System.out.println("Исходное сообщение:");
        System.out.println(decoder.getOriginalMessage());
    }
}

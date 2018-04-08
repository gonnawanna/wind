public class MainGenerator {
    /* 1. Точка входа для генерации ключей RSA */
    /* метод генерирует открытый и закрытый ключи определенной длины и сохраняет в файлы */
    public static void main (String[] args){

        int bitLength = 512;
        Key key = new Key(bitLength);
        key.saveKeysToFiles();

    }
}

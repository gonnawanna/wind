import java.math.BigInteger;
import java.util.Random;

public class RandomPrime{
    private BigInteger value;

    public BigInteger getValue() {
        return value;
    }

    /* конструктор экземпляра класса и присвоение полю значения рандомного простого числа  */
    public RandomPrime(int bitLength) {
        Random random = new Random();
        value = new BigInteger(bitLength,50, random);
            // встроенный тест Миллера-Рабина на простоту
    }

    /* метод, вычисляющий функцию Эйлера от двух простых чисел */
    public BigInteger calculateFunctionEuler(RandomPrime q){
        BigInteger firstMultiplier = this.value.subtract(BigInteger.ONE);
        BigInteger secondMultiplier = q.value.subtract(BigInteger.ONE);
        return firstMultiplier.multiply(secondMultiplier);
    }
}

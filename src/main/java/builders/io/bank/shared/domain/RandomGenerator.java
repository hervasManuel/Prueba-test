package builders.io.bank.shared.domain;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public final class RandomGenerator{
    private final Random random = new Random();

    private RandomGenerator() {}

    public String generateRandomHexString(int size) {
        StringBuffer sb = new StringBuffer();
        while (sb.length() < size) {
            sb.append(Integer.toHexString(this.random.nextInt()));
        }
        return sb.toString().substring(0, size).toUpperCase();
    }
}

package ru.otus.library.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import ru.otus.library.repository.book.BookRepository;

@RequiredArgsConstructor
@Component
public class BookHealthIndicator implements HealthIndicator {

    private final BookRepository bookRepository;

    @Override
    public Health health() {
        int size = bookRepository.findAll().size();
        if (size > 0) {
            return Health.up().withDetail("message", "Everything is fine, the books are in place!").build();
        }
        return Health.down().withDetail("message", "Alarm, the books are missing!").build();
    }
}

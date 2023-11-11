package spring.boot.optic.okulist.service.contactlenses;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.model.ContactLenses;
import spring.boot.optic.okulist.repository.ContactLensesRepository;

@Service
@RequiredArgsConstructor
public class DiopterOptionsService {
    private final ContactLensesRepository contactLensesRepository;

    public List<String> determineAvailableOptions(String parameter) {
        try {
            List<String> values = contactLensesRepository.findAll().stream()
                    .map(contactLenses -> getPropertyByParameter(contactLenses, parameter))
                    .filter(Objects::nonNull) // Filter out null values
                    .distinct()
                    .collect(Collectors.toList());

            return values;
        } catch (Exception e) {
            throw new RuntimeException("Error determining available options", e);
        }
    }

    private String getPropertyByParameter(ContactLenses contactLenses, String parameter) {
        try {
            // Use reflection to dynamically get the property value
            Field field = ContactLenses.class.getDeclaredField(parameter);
            field.setAccessible(true);
            return (String) field.get(contactLenses);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalArgumentException("Invalid parameter: " + parameter, e);
        }
    }
}

/*
Метод determineAvailableOptions:
Приймає параметр parameter, який представляє собою назву властивості
(наприклад, діоптри, кривизна тощо),
 для якої потрібно визначити доступні опції.
Використовує contactLensesRepository для отримання всіх контактних лінз з бази даних.
Створює потік (Stream) контактних лінз і викликає метод getPropertyByParameter
для кожного екземпляра,
 передаючи йому параметр.
Фільтрує значення, що не є null.
Здійснює вибірку унікальних значень за допомогою distinct().
Збирає отримані значення в список і повертає його.

Метод getPropertyByParameter:
Приймає об'єкт ContactLenses і назву параметра (властивості),
значення якого потрібно визначити.
Використовує рефлексію для динамічного отримання значення властивості за її ім'ям.
Якщо властивість не знайдена, виникає виключення NoSuchFieldException або IllegalAccessException.

Метод determineAvailableOptions:
Приймає параметр parameter, який представляє собою назву властивості
 (наприклад, діоптри, кривизна тощо),
 для якої потрібно визначити доступні опції.
Використовує contactLensesRepository для отримання всіх контактних лінз з бази даних.
Створює потік (Stream) контактних лінз і викликає метод getPropertyByParameter
 для кожного екземпляра,
 передаючи йому параметр.
Фільтрує значення, що не є null.
Здійснює вибірку унікальних значень за допомогою distinct().
Збирає отримані значення в список і повертає його.

Метод getPropertyByParameter:
Приймає об'єкт ContactLenses і назву параметра (властивості),
 значення якого потрібно визначити.
Використовує рефлексію для динамічного отримання значення властивості за її ім'ям.
Якщо властивість не знайдена, виникає виключення NoSuchFieldException або IllegalAccessException.
 */

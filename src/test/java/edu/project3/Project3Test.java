package edu.project3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Project3Test {
    @Test
    @DisplayName("Проверка на отсутствие параметра пути")
    void noPathArg() {
        // given
        String[] args = "--from 2022.12.15 --to 2022.12.27".split(" ");

        // when
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> NginxLogStatsAnalyzer.execute(args)
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo("--path is a mandatory argument");
    }

    @Test
    @DisplayName("Проверка на неверное задание параметров")
    void incorrectArgs() {
        // given
        String[] args = "--path a.txt --from 2022.12.05 --to".split(" ");

        // when
        ArrayIndexOutOfBoundsException thrown = assertThrows(
            ArrayIndexOutOfBoundsException.class,
            () -> NginxLogStatsAnalyzer.execute(args)
        );

        // then
        assertThat(thrown.getMessage()).isEqualTo("Arguments should come in pairs like: [--arg] [value]");
    }

    @Test
    @DisplayName("Проверка пустого ответа в markdown")
    void nullResponseMd() {
        // given
        String[] args = "--path a.txt --from 2016-12-05 --to 2017-10-12 --format markdown".split(" ");
        String expected = """
            #### Общая информация

            | Метрика                  | Значение   |
            |:-------------------------|-----------:|
            | Путь                     | `a.txt` |
            | Начальная дата           | 2016.12.05 |
            | Конечная дата            | 2017.10.12 |
            | Количество запросов      | 0 |
            | Средний размер ответа    | 0b |

            #### Запрашиваемые ресурсы (TOP 5)

            | Ресурс                   | Количество |
            |:-------------------------|-----------:|

            #### Коды ответа

            | Код  | Имя                    | Количество |
            |:----:|:-----------------------|-----------:|

            #### ip адреса (TOP 5)

            | Адрес| Частота обращения |
            |:----:|------------------:|

            #### типы запросов

            | Запрос| Количество|
            |:-----:|----------:|
            """;

        // when
        String real = NginxLogStatsAnalyzer.execute(args);

        // then
        assertThat(expected).isEqualTo(real);
    }

    @Test
    @DisplayName("Проверка пустого ответа в adoc")
    void nullResponseAdoc() {
        // given
        String[] args = "--path a.txt --from 2016-12-05 --to 2017-10-12 --format adoc".split(" ");
        String expected = """
            ==== Общая информация ====

            |=====
            |Метрика | Значение
            |Файл(-ы) | `a.txt`
            |Начальная дата | 2016.12.05
            |Конечная дата | 2017.10.12
            |Количество запросов | 0
            |Средний размер ответа | 0b
            |=====

            ==== Запрашиваемые ресурсы (TOP 5) ====

            |=====
            |Ресурс | Количество
            |=====

            ==== Коды ответа ====

            |=====
            |Код | Имя | Количество
            |=====

            ==== ip адреса (TOP 5) ====

            |=====
            |Адрес | Частота обращения
            |=====

            ==== типы запросов ====

            |=====
            |Запрос | Количество
            |=====
            """;

        // when
        String real = NginxLogStatsAnalyzer.execute(args);

        // then
        assertThat(expected).isEqualTo(real);
    }

    @Test
    @DisplayName("Проверка частичного нахождения в markdown")
    void partialResponseMd() {
        // given
        String[] args =
            "--path /Users/hoba/Downloads/JavaTinkoff/TinkoffJavaProjectTemplate/logs.txt --from 2023-11-16 --to 2023-11-17 --format markdown".split(
                " ");
        String expected = """
            #### Общая информация

            | Метрика                  | Значение   |
            |:-------------------------|-----------:|
            | Путь                     | `/Users/hoba/Downloads/JavaTinkoff/TinkoffJavaProjectTemplate/logs.txt` |
            | Начальная дата           | 2023.11.16 |
            | Конечная дата            | 2023.11.17 |
            | Количество запросов      | 31 |
            | Средний размер ответа    | 1514b |

            #### Запрашиваемые ресурсы (TOP 5)

            | Ресурс                   | Количество |
            |:-------------------------|-----------:|
            | /frame.php | 1 |
            | /Switchable%20Distributed.hmtl | 1 |
            | /encompassing/collaboration/Integrated.php | 1 |
            | /heuristic-adapter_open%20system.png | 1 |
            | /global/homogeneous.htm | 1 |

            #### Коды ответа

            | Код  | Имя                    | Количество |
            |:----:|:-----------------------|-----------:|
            | 200 | OK | 26 |
            | 400 | Bad Request | 3 |
            | 404 | Not Found | 1 |
            | 500 | Internal Server Error | 1 |

            #### ip адреса (TOP 5)

            | Адрес| Частота обращения |
            |:----:|------------------:|
            | 111.250.159.165 | 1 |
            | 78.100.105.231 | 1 |
            | 207.68.64.63 | 1 |
            | 206.54.151.21 | 1 |
            | 149.14.211.26 | 1 |

            #### типы запросов

            | Запрос| Количество|
            |:-----:|----------:|
            | GET | 21 |
            | DELETE | 4 |
            | PATCH | 3 |
            | PUT | 2 |
            | HEAD | 1 |
            """;

        // when
        String real = NginxLogStatsAnalyzer.execute(args);

        // then
        assertThat(expected).isEqualTo(real);
    }

    @Test
    @DisplayName("Проверка частичного нахождения в adoc")
    void partialResponseAdoc() {
        // given
        String[] args =
            "--path /Users/hoba/Downloads/JavaTinkoff/TinkoffJavaProjectTemplate/logs.txt --from 2023-11-17 --to 2023-11-18 --format adoc".split(
                " ");
        String expected = """
            ==== Общая информация ====

            |=====
            |Метрика | Значение
            |Файл(-ы) | `/Users/hoba/Downloads/JavaTinkoff/TinkoffJavaProjectTemplate/logs.txt`
            |Начальная дата | 2023.11.17
            |Конечная дата | 2023.11.18
            |Количество запросов | 12977
            |Средний размер ответа | 1634b
            |=====

            ==== Запрашиваемые ресурсы (TOP 5) ====

            |=====
            |Ресурс | Количество
            |/Up-sized.png | 6
            |/Self-enabling.css | 5
            |/Object-based.css | 5
            |/clear-thinking.jpg | 5
            |/functionalities.css | 5
            |=====

            ==== Коды ответа ====

            |=====
            |Код | Имя | Количество
            |200 | OK | 10801
            |301 | ? | 440
            |404 | Not Found | 439
            |400 | Bad Request | 434
            |500 | Internal Server Error | 434
            |302 | ? | 429
            |=====

            ==== ip адреса (TOP 5) ====

            |=====
            |Адрес | Частота обращения
            | 18.255.4.165 | 1
            | 60.151.210.34 | 1
            | 62.120.200.148 | 1
            | 148.222.121.40 | 1
            | 23.213.71.79 | 1
            |=====

            ==== типы запросов ====

            |=====
            |Запрос | Количество
            | GET | 8685
            | POST | 880
            | PATCH | 872
            | PUT | 859
            | DELETE | 847
            | HEAD | 834
            |=====
            """;

        // when
        String real = NginxLogStatsAnalyzer.execute(args);

        // then
        assertThat(expected).isEqualTo(real);
    }
}

package demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Test Class
 * Test Case
 * Test Fixture (подготовка тестовых данных)
 *
 * имя окначивается на Test, поскольку maven plugin surefire (запускалка тестов) по умолчанию смотрит только на такие классы
 *
 * ArrayList в данном случае - это SUT: system/subject under test
 */
public class ArrayListTest {

    /**
     * Имя по BDD - should<POST условие>When<PRED условие>
     *
     * PRED условие - что сделали
     * POST условие - результат (если их много - выносим в имя общее описание, в тесте дописываем подробнее)
     * декомпозиция POST на разные тесты зависит от ТЗ и доменной/предментной области ("что такое хранить элемент?")
     */
    @Test
    public void shouldStoreElementWhenAddElement() {
        // old school | bdd school

        //region Arrange | Given
        var sut = new ArrayList<>();
        var dummy = new Object();

        //noinspection ConstantConditions
        Assumptions.assumeTrue(sut.size() == 0); // если не выполнится, то тест выключится
        //endregion

        //region Act | When
        sut.add(dummy);
        //endregion

        //region Assert | Then
        Assertions.assertEquals(1, sut.size());
        Assertions.assertTrue(sut.contains(dummy));
        //endregion

        // примеры ещё
        Assertions.assertArrayEquals(sut.toArray(), new Object[]{dummy});
    }
}

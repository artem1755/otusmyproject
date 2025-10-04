package com.aero.tests;

import com.aero.jupiter.extensions.UiExtension;
import com.aero.pages.CoursesPage;
import com.aero.pages.MainPage;
import com.google.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(UiExtension.class)
public class CoursesPageTest {

//    todo:
//     1) Подключить линтеры - спотбагс и чекстайл,
//     2) внедрить di +
//     3) Отдельный класс вейтер +
//     4) Написать тесты
//     5) загрузка пропертей из файла
//     6) Использовать Actions

    @Inject
    CoursesPage coursesPage;

    @Inject
    MainPage mainPage;

    @Test
    void checkThatCourseIsAvailable() {
        String courseTitle = "Архитектура и шаблоны проектирования";

        coursesPage.open()
                .getCourseItemsByTitle(courseTitle)
                .gotoDetailPage()
                .checkThatPageIsCorrect(courseTitle);
    }

    @Test
    void checkThatTheRandomCategoryWillOpenCorrectly() {
        String expectedCategory = mainPage.open()
                .header()
                .selectRandomCategory();

        coursesPage.checkThatTheDesiredCategoryIsOpened(expectedCategory);
    }

    @Test
    void test(){
        coursesPage.open();
    }
}

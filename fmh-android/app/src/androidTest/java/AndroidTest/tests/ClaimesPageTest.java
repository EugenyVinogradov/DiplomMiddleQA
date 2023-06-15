package AndroidTest.tests;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static AndroidTest.data.Data.tittleClaimFiftyOneCharacter;
import static AndroidTest.data.DataHelper.getTextFromViewInteraction;
import static AndroidTest.data.DataHelper.getUniqueScreenshotName;
import static AndroidTest.pages.AuthPage.checkLogInAndLogInIfNot;
import static AndroidTest.pages.MainPage.goToClaimesPage;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import AndroidTest.steps.ClaimsPageSteps;
import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

@Epic("Тестирование страницы Заявки")

@RunWith(AllureAndroidJUnit4.class)
public class ClaimesPageTest {

  @Before
  public void setUp() {
    checkLogInAndLogInIfNot();
    goToClaimesPage();
  }


  @Rule
  public ActivityScenarioRule<AppActivity> myActivityScenarioRule =
      new ActivityScenarioRule<>(AppActivity.class);

  @Rule
  public ScreenshotRule screenshotRule =
      new ScreenshotRule(ScreenshotRule.Mode.FAILURE, getUniqueScreenshotName());

// Закомментированные тесты производят фильтрацию по всем существующим заявкам. Так как тестирование
// производится на продакшене, время прохождения каждого из них очень велико (около 30 минут самый быстрый).
// При переносе тестирования из продакшена в тестовую среду, будет возможно использование этих тестов

//  @Test
//  @DisplayName("Фильтрация заявок - статус 'Открыта'")
//  public void testAllItemsHaveOpenStatus() {
//    filterClimes(true, false, false, false);
//    checkFilteredClimes(mActivityScenarioRule, true, false, false, false);
//  }
//  @Test
//  @DisplayName("Фильтрация заявок - статусы 'Открыта' и 'В работе'")
//  public void testAllItemsHaveOpenOrInProgressStatus() {
//    filterClimes(true, true, false, false);
//    checkFilteredClimes(mActivityScenarioRule, true, true, false, false);
//  }
//
//  @Test
//  @DisplayName("Фильтрация заявок - статусы 'Открыта', 'В работе', 'Выполнена'")
//  public void testAllItemsHaveOpenOrInProgressOrExecutedStatus() {
//    filterClimes(true, true, true, false);
//    checkFilteredClimes(mActivityScenarioRule, true, true, true, false);
//  }
  //  @Test
//  @DisplayName("Фильтрация заявок - статусы 'Открыта', 'В работе', 'Отменена'")
//  public void testAllItemsHaveOpenOrInProgressOrExecutedOrCanceledStatus() {
//    filterClimes(true, true, false, true);
//    checkFilteredClimes(mActivityScenarioRule, true, true, false, true);
//  }
//  @Test
//  @DisplayName("Фильтрация заявок - статусы 'Открыта', 'В работе', 'Выполнена', 'Отменена'")
//  public void testAllItemsHaveOpenOrInProgressOrExecutedOrCanceledStatus() {
//    filterClimes(true, true, true, true);
//    checkFilteredClimes(mActivityScenarioRule, true, true, true, true);
//  }
  //  @Test
//  @DisplayName("Фильтрация заявок - статусы 'Открыта', 'Выполнена'")
//  public void testAllItemsHaveOpenOrInProgressOrExecutedOrCanceledStatus() {
//    filterClimes(true, false, true, false);
//    checkFilteredClimes(mActivityScenarioRule, true, false, true, false);
//  }
  //  @Test
//  @DisplayName("Фильтрация заявок - статусы 'Открыта', 'Выполнена', 'Отменена'")
//  public void testAllItemsHaveOpenOrInProgressOrExecutedOrCanceledStatus() {
//    filterClimes(true, false, true, true);
//    checkFilteredClimes(mActivityScenarioRule, true, false, true, true);
//  }
  //  @Test
//  @DisplayName("Фильтрация заявок - статусы 'Открыта', 'Отменена'")
//  public void testAllItemsHaveOpenOrInProgressOrExecutedOrCanceledStatus() {
//    filterClimes(true, false, false, true);
//    checkFilteredClimes(mActivityScenarioRule, true, false, false, true);
//  }
  //  @Test
//  @DisplayName("Фильтрация заявок - статус 'В работе'")
//  public void testAllItemsHaveOpenStatus() {
//    filterClimes(false, true, false, false);
//    checkFilteredClimes(mActivityScenarioRule, false, true, false, false);
//  }
//  @Test
//  @DisplayName("Фильтрация заявок - статусы 'В работе', 'Выполнена'")
//  public void testAllItemsHaveOpenOrInProgressOrExecutedStatus() {
//    filterClimes(false, true, true, false);
//    checkFilteredClimes(mActivityScenarioRule, false, true, true, false);
//  }
//  @Test
//  @DisplayName("Фильтрация заявок - статусы 'В работе', 'Выполнена', 'Отменена'")
//  public void testAllItemsHaveOpenOrInProgressOrExecutedStatus() {
//    filterClimes(false, true, true, true);
//    checkFilteredClimes(mActivityScenarioRule, false, true, true, true);
//  }
//  @Test
//  @DisplayName("Фильтрация заявок - статусы 'В работе', 'Отменена'")
//  public void testAllItemsHaveOpenOrInProgressOrExecutedStatus() {
//    filterClimes(false, true, false, true);
//    checkFilteredClimes(mActivityScenarioRule, false, true, false, true);
//  }
//  @Test
//  @DisplayName("Фильтрация заявок - статус 'Выполнена'")
//  public void testAllItemsHaveOpenOrInProgressOrExecutedStatus() {
//    filterClimes(false, false, true, false);
//    checkFilteredClimes(mActivityScenarioRule, false, false, true, false);
//  }
//  @Test
//  @DisplayName("Фильтрация заявок - статусы 'Выполнена', 'Отменена'")
//  public void testAllItemsHaveOpenOrInProgressOrExecutedStatus() {
//    filterClimes(false, false, true, true);
//    checkFilteredClimes(mActivityScenarioRule, false, false, true, true);
//  }
//  @Test
//  @DisplayName("Фильтрация заявок - статус 'Отменена'")
//  public void testAllItemsHaveOpenOrInProgressOrExecutedStatus() {
//    filterClimes(false, false, false, true);
//    checkFilteredClimes(mActivityScenarioRule, false, false, false, true);
//  }

  @Test
  @DisplayName("Фильтрация заявок - без статусов")
  public void testAllItemsWithoutStatus() {
    ClaimsPageSteps.filterClimesWithoutStatus();
    ClaimsPageSteps.messageThereIsNothingHereYet(myActivityScenarioRule);
  }

  @Test
  @DisplayName("Фильтрация заявок - статус 'Открыта'")
  public void testAllItemsHaveOpenStatus() {
    ClaimsPageSteps.addClaim();
    ClaimsPageSteps.filterClimesWithStatusOpen();
    ClaimsPageSteps.scrollToClaimWithTittleAndClick();
    ClaimsPageSteps.checkClaimWithStatusOpen();
  }

  @Test
  @DisplayName("Отмена создания новой заявки при нажатии кнопки 'Cancel'")
  public void testNotCreatingNewClaimWhenPressingCancelButton() {
    ClaimsPageSteps.fillingFieldsNewClimeAndPressCancel();
    ClaimsPageSteps.checkNotExistNewClaim();
  }

  @Test
  @DisplayName("Отмена создания новой заявки при нажатии системной кнопки 'Назад'")
  public void testNotCreatingNewClaimWhenPressingBackButton() {
    ClaimsPageSteps.fillingFieldsNewClimeAndPressBack();
    ClaimsPageSteps.checkNotExistNewClaim();
  }

  @Test
  @DisplayName("Фильтрация заявок - статус 'В работе'")
  public void testAllItemsHaveInProgressStatus() {
    ClaimsPageSteps.addClaim();
    ClaimsPageSteps.scrollToClaimWithTittleAndClick();
    ClaimsPageSteps.changeStatusToInProgressStep();
    ClaimsPageSteps.filterClimesWithStatusInProgress();
    ClaimsPageSteps.scrollToClaimWithTittleAndClick();
    ClaimsPageSteps.checkClaimWithStatusInProgress();
  }

  @Test
  @DisplayName("Фильтрация заявок - статус 'Отменена'")
  public void testAllItemsHaveCanceledStatus() {
    ClaimsPageSteps.addClaim();
    ClaimsPageSteps.scrollToClaimWithTittleAndClick();
    ClaimsPageSteps.changeStatusToInCanceledStep();
    ClaimsPageSteps.filterClimesWithStatusCanceled();
    ClaimsPageSteps.scrollToClaimWithTittleAndClick();
    ClaimsPageSteps.checkClaimWithStatusCanceled();
  }

  @Test
  @DisplayName("Фильтрация заявок - статус 'Исполнена'")
  public void testAllItemsHaveExecutedStatus() {
    ClaimsPageSteps.addClaim();
    ClaimsPageSteps.scrollToClaimWithTittleAndClick();
    ClaimsPageSteps.changeStatusToInProgressStep();
    ClaimsPageSteps.scrollToClaimWithTittleAndClick();
    ClaimsPageSteps.changeStatusToInExecutedStep();
    ClaimsPageSteps.filterClimesWithStatusExecuted();
    ClaimsPageSteps.scrollToClaimWithTittleAndClick();
    ClaimsPageSteps.checkClaimWithStatusExecuted();
  }

  @Test
  @DisplayName("Успешное создание новой заявки с одним символом в поле Тема")
  public void testSuccessCreatedNewClaimWithOneCharterInFieldTittle() {
    ClaimsPageSteps.addClaimWithOneCharacterInFieldTittle();
    ClaimsPageSteps.scrollToClaimWithOneCharacterTittleAndClick();
    ClaimsPageSteps.checkClaimWithStatusOpen();
  }

  @Test
  @DisplayName("Успешное создание новой заявки с 49 символами в поле Тема")
  public void testSuccessCreatedNewClaimWithFortyNineChartersInFieldTittle() {
    ClaimsPageSteps.addClaimWith49CharactersInFieldTittle();
    ClaimsPageSteps.scrollToClaimWith49CharacterTittleAndClick();
    ClaimsPageSteps.checkClaimWithStatusOpen();
  }

  @Test
  @DisplayName("Успешное создание новой заявки с 50 символами в поле Тема")
  public void testSuccessCreatedNewClaimWithFiftyChartersInFieldTittle() {
    ClaimsPageSteps.addClaimWith50CharactersInFieldTittle();
    ClaimsPageSteps.scrollToClaimWith50CharacterTittleAndClick();
    ClaimsPageSteps.checkClaimWithStatusOpen();
  }

  @Test
  @DisplayName("Обрезание тесктового значения поля Тема до 50 символов и успешное добавление новой заявки")
  public void testSuccessCreatedNewClaimWithFiftyOneChartersInFieldTittle() {
    String expectedTittle = tittleClaimFiftyOneCharacter.substring(0, 50);
    ClaimsPageSteps.addClaimWith51CharactersInFieldTittle();
    ClaimsPageSteps.scrollToClaimWithTrimmedTittleAndClick(expectedTittle);
    String actualTittle = getTextFromViewInteraction(onView(withId(R.id.title_text_view)));
    ClaimsPageSteps.checkThatTittleTrimmedTo50Characters(expectedTittle, actualTittle);
  }

  @Test
  @DisplayName("Отказ в создании новой заявки с незаполненным полем Тема")
  public void testRefusalCreatedNewClaimWithEmptyFieldTittle() {
    ClaimsPageSteps.addClaimWithEmptyFieldTittle();
    ClaimsPageSteps.checkErrorMessageBySomeParameterEmpty();
  }

  @Test
  @DisplayName("Отказ в создании новой заявки с заполнением поля Тема пробелами")
  public void testRefusalCreatedNewClaimWithOnlySpacesInFieldSTittle() {
    ClaimsPageSteps.addClaimWitOnlySpacesInFieldTittle();
    ClaimsPageSteps.checkErrorMessageBySomeParameterEmpty();
  }

  @Test
  @DisplayName("Отказ в создании новой заявки с незаполненным полем Дата")
  public void testRefusalCreatedNewClaimWithEmptyFieldDate() {
    ClaimsPageSteps.addClaimWithEmptyFieldDate();
    ClaimsPageSteps.checkErrorMessageBySomeParameterEmpty();
  }

  @Test
  @DisplayName("Отказ в создании новой заявки с незаполненным полем Время")
  public void testRefusalCreatedNewClaimWithEmptyFieldTime() {
    ClaimsPageSteps.addClaimWithEmptyFieldTime();
    ClaimsPageSteps.checkErrorMessageBySomeParameterEmpty();
  }

  @Test
  @DisplayName("Отказ в создании новой заявки с незаполнением поля Описание")
  public void testRefusalCreatedNewClaimWithEmptyFieldDescription() {
    ClaimsPageSteps.addClaimWithEmptyFieldDescription();
    ClaimsPageSteps.checkErrorMessageBySomeParameterEmpty();
  }

  @Test
  @DisplayName("Отказ в создании новой заявки с заполнением поля Описание пробелами")
  public void testRefusalCreatedNewClaimWithOnlySpacesInFieldDescription() {
    ClaimsPageSteps.addClaimWithOnlySpacesInFieldDescription();
    ClaimsPageSteps.checkErrorMessageBySomeParameterEmpty();
  }

  @Test
  @DisplayName("Успешное добавление комментария к заявке заполнением поля ввода символами")
  public void testSuccessAddingNewCommentToClaim() {
    ClaimsPageSteps.addClaim();
    ClaimsPageSteps.scrollToClaimWithTittleAndClick();
    ClaimsPageSteps.addNewCommentToClaim();
    ClaimsPageSteps.checkAddingNewComment();
  }

  @Test
  @DisplayName("Отмена добавления комментария к заявке заполнением поля ввода символами при нажатии кнопки Отмена")
  public void testCancelAddingNewCommentToClaimWithPressCancel() {
    ClaimsPageSteps.addClaim();
    ClaimsPageSteps.scrollToClaimWithTittleAndClick();
    ClaimsPageSteps.cancelAddingNewCommentToClaimWithPressCancel();
    ClaimsPageSteps.checkNotAddingNewComment();
  }

  @Test
  @DisplayName("Отмена добавления комментария к заявке заполнением поля ввода символами при нажатии кнопки Назад")
  public void testCancelAddingNewCommentToClaimWithPressBack() {
    ClaimsPageSteps.addClaim();
    ClaimsPageSteps.scrollToClaimWithTittleAndClick();
    ClaimsPageSteps.cancelAddingNewCommentToClaimWithPressBack();
    ClaimsPageSteps.checkNotAddingNewComment();
  }

  @Test
  @DisplayName("Отказ добавления комментария к заявке незаполненном поле ввода")
  public void testRefusalAddingNewCommentToClaimWithEmptyField() {
    ClaimsPageSteps.addClaim();
    ClaimsPageSteps.scrollToClaimWithTittleAndClick();
    ClaimsPageSteps.refusalAddingNewCommentToClaimWithEmptyField();
    ClaimsPageSteps.checkToastByEmptyField();
  }

  @Test
  @DisplayName("Отказ добавления комментария к заявке при заполнении поля ввода пробелами")
  public void testRefusalAddingNewCommentToClaimWithOnlySpacesInField() {
    ClaimsPageSteps.addClaim();
    ClaimsPageSteps.scrollToClaimWithTittleAndClick();
    ClaimsPageSteps.refusalAddingNewCommentToClaimWithOnlySpacesInField();
    ClaimsPageSteps.checkToastByEmptyField();
  }

  @Test
  @DisplayName("Редактирование существующего комментария у заявки")
  public void testSuccessEditingExistingCommentToClaim() {
    ClaimsPageSteps.addClaim();
    ClaimsPageSteps.scrollToClaimWithTittleAndClick();
    ClaimsPageSteps.addNewCommentToClaim();
    ClaimsPageSteps.pressCloseButton();
    ClaimsPageSteps.scrollToClaimWithTittleAndClick();
    ClaimsPageSteps.editExistingCommentToClaim();
    ClaimsPageSteps.checkEditingExistingComment();
  }

  @Test
  @DisplayName("Не изменение существующего комментария у заявки при редактировании и нажатии кнопки Отмена")
  public void testCancelEditingExistingCommentToClaimWithPressCancel() {
    ClaimsPageSteps.addClaim();
    ClaimsPageSteps.scrollToClaimWithTittleAndClick();
    ClaimsPageSteps.addNewCommentToClaim();
    ClaimsPageSteps.pressCloseButton();
    ClaimsPageSteps.scrollToClaimWithTittleAndClick();
    ClaimsPageSteps.cancelEditingExistingCommentToClaimWithPressCancel();
    ClaimsPageSteps.checkNotEditingExistingComment();
  }

  @Test
  @DisplayName("Не изменение существующего комментария у заявки при редактировании и нажатии кнопки Назад")
  public void testCancelEditingExistingCommentToClaimWithPressBack() {
    ClaimsPageSteps.addClaim();
    ClaimsPageSteps.scrollToClaimWithTittleAndClick();
    ClaimsPageSteps.addNewCommentToClaim();
    ClaimsPageSteps.pressCloseButton();
    ClaimsPageSteps.scrollToClaimWithTittleAndClick();
    ClaimsPageSteps.cancelEditingExistingCommentToClaimWithPressBack();
    ClaimsPageSteps.checkNotEditingExistingComment();
  }

  @Test
  @DisplayName("Редактирование Темы у существующей заявки")
  public void testSuccessEditingTittleToExistingClaim() {
    ClaimsPageSteps.addClaim();
    ClaimsPageSteps.scrollToClaimWithTittleAndClick();
    ClaimsPageSteps.editTittleClaim();
    ClaimsPageSteps.checkEditingTittleClaim();
  }

  @Test
  @DisplayName("Редактирование Даты у существующей заявки")
  public void testSuccessEditingDateToExistingClaim() {
    ClaimsPageSteps.addClaim();
    ClaimsPageSteps.scrollToClaimWithTittleAndClick();
    ClaimsPageSteps.editDateClaim();
    ClaimsPageSteps.checkEditingDateClaim();
  }

  @Test
  @DisplayName("Редактирование Времени у существующей заявки")
  public void testSuccessEditingTimeToExistingClaim() {
    ClaimsPageSteps.addClaim();
    ClaimsPageSteps.scrollToClaimWithTittleAndClick();
    ClaimsPageSteps.editTimeClaim();
    ClaimsPageSteps.checkEditingTimeClaim();
  }

  @Test
  @DisplayName("Редактирование Описания у существующей заявки")
  public void testSuccessEditingDescriptionToExistingClaim() {
    ClaimsPageSteps.addClaim();
    ClaimsPageSteps.scrollToClaimWithTittleAndClick();
    ClaimsPageSteps.editDescriptionClaim();
    ClaimsPageSteps.checkEditingDescriptionClaim();
  }

  @Test
  @DisplayName("Отмена редактирования полей существующей заявки при нажатии кнопки Назад")
  public void testCancelEditingToExistingClaimWithPressBack() {
    ClaimsPageSteps.addClaim();
    ClaimsPageSteps.scrollToClaimWithTittleAndClick();
    ClaimsPageSteps.cancelEditingFieldsExistingClaimWithPressBack();
    ClaimsPageSteps.checkNotChangingAttributeClaim();
  }

  @Test
  @DisplayName("Отмена редактирования полей существующей заявки при нажатии кнопки Отмена")
  public void testCancelEditingToExistingClaimWithPressCancel() {
    ClaimsPageSteps.addClaim();
    ClaimsPageSteps.scrollToClaimWithTittleAndClick();
    ClaimsPageSteps.cancelEditingFieldsExistingClaimWithPressCancel();
    ClaimsPageSteps.checkNotChangingAttributeClaim();
  }

}


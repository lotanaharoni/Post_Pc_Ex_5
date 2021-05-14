package exercise.android.reemh.todo_items;

import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.robolectric.annotation.Config;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@Config(sdk = 28)
public class TodoItemsHolderImplTest{

  @Test
  public void when_addingTodoItem_then_callingListShouldHaveThisItem(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");

    // verify
    assertEquals(1, holderUnderTest.getCurrentItems().size());
  }

  @Test
  public void when_adding_TodoItem_then_ItsSateShouldBeInProgress(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    assertEquals(1, holderUnderTest.getCurrentItems().size());
    assertFalse(holderUnderTest.getCurrentItems().get(0).isDone());
  }

  @Test
  public void when_adding_TodoItems_then_checkingTheirStateShouldBeCorrect(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    for (int i = 0; i < 5; i++){
      holderUnderTest.addNewInProgressItem(String.valueOf(i));
    }
    assertEquals(5, holderUnderTest.getCurrentItems().size());
    List<TodoItem> listToCheck = holderUnderTest.getCurrentItems();
    // test
    for (int i = 4; i >= 0; i--){
      assertEquals(String.valueOf(i),listToCheck.get(4 - i).getDescription());
    }
  }

  @Test
  public void when_marking_TodoItem_done_then_TheThereShouldBeFinishedTime(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    assertEquals(1, holderUnderTest.getCurrentItems().size());
    holderUnderTest.getCurrentItems().get(0).flipState();

    // verify
    assertNotEquals(null, holderUnderTest.getCurrentItems().get(0).getFinishedTime());
  }

  @Test
  public void when_adding_TodoItem_then_deleting_it_calling_then_callingListShouldNotHaveThisItem(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    assertEquals(1, holderUnderTest.getCurrentItems().size());
    TodoItem itemToDelete = holderUnderTest.getCurrentItems().get(0);
    holderUnderTest.deleteItem(itemToDelete);

    // verify
    assertEquals(0, holderUnderTest.getCurrentItems().size());
  }

  @Test
  public void when_adding_TodoItem_and_flipping_his_state_then_callingIsDoneShouldProvidedTheRightState(){
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    assertEquals(1, holderUnderTest.getCurrentItems().size());

    assertFalse(holderUnderTest.getCurrentItems().get(0).isDone());

    holderUnderTest.getCurrentItems().get(0).flipState();

    assertTrue(holderUnderTest.getCurrentItems().get(0).isDone());
  }

  @Test
  public void when_adding_two_TodoItems_then_callingListShouldHaveThemInTheRightOrder() {
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.addNewInProgressItem("go swimming");
    assertEquals(2, holderUnderTest.getCurrentItems().size());

    assertEquals("do shopping", holderUnderTest.getCurrentItems().get(1).getDescription());
    assertEquals("go swimming", holderUnderTest.getCurrentItems().get(0).getDescription());
  }

  @Test
  public void when_mark_TodoItem_done_then_TheListShouldSortAccordingly() {
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.addNewInProgressItem("go swimming");
    holderUnderTest.addNewInProgressItem("go fishing");
    assertEquals(3, holderUnderTest.getCurrentItems().size());
    assertEquals("go fishing", holderUnderTest.getCurrentItems().get(0).getDescription());
    assertEquals("go swimming", holderUnderTest.getCurrentItems().get(1).getDescription());
    assertEquals("do shopping", holderUnderTest.getCurrentItems().get(2).getDescription());

    TodoItem fishingItem = holderUnderTest.getCurrentItems().get(0);
    holderUnderTest.markItemDone(fishingItem);

    assertEquals("go swimming", holderUnderTest.getCurrentItems().get(0).getDescription());
    assertEquals("do shopping", holderUnderTest.getCurrentItems().get(1).getDescription());
    assertEquals("go fishing", holderUnderTest.getCurrentItems().get(2).getDescription());
  }

  @Test
  public void when_flipped_item_twice_then_TheListShouldSortAccordingly() throws InterruptedException {
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    TimeUnit.SECONDS.sleep(1);
    holderUnderTest.addNewInProgressItem("go swimming");
    assertEquals(2, holderUnderTest.getCurrentItems().size());
    assertEquals("go swimming", holderUnderTest.getCurrentItems().get(0).getDescription());
    assertEquals("do shopping", holderUnderTest.getCurrentItems().get(1).getDescription());

    TodoItem swimmingItem = holderUnderTest.getCurrentItems().get(0);
    holderUnderTest.markItemDone(swimmingItem);

    assertEquals("do shopping", holderUnderTest.getCurrentItems().get(0).getDescription());
    assertEquals("go swimming", holderUnderTest.getCurrentItems().get(1).getDescription());

    holderUnderTest.markItemInProgress(swimmingItem);

    assertEquals("go swimming", holderUnderTest.getCurrentItems().get(0).getDescription());
    assertEquals("do shopping", holderUnderTest.getCurrentItems().get(1).getDescription());
  }

  @Test
  public void when_mark_two_items_done_then_TheListShouldSortAccordingly() throws InterruptedException {
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.addNewInProgressItem("go swimming");
    assertEquals(2, holderUnderTest.getCurrentItems().size());
    assertEquals("go swimming", holderUnderTest.getCurrentItems().get(0).getDescription());
    assertEquals("do shopping", holderUnderTest.getCurrentItems().get(1).getDescription());

    TodoItem swimmingItem = holderUnderTest.getCurrentItems().get(0);
    holderUnderTest.markItemDone(swimmingItem);
    TimeUnit.SECONDS.sleep(1);
    TodoItem shoppingItem = holderUnderTest.getCurrentItems().get(1);
    holderUnderTest.markItemDone(shoppingItem);

    assertEquals("do shopping", holderUnderTest.getCurrentItems().get(0).getDescription());
    assertEquals("go swimming", holderUnderTest.getCurrentItems().get(1).getDescription());
  }

  @Test
  public void when_calling_load_state_then_TheListShouldBeLoadedCorrectly() {
    // setup
    TodoItemsHolderImpl holderUnderTest = new TodoItemsHolderImpl();
    TodoItemsHolderImpl holderUnderTest2 = new TodoItemsHolderImpl();
    assertEquals(0, holderUnderTest.getCurrentItems().size());
    assertEquals(0, holderUnderTest2.getCurrentItems().size());

    // test
    holderUnderTest.addNewInProgressItem("do shopping");
    holderUnderTest.addNewInProgressItem("go swimming");
    holderUnderTest.addNewInProgressItem("watch TV");
    assertEquals(3, holderUnderTest.getCurrentItems().size());
    assertEquals("watch TV", holderUnderTest.getCurrentItems().get(0).getDescription());
    assertEquals("go swimming", holderUnderTest.getCurrentItems().get(1).getDescription());
    assertEquals("do shopping", holderUnderTest.getCurrentItems().get(2).getDescription());

    holderUnderTest2.loadInstanceState(holderUnderTest.getCurrentItems());

    assertEquals("watch TV", holderUnderTest2.getCurrentItems().get(0).getDescription());
    assertEquals("go swimming", holderUnderTest2.getCurrentItems().get(1).getDescription());
    assertEquals("do shopping", holderUnderTest2.getCurrentItems().get(2).getDescription());
  }
}
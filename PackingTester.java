//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Suitcase Packing Tester
// Course:   CS 300 Spring 2024
//
// Author:   Karl Haidinyak
// Email:    haidinyak@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    N/A
// Partner Email:   N/A
// Partner Lecturer's Name: N/A
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:         N/A
// Online Sources:  N/A
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
/**
 * Class used for testing the methods in the Packing class.
 */
public class PackingTester {
  /**
   * Tester method for the Packing.rushedPacking() method base cases.
   * It should test at least the following scenarios:
   * - There are no items left to pack in the suitcase
   * - There are items left to pack, but none of them fit
   * @return true if all tests pass, false otherwise
   */
  public static boolean rushedPackingBaseTest() {
    ArrayList<Item> expectedList;

    {
      ArrayList<Item> items = new ArrayList<>();
      items.add(new Item("A", 7 ,4));
      items.add(new Item("B", 4, 5));

      Suitcase suitcase = new Suitcase(10, 10, items);
      suitcase.packItem(suitcase.getUnpackedItems().get(0));
      suitcase.packItem(suitcase.getUnpackedItems().get(1));
      suitcase = Packing.rushedPacking(suitcase);

      expectedList = new ArrayList<>();
      expectedList.add(new Item("A", 7, 4));
      expectedList.add(new Item("B", 4, 5));
      if (!suitcase.getPackedItems().equals(expectedList)) return false;
    }

    {
      ArrayList<Item> items = new ArrayList<>();
      items.add(new Item("A", 4, 2));
      items.add(new Item("B", 6, 3));
      items.add(new Item("C", 7, 4));
      items.add(new Item("D", 4, 5));
      items.add(new Item("E", 4, 5));
      items.add(new Item("F", 5, 4));
      items.add(new Item("G", 2, 6));

      Suitcase suitcase = new Suitcase(10, 10, items);
      suitcase = Packing.rushedPacking(suitcase);

      expectedList = new ArrayList<>();
      expectedList.add(new Item("A", 4, 2));
      expectedList.add(new Item("B", 6, 3));
      expectedList.add(new Item("C", 7, 4));
      expectedList.add(new Item("D", 4, 5));

      if (!suitcase.getPackedItems().equals(expectedList)) return false;
    }
    return true; // default return value
  }

  /**
   * Tester method for the Packing.rushedPacking() method recursive cases.
   * It should test at least the following scenarios:
   * - All the items remaining can fit in the suitcase
   * - At least one item remaining cannot fit in the suitcase
   * @return true if all tests pass, false otherwise
   */
  public static boolean rushedPackingRecursiveTest() {
    ArrayList<Item> expectedList;
    {
      ArrayList<Item> items = new ArrayList<>();
      items.add(new Item("A", 4, 2));
      items.add(new Item("B", 6, 3));
      items.add(new Item("C", 7, 4));
      items.add(new Item("D", 4, 5));

      Suitcase suitcase = new Suitcase(10, 10, items);
      suitcase = Packing.rushedPacking(suitcase);

      expectedList = new ArrayList<>();
      expectedList.add(new Item("A", 4, 2));
      expectedList.add(new Item("B", 6, 3));
      expectedList.add(new Item("C", 7, 4));
      expectedList.add(new Item("D", 4, 5));

      if (!suitcase.getPackedItems().equals(expectedList)) return false;
    }

    {
      ArrayList<Item> items = new ArrayList<>();
      items.add(new Item("A", 15, 15));
      items.add(new Item("B", 6, 3));
      items.add(new Item("C", 7, 4));
      items.add(new Item("D", 4, 5));
      items.add(new Item("E", 4, 2));
      items.add(new Item("F", 5, 4));
      items.add(new Item("G", 2, 6));

      Suitcase suitcase = new Suitcase(10, 10, items);
      suitcase = Packing.rushedPacking(suitcase);

      expectedList = new ArrayList<>();
      expectedList.add(new Item("B", 6, 3));
      expectedList.add(new Item("C", 7, 4));
      expectedList.add(new Item("E", 4, 2));
      expectedList.add(new Item("G", 2, 6));

      if (!suitcase.getPackedItems().equals(expectedList)) return false;

      return true; // default return value
    }
  }

  /**
   * Tester method for the Packing.greedyPacking() method base cases.
   * It should test at least the following scenarios:
   * - There are no items left to pack in the suitcase
   * - There are items left to pack, but none of them fit
   * @return true if all tests pass, false otherwise
   */
  public static boolean greedyPackingBaseTest() {
    ArrayList<Item> expectedList;

    {
      ArrayList<Item> items = new ArrayList<>();
      items.add(new Item("A", 4 ,5));
      items.add(new Item("B", 7, 4));

      Suitcase suitcase = new Suitcase(10, 10, items);
      suitcase.packItem(suitcase.getUnpackedItems().get(0));
      suitcase.packItem(suitcase.getUnpackedItems().get(1));
      suitcase = Packing.greedyPacking(suitcase);

      expectedList = new ArrayList<>();
      expectedList.add(new Item("B", 7, 4));
      expectedList.add(new Item("A", 4, 5));

      if (!suitcase.getPackedItems().equals(expectedList)) return false;

    }

    {
      ArrayList<Item> items = new ArrayList<>();
      items.add(new Item("A", 4, 2));
      items.add(new Item("B", 6, 3));
      items.add(new Item("C", 7, 4));
      items.add(new Item("D", 4, 5));
      items.add(new Item("E", 4, 5));
      items.add(new Item("F", 5, 4));
      items.add(new Item("G", 2, 6));

      Suitcase suitcase = new Suitcase(10, 10, items);
      suitcase = Packing.greedyPacking(suitcase);

      expectedList = new ArrayList<>();
      expectedList.add(new Item("C", 7, 4));
      expectedList.add(new Item("D", 4, 5));
      expectedList.add(new Item("E", 4, 5));
      expectedList.add(new Item("G", 2, 6));

      if (!suitcase.getPackedItems().equals(expectedList)) return false;
    }
    return true; // default return value
  }

  /**
   * Tester method for the Packing.greedyPacking() method recursive cases.
   * It should test at least the following scenarios:
   * - At least one item is packed out of order (an item with a higher index
   *   is packed before an item with a lower index)
   * - A scenario where the greedy packing method packs more of the suitcase
   *   than the rushed packing (you can use the example given in the writeup)
   * @return true if all tests pass, false otherwise
   */
  public static boolean greedyPackingRecursiveTest() {
    ArrayList<Item> actual;
    ArrayList<Item> expected;

    {
      ArrayList<Item> items = new ArrayList<>();
      items.add(new Item("A", 4, 2));
      items.add(new Item("B", 6, 3));
      items.add(new Item("C", 7, 4));
      items.add(new Item("D", 4, 5));
      items.add(new Item("E", 4, 5));
      items.add(new Item("F", 5, 4));
      items.add(new Item("G", 2, 6));

      Suitcase suitcase = new Suitcase(10, 10, items);
      actual = Packing.greedyPacking(suitcase).getPackedItems();
      expected = new ArrayList<>();
      expected.add(new Item("C", 7, 4));
      expected.add(new Item("D", 4, 5));
      expected.add(new Item("E", 4, 5));
      expected.add(new Item("G", 2, 6));

      if (!actual.equals(expected)) return false;
    }

    {
      ArrayList<Item> items = new ArrayList<>();
      items.add(new Item("A", 2, 2));
      items.add(new Item("B", 2, 2));
      items.add(new Item("C", 2, 2));

      Suitcase suitcase = new Suitcase(10, 10, items);
      suitcase = Packing.greedyPacking(suitcase);
      actual = suitcase.getPackedItems();
      expected = new ArrayList<>();
      expected.add(new Item("A", 2, 2));
      expected.add(new Item("B", 2, 2));
      expected.add(new Item("C", 2, 2));

      if (!actual.equals(expected)) return false;
    }
    return true; // default return value
  }

  /**
   * Tester method for the Packing.optimalPacking() method.
   * This tester should test the optimalPacking() method by
   * randomly generating at least TEN (10) different scenarios,
   * and randomly generating at least ONE-HUNDRED (100)
   * different packing solutions for EACH of the scenarios.
   * Each scenario should have at least FIVE (5) random items,
   * and the suitcases should be of size at least 5x5.
   * If any random solution is better than the optimal packing then
   * it is not actually optimal, so the method does not pass the test.
   * You should use the Utilities method to generate random lists of
   * items, and to randomly pack the suitcases.
   * @return true if all tests pass, false otherwise
   */
  public static boolean optimalPackingRandomTest() {
    ArrayList<Item> expectedList;

    {
      for (int i = 0; i < 15; ++i) {
        ArrayList<Item> randomItems = Utilities.randomItems(5);
        Suitcase randomSuitcase = new Suitcase(10, 10, randomItems);
        Suitcase optimallyPacked = Packing.optimalPacking(randomSuitcase);

        for (int j = 0; j < 200; ++j) {
          Suitcase randomlyPacked = Utilities.randomlyPack(randomSuitcase);
          if (optimallyPacked.areaPacked() < randomlyPacked.areaPacked()) return false;


        }
      }
    }

    {
      ArrayList<Item> items = new ArrayList<>();
      items.add(new Item("A", 4, 2));
      items.add(new Item("B", 6, 3));
      items.add(new Item("C", 7, 4));
      items.add(new Item("D", 4, 5));
      items.add(new Item("E", 4, 5));
      items.add(new Item("F", 5, 4));
      items.add(new Item("G", 2, 6));

      Suitcase suitcase = new Suitcase(10, 10, items);
      suitcase = Packing.optimalPacking(suitcase);

      expectedList = new ArrayList<>();
      expectedList.add(new Item("C", 7, 4));
      expectedList.add(new Item("D", 4, 5));
      expectedList.add(new Item("A", 4, 2));
      expectedList.add(new Item("F", 5, 4));
      expectedList.add(new Item("G", 2, 6));

      if (!suitcase.getPackedItems().equals(expectedList)) return false;
    }

    return true;
  }

  public static void main(String[] args) {
    boolean allPass = true;
    String printFormat = "%-29s %s\n";

    boolean rushedBase = rushedPackingBaseTest();
    allPass &= rushedBase;
    System.out.printf(printFormat, "rushedPackingBaseTest():", rushedBase);

    boolean rushedRecur = rushedPackingRecursiveTest();
    allPass &= rushedRecur;
    System.out.printf(printFormat, "rushedPackingRecursiveTest():", rushedRecur);

    boolean greedyBase = greedyPackingBaseTest();
    allPass &= greedyBase;
    System.out.printf(printFormat, "greedyPackingBaseTest():", greedyBase);

    boolean greedyRecur = greedyPackingRecursiveTest();
    allPass &= greedyRecur;
    System.out.printf(printFormat, "greedyPackingRecursiveTest():", greedyRecur);

    boolean optimalRandom = optimalPackingRandomTest();
    allPass &= optimalRandom;
    System.out.printf(printFormat, "optimalPackingRandomTest():", optimalRandom);

    System.out.printf(printFormat, "All tests:", allPass);
  }
}

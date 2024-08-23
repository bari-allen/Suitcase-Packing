//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Suitcase Packing
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
// Persons:         Ben Guthrie - Helped me understand how the optimal packing
//                                recursion worked
// Online Sources:  N/A
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;

/**
 * Class used for packing a 2D suitcase with items using various strategies.
 */
public class Packing {
    /**
     * Tries to pack each item in the items list in-order.
     * If an item can fit, it must be packed. Otherwise, it should be skipped.
     * Must be a recursive method.
     *
     * @param suitcase current Suitcase object
     * @return a Suitcase representing the outcome of a strategy in which the items
     * were attempted to be packed in-order.
     */
    public static Suitcase rushedPacking(Suitcase suitcase) {
        final int INDEX = 0;

        return rushedPackingHelper(suitcase, INDEX); // default return value
    }

    /**
     * Packs items by greedily packing the largest item which can currently be packed.
     * Must be a recursive method.
     *
     * @param suitcase current Suitcase object
     * @return a Suitcase representing the outcome of a greedy strategy in which at each
     * point the largest item that can fit is packed.
     */
    public static Suitcase greedyPacking(Suitcase suitcase) {
        ArrayList<Item> sortedItems = greedyPackingSort(suitcase.getUnpackedItems(),
                new ArrayList<Item>());
        suitcase = greedyHelper(suitcase, sortedItems, 0);

        return suitcase; // default return value
    }

    /**
     * Finds the optimal packing of items by trying all packing orders.
     * Must be a recursive method.
     *
     * @param suitcase current Suitcase
     * @return a Suitcase representing the optimal outcome.
     */
    public static Suitcase optimalPacking(Suitcase suitcase) {
        return optimalPackingHelper(suitcase, suitcase);

    }

    /**
     * Packs the suitcase using the rushed sorting algorithm described in the inline comments
     *
     * @param suitcase the current suitcase that is being read from
     * @param index the index of the getUnpackedItems() ArrayList that is being read from
     * @return the packed suitcase
     */
    private static Suitcase rushedPackingHelper(Suitcase suitcase, int index) {
        if (!(index >= suitcase.numItemsUnpacked())) {
            if (suitcase.canPackItem(suitcase.getUnpackedItems().get(index))) {
                suitcase = suitcase.packItem(suitcase.getUnpackedItems().get(index));
                //if the item can be packed, this will pack the item
                suitcase = rushedPackingHelper(suitcase, index);
                //calls the packingHelper method again with the same inputs
            } else {
                suitcase = rushedPackingHelper(suitcase, index + 1);
                //if the item cannot be packed, then it will call the packingHelper method
                // again but try the next unpacked item
            }
        }
        return suitcase; //the base case where all the items that can be packed are packed
    }

    /**
     * Sorts the item list from highest to lowest for the greedyPacking() method
     *
     * @param unsortedItems the ArrayList of all the unsorted items
     * @param sortedItems the ArrayList of all the sorted items sorted from
     *                    the highest area to lowest
     * @return the ArrayList of sorted items sorted from the highest area to lowest
     */
    private static ArrayList<Item> greedyPackingSort(ArrayList<Item> unsortedItems,
                                                     ArrayList<Item> sortedItems) {
        int maxArea = 0;
        int area = 0;
        Item maxItem = null;

        if (unsortedItems.isEmpty()) return sortedItems;

        for (Item unsortedItem : unsortedItems) {
            area = unsortedItem.height * unsortedItem.width;
            if (area > maxArea) {
                maxArea = area;
                maxItem = unsortedItem;
            }
        }

        sortedItems.add(maxItem);
        unsortedItems.remove(maxItem);
        greedyPackingSort(unsortedItems, sortedItems);

        return sortedItems;
    }

    /**
     * Packs the suitcase using the greedy sorting algorithm described in the inline comments
     *
     * @param suitcase the suitcase that needs to be packed
     * @param items the ArrayList of items sorted from the highest area to the lowest
     * @param index the index of the ArrayList that is being read from
     * @return the suitcase that has the items with the most area packed
     */
    private static Suitcase greedyHelper(Suitcase suitcase, ArrayList<Item> items, int index) {

        //Base case where the index is more than the number of unpacked items
        if (index >= suitcase.numItemsUnpacked()) return suitcase;

        if (suitcase.canPackItem(items.get(index))) {
            suitcase = suitcase.packItem(items.get(index)); //if the item can be packed it will be
            items.remove(index); //the item is removed from the sortedItems Array List
            suitcase = greedyHelper(suitcase, items, index);
            //the greedyHelper method is called with the same inputs
        } else {
            suitcase = greedyHelper(suitcase, items, index + 1);
            //if the item cannot be added, then the method will
            //skip the item and move to the next one
        }

        return suitcase;
        //Base case where all the items that can be packed are packed
    }

    /**
     * Packs the suitcase using the optimal sorting algorithm described in the inline comments
     *
     * @param optimalSuitcase the suitcase with the most area packed
     * @param currentSuitcase the suitcase that is being read from
     * @return the suitcase with the most area packed
     */
    private static Suitcase optimalPackingHelper(Suitcase optimalSuitcase,
                                                 Suitcase currentSuitcase) {
        boolean cannotPackItem = true;

        for (Item item : currentSuitcase.getUnpackedItems()) {
            if (currentSuitcase.canPackItem(item)) {
                cannotPackItem = false;
                Suitcase newCurrentSuitcase = currentSuitcase.packItem(item);
                //a new suitcase is made with the same packed and unpacked items, but the current
                //item is packed into the suitcase.
                optimalSuitcase = optimalPackingHelper(optimalSuitcase, newCurrentSuitcase);
                //the new suitcase is iterated until it cannot pack any more items
            }
        }

        //Base case where no more items can be backed into the suitcase, and the area packed
        //in the current suitcase is more than the area packed in the optimal suitcase
        if (cannotPackItem && currentSuitcase.areaPacked() > optimalSuitcase.areaPacked()) {
            return currentSuitcase;
        }

        //Base case where more items can be packed into the suitcase and/or the area packed in the
        //current suitcase is less than the area packed in the optimal suitcase
        return optimalSuitcase;
    }

}

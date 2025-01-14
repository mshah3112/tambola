package com.sahaj.ticketgenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TicketGenerator {

	static class Node {
		int A[][];

		public Node() {
			this.A = new int[3][9];
		}

		public int getRowCount(int r) {
			int count = 0;
			for (int i = 0; i < 9; i++) {
				if (A[r][i] != 0)
					count++;
			}

			return count;
		}

		public int getColCount(int c) {
			int count = 0;
			for (int i = 0; i < 3; i++) {
				if (A[i][c] != 0)
					count++;
			}

			return count;
		}

		// gives the row number of first found empty cell in given column
		public int getEmptyCellInCol(int c) {
			for (int i = 0; i < 3; i++) {
				if (A[i][c] == 0)
					return i;
			}

			return -1;
		}

		void sortColumnWithThreeNumbers(int c) throws Exception {
			int emptyCell = this.getEmptyCellInCol(c);
			if (emptyCell != -1) {
				throw new Exception("Hey! your column has <3 cells filled, invalid function called");
			}

			int tempArr[] = new int[] { this.A[0][c], this.A[1][c], this.A[2][c] };
			Arrays.sort(tempArr);

			for (int r = 0; r < 3; r++) {
				this.A[r][c] = tempArr[r];
			}
		}
		
		void sortColumnWithTwoNumbers(int c) throws Exception {
			int emptyCell = this.getEmptyCellInCol(c);
			if (emptyCell == -1) {
				throw new Exception("Hey! your column has 3 cells filled, invalid function called");
			}

			int cell1, cell2;
			if (emptyCell == 0) {
				cell1 = 1;
				cell2 = 2;
			} else if (emptyCell == 1) {
				cell1 = 0;
				cell2 = 2;
			} else { // emptyCell == 2
				cell1 = 0;
				cell2 = 1;
			}

			if (this.A[cell1][c] < this.A[cell2][c]) {
				return;
			} else {
				// swap
				int temp = this.A[cell1][c];
				this.A[cell1][c] = this.A[cell2][c];
				this.A[cell2][c] = temp;
			}
		}

		/*
		 * Three possible scenarios: 
		 * 1) only one number in the column - leave 
		 * 2) Two numbers in the column & not sorted - swap 
		 * 3) Three numbers in the column - sort
		 */
		void sortColumn(int c) throws Exception {
			if (this.getColCount(c) == 1) {
				return;
			}

			else if (this.getColCount(c) == 2) {
				this.sortColumnWithTwoNumbers(c);
			}

			else {
				this.sortColumnWithThreeNumbers(c);
			}
		}

		void sortColumns() throws Exception {
			for (int c = 0; c < 9; c++) {
				this.sortColumn(c);
			}
		}
	}

	static int getRand(int min, int max) {
		Random rand = new Random();
		return rand.nextInt(max - min + 1) + min;
	}

	static int getNumberOfElementsInSet(List<List<Integer>> set) {
		int count = 0;
		for (List<Integer> li : set)
			count += li.size();
		return count;
	}
}

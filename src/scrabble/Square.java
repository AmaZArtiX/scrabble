package scrabble;


import java.util.ArrayList;


public class Square {

	private Lettre lettre;
	private double[] coordinates;
	private int[] position;
	private int letterMult;
	private int wordMult;
	private Square top;
	private Square bottom;
	private Square left;
	private Square right;

	

	public Square(Lettre newLetter, double X, double Y, int R, int C) {
		lettre = newLetter;
		coordinates = new double[2];
		coordinates[0] = X;
		coordinates[1] = Y;
		position = new int[2];
		position[0] = R;
		position[1] = C;
		wordMult = 1;
		letterMult = 1;
	}

	public void setLetter(Lettre newTile) {
		lettre = newTile;
	}

	public void setLetterMult(int newLetterMult) {
		letterMult = newLetterMult;
	}

	public void setWordMult(int newWordMult) {
		wordMult = newWordMult;
	}

	public void addTop(Square square) {
		top = square;
	}

	public void addBottom(Square square) {
		bottom = square;
	}

	public void addLeft(Square square) {
		left = square;
	}

	public void addRight(Square square) {
		right = square;
	}

	public Square getTop() {
		return top;
	}

	public Square getBottom() {
		return bottom;
	}

	public Square getLeft() {
		return left;
	}

	public Square getRight() {
		return right;
	}

	/*
	 * Given a list of Squares, does this square have a neighbor not in the
	 * list?
	 */
	public boolean hasUniqueNeighbor(ArrayList<Square> word) {
		return getTop() != null && getTop().getLetter() != null
				&& !word.contains(getTop()) || getBottom() != null
				&& getBottom().getLetter() != null
				&& !word.contains(getBottom()) || getLeft() != null
				&& getLeft().getLetter() != null && !word.contains(getLeft())
				|| getRight() != null && getRight().getLetter() != null
				&& !word.contains(getRight());
	}

	public Lettre getLetter() {
		return lettre;
	}

	public int getLetterMult() {
		return letterMult;
	}

	public int getWordMult() {
		return wordMult;
	}

	public int[] getPosition() {
		return position;
	}

	
	

	public boolean isEmpty() {
		return lettre == null;
	}
	
	
}
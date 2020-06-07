package testMatrix;

public class TestMatrix {

	public static void main(String[] args) {
		Matrix test = new Matrix(new Matrix.RowCol(3,5));
		for (int n=0;n<16;n++) System.out.println(test.rotatedbyN(n));			
	}
}

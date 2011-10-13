/*
 * Clase para simular un paquete de n bits.
 */
public class Paquete {
	public static final int n = 256;
	private int bits[] = new int[n];

	public int[] getBits() {
		return bits;
	}

	@Override
	public String toString() {
		String s = "{";
		for (int i = 0; i < n - 1; i++) {
			s += bits[i] + ", ";
		}
		s += bits[n - 1];
		s += "}";
		return s;
	}

	public void inicializar() {
		int num = 0;
		for (int i = 0; i < n; i++) {
			if (Math.random() >= 0.5) {
				num = 1;
			} else {
				num = 0;
			}
			bits[i] = num;
		}
	}
}

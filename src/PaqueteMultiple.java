public class PaqueteMultiple {
	static final int n = 3;
	private Paquete paquetes[] = new Paquete[3];

	public PaqueteMultiple() {
		for(int i = 0 ; i<n ; i++){
			paquetes[i] = new Paquete();
		}
	}

	public void inicializar() {
		for (int i = 0; i < n; i++) {
			paquetes[i] = new Paquete();
			paquetes[i].inicializar();
		}
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < n; i++) {
			s += paquetes[i].toString();
			s += "\n";
		}
		return s;
	}

	public int[] getBits(int j) {
		return paquetes[j].getBits();
	}

	public int getRealBit(int j) {
		int sum = 0;
		for (int i = 0; i < n; i++) {
			sum += paquetes[i].getBits()[j];
		}
		if (sum >= (n/2)+1) {
			return 1;
		}
		return 0;
	}

}

public class PaqueteConChecksum {
	public static final int n = 264;
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
		int checkSum = 0;
		for (int i = 0; i < n - 8; i++) {
			if (Math.random() >= 0.5) {
				num = 1;
				checkSum++;
			} else {
				num = 0;
			}
			bits[i] = num;
		}
		String binario = Integer.toBinaryString(checkSum);
		for (int i = n - binario.length(); i < n; i++) {
			bits[i] = binario.charAt(i - n + binario.length()) - '0';
		}
	}

	public boolean tieneError() {
		int sum = 0;
		for (int i = 0; i < n - 8; i++) {
			sum += bits[i];
		}
		String s = "";
		int checkSum = 0;
		for (int i = n - 8; i < n; i++) {
			s += Integer.toString(bits[i]);
		}
		checkSum = Integer.parseInt(s, 2);
		if (sum == checkSum) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof PaqueteConChecksum)){
			return false;
		}
		PaqueteConChecksum paquete = (PaqueteConChecksum)obj;
		for(int i = 0; i <n ; i++){
			if(	paquete.getBits()[i] != bits[i]){
				return false;
			}
		}
		return true;
	}
}

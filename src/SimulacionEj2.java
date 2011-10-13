public class SimulacionEj2 {
	private static final int cantPaquetes = 1000;

	public static void main(String[] args) {
		/*
		 * Creacion de paquetes
		 */
		Paquete paquetes[] = new Paquete[cantPaquetes];
		for (int i = 0; i < cantPaquetes; i++) {
			paquetes[i] = new Paquete();
			paquetes[i].inicializar();
		}
		/*
		 * Envio de paquetes
		 */
		PaqueteMultiple paquetesRecividos[] = new PaqueteMultiple[cantPaquetes];
		for (int i = 0; i < cantPaquetes; i++) {
			paquetesRecividos[i] = enviar(paquetes[i]);
		}

		/*
		 * Creacion de los paquetes con los multipaquetes recibidos
		 */
		Paquete paquetesR[] = new Paquete[cantPaquetes];
		for (int i = 0; i < cantPaquetes; i++) {
			paquetesR[i] = new Paquete();
			for (int j = 0; j < Paquete.n; j++) {
				paquetesR[i].getBits()[j] = paquetesRecividos[i].getRealBit(j);
			}
		}

		/*
		 * Control de errores
		 */
		int[] cantErrores = new int[Paquete.n];
		int countErrores;
		for (int i = 0; i < cantPaquetes; i++) {
			countErrores = 0;
			for (int j = 0; j < Paquete.n; j++) {
				if (paquetes[i].getBits()[j] != paquetesR[i].getBits()[j]) {
					countErrores++;
				}
			}
			cantErrores[countErrores]++;
		}

		/*
		 * Impresion de un vector con la cantidad de errores. En la posicion 0
		 * la cantidad de paquetes con cero errores, en la posicion 1 la
		 * cantidad de paquetes con 1 error, ...
		 */
		System.out.print("{");
		for (int i = 0; i < Paquete.n - 1; i++) {
			System.out.print(cantErrores[i] + ", ");
		}
		System.out.print(cantErrores[Paquete.n - 1]);
		System.out.print("}");
	}

	/*
	 * Mensaje que modifica un paquete segun el valor obtenido por Math.random()
	 */
	private static PaqueteMultiple enviar(Paquete paquete) {
		PaqueteMultiple resp = new PaqueteMultiple();
		double rand;
			for (int i = 0; i < Paquete.n; i++) {
				for (int j = 0; j < PaqueteMultiple.n; j++) {
				rand = Math.random();
				if ((paquete.getBits()[i] == 0 && rand >= 0.96)
						|| (paquete.getBits()[i] == 1 && rand < 0.94)) {
					resp.getBits(j)[i] = 1;
				} else {
					resp.getBits(j)[i] = 0;
				}
			}
		}
		return resp;
	}
}

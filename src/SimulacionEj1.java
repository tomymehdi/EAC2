public class SimulacionEj1 {
	
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
		Paquete paquetesRecividos[] = new Paquete[cantPaquetes];
		for (int i = 0; i < cantPaquetes; i++) {
			paquetesRecividos[i] = enviar(paquetes[i]);
		}

		/*
		 * Control de errores
		 */
		int[] cantErrores = new int[Paquete.n];
		int countErrores;
		for (int i = 0; i < cantPaquetes; i++) {
			countErrores = 0;
			for (int j = 0; j < Paquete.n; j++) {
				if (paquetes[i].getBits()[j] != paquetesRecividos[i].getBits()[j]) {
					countErrores++;
				}
			}
			cantErrores[countErrores]++;
		}
		
		/*
		 * Impresion de un vector con la cantidad de errores. En la posicion 0 la cantidad 
		 * de paquetes con cero errores, en la posicion 1 la cantidad de paquetes con 1 error, ...
		 */
		System.out.print("{");
		for (int i = 0; i < Paquete.n-1; i++) {
			System.out.print(cantErrores[i] + ", ");
		}
		System.out.print(cantErrores[Paquete.n-1]);
		System.out.print("}");
	}

	
	/*
	 * Mensaje que modifica un paquete segun el valor obtenido por Math.random()
	 */
	private static Paquete enviar(Paquete paquete) {
		Paquete resp = new Paquete();
		double rand;
		for (int i = 0; i < Paquete.n; i++) {
			rand = Math.random();
			if ((paquete.getBits()[i] == 0 && rand >= 0.96)
					|| (paquete.getBits()[i] == 1 && rand < 0.94)) {
				resp.getBits()[i] = 1;
			} else {
				resp.getBits()[i] = 0;
			}
		}
		return resp;
	}
}

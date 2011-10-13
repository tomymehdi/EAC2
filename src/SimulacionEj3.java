public class SimulacionEj3 {

	private static final int cantPaquetes = 1000;

	public static void main(String[] args) {
		/*
		 * Creacion de paquetes
		 */
		PaqueteConChecksum paquetes[] = new PaqueteConChecksum[cantPaquetes];
		for (int i = 0; i < cantPaquetes; i++) {
			paquetes[i] = new PaqueteConChecksum();
			paquetes[i].inicializar();
		}
		/*
		 * Envio de paquetes
		 */
		PaqueteConChecksum paquetesRecividos[] = new PaqueteConChecksum[cantPaquetes];
		for (int i = 0; i < cantPaquetes; i++) {
			paquetesRecividos[i] = enviar(paquetes[i]);
		}

		/*
		 * Control de errores
		 */
		int cantErroresEnLos1000Paquetes = 0;
		for (int i = 0; i < cantPaquetes; i++) {
			if (paquetesRecividos[i].tieneError()) {
				cantErroresEnLos1000Paquetes++;
			}
		}
		int cantPaquetesErroneosPeroCorrectosPorElChecksum = 0;
		for (int i = 0; i < cantPaquetes; i++) {
			if (!paquetes[i].equals(paquetesRecividos)) {
				cantPaquetesErroneosPeroCorrectosPorElChecksum++;
			}
		}

		/*
		 * Impresion de la cantidad de paquetes que tienene errores segun el checksum
		 */
		System.out
				.println("Cantidad de paquetes erroneos de los 1000 segun el checksum: "
						+ cantErroresEnLos1000Paquetes);
		/*
		 * Impresion de la cantidad de paquetes que tienen cero errores al ser recividos
		 */
		System.out.println("Cantidad de paquetes realmente erroneos: "
				+ cantPaquetesErroneosPeroCorrectosPorElChecksum);
	}

	/*
	 * Mensaje que modifica un paquete segun el valor obtenido por Math.random()
	 */
	private static PaqueteConChecksum enviar(PaqueteConChecksum paquete) {
		PaqueteConChecksum resp = new PaqueteConChecksum();
		double rand;
		for (int i = 0; i < PaqueteConChecksum.n; i++) {
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

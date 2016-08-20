import java.util.Random;

public class Main {

	public static void main(String[] args) {
		Integer tiempoSimulacion = 600000;
		
		Long stll = 0L; //Sumatoria tiempo llegada
		Long sts = 0L; //Sumatoria tiempo salida
		Long cll = 0L;
		Long sto = 0L; //Sumatoria tiempo oscioso
		Long ito = 0L; //Inicio tiempo oscioso
		Long t = 0L;
		
		Integer ns = 0;
		
		Long tpll = 0L; //tiempo de llegada
		Long tps = Long.MAX_VALUE; //tiempo de salida
		
		Integer ia = null; //intervalo entre arrivos
		Integer ta = null; //tiempo atencion
		
		Long pto;
		Long pps;
		
		while(t < tiempoSimulacion) {
			if(tpll <= tps) {
				t = tpll;
				ia = new Random().nextInt(16) + 10;
				tpll = t + ia;
				ns = ns + 1;
				stll += t;
				cll += 1;
				
				if(ns.equals(1)) {
					ta = getTiempoAtencion();
					tps = t + ta;
					sto += (t-ito);
				}
			} else {
				t = tps;
				ns = ns-1;
				sts += t;
				
				if(ns > 0) {
					ta = getTiempoAtencion();
					tps = t + ta;
				} else {
					ito = t;
					tps = Long.MAX_VALUE;
				}
			}
			
		}
		
		pps = (sts-stll)/cll;
		pto = sto*100/t;
		
		System.out.println("PPS: " + pps);
		System.out.println("STO: " + sto + ", PTO: " + pto);
		System.out.println("NS: " + ns);

	}

	
	private static Integer getTiempoAtencion() {
		return new Random().nextInt(36) + 5;
	}
}
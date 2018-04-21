package com.dannypiper.tt;

public class tiltedtowersmain {
	public static float gfs = 9.8f;
	//GravitationalFieldStrength
	public static int atmosphereHeight = 100000;
	//in m
	public static long jioktoTNT = 4184000000l;
	//joulesInKiloTonOfTNT
	public static float diameter = 4.8f;//diameter of the comet
	public static long mass = 200000; //in kg
	
	//20 kilotons of TNT = 330m wide crater - the trinity bomb was 20 kilotons and made a 330 m crater
	public static float getCraterSize(long Joules) {
		float kilotons = Joules / jioktoTNT;	
		return kilotons;
	}
	
	public static long calculateGPE(long height, float vel) {
		//gpe = mass * gfs * height
		long gpe =  (long) (mass * gfs * height);
		
		float p = 1.225f; //air density
		float vsquared = vel * vel;
		float cd = 0.12f;
		float pi = 3.141592643f;
		float a = pi * diameter;
				
		long drag = (long) ((p*vsquared*cd*a) / 2 * mass);
	
		//Reference https://www.physicsforums.com/threads/deceleration-of-a-projectile-with-air-resistance.696022/
		return gpe - drag;

	}
	
	public static void main (String args[]) {
		System.out.println("Running...\n");
		System.out.println("Game theory's calculation is wrong as it assumes that the comet does not accelerate in the atmosphere.\n");
		System.out.println("This also makes an estimate for drag to provide a more accurate result. I'd like to add this is merely an estimate!");
		for (int i = 1; i < atmosphereHeight*1000 ; i++) {
			float t = (float) Math.sqrt(0.5*gfs);
			float v = (float) (0.5*gfs* t * gfs* t * t);
			if(getCraterSize(calculateGPE(i, v)) >=20) {
				System.out.println("\n\nTilted would be destroyed if the comet is dropped from "+i+" m above it.\n\n"
								+ "\nThe atmosphere is ~"+atmosphereHeight+" m high so if the comet falls from"
								+ "\nthe top of the atmosphere it will reach the required force to destroy tilted "
								+ "\ntowers "+i+" m into its fall. Impact Velocity was: "+v+"m/s");
				System.exit(0);
			}
		}
		System.out.println("Tilted shan't die.");
	}
}

package cn.core.algorithm;

public class UtilsPixel {

	public static double getDistence(int RGB1, int RGB2){
		int pr = (RGB1>> 16) & 0xff;
		int pg = (RGB1 >> 8) & 0xff;
		int pb = RGB1 & 0xff;
		
		int cr = (RGB2 >> 16) & 0xff;
		int cg = (RGB2 >> 8) & 0xff;
		int cb = (RGB2 & 0xff);
		
		return Math.sqrt(Math.pow((pr-cr), 2.0)+Math.pow((pg-cg), 2.0)+Math.pow((pb-cb), 2.0));
	}
}

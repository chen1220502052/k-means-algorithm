package cn.core.algorithm;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 对kmeans算法中的K，以及聚类中心进行初始化，进而优化kmeans算法	
 * @author cqk
 *
 */
public class InitKMeans {

	private double[] distences;//每一个个体之间的距离
	
	private wrabDC[] countDc;//每一个个体的局部密度
	
	
	private double[] pi;
	
	private int step = 500;//为了实验结果，要自己调step
	private double dc=100;//指定一个数,为了试验效果，要自己调
	
	private int k;//通过算法优化有k个聚类中心
	
	private ArrayList<ClassCenter> centerList = new ArrayList<ClassCenter>();//通过算法优化的聚类中心点
	
	private void initDic(int[] pixel){
		

		
		int len = pixel.length/step;
		distences = new double [len*(len+1)];
		countDc = new wrabDC [len];
		for(int i = 0; i < len; i++){
			countDc[i] = new wrabDC();
		}

//		int k = 0;
		for(int i = 0; i < pixel.length; i+=step){
			for(int j = i+step; j < pixel.length; j+=step){
				int k1 = (i * len + j)/step;
//				int k2 = (j * len + i)/step;
				distences[k1] = UtilsPixel.getDistence(pixel[i], pixel[j]);
				if(distences[k1] < dc){
					int dcI = i/step;
					int count = countDc[dcI].count;
					countDc[dcI].count = count+1;
					countDc[dcI].index = i;
				}
//				k++;
			}
		}
	}
	
	private void initPI(int width, int len){
		
		Arrays.sort(countDc);
		
		pi = new double [countDc.length];
		
	
		for(int i = 0; i < countDc.length; i++){
			double npi = -1;
			int n = countDc[i].index; 
			int k = -1;
			if( i == 0){
				for(int j = 1; j < countDc.length; j++){
					int m = countDc[j].index;
					if(n > m){
						k = (m * len + n)/step;
					}else{
						k = (n * len + m)/step;
					}
					if(distences[k]>npi)
						npi = distences[k];
				}
			}else{
				npi = Double.MAX_VALUE;
				for(int j = 0; j < i; j++){
					int m = countDc[j].index;
					if(n > m){
						k = (m * len + n)/step;
					}else{
						k = (n * len + m)/step;
					}
					if(distences[k] < npi){
						npi = distences[k];
					}
						
				}
			}
			
			pi[n/step] = npi;
		}
	}
	
	
	public void initAll(int[] pixel, int width, int height){
		initDic(pixel);
		initPI(width, pixel.length/step);
		
		RDP[] rdps = new RDP [countDc.length];
		for(int i = 0; i < countDc.length; i++ ){
			rdps[i] = new RDP();
		}
		for(int i = 0; i < countDc.length; i++){
			int index = countDc[i].index;
			rdps[i].index = index;
			rdps[i].r = countDc[i].count * pi[index/step];
		}
		
		Arrays.sort(rdps);
		
		//当相邻点的差值变化比较大时为聚类中心点
		
		double maxD = 0;
		this.k = 0;
		for(int i = 0; i < rdps.length-1; i++){
			double r = rdps[i].r;
			if(r < 5000){
				k = i;
				break;
			}
		}
		
		for(int i = 0; i < k; i++){

			int x = rdps[i].index/width;
			int y = rdps[i].index%width;
			int rgbData = pixel[rdps[i].index];
			ClassCenter cc = new ClassCenter(x, y, rgbData);
			cc.setPixels(pixel);
			centerList.add(cc);
		}
		
		
	}
	


	public ArrayList<ClassCenter> getCenterList() {
		return centerList;
	}

	public void setCenterList(ArrayList<ClassCenter> centerList) {
		this.centerList = centerList;
	}

	public int getK() {
		return k;
	}

	public void setK(int k) {
		this.k = k;
	}
	
	private class wrabDC implements Comparable<wrabDC>{
		int count = 0;
		int index;
		public wrabDC(){
			this.count = 0;
		}
		
		@Override
		public int compareTo(wrabDC o) {
			// TODO Auto-generated method stub
			
			if(count > o.count){
				return -1;
			}else if(count < o.count)
				return 1;
			
			return 0;
		}
	}
	
	private class RDP implements Comparable<RDP>{
		
		double r;//r = pi * dc
		int index;//pixel 索引值
		
		@Override
		public int compareTo(RDP o) {
			// TODO Auto-generated method stub
			if(r > o.r)
				return -1;
			else if(r < o.r)
				return 1;
			return 0;
		}
		
	}
}

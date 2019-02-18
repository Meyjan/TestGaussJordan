package CodeTubes;

import java.util.*;

public class Soal2_2 {
	
	public Soal2_2()
	{
		int i;
		int N;
		int tester;
		double [] listx;
		double [] listy;
		String [] liststring;
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Soal 2-1 : Menyelesaikan Interpolasi dengan Gauss");
		
		System.out.print("Masukkan nilai N (jumlah titik - 1) : ");
		N = input.nextInt ();
		
		listx = new double[N+1];
		listy = new double[N+1];
		liststring = new String [N+1];
		
		for (i = 0; i <= N; i++)
		{
			System.out.print("Masukkan nilai (x"+ i + ",y" + i + ") : ");
			if (i == 0) {
				input.nextLine();
				liststring[i] = input.nextLine();
			}
			else {
				liststring[i] = input.nextLine();
			}
			SplitString (liststring[i], ",", listx, listy,i);
		}
		
		MatrixClass M = new MatrixClass((N+1),(N+1));
		CreateMatrix (M, listx, listy);
		
		System.out.println();
		M.TulisMatriks();
		System.out.println();
		
		System.out.println("Applicating Gauss-Jordan");
		M.Gauss();
		M.Jordan();
		M.TulisMatriks();
		
		if (M.IsSolusiAda())
		{
			M.PrintConsoleInterpolasi();
			System.out.println("Ada 4 kali percobaan taksiran nilai y dari x yang diberikan");
			for (int a = 1; a <= 4; a++)
			{
				System.out.println("Tuliskan nilai x pada sebuah fungsi ini yang ingin Anda ketahui");
				tester = input.nextInt();
				System.out.println("Hasilnya adalah : " + M.NilaiFungsi(tester));
			}
		}
		else
		{
			System.out.println("Solusi tidak ada");
		}
	}
	
	void CreateMatrix (MatrixClass M, double[] listx, double[] listy)
	{
		int i,j;
		
		for (i = 0; i < M.Brs; i++)
		{
			for (j = 0; j < (M.Kol - 1); j++)
			{
				M.Mat [i][j] = Math.pow(listx[i],j);
			}
		}
		
		for (i = 0; i < M.Brs; i++)
		{
			M.Mat[i][M.Kol-1] = listy[i];
		}
	}
	
	public static void SplitString (String str, String delimiter, double[] listx, double[] listy, int i)
	{
		System.out.println ("String = "+str);
		String[] temp;
		temp = str.split(delimiter);
		listx[i] = Double.parseDouble (temp[0]);
		listy[i] = Double.parseDouble (temp[1]);
		System.out.println(listx[i]);
		System.out.println(listy[i]);
	}

}

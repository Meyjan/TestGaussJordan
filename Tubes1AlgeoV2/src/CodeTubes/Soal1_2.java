package CodeTubes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Soal1_2 {
	
	public Soal1_2()
	{
		int a, b;
		Scanner input = new Scanner(System.in);
		
		System.out.println("Soal 1-2 : Menyelesaikan SPL dengan Gauss-Jordan");
		
		System.out.print("Masukkan nilai baris (M) : ");
		a = input.nextInt ();
		System.out.print("Masukkan nilai kolom (N) : ");
		b = input.nextInt ();
		
		MatrixClass M = new MatrixClass(a,b);
		
		System.out.println("Membaca matriks...");
		BacaMatriks1(M);
		
		System.out.println("Membaca matriks2...");
		BacaMatriks2(M);
		
		System.out.println ("Menulis matriks augmented...");
		M.TulisMatriks();
		System.out.println();
		
		System.out.println("Gauss...Gauss...dan Jordan...");
		M.Gauss();
		M.Jordan();
		System.out.println();
		M.TulisMatriks();
		System.out.println();
		M.VariableType();
		if (M.isSolusi == true)
		{
			M.PrintConsoleParametrik();
		}
		
		// Menulis ke file
		String enter = System.getProperty("line.separator");
		String namaFileOut = "E:\\ContohOut.txt";
		
		for (int i=0; i< M.Brs; i++)
        {
        	for (int j=0; j<M.Kol; j++)
        	{
        		tulisFile(M.Mat[i][j]+"		", namaFileOut);
        	}
    		
        	tulisFile(enter, namaFileOut);
        }
		
	}
	
	void BacaMatriks1 (MatrixClass M)
	{
		int i,j;
		Scanner input = new Scanner (System.in);
		
		for (i = 0; i < M.Brs; i++)
		{
			for (j = 0; j < (M.Kol - 1); j++)
			{
				System.out.print("Masukkan elemen matriks [" + i + "][" + j + "]!");
				M.Mat [i][j] = input.nextDouble();
			}
		}
	}
	
	void BacaMatriks2 (MatrixClass M)
	{
		int i;
		Scanner input = new Scanner (System.in);
		
		for (i = 0; i < M.Brs; i++)
		{
			System.out.print("Masukkan elemen matriks2 [" + i + "]!");
			M.Mat [i][M.Kol - 1] = input.nextDouble();
		}
	}
	
	// MENULIS FILE
	
	void tulisFile(String teks, String namaFile)
	{
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
                    new FileWriter(namaFile, true)));
            out.print(teks);
            out.close(); 
        } catch (IOException e) {
            System.out.print("Gagal menulis ke file " + namaFile);
            e.printStackTrace();
        }
	}
}
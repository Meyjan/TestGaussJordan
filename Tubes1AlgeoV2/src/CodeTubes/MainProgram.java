package CodeTubes;

import java.util.*;

public class MainProgram {
	
	public static void main(String[] args)
	{
		// Menjalankan opsi dan menagatur file yang akan diakses
		int option;
		int option2;
		int option3;
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("------------------------------------------");
		System.out.println("-----------------TUBES ALGEO--------------");
		System.out.println("------------------------------------------");
		System.out.println();
		
		System.out.println("MENU");
		System.out.println("1. Sistem Persamaan Linier");
		System.out.println("2. Interpolasi Polinom");
		System.out.println("3. Keluar");
		
		option = input.nextInt();
		System.out.println();
		
		
		
		while (option != 3)
		{
			if (option == 1)
			{;
				System.out.println("MENU2");
				System.out.println("1. Metode eliminasi Gauss");
				System.out.println("2. Metode eliminasi Gauss-Jordan");
				
				option2 = input.nextInt();
				System.out.println();
				
				if (option2 == 1)
				{
					System.out.println("MENU3");
					System.out.println("1. Input dari Console");
					System.out.println("2. Input dari File");
					
					option3 = input.nextInt();
					System.out.println();
					
					if (option3 == 1)
					{
						Soal1_1 soal1a = new Soal1_1();
					}
					else if (option3 == 2)
					{
						Soal1_3 soal1c = new Soal1_3();
					}
					else
					{
						System.out.println("Input tidak valid");
						System.out.println();
					}
				}
				else if (option2 == 2)
				{
					System.out.println("MENU3");
					System.out.println("1. Input dari Console");
					System.out.println("2. Input dari File");
					
					option3 = input.nextInt();
					System.out.println();
					
					if (option3 == 1)
					{
						Soal1_2 soal1b = new Soal1_2();
					}
					else if (option3 == 2)
					{
						Soal1_4 soal1d = new Soal1_4();
					}
					else
					{
						System.out.println("Input tidak valid");
						System.out.println();
					}
				}
				else
				{
					System.out.println("Input tidak valid...");
				}
			}
			else if (option == 2)
			{
				System.out.println("1. Metode eliminasi Gauss");
				System.out.println("2. Metode eliminasi Gauss-Jordan");
				
				option2 = input.nextInt();
				
				if (option2 == 1)
				{
					System.out.println("MENU3");
					System.out.println("1. Input dari Console");
					System.out.println("2. Input dari File");
					
					option3 = input.nextInt();
					System.out.println();
					
					if (option3 == 1)
					{
						Soal2_1 soal2a = new Soal2_1 ();
					}
					else if (option3 == 2)
					{
						Soal2_3 soal2c = new Soal2_3 ();
					}
					else
					{
						System.out.println("Input tidak valid");
						System.out.println();
					}
				}
				else if (option2 == 2)
				{
					System.out.println("MENU3");
					System.out.println("1. Input dari Console");
					System.out.println("2. Input dari File");
					
					option3 = input.nextInt();
					System.out.println();
					
					if (option3 == 1)
					{
						Soal2_2 soal2b = new Soal2_2();
					}
					else if (option3 == 2)
					{
						Soal2_4 soal2d = new Soal2_4();
					}
					else
					{
						System.out.println("Input tidak valid");
						System.out.println();
					}
				}
				else
				{
					System.out.println("Input tidak valid...\n");
				}
			}
			else
			{
				System.out.println("Input yang dimasukkan tidak benar\n");
			}
			
			System.out.println();
			System.out.println();
			
			System.out.println("MENU");
			System.out.println("1. Sistem Persamaan Linier");
			System.out.println("2. Interpolasi Polinom");
			System.out.println("3. Keluar");
			System.out.println();
			
			option = input.nextInt();
		}
		input.close();
		System.out.println("KELUAR...\n");
	}
}

package CodeTubes;

public class MatrixClass {
	
	// MATRIKS
	public double [][] Mat;
	public int Brs;
	public int Kol;
	
	// PARAMETER
	public String [] solusi;
	public boolean isSolusi;
	public int [] vartype;
	public double [][] nilai;
	
	// INTERPOLASI
	public double [] nilai2;
	public String solusi2;
	
	// KONSTRUKTOR
	public MatrixClass(int N, int M)
	{	
		int i,j;
		Mat = new double [N][M + 1];
		
		Brs = N;
		Kol = M + 1;
		
		for (i = 1; i < N; i++)
		{
			for (j = 1; j <= M; j++)
			{
				Mat[i][j] = 0;
			}
		}
	}
	
	
	//TULIS MATRIKS & SOLUSI
	public void TulisMatriks ()
	// Menuliskan matriks ke layar dengan ukuran Brs * Kol
	{
		int i,j;
		
		for (i = 0; i < this.Brs; i++)
		{
			for (j = 0; j < this.Kol; j++)
			{
				if (this.Mat[i][j] == -0.0)
				{
					this.Mat[i][j] = 0.0;
				}
				System.out.print(this.Mat[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	
	// HELPER
	void tukar (int row1, int row2)
	// Menukar matriks pada baris row1 dengan baris row2
	{
		double temp[] = this.Mat[row1];
		this.Mat[row1] = this.Mat[row2];
		this.Mat[row2] = temp;
	}
	
	boolean IsBaris0 (int i)
	// Mengembalikan nilai true jika baris i tidak memiliki angka selain 0
	// sepanjang barisnya
	{
		boolean is0 = true;
		for (int j = 0; j < (this.Kol - 1); j++)
		{
			if (this.Mat[i][j] != 0)
			{
				is0 = false;
				break;
			}
		}
		return (is0);
	}
	
	int Marker (int i)
	// Mencari indeks mark dari suatu baris
	// Indeks mark adalah indeks pertama dari suatu baris yang tidak bernilai 0
	{
		int j = 0;
		for (; j < (this.Kol - 1); j++)
		{
			if (this.Mat[i][j] != 0)
			{
				break;
			}
		}
		return (j);
	}
	
	void DivideRow (int marki, int markj)
	// Membagi suatu baris dengan suatu elemen di baris tersebut
	{
		double divisor = this.Mat[marki][markj];
		for (int j = 0; j < this.Kol; j++)
		{
			this.Mat[marki][j] /= divisor;
		}
	}
	
	void SubstractRow (int marki, int markj, int i)
	// Mengurangi suatu baris (i) dengan baris lain yang lebih teratur (marki)
	// Pengali adalah nilai dari baris i pada kolom markj
	{
		double multiplier = this.Mat[i][markj];
		for (int j = 0; j < this.Kol; j++)
		{
			this.Mat[i][j] -= this.Mat[marki][j] * multiplier;
		}
	}
	
	
	// GAUSS & GAUSSJORDAN
	public void Gauss ()
	// Melakukan operasi Gauss pada matriks
	
	/* Tahap-tahap yang dilakukan oleh operasi Gauss 
	 * 
	 * Memastikan bahwa posisi markj tidak sampai ke (this.Kol - 1)
	 * karena berisi matriks B (yaitu matriks ix1 pada sisi paling kanan
	 * dari matriks augmented
	 * 
	 * Memastikan nilai Mat[marki][markj] pada kolom markj tidak 0
	 * Jika baris pertama memiliki nilai 0 pada kolom markj, maka tukar dengan baris lain
	 * Jika tidak ada baris sepanjang markj yang memiliki nilai 0. Tambah nilai markj dengan 1
	 * 
	 * Membagi nilai pada suatu baris sehingga nilai pada kolom markj baris tersebut 1
	 * Kolom markj berisi nilai pertama pada suatu baris yang tidak 0
	 * 
	 * Mengurangi baris-baris di bawahnya pada kolom yang sama dengan baris marki*/
	{
		
		int markj = 0;
		for (int marki = 0; marki < this.Brs; marki++)
		{
			// Cek markj
			if (markj > (this.Kol - 2))
			{
				break;
			}
			
			// Inisialisasi
			int i = marki;
			
			// Mencari baris yang tidak 0 pada this.Kolom markj
			while (this.Mat[i][markj] == 0)
			{
				i++;
				if 	(this.Brs == i)
				{
					i = marki;
					markj += 1;
					// Cek markj
					if (markj == (this.Kol - 1))
					{
						break;
					}
				}
			}
			
			//Cek markj tidak sampai matriks B
			if (markj == (this.Kol - 1))
			{
				break;
			}
			
			// Tukar baris dengan baris yang memiliki markj tidak 0
			// Jika baris itu sendiri, maka tidak akan berubah apa-apa
			tukar (i,marki);
			
			// Membagi baris sehingga koefisien pada mark menjadi 1
			if (this.Mat[marki][markj] != 0)
			{
				this.DivideRow (marki, markj);
			}
			
			// Menguarngi baris-baris di bawahnya
			for (i = (marki + 1); i < this.Brs; i++)
			{
				this.SubstractRow (marki, markj, i);
			}
		}
		markj++;
	}
	
	public void Jordan ()
	// Melakukan operasi Jordan pada matriks
	// PREKONSDISI : Matriks telah di-gauss
	
	/* Tahap yang dilakukan untuk operasi ini
	 * 
	 * Menemukan marker mulai dari baris paling bawah
	 * Keterangan : Karena Gauss, maka Marker sudah bernilai 1
	 * 
	 * Mengurangi setiap baris di atasnya dengan baris marki sehingga menjadi 0*/
	{
		for (int marki = (this.Brs - 1); marki > 0; marki--)
		{
			if (!this.IsBaris0 (marki))
			{
				// Set kolom marker
				int markj = Marker (marki);
				
				// Mengurangi setiap baris di atasnya
				for (int i = 0; i < marki; i++)
				{
					SubstractRow (marki, markj, i);
				}
			}
		}
	}
	
	
	// PARAMETRIK
	public void VariableType ()
	{
		// Menentukan apakah terdapat solusi dari matriks atau tidak
		// Menentukan tipe variabel yang teradapat dalam matriks dengan ketentuan :
		// 1 -> Variabel memiliki nilai pasti (Contoh : 2.00)
		// 2 -> Variabel memiliki nilai terikat variabel lain (Contoh : 2a - 3.00)
		// 3 -> Variabel memiliki nilai bebas (Contoh : a)
		
		// Inisiasi nilai pada class matrix
		vartype = new int[this.Kol - 1];
		isSolusi = true;
		
		// Set nilai awal variabel sebagai bebas
		for (int i = 0; i <= (this.Kol - 2); i++)
		{
			vartype[i] = 3;
		}
		
		// Atur nilai varibabel
		for (int i = (this.Brs - 1); i >= 0; i--)
		{
			// Cek matriks tidak memiliki solusi
			// Syarat : matriks tersebut memiliki IsBaris0(i) tapi nilai MatriksB
			// pada baris i tidak memiliki nilai 0
			if (this.IsBaris0(i) && (this.Mat[i][Kol - 1] != 0))
			{
				this.isSolusi = false;
				break;
			}
			else if (!this.IsBaris0(i))
			{
				int markj = Marker (i);
				// Cek apakah kanan kosong atau memiliki nilai terikat
				for (int j = (markj + 1); j <= (this.Kol - 2); j++)
				{
					if (this.Mat[i][j] != 0 && this.vartype[j] != 1)
					{
						this.vartype[markj] = 2;
						break;
					}
				}
				// Jika kanan kosong, maka elemen ini memiliki nilai pasti
				if (this.vartype[markj] == 3)
				{
					this.vartype[markj] = 1;
				}
			}
		}
		
		// Jika terdapat solusi
		if (isSolusi)
		{
			System.out.println("Terdapat solusi");
			/*for (int i = 0; i <= (Kol - 2); i++)
			{
				System.out.println("Vartype " + i + " = " + this.vartype[i]);
			}*/
		}
		else
		{
			System.out.println("Tidak terdapat solusi");
		}
	}
	
	public void Parametrik()
	{
		// PRASYARAT : Terdapat solusi dari matriks
		
		nilai = new double[this.Kol][this.Kol - 1];
		
		// INISIASI NILAI ARRAY
		for (int i = 0; i < this.Kol; i++)
		{
			for (int j = 0; j <= (this.Kol - 2); j++)
			{
				nilai[i][j] = 0;
			}
		}
		
		for (int i = (this.Brs - 1); i >= 0; i--)
		{
			if (!IsBaris0(i))
			{
				int markj = Marker(i);
				this.nilai[this.Kol - 1][markj] = this.Mat[i][this.Kol - 1];
				for (int j = markj + 1; j <= (this.Kol - 2); j++)
				{
					if (this.vartype[j] == 1)
					{
						this.nilai[this.Kol - 1][markj] -= this.nilai[this.Kol - 1][j] * this.Mat[i][j];
					}
					else if (this.vartype[j] == 2)
					{
						for (int k = (this.Kol - 1); k >= 0; k--)
						{
							this.nilai[k][markj] -= this.nilai[k][j] * this.Mat[i][j];
						}
					}
					else if (this.vartype[j] == 3)
					{
						this.nilai[j][markj] -= this.Mat[i][j];
					}
				}
			}
		}
	}
	
	public void ResulttoString()
	{
		solusi = new String [this.Kol - 1];
		
		// Menuliskan hasil dari pembacaan matriks nilai
		for (int j = 0; j <= (this.Kol - 2); j++)
		{
			if (vartype[j] == 1) // Nilai variabel pasti
			{
				this.solusi[j] = String.format("%.2f", this.nilai[this.Kol - 1][j]);
			}
			else if (vartype[j] == 2) // Nilai variabel terikat
			{
				String s = new String();
				boolean first = true;
				s = "";
				
				for (int i = 0; i <= (this.Kol - 2); i++)
				{
					
					if (!first && this.nilai[i][j] > 0)
					{
						s += "+";
					}
					
					if (this.nilai[i][j] != 0)
					{
						first = false;
						/* Memindahkan nilai indeks jadi alfabet
						 * Contoh : indeks 0 menjadi a*/
						s += String.format("%.2f", this.nilai[i][j]);
						char asconvert = (char)(i + 97);
						s += asconvert;
					}
				}
				if (this.nilai[this.Kol - 1][j] > 0 && !first)
				{
					s += "+";
				}
				s += String.format("%.2f", this.nilai[this.Kol - 1][j]);
				solusi[j] = s;
			}
			else if (vartype[j] == 3) // Nilai variabel bebas
			{
				/* Memindahkan nilai indeks jadi alfabet
				 * Contoh : indeks 0 menjadi a*/
				char asconvert = (char)(j + 97);
				this.solusi[j] = "" + asconvert;
			}
		}
	}
	
	public void PrintConsoleParametrik()
	// Menuliskan pada layar solusi parametrik dari sebuah fungsi
	{
		this.Parametrik();
		this.ResulttoString();
		
		for (int j = 0; j <= (this.Kol - 2); j++)
		{
			System.out.println("x" + j + " = " + this.solusi[j]);
		}
	}
	
	// INTERPOLASI
	
	// Matriks tidak boleh tidak memiliki solusi atau memiliki solusi bebas
	// Jika ya maka terdapat parameter dari variabel dan itu bukan merupakan fungsi
	
	boolean IsSolusiAda ()
	// Mengecek apakah matriks interpolasi memiliki solusi yang pasti
	// Syarat : tidak ada baris 0 semua
	{
		boolean solusiAda = true;
		
		for (int i = 0; i < this.Brs; i++)
		{
			if (IsBaris0 (i))
			{
				solusiAda = false;
				break;
			}
		}
		return (solusiAda);
	}
	
	public void Interpolasi ()
	// PRASYARAT : Terdapat solusi pasti (IsSolusiAda() = true)
	// Menuliskan nilai hasil interpolasi suatu permasalahan
	{
		nilai2 = new double [this.Kol - 1];
		
		for (int i = (this.Brs - 1); i >= 0; i--)
		{
			int markj = this.Marker(i);
			this.nilai2[markj] = this.Mat[i][this.Kol - 1];
			for (int j = (markj + 1); j < (this.Kol - 1); j++)
			{
				this.nilai2[markj] -= this.nilai2[j] * this.Mat[i][j];
			}
		}
		
		solusi = new String [this.Kol - 1];
		
		for (int j = 0; j < (this.Kol - 1); j++)
		{
			String s = new String();
			s = "";
			if (this.nilai2[j] != 0)
			{
				if (this.nilai2[j] > 0 && j < (this.Kol - 2))
				{
					s += "+";
				}
				s += String.format("%.2f", this.nilai2[j]);
				if (j != 0)
				{
					s += "(x^";
					s += Integer.toString(j);
					s += ")";
				}
			}
			solusi[j] = s;
		}
	}
	
	public void PrintConsoleInterpolasi()
	// Menuliskan pada console hasil interpolasi sebuah matriks
	{
		this.Interpolasi();
		System.out.print("y = ");
		for (int i = (this.Kol - 2); i >= 0; i--)
		{
			System.out.print(solusi[i]);
		}
		System.out.println();
	}
	
	public double NilaiFungsi (int N)
	// Mengembalikan sebuah nilai double dari hasil fungsi interpolasi
	{
		double totnilai = this.nilai2[0];
		
		for (int i = 1; i < (this.Kol - 1); i++)
		{
			totnilai += this.nilai2[i] * Math.pow(N,i);
		}
		return (totnilai);
	}
}

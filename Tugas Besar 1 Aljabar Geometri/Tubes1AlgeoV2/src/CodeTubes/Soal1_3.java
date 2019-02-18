package CodeTubes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Soal1_3 {
		
	String[] arraykalimat = new String [10];
	int countbaris;
	int countkolom;
	
	public Soal1_3() 
	{
	    String enter = System.getProperty("line.separator");
	    	
	    String namaFile = "E:\\ContohTeks.txt";
	    String namaFileOut = "E:\\ContohOut.txt";
	       
	    bacaFile(namaFile);
	    banyakBaris(arraykalimat[1], " ", 1);
	    
	    MatrixClass M = new MatrixClass(countbaris,countkolom-1);
	    
	    for (int i=0; i<countbaris; i++) {
	        splitString(arraykalimat[i+1], " ", i, M);
	    }
	    
	    System.out.println("Gauss...Gauss...dan Gauss...");
		M.Gauss();
		System.out.println();
		M.TulisMatriks();
		System.out.println();
		M.VariableType();
		if (M.isSolusi == true)
		{
			M.PrintConsoleParametrik();
		}
		
		// Input output ke file
		
		for (int i=0; i< M.Brs; i++)
        {
        	for (int j=0; j<M.Kol; j++)
        	{
        		tulisFile(M.Mat[i][j]+"		", namaFileOut);
        	}
    		
        	tulisFile(enter, namaFileOut); 
        }	        
	}
		
	// Method tulis file
	void tulisFile(String teks, String namaFile) {
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
	 
	// Method baca file
	void bacaFile(String namaFile) {
        BufferedReader br = null;
        
        try {
            String sCurrentLine;
            int i = 1;
            br = new BufferedReader(new FileReader(namaFile));
            while ((sCurrentLine = br.readLine()) != null) {
                arraykalimat[i] = sCurrentLine;
                i = i+1;
            }
            countbaris = i-1;
        } catch (IOException e) {
            System.out.println("Gagal membaca file " + namaFile);
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
	 }
	    
	void banyakBaris(String str, String delimiter, int i) {
        String[] temp;
        temp = str.split(delimiter);
        countkolom = temp.length;
	}
	
	 void splitString(String str, String delimiter, int i,MatrixClass M) {
        String[] temp;
        temp = str.split(delimiter);
        countkolom = temp.length;
        for (int j = 0; j < temp.length; j++) {
            M.Mat[i][j] = Double.parseDouble(temp[j]);
        }
	        
	}
	    
	    
}

package Koneksi_Database;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Date;

import com.mysql.cj.protocol.Resultset;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class Program {
	//static Scanner scanner;
	static Connection conn;
	
    public static void main(String[] args) {
    	Scanner terimaInput = new Scanner (System.in);
    	String pilihanUser;
    	boolean isLanjutkan = true;
    	
    	String url = "jdbc:mysql://localhost:3306/db_ptabc";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,"root","");
			System.out.println("Class Driver ditemukan");
			Gaji gaji = new Gaji();
			
			while (isLanjutkan) {
				System.out.println("----------------------");
				System.out.println("Database Pegawai PTABC");
				System.out.println("----------------------");
				System.out.println("1. Lihat Data Pegawai");
				System.out.println("2. Tambah Data Pegawai");
				System.out.println("3. Ubah Data Pegawai");
				System.out.println("4. Hapus Data Pegawai");
				System.out.println("5. Cari Data Pegawai");
				
				System.out.print("\nPilihan anda (1/2/3/4/5): ");
				pilihanUser = terimaInput.next();
				
				switch (pilihanUser) {
				case "1":
					gaji.lihatdata();
					break;
				case "2":
					gaji.tambahdata();
					break;
				case "3":
					gaji.ubahdata();
					break;
				case "4":
					gaji.hapusdata();
					break;
				case "5":
					gaji.caridata();
					break;
				default:
					System.err.println("\nInput anda tidak ditemukan\nSilakan pilih [1-5]");
				}
				
				System.out.print("\nApakah Anda ingin melanjutkan [y/n]? ");
				pilihanUser = terimaInput.next();
				isLanjutkan = pilihanUser.equalsIgnoreCase("y");

		        Date date = new Date();
		        String str = String.format("Tanggal/Waktu sekarang: %tc", date);
		        System.out.println(str);
		        System.out.println("");
			}
			System.out.println("Program selesai...");
			
		}
		catch(ClassNotFoundException ex) {
			System.err.println("Driver Error");
			System.exit(0);
		}
		catch(SQLException e){
			System.err.println("Tidak berhasil koneksi");
		}
    }
}

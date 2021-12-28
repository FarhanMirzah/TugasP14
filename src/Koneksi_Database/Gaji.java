package Koneksi_Database;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class Gaji implements PTABC {
	//static Scanner scanner;
	static Connection conn;
	String url = "jdbc:mysql://localhost:3306/db_ptabc";
	
	public int noPegawai;
	public int gajiPokok = 0; 
    public int jumlahHariMasuk = 0;
    public int jumlahHariAbsen = 0;
    public int totalGaji = 0;
    public double potongan = 0;
    public String namaPegawai, jabatan;
    Scanner terimaInput = new Scanner (System.in);

    public void lihatdata() throws SQLException {
		String text1 = "\n===Daftar Seluruh Data Pegawai===";
		System.out.println(text1.toUpperCase());
						
		String sql ="SELECT * FROM data_pegawai";
		conn = DriverManager.getConnection(url,"root","");
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);
		
		while(result.next()){
			System.out.print("\nNomor pegawai\t  : ");
            System.out.print(result.getInt("no_pegawai"));
            System.out.print("\nNama pegawai\t  : ");
            System.out.print(result.getString("nama_pegawai"));
            System.out.print("\nJabatan\t\t  : ");
            System.out.print(result.getString("jabatan"));
            System.out.print("\nGaji pokok\t  : ");
            System.out.print(result.getInt("gaji_pokok"));
            System.out.print("\nJumlah hari masuk : ");
            System.out.print(result.getInt("jumlah_hari_masuk"));
            System.out.print("\nTotal gaji\t  : ");
            System.out.print(result.getInt("total_gaji"));
            System.out.print("\n");
		}
	}
    
    public void tambahdata() throws SQLException {
    	String text2 = "\n===Tambah Data Pegawai===";
		System.out.println(text2.toUpperCase());
		
    	try {
	        // NoPegawai
	    	System.out.print("Masukkan nomor pegawai: ");
	        noPegawai = terimaInput.nextInt();
	        terimaInput.nextLine();
	
	        // NamaPegawai
	        System.out.print("\nMasukkan nama pegawai: ");
	        namaPegawai = terimaInput.nextLine(); 
	
	        System.out.print("\nNama dan nomor pegawai: ");
	        System.out.print(namaPegawai +" "+ noPegawai);
	        
	        // PilihJabatan
	        int pilihJabatan;
	        System.out.println("\n1. Direktur\n2. Manajer\n3. Administrasi\n4. Karyawan");
	        System.out.print("Pilih jabatan (1 - 4): ");
	        pilihJabatan = terimaInput.nextInt();
	        if (pilihJabatan == 1){
	            jabatan = "Direktur";
	        }
	        else if (pilihJabatan == 2){
	            jabatan = "Manajer";
	        }
	        else if (pilihJabatan == 3){
	            jabatan = "Administrasi";
	        }
	        else if (pilihJabatan == 4){
	            jabatan = "Karyawan";
	        }
	        else{
	            System.out.println("Jabatan tidak tersedia");
	        }
	        System.out.println(jabatan.toUpperCase().concat(" / ").concat(jabatan.toLowerCase()));
	
	        // Jabatan
	        if (jabatan == "Direktur"){
	            gajiPokok = 5000000;
	        }
	        else if (jabatan == "Manajer"){
	            gajiPokok = 3500000;
	        }
	        else if (jabatan == "Administrasi"){
	            gajiPokok = 2750000;
	        }
	        else if (jabatan == "Karyawan"){
	            gajiPokok = 2000000;
	        }
	        else{
	            System.out.println("\nGaji pokok tidak tersedia");
	        }
	        System.out.println("\nGaji pokok: Rp" + gajiPokok);
	        
	        // JumlahHariMasuk
	        System.out.print("\nMasukkan jumlah hari absen (0 - 30): ");
	        jumlahHariAbsen = terimaInput.nextInt();
	        jumlahHariMasuk = 30 - jumlahHariAbsen;
	        System.out.println("Jumlah hari masuk: " + jumlahHariMasuk);
	        
	        // Potongan
	        potongan = ((double) jumlahHariAbsen/30) * gajiPokok;
	        System.out.println("\nPotongan: Rp" + (int) potongan);
	        
	        // TotalGaji
	        totalGaji = gajiPokok - (int) potongan;
	        System.out.println("Total gaji: Rp" + totalGaji);
	        System.out.println("");
	        
	        String sql = "INSERT INTO data_pegawai (no_pegawai, nama_pegawai, jabatan, gaji_pokok, jumlah_hari_masuk, total_gaji) VALUES ('"+noPegawai+"','"+namaPegawai+"','"+jabatan+"','"+gajiPokok+"','"+jumlahHariMasuk+"','"+totalGaji+"')";
	        conn = DriverManager.getConnection(url,"root","");
	        Statement statement = conn.createStatement();
	        statement.execute(sql);
	        System.out.println("Berhasil input data");
    	}
    	catch (SQLException e) {
	        System.err.println("Terjadi kesalahan input data");
	    } 
    	catch (InputMismatchException e) {
	    	System.err.println("Inputlah dengan angka saja");
	   	}
	} 
	public void ubahdata() throws SQLException{
		String text3 = "\n===Ubah Data Pegawai===";
		System.out.println(text3.toUpperCase());
		
		try {
            lihatdata();
            System.out.print("\nMasukkan Nomor Pegawai yang akan di ubah atau update : ");
            Integer noPegawai = Integer.parseInt(terimaInput.nextLine());
            
            String sql = "SELECT * FROM data_pegawai WHERE no_pegawai = " +noPegawai;
            conn = DriverManager.getConnection(url,"root","");
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            if(result.next()){
                
                System.out.print("Nama baru ["+result.getString("nama_pegawai")+"]\t: ");
                String namaPegawai = terimaInput.nextLine();
                   
                sql = "UPDATE data_pegawai SET nama_pegawai='"+namaPegawai+"' WHERE no_pegawai='"+noPegawai+"'";
                //System.out.println(sql);

                if(statement.executeUpdate(sql) > 0){
                    System.out.println("Berhasil memperbaharui data pegawai (Nomor "+noPegawai+")");
                }
            }
            statement.close();        
        } 
		catch (SQLException e) {
        	System.err.println("Terjadi kesalahan dalam mengedit data");
            System.err.println(e.getMessage());
        }
	}
	
	public void hapusdata() {
		String text4 = "\n===Hapus Data Pegawai===";
		System.out.println(text4.toUpperCase());
		
		try{
	        lihatdata();
	        System.out.print("\nKetik Nomor Pegawai yang akan Anda Hapus : ");
	        Integer noPegawai= Integer.parseInt(terimaInput.nextLine());
	        
	        String sql = "DELETE FROM data_pegawai WHERE no_pegawai = "+ noPegawai;
	        conn = DriverManager.getConnection(url,"root","");
	        Statement statement = conn.createStatement();
	        //ResultSet result = statement.executeQuery(sql);
	        
	        if(statement.executeUpdate(sql) > 0){
	            System.out.println("Berhasil menghapus data pegawai (Nomor "+noPegawai+")");
	        }
	   }
		catch(SQLException e){
	        System.out.println("Terjadi kesalahan dalam menghapus data");
	    }
	}
	
	public void caridata () throws SQLException {
		String text5 = "\n===Cari Data Pegawai===";
		System.out.println(text5.toUpperCase());
				
		System.out.print("Masukkan Nama Pegawai : ");    
		String keyword = terimaInput.nextLine();
		
		String sql = "SELECT * FROM data_pegawai WHERE nama_pegawai LIKE '%"+keyword+"%'";
		conn = DriverManager.getConnection(url,"root","");
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql); 
                
        while(result.next()){
        	System.out.print("\nNomor pegawai\t  : ");
            System.out.print(result.getInt("no_pegawai"));
            System.out.print("\nNama pegawai\t  : ");
            System.out.print(result.getString("nama_pegawai"));
            System.out.print("\nJabatan\t\t  : ");
            System.out.print(result.getString("jabatan"));
            System.out.print("\nGaji pokok\t  : ");
            System.out.print(result.getInt("gaji_pokok"));
            System.out.print("\nJumlah hari masuk : ");
            System.out.print(result.getInt("jumlah_hari_masuk"));
            System.out.print("\nTotal gaji\t  : ");
            System.out.print(result.getInt("total_gaji"));
            System.out.print("\n");
        }
	}   
}

package tugasakhir;

public class Transaksi {
   
    private String nama;
    private int idProduk;
    private int jumlah;
    private String date;
    
    public Transaksi(String nama, int idProduk, int jumlah, String date) {
        this.nama       = nama;
        this.idProduk  = idProduk;
        this.jumlah     = jumlah;
        this.date       = date;
    }
    
    public String getNama(){ return nama; }
    public int getIdProduk(){ return idProduk; }
    public int getJumlah(){ return jumlah; }
    public String getDate(){ return date; }
    
    public void editNama(String newNama){ this.nama = newNama; }
    public void editIdProduk(int newIdProduk){ this.idProduk = newIdProduk; }
    public void editJumlah(int newJumlah){ this.jumlah = newJumlah; }
    public void editDate(String newDate){ this.date = newDate; }
    
    @Override
    public String toString() {
        return "Transaksi{nama='"+nama+"', idPesanan='"+idProduk+"', jumlah='"+jumlah+"', date='"+date+"'}";
    }
    
}

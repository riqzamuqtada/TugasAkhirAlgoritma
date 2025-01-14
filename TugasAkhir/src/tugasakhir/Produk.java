package tugasakhir;

public class Produk {
    
    private String namaP;
    private int type;
    private int harga;
    private int stok;
    private String keterangan;
    
    public Produk(String namaP, int type,int harga, int stok, String keterangan){
        this.namaP      = namaP;
        this.type       = type;
        this.harga      = harga;
        this.stok       = stok;
        this.keterangan = keterangan;
    }
    
    public String getNama() { return namaP; }
    public int getType() { return type; }
    public int getHarga() { return harga; }
    public int getStok() { return stok; }
    public String getKeterangan() { return keterangan; }
    
    public void editNama(String newNama){ this.namaP = newNama; }
    public void editType(int newType){ this.type = newType; }
    public void editHarga(int newHarga){ this.harga = newHarga; }
    public void editStok(int newStok){ this.stok = newStok; }
    public void editKeterangan(String newKeterangan){ this.keterangan = newKeterangan; }
    
}

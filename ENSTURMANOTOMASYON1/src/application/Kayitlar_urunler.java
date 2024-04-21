package application;

public class Kayitlar_urunler {

	private int id;
	private String turu;
	private String marka;
	private String ozelik;
	private double fiyat;
	private int adet;
	private String kod;
	
	 
	Kayitlar_urunler(int id, String turu, String marka, String ozelik, double fiyat, int adet, String kod){
		this.id=id;
		this.turu=turu;
		this.marka=marka;
		this.ozelik=ozelik;
		this.fiyat=fiyat;
		this.adet=adet;
		this.kod=kod;
	}
	 public String getKod() {
		return kod;
	}
	public void setKod(String kod) {
		this.kod = kod;
	}
	
	public int getId() {
		return id;
	}
      
	public void setId(int id) {
		this.id = id;
	}

	public String getTuru() {
		return turu;
	}

	public void setTuru(String turu) {
		this.turu = turu;
	}

	public String getMarka() {
		return marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public String getOzelik() {
		return ozelik;
	}

	public void setOzelik(String ozelik) {
		this.ozelik = ozelik;
	}

	public double getFiyat() {
		return fiyat;
	}

	public void setFiyat(double fiyat) {
		this.fiyat = fiyat;
	}

	public int getAdet() {
		return adet;
	}

	public void setAdet(int adet) {
		this.adet = adet;
	}
}
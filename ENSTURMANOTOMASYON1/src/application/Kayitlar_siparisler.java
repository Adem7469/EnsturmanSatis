package application;



public class Kayitlar_siparisler {

	private String ad;
	private String soyad;
	private String no;
	private String adres;
	private int adet;
	private double tutar;
	private String tur;
	private String marka;
	private String kod;
	
	Kayitlar_siparisler(String ad, String soyad, String no, String adres,  int adet, double tutar, String tur, String marka,String kod){
		this.ad=ad;
		this.soyad=soyad;
		this.no=no;
		this.adres=adres;
		this.adet=adet;
		this.tutar=tutar;
		this.tur=tur;
		this.marka=marka;
		this.kod=kod;
	}
	
	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getSoyad() {
		return soyad;
	}

	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public int getAdet() {
		return adet;
	}

	public void setAdet(int adet) {
		this.adet = adet;
	}

	public double getTutar() {
		return tutar;
	}

	public void setTutar(double tutar) {
		this.tutar = tutar;
	}

	public String getTur() {
		return tur;
	}

	public void setTur(String tur) {
		this.tur = tur;
	}

	public String getMarka() {
		return marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public String getKod() {
		return kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}

	
}

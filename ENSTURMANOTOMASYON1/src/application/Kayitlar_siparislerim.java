package application;



public class Kayitlar_siparislerim {

	private String ad;
	private String soyad;
	private String no;
	private String adres;
	private int adet;
	private double tutar;
	private String kod;
	
	Kayitlar_siparislerim(String ad, String soyad, String no, String adres, int adet, double tutar, String kod){
		this.ad=ad;
		this.soyad=soyad;
		this.no=no;
		this.adres=adres;
		this.adet=adet;
		this.tutar=tutar;
		this.kod=kod;
	}
	
	public String getKod() {
		return kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
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

	
}

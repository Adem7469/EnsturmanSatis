package application;

import java.sql.Date;

public class Kayitlar_kulanici {
private int id;
private String kulanici;
private String sifre;
private Date tarih;
private int yetki;
Kayitlar_kulanici(int id, String kulanici, String sifre, Date tarih, int yetki){
	this.id=id;
	this.kulanici=kulanici;
	this.sifre=sifre;
	this.tarih=tarih;
	this.yetki=yetki;
}


public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getKulanici() {
	return kulanici;
}

public void setKulanici(String kulanici) {
	this.kulanici = kulanici;
}

public String getSifre() {
	return sifre;
}

public void setSifre(String sifre) {
	this.sifre = sifre;
}

public Date getTarih() {
	return tarih;
}

public void setTarih(Date tarih) {
	this.tarih = tarih;
}

public int getYetki() {
	return yetki;
}

public void setYetki(int yetki) {
	this.yetki = yetki;
}



}

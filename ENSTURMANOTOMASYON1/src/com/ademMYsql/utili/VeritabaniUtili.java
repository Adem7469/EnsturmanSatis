package com.ademMYsql.utili;
import java.sql.*;
public class VeritabaniUtili {
	static Connection coon=null;
	public static Connection Baglanti() {
		try {
		coon=DriverManager.getConnection("jdbc:mysql://localhost/projentp","root","mysql");
		return coon;
		}
		catch(Exception e){
			System.out.println(e.getMessage().toString());
			return null;
		}
	}

}

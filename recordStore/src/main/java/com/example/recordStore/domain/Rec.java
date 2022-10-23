package com.example.recordStore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rec {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String artist;
	private String size;
	private String title;
	private String label;
	private String lev;
	private String kan;
	private double price;
	private String discogs;
	private String genre;

	public Rec() {

	}

	public Rec(String artist, String size, String title, String label, String lev, String kan, double price, String discogs, String genre) {
		this.artist = artist;
		this.size = size;
		this.title = title;
		this.label = label;
		this.lev = lev;
		this.kan = kan;
		this.price = price;
		this.discogs = discogs;
		this.genre= genre;
		}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLev() {
		return lev;
	}

	public void setLev(String lev) {
		this.lev = lev;
	}

	public String getKan() {
		return kan;
	}

	public void setKan(String kan) {
		this.kan = kan;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDiscogs() {
		return discogs;
	}

	public void setDiscogs(String discogs) {
		this.discogs = discogs;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	

}

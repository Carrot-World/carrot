package com.carrot.domain;

public class GoogleRequest {
    private String iss;
    private String azp;
    private String aud;
    private String sub;
    private String email;
    private String email_verified;
    private String at_hash;
    private String name;
    private String picture;
    private String given_name;
    private String family_name;
    private String locale;
    private String iat;
    private String exp;
    private String alg;
    private String kid;
    private String typ;
    
    public GoogleRequest() {
    	
    }
    
	public GoogleRequest(String iss, String azp, String aud, String sub, String email, String email_verified,
			String at_hash, String name, String picture, String given_name, String family_name, String locale,
			String iat, String exp, String alg, String kid, String typ) {
		super();
		this.iss = iss;
		this.azp = azp;
		this.aud = aud;
		this.sub = sub;
		this.email = email;
		this.email_verified = email_verified;
		this.at_hash = at_hash;
		this.name = name;
		this.picture = picture;
		this.given_name = given_name;
		this.family_name = family_name;
		this.locale = locale;
		this.iat = iat;
		this.exp = exp;
		this.alg = alg;
		this.kid = kid;
		this.typ = typ;
	}
	
	public String getIss() {
		return iss;
	}
	public void setIss(String iss) {
		this.iss = iss;
	}
	public String getAzp() {
		return azp;
	}
	public void setAzp(String azp) {
		this.azp = azp;
	}
	public String getAud() {
		return aud;
	}
	public void setAud(String aud) {
		this.aud = aud;
	}
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail_verified() {
		return email_verified;
	}
	public void setEmail_verified(String email_verified) {
		this.email_verified = email_verified;
	}
	public String getAt_hash() {
		return at_hash;
	}
	public void setAt_hash(String at_hash) {
		this.at_hash = at_hash;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getGiven_name() {
		return given_name;
	}
	public void setGiven_name(String given_name) {
		this.given_name = given_name;
	}
	public String getFamily_name() {
		return family_name;
	}
	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getIat() {
		return iat;
	}
	public void setIat(String iat) {
		this.iat = iat;
	}
	public String getExp() {
		return exp;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}
	public String getAlg() {
		return alg;
	}
	public void setAlg(String alg) {
		this.alg = alg;
	}
	public String getKid() {
		return kid;
	}
	public void setKid(String kid) {
		this.kid = kid;
	}
	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}

	@Override
	public String toString() {
		return "GoogleRequest [iss=" + iss + ", azp=" + azp + ", aud=" + aud + ", sub=" + sub + ", email=" + email
				+ ", email_verified=" + email_verified + ", at_hash=" + at_hash + ", name=" + name + ", picture="
				+ picture + ", given_name=" + given_name + ", family_name=" + family_name + ", locale=" + locale
				+ ", iat=" + iat + ", exp=" + exp + ", alg=" + alg + ", kid=" + kid + ", typ=" + typ + "]";
	}
    
}



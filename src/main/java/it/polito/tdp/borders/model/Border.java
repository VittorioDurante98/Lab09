package it.polito.tdp.borders.model;

public class Border {
	
	private Country statoA;
	private Country statoB;
	private int year;
	
	public Border(Country statoA, Country statoB, int year) {
		this.statoA = statoA;
		this.statoB = statoB;
		this.year = year;
	}
	
	public Country getStatoA() {
		return statoA;
	}
	public void setStatoA(Country statoA) {
		this.statoA = statoA;
	}
	public Country getStatoB() {
		return statoB;
	}
	public void setStatoB(Country statoB) {
		this.statoB = statoB;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((statoA == null) ? 0 : statoA.hashCode());
		result = prime * result + ((statoB == null) ? 0 : statoB.hashCode());
		result = prime * result + year;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Border other = (Border) obj;
		if (statoA == null) {
			if (other.statoA != null)
				return false;
		} else if (!statoA.equals(other.statoA))
			return false;
		if (statoB == null) {
			if (other.statoB != null)
				return false;
		} else if (!statoB.equals(other.statoB))
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	
}

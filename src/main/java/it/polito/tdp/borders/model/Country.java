package it.polito.tdp.borders.model;

public class Country {
	
	private String stateA;
	private int cCode;
	private String nome;
	
	public Country(String stateA, int cCode, String nome) {
		super();
		this.stateA = stateA;
		this.cCode = cCode;
		this.nome = nome;
	}

	public String getStateA() {
		return stateA;
	}

	public void setStateA(String stateA) {
		this.stateA = stateA;
	}

	public int getcCode() {
		return cCode;
	}

	public void setcCode(int cCode) {
		this.cCode = cCode;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cCode;
		result = prime * result + ((stateA == null) ? 0 : stateA.hashCode());
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
		Country other = (Country) obj;
		if (cCode != other.cCode)
			return false;
		if (stateA == null) {
			if (other.stateA != null)
				return false;
		} else if (!stateA.equals(other.stateA))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return  nome ;
	}
	
	
}

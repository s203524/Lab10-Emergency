package it.polito.tdp.emergency.simulation;

public class Dottore {
	protected int id;
	protected String nome;
	public enum statoDottore{
		LIBERO, OCCUPATO, PAUSA 
		};
	protected statoDottore stato;

	public Dottore(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public statoDottore getStato() {
		return stato;
	}

	public void setStato(statoDottore stato) {
		this.stato = stato;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dottore other = (Dottore) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Dottore [id=" + id + ", nome=" + nome + "]";
	}	
	
}

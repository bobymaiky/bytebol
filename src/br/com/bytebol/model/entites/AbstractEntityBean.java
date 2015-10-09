package br.com.bytebol.model.entites;

import java.io.Serializable;
import javax.persistence.MappedSuperclass;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class AbstractEntityBean implements Serializable{

	public abstract Integer getId();
	
	public AbstractEntityBean() {
	}

	@Override
	public boolean equals(Object outro) {
		if ((outro == null) || !(outro instanceof AbstractEntityBean)) {
			return false;
		}
		return getId().equals( ((AbstractEntityBean) outro).getId() );
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}
}

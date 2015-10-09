package br.com.bytebol.repository.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.bytebol.model.entites.AbstractEntityBean;

@SuppressWarnings({ "unchecked", "serial" })
public class EntitiesRepository<T extends AbstractEntityBean> implements Serializable {

	public EntityManager entityManager;
	
	private Class<T> classe;
	

	public EntitiesRepository() {
		this.classe = getClassType();
	}

	private Class<T> getClassType() {
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}

	public void salvar(T t) { 
		this.entityManager.persist(t);
	}

	public void alterar(T t) {
		this.entityManager.merge(t);
	}

	public void excluir(T t) {
		this.entityManager.remove(entityManager.merge(t));
	}

	public T consultar(Long id) {
		return (T) entityManager.getReference(classe, id);
	}

	public List<T> listar() {
		return entityManager.createQuery(("From " + classe.getName())).getResultList();
	}
	
	public void excluirTodos(){
		for(T t: listar()){
			excluir(t);
		}
	}	
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
	

}

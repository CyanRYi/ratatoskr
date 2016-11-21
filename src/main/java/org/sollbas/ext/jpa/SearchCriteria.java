package org.sollbas.ext.jpa;

public class SearchCriteria {

	private String key;
	
	private Object value;
	
	private String operation;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	/*private SearchOperation conj;
	
	public SearchOperation getConj() {
		return conj;
	}

	public void setConj(SearchOperation conj) {
		this.conj = conj;
	}*/
}

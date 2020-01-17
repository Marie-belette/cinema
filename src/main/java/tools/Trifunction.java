package tools;

public interface Trifunction<T,U,V,R> {
	R apply(T t,U u,V v);
	
}

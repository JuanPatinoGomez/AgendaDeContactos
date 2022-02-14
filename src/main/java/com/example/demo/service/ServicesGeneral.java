package com.example.demo.service;

import java.util.List;

public interface ServicesGeneral <T> {

	List<T> findAll();
	T findById(int id);
	T save(T t);
	List<T> saveAll(List<T> t);
	void deleteById(int id);
	T update(T t);
}

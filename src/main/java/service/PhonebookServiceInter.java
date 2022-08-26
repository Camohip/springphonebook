package service;

import java.util.List;

import vo.PhonebookVO;

public interface PhonebookServiceInter {

	List<PhonebookVO> getList();

	List<PhonebookVO> find(String search);

	int insert(PhonebookVO pb);

	PhonebookVO findOne(int idx);

	int update(PhonebookVO pb);

	int delete(int idx);


	
}

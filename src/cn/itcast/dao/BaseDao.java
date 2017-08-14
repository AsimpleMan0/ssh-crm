package cn.itcast.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

//ͨ��Dao��ȡ
public interface BaseDao<T> {
	//����ɾ
	void saveOrUpdate(T t);
	//��
	void save(T t);
	//ɾ
	void delete(T t);
	//ɾ 
	void delete(Serializable id);
	//��
	void update(T t);
	//�� ����id��ѯ
	T getById(Serializable id);
	//�� �����������ܼ�¼��
	Integer getTotalCount(DetachedCriteria dc);
	//�� ��ѯ��ҳ�б�����
	List<T> getPageList(DetachedCriteria dc,Integer start,Integer pageSize);
}
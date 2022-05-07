package com.nsec.taskManager.repositories;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.nsec.taskManager.models.RegisteredUser;

public class RegisteredUserRepoImpl {
	@PersistenceContext
	private EntityManager entityManager;
	
	public Optional<RegisteredUser> findByEmailId(String emailId) {
		String hql = "from RegisteredUser u where u.emailId = :email";
		Query q = entityManager.createQuery(hql , RegisteredUser.class);
		q.setParameter("email", emailId);
		
		@SuppressWarnings("unchecked")
		List<RegisteredUser> li = q.getResultList();
		System.out.println(li.size());
		return li.isEmpty() == true ? Optional.ofNullable(null) : Optional.of(li.get(0)); 
	}
}

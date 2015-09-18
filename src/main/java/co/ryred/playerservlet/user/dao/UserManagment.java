package co.ryred.playerservlet.user.dao;

import co.ryred.playerservlet.user.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author Cory Redmond
 *         Created by acech_000 on 18/09/2015.
 */
@Service
public class UserManagment
{

	@Autowired
	private SessionFactory sessionFactory;

	public void insertUser( User user )
	{
		sessionFactory.getCurrentSession().save( user );
	}

	public User getUser( UUID uuid )
	{
		return (User) sessionFactory.getCurrentSession().get( User.class, uuid.toString() );
	}

	public User getUser( String username )
	{
		Query query = sessionFactory.getCurrentSession().createQuery( "from players where name = :username" );
		query.setParameter( "username", username );
		return (User) query.list().get( 0 );
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsers()
	{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria( User.class );
		return criteria.list();
	}

}
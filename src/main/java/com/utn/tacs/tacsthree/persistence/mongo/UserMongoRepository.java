package com.utn.tacs.tacsthree.persistence.mongo;

import java.util.List;

import org.mongodb.morphia.Datastore;

import com.utn.tacs.tacsthree.exceptions.InexistentTacsModelException;
import com.utn.tacs.tacsthree.models.TacsModel;
import com.utn.tacs.tacsthree.models.User;
import com.utn.tacs.tacsthree.persistence.UserDAO;

public class UserMongoRepository implements UserDAO {

	private Datastore datastore;

	public UserMongoRepository(Datastore _datastore) {
		this.datastore = _datastore;
	}

	@Override
	public List<User> get() {
		return datastore.find(User.class).asList();
	}

	@Override
	public void save(User user) {
		datastore.save(user);
	}

	@Override
	public User get(User user) throws InexistentTacsModelException {
		User _user = datastore.get(user);
		if (_user == null)
			throw new InexistentTacsModelException("get failed");
		return _user;
	}
    
	@Override
    public User get(String username) throws InexistentTacsModelException{
        
		try{
        	User user = datastore.find(User.class).filter("username =", username).get();
        	return user;
        } catch (InexistentTacsModelException e) {
        	throw new InexistentTacsModelException("Get user by name failed");
        }
		
    }
        
	@Override
	public void delete(User user) throws InexistentTacsModelException {
		try {
			get(user);
			datastore.delete(user);
		} catch (InexistentTacsModelException e) {
			throw new InexistentTacsModelException("delete failed");
		}
	}
	
	@Override
	public void delete() {
		datastore.getCollection(User.class).drop();
	}

	@Override
	public void tellAboutElimination(List<? extends TacsModel> observees) {
		// TODO Auto-generated method stub
		
	}
}

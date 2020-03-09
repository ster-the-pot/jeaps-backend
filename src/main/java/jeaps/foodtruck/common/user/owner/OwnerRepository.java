package jeaps.foodtruck.common.user.owner;


import org.springframework.data.repository.CrudRepository;

/**
 * Repository of Owner objects in the database
 */
public interface OwnerRepository extends CrudRepository<Owner,Integer> {

};
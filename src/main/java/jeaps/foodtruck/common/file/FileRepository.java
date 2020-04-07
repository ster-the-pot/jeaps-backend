package jeaps.foodtruck.common.file;

import jeaps.foodtruck.common.truck.food.Food;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="files", collectionResourceRel="files")
public interface FileRepository extends CrudRepository<UserFile, Long> {

}

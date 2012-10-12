package pt.up.fe

class Entity(val entity: EntityType,
			 val name: String,
			 var properties: List[Property] = Nil)
{
	entity.entities = this :: entity.entities

}
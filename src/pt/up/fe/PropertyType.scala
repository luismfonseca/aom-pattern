package pt.up.fe

class PropertyType(val entityType: EntityType,
				   val name: String,
				   var properties: List[Property] = Nil)
{
	entityType.properties = this :: entityType.properties

}
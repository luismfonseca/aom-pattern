package pt.up.fe

class Property(val entity: Entity,
			   val propertyType: PropertyType,
			   val value: Any)
{
	entity.properties = this :: entity.properties
	propertyType.properties = this :: propertyType.properties
}
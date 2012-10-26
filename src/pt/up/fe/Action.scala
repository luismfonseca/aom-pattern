package pt.up.fe

trait Action {
  def apply(that: Universe): Universe
  def revertFrom(that: Universe): Universe
}

case class AddEntity(val entity: Entity) extends Action
{
	def apply(that: Universe) =
	{
	    val et = entity.entityType
	    val newET = new EntityType(et.name, et.properties, entity :: et.entities)
	    val newSetET = that.entityTypes - et + newET
	    new Universe(newSetET, that.entities + entity, that.propertyTypes, that.property, this :: that.transformAction)
	}
	
	def revertFrom(that: Universe) = // don't call this yet
	{
	  // Revert action
	  val et = entity.entityType
	  val prevET = new EntityType(et.name, et.properties, et.entities.tail)
	  val prevSetET = that.entityTypes - et + prevET
	  new Universe(prevSetET, that.entities + entity, that.propertyTypes, that.property, that.transformAction.tail)
	}
}

case class AddEntityType(val entityType: EntityType) extends Action
{
	def apply(that: Universe) =
	{
	  new Universe(that.entityTypes + entityType, that.entities, that.propertyTypes, that.property, this :: that.transformAction)
	}
	
	def revertFrom(that: Universe) =
	{
	  new Universe(that.entityTypes - entityType, that.entities, that.propertyTypes, that.property, that.transformAction.tail)
	}
}

case class AddProperty(val property: Property) extends Action
{
	def apply(that: Universe) = BigBang
	def revertFrom(that: Universe) = BigBang
}


case class AddPropertyType(val entity: PropertyType) extends Action
{
	def apply(that: Universe) = BigBang
	def revertFrom(that: Universe) = BigBang
}

case class Merge(val universe : Universe) extends Action
{
	def apply(other: Universe) = {
	  // ignoring conflits
	  universe.transformAction.foldLeft(other)((acc, cur) => cur.apply(acc))
	}
	
	def revertFrom(other: Universe) = {
	  other
	  //universe
	}
}






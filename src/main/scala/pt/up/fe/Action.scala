package pt.up.fe

trait Action {
	def apply(that: Universe): Universe
	def revertFrom(that: Universe): Either[Universe, (Universe, Universe)]
}

case class AddEntity(val entity: Entity) extends Action
{
	def apply(that: Universe) =
	{
	    val et = entity.entityType
	    val newET = new EntityType(et.name, et.properties, entity :: et.entities)
	    val newSetET = that.entityTypes - et + newET
	    new Universe(newSetET, that.entities + entity, that.propertyTypes, that.properties, this :: that.transformAction)
	}
	
	def revertFrom(that: Universe) =
	{
		val et = entity.entityType
		val oldET = that.entityTypes.find(_.entities == entity :: et.entities).get
		Left(new Universe(that.entityTypes + et - oldET, that.entities.tail, that.propertyTypes, that.properties, that.transformAction.tail))
	}
}

case class AddEntityType(val entityType: EntityType) extends Action
{
	def apply(that: Universe) =
	{
	  new Universe(that.entityTypes + entityType, that.entities, that.propertyTypes, that.properties, this :: that.transformAction)
	}
	
	def revertFrom(that: Universe) =
	{
	  Left(new Universe(that.entityTypes - entityType, that.entities, that.propertyTypes, that.properties, that.transformAction.tail))
	}
}

case class AddProperty(val property: Property) extends Action
{
	def apply(that: Universe) = 
	{
	    val pe = property.entity
	    val ppt = property.propertyType
	    
	    val newE   = new Entity(pe.entityType, pe.name, property :: pe.properties)
	    val newSetE = that.entities - pe + newE;
	    
	    val newPT	= new PropertyType(ppt.entityType, ppt.name, property :: ppt.properties)
	    val newSetPT = that.propertyTypes - ppt + newPT
	    
	    new Universe(that.entityTypes, newSetE, newSetPT, that.properties + property, this :: that.transformAction)
	}
	
	def revertFrom(that: Universe) =
	{
	    val pe = property.entity
	    val ppt = property.propertyType
	    val oldE = that.entities.find(_.properties == property :: pe.properties).get
		
	    val oldPT = that.propertyTypes.find(_.properties == property :: ppt.properties).get
		
		Left(new Universe(that.entityTypes, that.entities + pe - oldE, that.propertyTypes + ppt - oldPT, that.properties.tail, that.transformAction.tail))
	}
}


case class AddPropertyType(val property: PropertyType) extends Action
{
	def apply(that: Universe) = 
	{
	    val ptet = property.entityType;
	    val newET = new EntityType(ptet.name, property :: ptet.properties, ptet.entities)
	    val netSetET = that.entityTypes - ptet + newET
	    
	    new Universe(netSetET, that.entities, that.propertyTypes + property, that.properties, this :: that.transformAction)
	}
	
	def revertFrom(that: Universe) =
	{
	    val ptet = property.entityType;
	    val oldET = that.entityTypes.find(_.properties == property :: ptet.properties).get
	  
	    Left(new Universe(that.entityTypes + ptet - oldET, that.entities, that.propertyTypes.tail, that.properties, that.transformAction.tail))
	}
}

case class Merge(val universe : Universe) extends Action
{
	def apply(other: Universe) = {
	  // ignoring conflits
	  universe.transformAction.foldLeft(other)((currentUni, action) => action.apply(currentUni))
	}
	
	def revertFrom(other: Universe) = {
	  Right(universe, other)
	}
}






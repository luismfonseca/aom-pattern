package pt.up.fe

object BigBang extends Universe(Set.empty, Set.empty, Set.empty, Set.empty)
{
  override def wasMerge = false
}

class Universe(
  val entityTypes	: Set[EntityType],
  val entities		: Set[Entity],
  val propertyTypes	: Set[PropertyType],
  val properties	: Set[Property],
  val transformAction: List[Action]		// Actions that transformed the previous universe into this one
)
{
  def this(entityTypes	: Set[EntityType],
		   entities		: Set[Entity],
		   propertyTypes: Set[PropertyType],
		   properties	: Set[Property])
  {
	  this(entityTypes, entities, propertyTypes, properties, List[Action]())
  }
  
  // TODO: fun
  def merge(that : Universe): Universe = Merge(that)(this)
  
  def wasMerge = transformAction.head.isInstanceOf[Merge]
  
  def Add(entity: Entity) = AddEntity(entity)(this)
  def Add(entityType: EntityType) = AddEntityType(entityType)(this)
  def Add(propertyType: PropertyType) = AddPropertyType(propertyType)(this)
  def Add(property: Property) = AddProperty(property)(this)
  
  // TODO: Add removes
  //def Remove(entity: Entity) = RemoveEntity(entity)(this)
  
  def revert : Universe = transformAction.head.revertFrom(this)
  
  def findEntityType(name: String) = entityTypes.find(_.name == name).get
  def findEntity(name: String) = entities.find(_.name == name).get
  def findPropertyTypes(name: String) = propertyTypes.find(_.name == name).get
  
  
}
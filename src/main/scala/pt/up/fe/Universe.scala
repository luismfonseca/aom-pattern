package pt.up.fe

object BigBang extends Universe(Set.empty, Set.empty, Set.empty, Set.empty, List[Action]())

class Universe(
  val entityTypes	: Set[EntityType],
  val entities		: Set[Entity],
  val propertyTypes	: Set[PropertyType],
  val properties	: Set[Property],
  val transformAction: List[Action]		// Actions that transformed the previous universes into this one
)
{
  def merge(that : Universe): Universe = Merge(that)(this)
  
  def Add(entity: Entity) = AddEntity(entity)(this)
  def Add(entityType: EntityType) = AddEntityType(entityType)(this)
  def Add(propertyType: PropertyType) = AddPropertyType(propertyType)(this)
  def Add(property: Property) = AddProperty(property)(this)
  
  // TODO: Add removes
  //def Remove(entity: Entity) = RemoveEntity(entity)(this)
  
  def revert = transformAction.head.revertFrom(this)
  
  def findEntityType(name: String) = entityTypes.find(_.name == name).get
  def findEntity(name: String) = entities.find(_.name == name).get
  def findPropertyTypes(name: String) = propertyTypes.find(_.name == name).get
}
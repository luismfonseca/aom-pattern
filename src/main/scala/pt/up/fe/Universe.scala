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
  
  def Add(thing: Thing) = thing match {
    case e : Entity => AddEntity(e)(this)
    case et: EntityType => AddEntityType(et)(this)
    case pt: PropertyType => AddPropertyType(pt)(this)
    case p : Property => AddProperty(p)(this) 
  }
  
  // TODO: Add removes
  //def Remove(entity: Entity) = RemoveEntity(entity)(this)
  
  def revert = transformAction.head.revertFrom(this)
  
  def findEntityType(name: String) = entityTypes.find(_.name == name)
  def findEntity(name: String) = entities.find(_.name == name)
  def findPropertyTypes(name: String) = propertyTypes.find(_.name == name)
}
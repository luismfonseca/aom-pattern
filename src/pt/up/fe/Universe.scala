package pt.up.fe

object BigBang extends Universe(Set.empty, Set.empty, Set.empty, Set.empty)
{
  override def wasMerge = false
}

class Universe(
  val entityTypes	: Set[EntityType],
  val entities		: Set[Entity],
  val propertyTypes	: Set[PropertyType],
  val property		: Set[Property],
  val transformAction: List[Action]		// Actions that transformed the previous universe into this one
)
{
  def this(entityTypes	: Set[EntityType],
		   entities		: Set[Entity],
		   propertyTypes: Set[PropertyType],
		   property		: Set[Property])
  {
    this(entityTypes, entities, propertyTypes, property, List[Action]())
  }
  
  // TODO: fun
  def merge(that : Universe) = new Universe(entityTypes, entities, propertyTypes, property, Merge(this, that) :: transformAction)
  
  def wasMerge = transformAction.isInstanceOf[Merge]
  
  def newEntityType(et: EntityType) =
    new Universe(entityTypes + et, entities, propertyTypes, property, AddEntityType(et) :: transformAction)
  
  
  def Add(entity: Entity) = AddEntity(entity)(this)
  //def Remove(entity: Entity) = RemoveEntity(entity)(this)
  def Add(entityType: EntityType) = AddEntityType(entityType)(this)
  
  
  def revert : Universe = transformAction.head.revertFrom(this)
  
  
  def newEntity(e: Entity) = {
    val et = e.entityType
    val newET = new EntityType(et.name, et.properties, e :: et.entities)
    val newSetET = entityTypes - et + newET
    new Universe(newSetET, entities + e, propertyTypes, property, AddEntity(e) :: transformAction)
  }
  
  def newPropertyType(pt: PropertyType) = {
    val ptet = pt.entityType;
    val newET = new EntityType(ptet.name, pt :: ptet.properties, ptet.entities)
    val netSetET = entityTypes - ptet + newET
    
    new Universe(netSetET, entities, propertyTypes + pt, property, AddPropertyType(pt) :: transformAction)
  }
  
  def newProperty(p: Property) = {
    val pe = p.entity
    val ppt = p.propertyType
    
    val newE   = new Entity(pe.entityType, pe.name, p :: pe.properties)
    val newSetE = entities - pe + newE;
    
    val newPT	= new PropertyType(ppt.entityType, ppt.name, p :: ppt.properties)
    val newSetPT = propertyTypes - ppt + newPT
    
    new Universe(entityTypes, newSetE, newSetPT, property + p, AddProperty(p) :: transformAction)
  }
  
}
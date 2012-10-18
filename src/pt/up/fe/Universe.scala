package pt.up.fe

object BigBang extends Universe(null, Set.empty, Set.empty, Set.empty, Set.empty)
{
  override def wasMerge = false
  override def previous = throw new Error("Universe.previous doesn't exist before Big Bang.")
}

class Universe(
  previousUni		: Universe,
  val previousOther : Universe,			// If not null, represents a merge
  val entityTypes	: Set[EntityType],
  val entities		: Set[Entity],
  val propertyTypes	: Set[PropertyType],
  val property		: Set[Property],
  val transformAction: Action			// Action that transformed the previous universe into this one
)
{
  def this(previous		: Universe,
		   entityTypes	: Set[EntityType],
		   entities		: Set[Entity],
		   propertyTypes: Set[PropertyType],
		   property		: Set[Property])
  {
    this(previous, null, entityTypes, entities, propertyTypes, property, null)
  }
  
  def this(previous: Universe, previousOther: Universe)
  {
    // TODO: fun
    // Find a universe where both diverged from & apply all actions since
    this(previous, previousOther, null, null, null, null, null);
  }
  
  def merge(that : Universe) = new Universe(this, that)
  
  def previous = previousUni
  def wasMerge = previousOther != null
  
  def newEntityType(et: EntityType) =
    new Universe(this, null, entityTypes + et, entities, propertyTypes, property, AddEntityType(et))
  
  def newEntity(e: Entity) = {
    val et = e.entityType
    val newET = new EntityType(et.name, et.properties, e :: et.entities)
    val newSetET = entityTypes - et + newET
    
    new Universe(this, null, newSetET, entities + e, propertyTypes, property, AddEntity(e))
  }
  
  def newPropertyType(pt: PropertyType) = {
    val ptet = pt.entityType;
    val newET = new EntityType(ptet.name, pt :: ptet.properties, ptet.entities)
    val netSetET = entityTypes - ptet + newET
    
    new Universe(this, null, netSetET, entities, propertyTypes + pt, property, AddPropertyType(pt))
  }
  
  def newProperty(p: Property) = {
    val pe = p.entity
    val ppt = p.propertyType
    
    val newE   = new Entity(pe.entityType, pe.name, p :: pe.properties)
    val newSetE = entities - pe + newE;
    
    val newPT	= new PropertyType(ppt.entityType, ppt.name, p :: ppt.properties)
    val newSetPT = propertyTypes - ppt + newPT
    
    new Universe(this, null, entityTypes, newSetE, newSetPT, property + p, AddProperty(p))
  }
  
}
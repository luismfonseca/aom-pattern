package pt.up.fe

object Universe {
  def empty = new Universe(Set.empty, Set.empty, Set.empty, Set.empty)
}

class Universe(  
  val entityTypes	: Set[EntityType],
  val entities		: Set[Entity],
  val propertyTypes	: Set[PropertyType],
  val property		: Set[Property]
)
{
  def newEntityType(et: EntityType) = new Universe(entityTypes + et, entities, propertyTypes, property)
  
  def newEntity(e: Entity) = {
    val et = e.entityType
    val newET = new EntityType(et.name, et.properties, e :: et.entities)
    val newSetET = entityTypes - et + newET
    
    new Universe(newSetET, entities + e, propertyTypes, property)
  }
  
  def newPropertyType(pt: PropertyType) = {
    val ptet = pt.entityType;
    val newET = new EntityType(ptet.name, pt :: ptet.properties, ptet.entities)
    val netSetET = entityTypes - ptet + newET
    
    new Universe(netSetET, entities, propertyTypes + pt, property)
  }
  
  def newProperty(p: Property) = {
    val pe = p.entity
    val ppt = p.propertyType
    
    val newE   = new Entity(pe.entityType, pe.name, p :: pe.properties)
    val newSetE = entities - pe + newE;
    
    val newPT	= new PropertyType(ppt.entityType, ppt.name, p :: ppt.properties)
    val newSetPT = propertyTypes - ppt + newPT
    
    new Universe(entityTypes, newSetE, newSetPT, property + p)
  }
  
}
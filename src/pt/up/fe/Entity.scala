package pt.up.fe

protected class Entity(val entityType: EntityType,
			 val name: String,
			 val properties: List[Property] = Nil)
{
  
  override def toString = "Entity[name = " + name + "]"
}
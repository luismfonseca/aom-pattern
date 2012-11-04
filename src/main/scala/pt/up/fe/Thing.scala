package pt.up.fe

sealed abstract class Thing {
  
  
  def map[T <: Thing](f: T => Array[Set[Thing]]): T => Universe =
    (thing: T) => new Universe(f(thing))
  
  
  def AddSomething[S <: Thing](something: S => Array[Set[Thing]]) = map(something)
  
}

protected case class Entity(
	val entityType: EntityType,
	val name: String,
	val properties: List[Property] = Nil)
	extends Thing
{
  // Class Body intentionally left mostly blank.
}

protected case class EntityType(
    val name: String,
	val properties: List[PropertyType] = Nil,
	val entities : List[Entity] = Nil)
	extends Thing
{
  // Class Body intentionally left mostly blank.
}

protected case class Property(
    val entity: Entity,
    val propertyType: PropertyType,
    val value: Any)
	extends Thing
{
  // Class Body intentionally left mostly blank.
}

protected case class PropertyType(
	val entityType: EntityType,
	val name: String,
	val properties: List[Property] = Nil)
	extends Thing
{
  // Class Body intentionally left mostly blank.
}
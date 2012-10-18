package pt.up.fe

sealed abstract class TypeSquare
protected case class Entity(
	val entityType: EntityType,
	val name: String,
	val properties: List[Property] = Nil)
	extends TypeSquare
{
  // Class Body intentionally left mostly blank.
}

protected case class EntityType(
    val name: String,
	val properties: List[PropertyType] = Nil,
	val entities : List[Entity] = Nil)
	extends TypeSquare
{
  // Class Body intentionally left mostly blank.
}

protected case class Property(
    val entity: Entity,
    val propertyType: PropertyType,
    val value: Any)
	extends TypeSquare
{
  // Class Body intentionally left mostly blank.
}

protected case class PropertyType(
	val entityType: EntityType,
	val name: String,
	val properties: List[Property] = Nil)
	extends TypeSquare
{
  // Class Body intentionally left mostly blank.
}
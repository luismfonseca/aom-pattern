package pt.up.fe

abstract class Action

case class AddEntity(val entity: Entity) extends Action
case class AddEntityType(val entityType: EntityType) extends Action
case class AddProperty(val property: Property) extends Action
case class AddPropertyType(val entity: PropertyType) extends Action
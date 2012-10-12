package pt.up.fe

object test {
/* Model */
var procedure = new EntityType("Procedure");
var duration = new PropertyType(procedure, "Duration");
/* Data */
var p1 = new Entity(procedure, "Surgery");
var p1Duration = new Property(p1, duration, "00:35");
/* Modifying Model */
var description = new PropertyType(procedure, "Description");
/* Updating Data */
var p1Description = new Property(p1, description, "Tooth Removal");

}